package antiqueatlasautomarker.util;

import antiqueatlasautomarker.config.AutoMarkSetting;
import hunternif.mc.atlas.api.AtlasAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StructureGenerationUtil {
    public static void markStructure(World world, BlockPos pos, int maxDist, String markerType, String markerName) {
        for (EntityPlayer player :
                world.getPlayers(EntityPlayer.class, player -> {
                    if (player == null) return false;
                    BlockPos dist = player.getPosition().subtract(pos);
                    return Math.abs(dist.getX()) < maxDist && Math.abs(dist.getZ()) < maxDist;
                })
        )
            for (int atlasID : AtlasAPI.getPlayerAtlases(player))
                AtlasAPI.getMarkerAPI().putMarker(world, true, atlasID, markerType, markerName, pos.getX(), pos.getZ());


    }

    public static void markStructure(World world, BlockPos pos, AutoMarkSetting settings) {
        if(settings != null && settings.enabled)
            markStructure(world, pos, settings.dist, settings.type, settings.label);
    }
}

