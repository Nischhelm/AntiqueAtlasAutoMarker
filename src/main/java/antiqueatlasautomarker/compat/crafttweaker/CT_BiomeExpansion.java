package antiqueatlasautomarker.compat.crafttweaker;

import crafttweaker.annotations.ZenDoc;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBiome;
import net.minecraft.world.biome.Biome;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenExpansion("crafttweaker.world.IBiome")
public class CT_BiomeExpansion {
    @ZenDoc("Used to check if the biome and the biomes registry name actually exist, as .id gets the string directly")
    @ZenMethod("isRegistered")
    public boolean isRegistered(IBiome biome) {
        if(biome == null) return false;
        Biome nativeBiome = CraftTweakerMC.getBiome(biome);
        return nativeBiome != null && nativeBiome.getRegistryName() != null;
    }
}
