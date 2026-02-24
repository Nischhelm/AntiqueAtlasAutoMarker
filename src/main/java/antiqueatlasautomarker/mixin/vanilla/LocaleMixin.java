package antiqueatlasautomarker.mixin.vanilla;

import antiqueatlasautomarker.config.ConfigHandler;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.Locale;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(Locale.class)
public abstract class LocaleMixin {
    @Shadow Map<String, String> properties;

    @Inject(
            method = "loadLocaleData(Ljava/util/List;)V",
            at = @At("RETURN")
    )
    private void aaam_injectLangKeys(List<IResource> resourcesList, CallbackInfo ci){
        if(ConfigHandler.automark.localisation != null) {
            for (Map.Entry<String, String> entry : ConfigHandler.automark.localisation.langKeys.entrySet()) {
                String key = "gui.aaam.marker." + entry.getKey();
                //We only inject if there isn't a lang file already which provides a translation for the current key
                //Or if config is prioritised
                if (!properties.containsKey(key) || ConfigHandler.automark.localisation.prioritiseConfigLangKeys)
                    properties.put(key, entry.getValue());
            }
        }
    }
}
