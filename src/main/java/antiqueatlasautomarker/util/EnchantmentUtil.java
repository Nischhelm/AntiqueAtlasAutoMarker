package antiqueatlasautomarker.util;

import antiqueatlasautomarker.config.AutoMarkSetting;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.marker.Marker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.stream.Collectors;

public class EnchantmentUtil {
    public static void markLibrarian(EntityPlayer player, BlockPos villagerPos, String newLabel) {
        AutoMarkSetting setting = AutoMarkSetting.get("enchantmentTrade");
        if(setting == null || !setting.enabled) return;

        for (int atlasID : AtlasAPI.getPlayerAtlases(player)) {
            //Get all enchantment markers around librarian (+-64 blocks) in current atlas
            List<Marker> markersAtPosition = AntiqueAtlasMod.markersData
                    .getMarkersData(atlasID, player.world)
                    .getMarkersDataInDimension(player.world.provider.getDimension())
                    .getAllMarkers()
                    .stream()
                    .filter(marker -> Math.abs(marker.getX() - villagerPos.getX()) < 64 && Math.abs(marker.getZ() - villagerPos.getZ()) < 64)
                    .filter(marker -> marker.getType().equals(setting.type))
                    .collect(Collectors.toList());

            //Remove old librarian markers
            for (Marker marker : markersAtPosition) {
                //Remove if new label any of the same enchantment contents as the old label
                if(areAnyEnchantTradesEqual(newLabel, marker))
                    AtlasAPI.getMarkerAPI().deleteMarker(player.world, atlasID, marker.getId());
            }
            //Put new librarian marker
            AtlasAPI.getMarkerAPI().putMarker(player.world, false, atlasID, setting.type, newLabel, villagerPos.getX(), villagerPos.getZ());
        }
    }

    private static boolean areAnyEnchantTradesEqual(String newLabel, Marker marker) {
        String[] split1 = newLabel.split(", ");
        String[] split2 = marker.getLabel().split(", ");
        boolean anyEqual = false;
        for(String s1 : split1){
            for(String s2 : split2){
                if(s1.equals(s2)){
                    anyEqual = true;
                    break;
                }
            }
        }
        return anyEqual;
    }
}
