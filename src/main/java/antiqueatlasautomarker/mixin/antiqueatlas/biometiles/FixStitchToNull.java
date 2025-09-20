package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.client.BiomeTextureMap;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.client.TileRenderIterator;
import hunternif.mc.atlas.core.Tile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TileRenderIterator.class)
public abstract class FixStitchToNull {

    @WrapOperation(
            method = {"shouldStitchTo", "shouldStitchToHorizontally", "shouldStitchToVertically"},
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/BiomeTextureMap;getTextureSet(Lhunternif/mc/atlas/core/Tile;)Lhunternif/mc/atlas/client/TextureSet;", ordinal = 1),
            remap = false
    )
    private static TextureSet aaam_makeVoidStitchToNull(BiomeTextureMap instance, Tile tile, Operation<TextureSet> original, Tile tileFrom, Tile tileTo, @Local TextureSet setFrom){
        return tile == null ? null : original.call(instance, tile);
    }
}
