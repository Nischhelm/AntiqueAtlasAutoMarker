package antiqueatlasautomarker.mixin.vanilla;

import antiqueatlasautomarker.config.data.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapGenStructure.class)
public abstract class MapGenStructureMixin {
    @Shadow
    public abstract String getStructureName();

    @Inject(
            method = "generateStructure",
            at = @At(value = "NEW", target = "(IIII)Lnet/minecraft/world/gen/structure/StructureBoundingBox;")
    )
    private void aaam_markStructures(CallbackInfoReturnable<Boolean> cir, @Local StructureStart structureStart, @Local(argsOnly = true) World worldIn){
        AutoMarkSetting.Data setting = AutoMarkSetting.get(MapGenStructureIO.getStructureStartName(structureStart));
        if(setting != null && setting.enabled) {
            String label = setting.label;
            if ("DEFAULT".equals(label)) label = this.getStructureName();

            StructureBoundingBox box = structureStart.getBoundingBox();
            StructureMarkersDataHandler.markStructure(
                    worldIn,
                    (box.maxX + box.minX)/2, (box.maxZ + box.minZ) / 2,
                    setting.type, label, setting.context
            );
        }
    }
}
