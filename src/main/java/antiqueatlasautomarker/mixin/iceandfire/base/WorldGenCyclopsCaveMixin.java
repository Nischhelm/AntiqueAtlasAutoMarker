package antiqueatlasautomarker.mixin.iceandfire.base;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
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
    private void markCyclops(World worldIn, Random rand, BlockPos position, CallbackInfoReturnable<Boolean> cir){
        StructureMarkersDataHandler.markStructure(
                worldIn,
                position,
                AutoMarkSetting.get("cyclopsCave")
        );
    }
}
