package antiqueatlasautomarker.mixin.iceandfire.rlcraft;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.util.StructureGenerationUtil;
import com.github.alexthe666.iceandfire.structures.WorldGenCyclopsCave;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(WorldGenCyclopsCave.class)
public class WorldGenCyclopsCaveMixin {
    @Inject(
            method = "generate",
            at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0)
    )
    void markCyclops(World worldIn, Random rand, BlockPos position, CallbackInfoReturnable<Boolean> cir){
        StructureGenerationUtil.markStructure(
                worldIn,
                position,
                AutoMarkSetting.autoMarkSettings.get("cyclopsCave")
        );
    }
}
