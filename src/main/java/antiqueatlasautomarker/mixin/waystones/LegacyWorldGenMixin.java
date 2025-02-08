package antiqueatlasautomarker.mixin.waystones;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.util.StructureGenerationUtil;
import com.llamalad7.mixinextras.sugar.Local;
import net.blay09.mods.waystones.worldgen.LegacyWorldGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LegacyWorldGen.class)
public class LegacyWorldGenMixin {
    @ModifyArg(
            method = "generate",
            at = @At(value = "INVOKE", target = "Lnet/blay09/mods/waystones/block/TileWaystone;setWaystoneName(Ljava/lang/String;)V"),
            remap = false
    )
    private String aaam_markGeneratedWildWaystone(String waystoneName, @Local(argsOnly = true) World world, @Local(ordinal = 0) BlockPos pos) {
        //Search for atlasses in all nearby players inventory and mark generated waystone
        AutoMarkSetting settings = AutoMarkSetting.autoMarkSettings.get("wildWaystone");
        if(settings != null && settings.enabled) {
            String name = settings.label;
            if(name.equals("DEFAULT")) name = waystoneName;
            StructureGenerationUtil.markStructure(
                    world,
                    pos,
                    settings.dist,
                    settings.type,
                    name
            );
        }

        //Default behavior
        return waystoneName;
    }
}
