package antiqueatlasautomarker.mixin.vanilla;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
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
public class LocaleMixin {
    @Shadow Map<String, String> properties;

    @Inject(
            method = "loadLocaleData(Ljava/util/List;)V",
            at = @At("RETURN")
    )
    private void aaam_injectCustomLangKeys(List<IResource> resourcesList, CallbackInfo ci) {
        for(String s : ConfigHandler.langKeys) {
            String[] split = s.split("=");
            if(split.length != 2) {
                AntiqueAtlasAutoMarker.LOGGER.warn("AAAM Unable to parse Lang Key line (expected pattern: key=translation): {}", s);
                continue;
            }
            String key = "gui.aaam.marker."+split[0];
            String translation = split[1];
            //We only inject if there isn't a lang file already which provides a translation for the current key
            if(!properties.containsKey(key)) properties.put(key, translation);
        }
    }
}
