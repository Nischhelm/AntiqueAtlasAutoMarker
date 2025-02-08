package antiqueatlasautomarker.mixin.iceandfire.rotn;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.util.StructureGenerationUtil;
import com.github.alexthe666.iceandfire.world.gen.WorldGenFireDragonRoosts;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(WorldGenFireDragonRoosts.class)
public class WorldGenFireDragonRoostsMixin {
    @Inject(method = "generate", at = @At("HEAD"))
    void markFireDragon(World worldIn, Random rand, BlockPos position, CallbackInfoReturnable<Boolean> cir){
        StructureGenerationUtil.markStructure(
                worldIn,
                position,
                AutoMarkSetting.autoMarkSettings.get("fireDragon")
        );
    }
}
