package antiqueatlasautomarker.mixin.antiqueatlas.overhaul;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.marker.Marker;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Arrays;

@Mixin(Marker.class)
public class LangKeyFix {
    @WrapOperation(
            method = "getLocalizedLabel",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;matches(Ljava/lang/String;)Z"),
            remap = false
    )
    private boolean fixLangKey(String instance, String regex, Operation<Boolean> original){
        return I18n.hasKey(instance);
    }

    @ModifyArgs(
            method = "getLocalizedLabel",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/I18n;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;")
    )
    private void fixI18nParams(Args args, @Local String[] split){
        //AA only uses the first parameter after the lang key. For people wanting to use more than one param, this fix makes it possible
        args.set(1, Arrays.copyOfRange(split,1, split.length));
    }


    @WrapOperation(
            method = "getLocalizedLabel",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"),
            remap = false
    )
    private boolean dontCheckAfterwards(String instance, Object o, Operation<Boolean> original){
        //No need to check if the translated text is still the same as before, as we use I18n.hasKey
        return false;
    }
}
