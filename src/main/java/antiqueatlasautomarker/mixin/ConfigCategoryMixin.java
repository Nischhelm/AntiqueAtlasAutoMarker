package antiqueatlasautomarker.mixin;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * needed for subconfigs to be sorted correctly
 */
@Mixin(ConfigCategory.class)
public abstract class ConfigCategoryMixin {
    @Shadow(remap = false) private Map<String, Property> properties;
    @Shadow(remap = false) public abstract ConfigCategory getFirstParent();
    @Shadow(remap = false) public abstract String getName();

    @Shadow(remap = false) private String name;

    @Inject(
            method = "<init>(Ljava/lang/String;Lnet/minecraftforge/common/config/ConfigCategory;)V",
            at = @At("TAIL"),
            remap = false
    )
    private void aaam_sortConfig(String name, ConfigCategory parent, CallbackInfo ci){
        //this only works if the main @Config class is not only annotated with modid = .. but also with category = modid
        //Idea: if you instead just check for category == "not ordered" or smth, this could directly work however you want.
        //TODO category is usually just "general". idk if changing that brings issues but id expect it
        // gotta grab the modid some other way
        if(this.getFirstParent().getName().equals(AntiqueAtlasAutoMarker.MODID))
            this.properties = new LinkedHashMap<>();
    }
}
