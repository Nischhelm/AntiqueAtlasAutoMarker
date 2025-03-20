package antiqueatlasautomarker.mixin.iceandfire.rlcraft;

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
    void markCyclops(World worldIn, Random rand, BlockPos position, CallbackInfoReturnable<Boolean> cir){
        AutoMarkSetting setting = AutoMarkSetting.get("cyclopsCave");
        if(setting == null || !setting.enabled) return;
        String usedLabel = setting.label;
        if(usedLabel.equals("DEFAULT")) usedLabel = "entity.cyclops.name";
        StructureMarkersDataHandler.markStructure(
                worldIn,
                position,
                setting.type,
                usedLabel,
                setting.context
        );
    }
}
