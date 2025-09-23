package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers.custompositions;

import antiqueatlasautomarker.structuremarkers.CustomPosition;
import antiqueatlasautomarker.util.ICustomPosMarker;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hunternif.mc.atlas.marker.DimensionMarkersData;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.util.IntVec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DimensionMarkersData.class)
public abstract class DimensionMarkersDataMixin {
    @WrapOperation(
            method = "insertMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/util/IntVec2;set(II)Lhunternif/mc/atlas/util/IntVec2;"),
            remap = false
    )
    private IntVec2 aaam_putMarkerAtCustomPos(IntVec2 instance, int x, int z, Operation<IntVec2> original, Marker marker){
        //If we are coming from insertMarkerAtCustomPos, use the custom position
        if(((ICustomPosMarker) marker).isCustomPosMarker()) {
            CustomPosition customPos = ((ICustomPosMarker) marker).aaam$getDiscoverPosition();
            return original.call(instance, customPos.bigChunkX, customPos.bigChunkZ);
        }

        //else default behavior
        return original.call(instance, x, z);
    }
}
