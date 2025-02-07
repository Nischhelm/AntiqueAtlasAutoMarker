package antiqueatlasautomarker.mixin.waystones;

import antiqueatlasautomarker.handlers.ForgeConfigHandler;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.api.AtlasAPI;
import net.blay09.mods.waystones.worldgen.LegacyWorldGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Objects;

@Mixin(LegacyWorldGen.class)
public class LegacyWorldGenMixin {
    @ModifyArg(
            method = "generate",
            at = @At(value = "INVOKE", target = "Lnet/blay09/mods/waystones/block/TileWaystone;setWaystoneName(Ljava/lang/String;)V"),
            remap = false
    )
    private String aaam_markGeneratedWildWaystone(String waystoneName, @Local(argsOnly = true) World world, @Local(ordinal = 0) BlockPos pos) {
        //Search for atlasses in all nearby players inventory and mark generated waystone
        if(ForgeConfigHandler.server.autoMarkWildWaystones) {
            for (EntityPlayer player : world.getPlayers(EntityPlayer.class, v -> true)) {
                BlockPos dist = player.getPosition().subtract(pos);
                if (Math.abs(dist.getX()) < ForgeConfigHandler.server.wildWaystoneDistance && Math.abs(dist.getZ()) < ForgeConfigHandler.server.wildWaystoneDistance) { //20 chunks render distance max
                    for (int atlasID : AtlasAPI.getPlayerAtlases(player)) {
                        String name = ForgeConfigHandler.server.wildWayStoneName;
                        if(Objects.equals(name, "DEFAULT"))
                            name = waystoneName;
                        AtlasAPI.getMarkerAPI().putMarker(world, true, atlasID, ForgeConfigHandler.server.wildWayStoneMarkerType, name, pos.getX(), pos.getZ());
                    }
                }
            }
        }

        //Default behavior
        return waystoneName;
    }
}
