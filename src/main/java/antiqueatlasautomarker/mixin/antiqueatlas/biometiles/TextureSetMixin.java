package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import hunternif.mc.atlas.client.TextureSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(TextureSet.class)
public abstract class TextureSetMixin {
    @Shadow(remap = false) abstract TextureSet stitchesToNull();

    @ModifyExpressionValue(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/TextureSet;standard(Ljava/lang/String;[Lnet/minecraft/util/ResourceLocation;)Lhunternif/mc/atlas/client/TextureSet;", remap = false),
            slice = @Slice(
                    from = @At(value = "FIELD", target = "Lhunternif/mc/atlas/client/Textures;TILE_END_VOID:Lnet/minecraft/util/ResourceLocation;", remap = false),
                    to = @At(value = "FIELD", target = "Lhunternif/mc/atlas/client/Textures;TILE_END_ISLAND:Lnet/minecraft/util/ResourceLocation;", remap = false)
            )
    )
    private static TextureSet aaam_makeVoidStitchToNull(TextureSet original){
        return ((TextureSetMixin) (Object) original).stitchesToNull();
    }
}
