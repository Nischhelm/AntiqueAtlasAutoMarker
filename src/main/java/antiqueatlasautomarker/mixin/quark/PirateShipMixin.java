package antiqueatlasautomarker.mixin.quark;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.world.world.PirateShipGenerator;

import java.util.Random;

@Mixin(PirateShipGenerator.class)
public class PirateShipMixin {
    @Inject(
            method = "generateShipAt",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/structure/template/Template;addBlocksToWorld(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/gen/structure/template/PlacementSettings;)V")
    )
    private static void markPirateShip(WorldServer world, Random random, BlockPos pos, CallbackInfo ci){
        AutoMarkSetting setting = AutoMarkSetting.get("quarkPirateShip");
        if(setting == null || !setting.enabled) return;

        String usedLabel = setting.label;
        if(usedLabel.equals("DEFAULT")) usedLabel = "gui.aaam.marker.quarkPirateShip";

        StructureMarkersDataHandler.markStructure(
                world,
                pos,
                setting.type,
                usedLabel,
                setting.context
        );
    }
}
