package antiqueatlasautomarker.compat;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.ConfigHandler;
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
        if (!ConfigHandler.waystones.autoUpdateWaystones) return;
        if(!ConfigHandler.waystones.enabled) return;

        for (int atlasID : AtlasAPI.getPlayerAtlases(player)) {
            //Get all markers at waystone position in current atlas with correct marker type
            List<Marker> markersAtPosition = AntiqueAtlasMod.markersData
                    .getMarkersData(atlasID, world)
                    .getMarkersDataInDimension(world.provider.getDimension())
                    .getAllMarkers()
                    .stream()
                    .filter(marker -> Math.abs(marker.getX() - waystonePos.getX()) < 2 && Math.abs(marker.getZ() - waystonePos.getZ()) < 2)
                    .filter(marker -> marker.getType().equals(ConfigHandler.waystones.marker))
                    .collect(Collectors.toList());

            //Remove old waystone markers
            int counterMarkersRemoved = 0;
            for (Marker marker : markersAtPosition) {
                //Not renamed: don't delete, don't add
                if (marker.getLabel().equals(newName) && !ConfigHandler.waystones.alwaysMarkWaystones) continue;
                //Remove
                AtlasAPI.getMarkerAPI().deleteMarker(world, atlasID, marker.getId());
                counterMarkersRemoved++;
            }
            //Put new waystone marker, but not if player doesn't want a marker there
            if (counterMarkersRemoved > 0 || ConfigHandler.waystones.alwaysMarkWaystones)
                AtlasAPI.getMarkerAPI().putMarker(world, false, atlasID, ConfigHandler.waystones.marker, newName, waystonePos.getX(), waystonePos.getZ());
        }
    }
}
