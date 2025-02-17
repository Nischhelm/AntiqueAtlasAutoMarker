package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers;

import antiqueatlasautomarker.structuremarkers.CustomPosition;
import hunternif.mc.atlas.marker.DimensionMarkersData;
import hunternif.mc.atlas.util.IntVec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DimensionMarkersData.class)
public abstract class DimensionMarkersDataMixin {
    @Redirect(
            method = "insertMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/util/IntVec2;set(II)Lhunternif/mc/atlas/util/IntVec2;"),
            remap = false
    )
    private IntVec2 putMarkerAtCustomPos(IntVec2 key, int x, int z){
        //If we are coming from insertMarkerAtCustomPos, use the custom position
        if(!CustomPosition.isEmpty()) {
            CustomPosition customPos = CustomPosition.getAndRemove();
            key.set(customPos.bigChunkX, customPos.bigChunkZ);
            return key;
        }

        //else default behavior
        return key.set(x,z);
    }
}
