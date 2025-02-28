package antiqueatlasautomarker.mixin.battletowers;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import atomicstryker.battletowers.common.AS_WorldGenTower;
import atomicstryker.battletowers.common.WorldGenHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Random;

@Mixin(WorldGenHandler.class)
public class BTWorldGenMixin {
    @WrapOperation(
            method = "attemptToSpawnTower",
            at = @At(value = "INVOKE", target = "Latomicstryker/battletowers/common/AS_WorldGenTower;generate(Lnet/minecraft/world/World;Ljava/util/Random;IIIIZ)V"),
            remap = false
    )
    private void markBattleTower(AS_WorldGenTower instance, World world, Random random, int x, int y, int z, int choice, boolean  underground, Operation<Void> original){
        original.call(instance, world, random,  x, y, z, choice, underground);

        AutoMarkSetting setting = AutoMarkSetting.get("battleTower");
        if(setting == null || !setting.enabled) return;

        String usedLabel = setting.label;
        if(setting.label.equals("DEFAULT")) {
            String chosenType = AS_WorldGenTower.TowerTypes.values()[choice].getName() + " Battle Tower";

            if(underground) usedLabel = "Reverse " + chosenType;
            else usedLabel = chosenType;
        }

        StructureMarkersDataHandler.markStructure(
                world,
                x,
                z,
                setting.type,
                usedLabel,
                setting.context
        );
    }
}
