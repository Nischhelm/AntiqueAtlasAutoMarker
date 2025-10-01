package antiqueatlasautomarker.structuremarkers.network;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.ConfigHandler;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import hunternif.mc.atlas.network.AbstractMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class AddedStructureMarkersPacket extends AbstractMessage.AbstractServerMessage<AddedStructureMarkersPacket> {
    private int atlasID;
    private int dimension;
    private final ListMultimap<String, Marker> markersByType = ArrayListMultimap.create();

    public AddedStructureMarkersPacket() {}

    public AddedStructureMarkersPacket(int atlasID, int dimension, Marker... markers) {
        this.atlasID = atlasID;
        this.dimension = dimension;
        for (Marker marker : markers)
            markersByType.put(marker.getType(), marker);
    }

    public AddedStructureMarkersPacket putMarker(Marker marker) {
        markersByType.put(marker.getType(), marker);
        return this;
    }

    @Override
    public void read(PacketBuffer buffer) throws IOException {
        atlasID = buffer.readVarInt();
        dimension = buffer.readVarInt();
        int typesLength = buffer.readVarInt();
        for (int i = 0; i < typesLength; i++) {
            String type = ByteBufUtils.readUTF8String(buffer);
            int markersLength = buffer.readVarInt();
            for (int j = 0; j < markersLength; j++) {
                Marker marker = new Marker(buffer.readVarInt(), type, ByteBufUtils.readUTF8String(buffer), dimension, buffer.readInt(), buffer.readInt(), buffer.readBoolean());
                markersByType.put(type, marker);
            }
        }
    }

    @Override
    public void write(PacketBuffer buffer) throws IOException {
        buffer.writeVarInt(atlasID);
        buffer.writeVarInt(dimension);
        Set<String> types = markersByType.keySet();
        buffer.writeVarInt(types.size());
        for (String type : types) {
            ByteBufUtils.writeUTF8String(buffer, type);
            List<Marker> markers = markersByType.get(type);
            buffer.writeVarInt(markers.size());
            for (Marker marker : markers) {
                buffer.writeVarInt(marker.getId());
                ByteBufUtils.writeUTF8String(buffer, marker.getLabel());
                buffer.writeInt(marker.getX());
                buffer.writeInt(marker.getZ());
                buffer.writeBoolean(marker.isVisibleAhead());
            }
        }
    }

    /**
     * Sends new structure markers on players atlas back to the server for sync
     */
    @Override
    protected void process(EntityPlayer player, Side side) {
        if(ConfigHandler.internal.doDebugLogs) AntiqueAtlasAutoMarker.LOGGER.info("Server received {} new structure markers for atlas #{}", markersByType.size(), atlasID);
        MarkersData markersData = AntiqueAtlasMod.markersData.getMarkersData(atlasID, player.getEntityWorld());
        for (Marker marker : markersByType.values()) {
            markersData.loadMarker(marker);
        }
    }
}
