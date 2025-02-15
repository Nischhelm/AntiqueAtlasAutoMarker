package antiqueatlasautomarker.mixin.iceandfire.base;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.github.alexthe666.iceandfire.structures.WorldGenIceDragonRoosts;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(WorldGenIceDragonRoosts.class)
public class WorldGenIceDragonRoostsMixin {
    @Inject(method = "generate", at = @At("HEAD"))
    void markIceDragon(World worldIn, Random rand, BlockPos position, CallbackInfoReturnable<Boolean> cir){
        StructureMarkersDataHandler.markStructure(
                worldIn,
                position,
                AutoMarkSetting.get("iceDragon")
        );
    }
}
