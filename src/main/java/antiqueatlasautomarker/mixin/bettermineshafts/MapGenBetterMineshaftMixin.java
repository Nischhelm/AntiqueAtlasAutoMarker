package antiqueatlasautomarker.mixin.bettermineshafts;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.yungnickyoung.minecraft.bettermineshafts.world.MapGenBetterMineshaft;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapGenBetterMineshaft.class)
public class MapGenBetterMineshaftMixin extends MapGenMineshaft {
    @Inject(method = "getStructureStart", at = @At("HEAD"))
    private void markBetterMineshaft(int chunkX, int chunkZ, CallbackInfoReturnable<StructureStart> cir){
        AutoMarkSetting setting = AutoMarkSetting.get("betterMineshaft");
        if(setting == null || !setting.enabled) return;

        String usedLabel = setting.label;
        if(usedLabel.equals("DEFAULT")) usedLabel = "gui.aaam.marker.betterMineshaft";

        StructureMarkersDataHandler.markStructure(
                world,
                chunkX << 4,
                chunkZ << 4,
                setting.type,
                usedLabel,
                setting.context
        );
    }
}
