package antiqueatlasautomarker.mixin.vanilla;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MapGenStructure.class)
public class MapGenStructureMixin {
    @WrapOperation(
            method = "generateStructure",
            at = @At(value = "NEW", target = "(IIII)Lnet/minecraft/world/gen/structure/StructureBoundingBox;")
    )
    private StructureBoundingBox aaam_markStructures(int xMin, int zMin, int xMax, int zMax, Operation<StructureBoundingBox> original, @Local StructureStart structureStart, @Local(argsOnly = true) World worldIn){
        AutoMarkSetting setting = AutoMarkSetting.get(MapGenStructureIO.getStructureStartName(structureStart));
        if(setting != null && setting.enabled)
            StructureMarkersDataHandler.markStructure(worldIn, structureStart.getChunkPosX() << 4 + 8, structureStart.getChunkPosZ() << 4 + 8, setting);
        return original.call(xMin, zMin, xMax, zMax);
    }
}
