package antiqueatlasautomarker.mixin.antiqueatlas.overhaul;

import hunternif.mc.atlas.network.client.IntDimensionUpdatePacket;
import hunternif.mc.atlas.network.client.ShortDimensionUpdatePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {ShortDimensionUpdatePacket.class, IntDimensionUpdatePacket.class})
public class UpdateCorrectClientAtlas {
    @Shadow(remap = false) private int atlasID;

    @Inject(method = "<init>(II)V", at = @At("TAIL"))
    private void aaam_initAtlasID(int atlasID, int dimension, CallbackInfo ci){
        this.atlasID = atlasID; //so much wasted network over 10 years of AA
    }
}
