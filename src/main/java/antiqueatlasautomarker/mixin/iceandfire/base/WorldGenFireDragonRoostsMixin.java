package antiqueatlasautomarker.mixin.iceandfire.base;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.github.alexthe666.iceandfire.structures.WorldGenFireDragonRoosts;
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
        AutoMarkSetting setting = AutoMarkSetting.get("fireDragon");
        if(setting == null || !setting.enabled) return;
        String usedLabel = setting.label;
        if(usedLabel.equals("DEFAULT")) usedLabel = "entity.firedragon.name";
        StructureMarkersDataHandler.markStructure(
                worldIn,
                position,
                setting.type,
                usedLabel,
                setting.context
        );
    }
}
