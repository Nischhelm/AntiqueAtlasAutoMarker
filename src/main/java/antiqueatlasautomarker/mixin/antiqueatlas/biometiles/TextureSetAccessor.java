package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import hunternif.mc.atlas.client.TextureSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TextureSet.class)
public interface TextureSetAccessor {
    @Invoker(value = "stitchesToNull", remap = false)
    TextureSet invokeStitchesToNull();
}
