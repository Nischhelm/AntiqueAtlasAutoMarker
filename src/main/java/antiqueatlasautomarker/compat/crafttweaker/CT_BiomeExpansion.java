package antiqueatlasautomarker.compat.crafttweaker;

import crafttweaker.annotations.ZenDoc;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBiome;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenExpansion("crafttweaker.world.IBiome")
public class CT_BiomeExpansion {
    @ZenDoc("Used to check if the biomes registry name actually exist, as .id gets the string directly")
    @ZenMethod("isRegistered")
    public boolean isRegistered(IBiome biome) {
        return CraftTweakerMC.getBiome(biome).getRegistryName() != null;
    }
}
