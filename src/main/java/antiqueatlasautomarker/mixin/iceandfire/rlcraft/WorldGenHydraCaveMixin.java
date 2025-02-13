package antiqueatlasautomarker.mixin.iceandfire.rlcraft;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.util.StructureGenerationUtil;
import com.github.alexthe666.iceandfire.structures.WorldGenHydraCave;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(WorldGenHydraCave.class)
public class WorldGenHydraCaveMixin {
    @Inject(
            method = "generate",
            at = @At(value = "INVOKE", target = "Lcom/github/alexthe666/iceandfire/structures/WorldGenHydraCave;generateExterior(Lnet/minecraft/world/World;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;I)V", remap = false)
    )
    void markHydra(World worldIn, Random rand, BlockPos position, CallbackInfoReturnable<Boolean> cir){
        StructureGenerationUtil.markStructure(
                worldIn,
                position,
                AutoMarkSetting.autoMarkSettings.get("hydraCave")
        );
    }
}
