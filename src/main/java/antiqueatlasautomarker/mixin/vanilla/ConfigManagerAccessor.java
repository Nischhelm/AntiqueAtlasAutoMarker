package antiqueatlasautomarker.mixin.vanilla;

import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(ConfigManager.class)
public interface ConfigManagerAccessor {
    @Accessor(value = "CONFIGS", remap = false)
    static Map<String, Configuration> getConfigMap() {
        throw new AssertionError("AAAM: Failed to access ConfigManager.CONFIG");
    }
}
