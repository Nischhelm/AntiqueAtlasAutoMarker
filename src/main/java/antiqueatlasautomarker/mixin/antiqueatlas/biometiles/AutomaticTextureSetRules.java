package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import antiqueatlasautomarker.config.folders.BiomeTileConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Cancellable;
import hunternif.mc.atlas.client.BiomeTextureMap;
import hunternif.mc.atlas.client.TextureSet;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(BiomeTextureMap.class)
public abstract class AutomaticTextureSetRules {
    @Shadow(remap = false) public abstract void setTexture(Biome biome, TextureSet textureSet);

    @WrapOperation(
            method = "autoRegister",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/BiomeDictionary;getTypes(Lnet/minecraft/world/biome/Biome;)Ljava/util/Set;"),
            remap = false
    )
    private Set<BiomeDictionary.Type> aaam_overwriteAutomaticLogic(Biome biome, Operation<Set<BiomeDictionary.Type>> original, @Cancellable CallbackInfo ci){
        Set<BiomeDictionary.Type> types = original.call(biome);
        this.setTexture(biome, BiomeTileConfig.getTextureSet(types));
        ci.cancel();
        return types;
    }
}
