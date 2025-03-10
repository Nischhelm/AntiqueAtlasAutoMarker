package antiqueatlasautomarker.mixin.betterend;

import mod.beethoven92.betterendforge.BetterEnd;
import mod.beethoven92.betterendforge.common.init.ModBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

@Mixin(BetterEnd.WorldGenRegistryEvents.class)
public class BetterEndMixin {
    @Inject(
            method = "registerBiomes",
            at = @At("TAIL"),
            remap = false
    )
    private static void setBiomeTypes(RegistryEvent.Register<Biome> event, CallbackInfo ci) {
        ModBiomes.getModBiomes().forEach(biome -> BiomeDictionary.addTypes(biome.getBiome(), COLD, DRY, END));
    }
}
