package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import antiqueatlasautomarker.config.ConfigHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.client.Textures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(Textures.class)
public abstract class CustomEndTextures {
    @ModifyExpressionValue(
            method = "tile",
            at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;toString()Ljava/lang/String;"),
            remap = false
    )
    private static String aaam_swapToModifiedEnd(String original, @Local(argsOnly = true) String fileName){
        if(!ConfigHandler.overhaul.tileConfig.useModifiedEndTiles) return original;
        if(aaam$endTextures.contains(fileName))
            return "antiqueatlas:textures/gui/tiles/nischhelm/end/" + fileName;
        return original;
    }

    @Unique private static final Set<String> aaam$endTextures = Stream.of(
            "end_island",
            "end_island2",
            "end_island_plants",
            "end_island_plants2",
            "end_void"
    ).collect(Collectors.toSet());
}
