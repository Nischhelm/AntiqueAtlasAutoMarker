package antiqueatlasautomarker.util;

import antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding.MarkersDataAccessor;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.marker.MarkersData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerLogoutHandler {
    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        //Remove logged out player from all markersData playersSentTo lists,
        // so the server doesn't try to send packets to players that don't exist anymore
        if (event.player.world.isRemote) return;
        for (int atlasId : ((ILoadedDataList) AntiqueAtlasMod.markersData).getLoadedIds()) {
            MarkersData markersData = AntiqueAtlasMod.markersData.getMarkersData(atlasId, event.player.world);
            ((MarkersDataAccessor) markersData).getPlayersSentTo().remove(event.player);
        }
    }
}
