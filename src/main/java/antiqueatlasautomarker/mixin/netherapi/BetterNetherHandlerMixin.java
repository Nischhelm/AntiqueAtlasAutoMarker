package antiqueatlasautomarker.mixin.netherapi;

import antiqueatlasautomarker.mixin.betternether.ConfigLoaderAccessor;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import git.jbredwards.nether_api.mod.common.compat.betternether.BetterNetherHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import paulevs.betternether.biomes.BiomeRegister;
import paulevs.betternether.config.ConfigLoader;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.ObjIntConsumer;

@Mixin(BetterNetherHandler.class)
public class BetterNetherHandlerMixin  {
    @WrapWithCondition(
            method = "registerBiomes(Lnet/minecraftforge/event/RegistryEvent$Register;)V",
            at = @At(value = "INVOKE", target = "Ljava/util/function/ObjIntConsumer;accept(Ljava/lang/Object;I)V"),
            remap = false
    )
    private static boolean dontRegisterIfDisabled(ObjIntConsumer instance, Object biome, int biomeId){
        if(ConfigLoaderAccessor.getBiomeIsRegistered() == null) return true;
        return ConfigLoaderAccessor.getBiomeIsRegistered()[biomeId];
    }

    @WrapWithCondition(
            method = "registerBiomes(Lnet/minecraftforge/event/RegistryEvent$Register;)V",
            at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"),
            remap = false
    )
    private static boolean dontAddIfDisabled(Set instance, Object biome){
        if(ConfigLoaderAccessor.getBiomeIsRegistered() == null) return true;

        int biomeId;
        if(biome.equals(BiomeRegister.BIOME_GRAVEL_DESERT)) biomeId = 1;
        else if(biome.equals(BiomeRegister.BIOME_NETHER_JUNGLE)) biomeId = 2;
        else if(biome.equals(BiomeRegister.BIOME_WART_FOREST)) biomeId = 3;
        else if(biome.equals(BiomeRegister.BIOME_GRASSLANDS)) biomeId = 4;
        else if(biome.equals(BiomeRegister.BIOME_MUSHROOM_FOREST)) biomeId = 5;
        else return true;

        return ConfigLoaderAccessor.getBiomeIsRegistered()[biomeId];
    }
}
