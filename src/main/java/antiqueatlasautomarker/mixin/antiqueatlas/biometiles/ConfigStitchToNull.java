package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import antiqueatlasautomarker.config.ConfigHandler;
import hunternif.mc.atlas.client.TextureSet;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Mixin(TextureSet.class)
public abstract class ConfigStitchToNull {
    @Shadow(remap = false) abstract TextureSet stitchesToNull();
    @Shadow(remap = false) private boolean stitchesToNull;

    @Unique private static Set<String> aaam$configSet = null;

    @Inject(
            method = "<init>(ZLjava/lang/String;[Lnet/minecraft/util/ResourceLocation;)V",
            at = @At(value = "TAIL"),
            remap = false
    )
    private void aaam_stitchToNull(boolean isStandard, String name, ResourceLocation[] textures, CallbackInfo ci){
        if(aaam$configSet == null)
            aaam$configSet = new HashSet<>(Arrays.asList(ConfigHandler.overhaul.tileConfig.stitchToNullSets));

        if(aaam$configSet.contains(name))
            this.stitchesToNull();
        else
            this.stitchesToNull = false; //to fully make it config dependent
    }
}
