package antiqueatlasautomarker.mixin.antiqueatlas.overhaul;

import hunternif.mc.atlas.marker.DimensionMarkersData;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import hunternif.mc.atlas.network.PacketDispatcher;
import hunternif.mc.atlas.network.client.MarkersPacket;
import hunternif.mc.atlas.util.Log;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.*;

import java.util.*;

@Mixin(MarkersData.class)
public abstract class MarkersDataSyncInChunksMixin {
    @Shadow(remap = false) @Final private Map<Integer, DimensionMarkersData> dimensionMap;
    @Shadow(remap = false) @Final private Set<EntityPlayer> playersSentTo;
    @Shadow(remap = false) public abstract DimensionMarkersData getMarkersDataInDimension(int dimension);
    //Using this over just using a constructor to account for global markers (that should be disabled anyway)
    @Shadow(remap = false) abstract MarkersPacket newMarkersPacket(int atlasID, int dimension);

    @Unique private static final int MARKERS_PER_PACKET = 100;

    /**
     * @author Nischhelm
     * @reason don't send so many markers in one packet to reduce lag spikes
     */
    @Overwrite(remap = false)
    public void syncOnPlayer(int atlasID, EntityPlayer player) {
        int totalMarkersSent = 0;
        for (Integer dimension : dimensionMap.keySet()) {
            //At least one packet per dimension
            Collection<Marker> allMarkersInDimension = getMarkersDataInDimension(dimension).getAllMarkers();
            totalMarkersSent += allMarkersInDimension.size();

            List<Marker> markersChunk = new ArrayList<>(MARKERS_PER_PACKET);
            for (Marker marker : allMarkersInDimension) {
                markersChunk.add(marker);

                //Multiple packets if there's more than x markers in that dimension
                if (markersChunk.size() >= MARKERS_PER_PACKET) {
                    //Make a new packet once we gathered 100 markers in the markersChunk list
                    MarkersPacket p = this.newMarkersPacket(atlasID, dimension);
                    for(Marker markerInChunk : markersChunk)
                        p.putMarker(markerInChunk);
                    PacketDispatcher.sendTo(p, (EntityPlayerMP) player);
                    markersChunk.clear();
                }
            }

            //Remaining markers, last packet
            if (!markersChunk.isEmpty()) {
                MarkersPacket p = this.newMarkersPacket(atlasID, dimension);
                for(Marker markerInChunk : markersChunk)
                    p.putMarker(markerInChunk);
                PacketDispatcher.sendTo(p, (EntityPlayerMP) player);
                //TODO: would be cool to know how much data that actually is and compare it to the DimensionData.syncOnPlayer packet
            }
        }

        Log.info("Sent markers data #%d to player %s (%d markers)", atlasID, player.getName(), totalMarkersSent);
        playersSentTo.add(player);
    }
}
