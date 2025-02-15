package antiqueatlasautomarker.structuremarkers;

import antiqueatlasautomarker.compat.AARCCompat;
import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.util.IMarkerConstructor;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.SettingsConfig;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import hunternif.mc.atlas.network.AbstractMessage;
import hunternif.mc.atlas.network.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class OptionalStructureMarkerPacket extends AbstractMessage.AbstractClientMessage<OptionalStructureMarkerPacket> {
    private int atlasID;
    private int dimension;
    private final ListMultimap<String, Marker> markersByType = ArrayListMultimap.create();

    public OptionalStructureMarkerPacket() {}

    public OptionalStructureMarkerPacket(int atlasID, int dimension, Marker... markers) {
        this.atlasID = atlasID;
        this.dimension = dimension;
        for (Marker marker : markers)
            markersByType.put(marker.getType(), marker);
    }

    public OptionalStructureMarkerPacket putMarker(Marker marker) {
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
                Marker marker = new Marker(buffer.readVarInt(), type, ByteBufUtils.readUTF8String(buffer), dimension, buffer.readInt(), buffer.readInt(), false);
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
            }
        }
    }

    /**
     * Enables clients to reject automatic markings of certain structures
     */
    @Override
    protected void process(EntityPlayer player, Side side) {
        MarkersData markersData = AntiqueAtlasMod.markersData.getMarkersData(atlasID, player.world);

        AddedStructureMarkersPacket updatePacket = new AddedStructureMarkersPacket(atlasID, player.world.provider.getDimension());

        for (Marker marker : markersByType.values()) {

            //Decode received marker from server
            //marker.getType consists of context;serverMarkerType
            //If it's an AARC marker, the context again consists of context$recurrentComplexStructureName
            String[] context_type = IMarkerConstructor.splitContext(marker.getType());
            String context = context_type[0];
            String serverType = context_type[1];
            String serverLabel = marker.getLabel();

            AutoMarkSetting clientSetting = null;

            //AARC compat
            if(ModCompat.isAARCLoaded() && context.startsWith("AARCAddon"))
                clientSetting = AARCCompat.getAARCSetting(context);

            //Overwrite for AA global markers (village+end_city/generic), use serverside label/type but clientside enabled config (true if generic global marker by some random mod idk)
            else if(context.startsWith("aa_")) clientSetting = new AutoMarkSetting(context.equals("aa_global") || SettingsConfig.gameplay.autoVillageMarkers, "DEFAULT", "DEFAULT", context);

            //AAAM base behavior
            else clientSetting = AutoMarkSetting.get(context);

            //Check if client has a config for this and whether its enabled
            if(clientSetting != null && clientSetting.enabled) {
                String clientType = clientSetting.type.equals("DEFAULT") ? serverType : clientSetting.type;
                String clientLabel = clientSetting.label.equals("DEFAULT") ? serverLabel : clientSetting.label;

                //Copy has client side type+label and negative id to not run into conflicts (can conflict with global markers though)
                Marker newMarker = ((IMarkerConstructor) marker).structureMarkerCopy(clientType, clientLabel);
                markersData.loadMarker(newMarker);

                //Collect for sending back to server for sync
                updatePacket.putMarker(newMarker);
            }
        }

        //Send the new Markers back to the server
        PacketDispatcher.sendToServer(updatePacket);
    }
}
