package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import antiqueatlasautomarker.config.ConfigHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.client.Textures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.List;

@Mixin(Textures.class)
public abstract class CustomEndTextures {
    @Unique private static List<String> aaam$endTextures = null;

    @ModifyExpressionValue(
            method = "tile",
            at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;toString()Ljava/lang/String;"),
            remap = false
    )
    private static String aaam_swapToModifiedEnd(String original, @Local(argsOnly = true) String fileName){
        if(aaam$endTextures == null) aaam$endTextures = Arrays.asList( //cant statically clinit cause this runs in Textures.clinit... never had that situation before
                "end_island.png",
                "end_island2.png",
                "end_island_plants.png",
                "end_island_plants2.png"
        );
        if(fileName.equals("end_void.png"))
            return "antiqueatlas:textures/gui/tiles/nischhelm/end/" + (ConfigHandler.overhaul.tileConfig.purpleVoid ? "end_void_purple.png" : "end_void_empty.png");
        if(aaam$endTextures.contains(fileName))
                return "antiqueatlas:textures/gui/tiles/nischhelm/end/" + fileName;
        return original;
    }
}
