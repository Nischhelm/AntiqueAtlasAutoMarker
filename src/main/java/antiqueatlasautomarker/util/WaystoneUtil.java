package antiqueatlasautomarker.util;

import antiqueatlasautomarker.handlers.ForgeConfigHandler;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.marker.Marker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.stream.Collectors;

public class WaystoneUtil {
    @SideOnly(Side.CLIENT)
    public static void updateRenamedWaystoneMarker(EntityPlayer player, World world, BlockPos waystonePos, String newName) {
        if (!ForgeConfigHandler.client.autoUpdateWaystones) return;

        for (int atlasID : AtlasAPI.getPlayerAtlases(player)) {
            //Get all markers at waystone position in current atlas
            List<Marker> markersAtPosition = AntiqueAtlasMod.markersData
                    .getMarkersData(atlasID, world)
                    .getMarkersDataInDimension(world.provider.getDimension())
                    .getAllMarkers()
                    .stream().filter(marker -> Math.abs(marker.getX() - waystonePos.getX()) < 2 && Math.abs(marker.getZ() - waystonePos.getZ()) < 2)
                    .collect(Collectors.toList());

            //Remove old waystone markers
            int counterMarkersRemoved = 0;
            for (Marker marker : markersAtPosition) {
                //Not correct marker
                if (!marker.getType().equals(ForgeConfigHandler.client.activatedWayStoneMarkerType)) continue;
                //Not renamed
                if (marker.getLabel().equals(newName)) continue;
                //Remove
                AtlasAPI.getMarkerAPI().deleteMarker(world, atlasID, marker.getId());
                counterMarkersRemoved++;
            }
            //Put new waystone marker, but not if player doesn't want a marker there
            if (counterMarkersRemoved > 0)
                AtlasAPI.getMarkerAPI().putMarker(world, false, atlasID, ForgeConfigHandler.client.activatedWayStoneMarkerType, newName, waystonePos.getX(), waystonePos.getZ());
        }
    }
}
