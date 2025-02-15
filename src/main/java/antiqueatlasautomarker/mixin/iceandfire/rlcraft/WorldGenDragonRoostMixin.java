package antiqueatlasautomarker.mixin.iceandfire.rlcraft;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.github.alexthe666.iceandfire.structures.WorldGenDragonRoost;
import com.github.alexthe666.iceandfire.structures.WorldGenFireDragonRoost;
import com.github.alexthe666.iceandfire.structures.WorldGenIceDragonRoost;
import com.github.alexthe666.iceandfire.structures.WorldGenLightningDragonRoost;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(WorldGenDragonRoost.class)
public class WorldGenDragonRoostMixin {
    @Inject(method = "generate", at = @At("HEAD"))
    void markDragon(World worldIn, Random rand, BlockPos position, CallbackInfoReturnable<Boolean> cir){
        String dragonType = "";
        if(((Object) this) instanceof WorldGenFireDragonRoost)
            dragonType = "fireDragon";
        else if(((Object) this) instanceof WorldGenIceDragonRoost)
            dragonType = "iceDragon";
        else if(((Object) this) instanceof WorldGenLightningDragonRoost)
            dragonType = "lightningDragon";

        StructureMarkersDataHandler.markStructure(
                worldIn,
                position,
                AutoMarkSetting.get(dragonType)
        );
    }
}
