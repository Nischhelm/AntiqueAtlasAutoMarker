package antiqueatlasautomarker.custombiometiles;

import git.jbredwards.nether_api.mod.common.compat.betternether.BetterNetherHandler;
import git.jbredwards.nether_api.mod.common.compat.betternether.BiomeBetterNether;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import net.minecraft.util.ResourceLocation;
import paulevs.betternether.biomes.BiomeRegister;
import paulevs.betternether.biomes.NetherBiome;

public class BetterNetherCompat {
    public static void registerTiles(){
        TileAPI api = AtlasAPI.getTileAPI();

        TextureSet emptyNether = NetherTiles.HELL_TEXTURE; //Normal nether
        TextureSet gravelDesert = new TextureSet("BN_GRAVEL_DESERT", tileLoc("gravel_desert"));
        TextureSet netherJungle = new TextureSet("BN_JUNGLE", tileLoc("nether_jungle"));
        TextureSet boneReef = new TextureSet("BN_BONE_REEF", tileLoc("bone_reef"));
        TextureSet grasslands = new TextureSet("BN_GRASSLANDS", tileLoc("nether_grasslands"));
        TextureSet mushroom = new TextureSet("BN_MUSHROOM_FOREST", tileLoc("nether_mushroom_forest"));
        //TextureSet mushroomEdge = mushroom;
        TextureSet poorGrasslands = new TextureSet("BN_POOR_GRASSLANDS", tileLoc("poor_nether_grasslands"));
        TextureSet wartForest = new TextureSet("BN_WART_FOREST", tileLoc("netherwart_forest"));
        //TextureSet wartForestEdge = wartForest;

        registerIfPresent(api, BiomeRegister.BIOME_EMPTY_NETHER, emptyNether);
        registerIfPresent(api, BiomeRegister.BIOME_GRAVEL_DESERT, gravelDesert);
        registerIfPresent(api, BiomeRegister.BIOME_NETHER_JUNGLE, netherJungle);
        registerIfPresent(api, BiomeRegister.BIOME_BONE_REEF, boneReef);
        registerIfPresent(api, BiomeRegister.BIOME_GRASSLANDS, grasslands);
        registerIfPresent(api, BiomeRegister.BIOME_MUSHROOM_FOREST, mushroom);
        registerIfPresent(api, BiomeRegister.BIOME_MUSHROOM_FOREST_EDGE, mushroom);
        registerIfPresent(api, BiomeRegister.BIOME_POOR_GRASSLANDS, poorGrasslands);
        registerIfPresent(api, BiomeRegister.BIOME_WART_FOREST, wartForest);
        registerIfPresent(api, BiomeRegister.BIOME_WART_FOREST_EDGE, wartForest);
    }

    private static void registerIfPresent(TileAPI api, NetherBiome better_nether_biome, TextureSet texture){
        try {
            BiomeBetterNether nether_api_biome = BetterNetherHandler.getBiomeFromLookup(better_nether_biome);
            if(nether_api_biome != null) //needed due to fermiummixins fix for old betternether
                api.setBiomeTexture(nether_api_biome, texture);
        } catch (Exception ignored){/* we just don't register here*/}
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/artsy/betternether/"+tileName+".png");
    }
}
