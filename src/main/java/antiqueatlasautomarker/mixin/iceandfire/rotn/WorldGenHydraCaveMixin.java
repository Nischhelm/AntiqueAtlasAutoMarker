package antiqueatlasautomarker.mixin.iceandfire.rotn;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.github.alexthe666.iceandfire.world.gen.WorldGenHydraCave;
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
            at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0)
    )
    void markHydra(World worldIn, Random rand, BlockPos position, CallbackInfoReturnable<Boolean> cir) {
        AutoMarkSetting setting = AutoMarkSetting.get("hydraCave");
        if(setting == null || !setting.enabled) return;
        String usedLabel = setting.label;
        if(usedLabel.equals("DEFAULT")) usedLabel = "entity.if_hydra.name";
        StructureMarkersDataHandler.markStructure(
                worldIn,
                position,
                setting.type,
                usedLabel,
                setting.context
        );
    }
}
