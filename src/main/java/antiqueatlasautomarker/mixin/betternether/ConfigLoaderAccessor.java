package antiqueatlasautomarker.mixin.betternether;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import paulevs.betternether.config.ConfigLoader;

@Mixin(ConfigLoader.class)
public interface ConfigLoaderAccessor {
    @Accessor(value = "registerBiomes", remap = false)
    static boolean[] getBiomeIsRegistered() {return null;}
}
