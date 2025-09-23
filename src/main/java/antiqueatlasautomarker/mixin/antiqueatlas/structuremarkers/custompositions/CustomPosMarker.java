package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers.custompositions;

import antiqueatlasautomarker.structuremarkers.CustomPosition;
import antiqueatlasautomarker.util.ICustomPosMarker;
import hunternif.mc.atlas.marker.Marker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Marker.class)
public abstract class CustomPosMarker implements ICustomPosMarker {
    @Unique private CustomPosition aaam$customPos = null;

    @Override
    public void aaam$setDiscoverPosition(CustomPosition pos) {
        this.aaam$customPos = pos;
    }

    @Override
    public CustomPosition aaam$getDiscoverPosition() {
        return aaam$customPos;
    }

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void aaam_initCustomPos(CallbackInfo ci){
        if(!CustomPosition.isEmpty()) this.aaam$setDiscoverPosition(CustomPosition.get());
    }
}
