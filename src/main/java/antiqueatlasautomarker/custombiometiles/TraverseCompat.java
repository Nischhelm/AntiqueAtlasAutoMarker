package antiqueatlasautomarker.custombiometiles;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.client.Textures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import prospector.traverse.world.TraverseWorld;

public class TraverseCompat {
    public static void registerTiles() {
        TileAPI api = AtlasAPI.getTileAPI();

        TextureSet autumnHills = new TextureSet("T_AUTUMN_HILLS", tileLoc("t_autumn_forest_hills"), tileLoc("t_autumn_forest_hills2"), tileLoc("t_autumn_forest_hills3"));
        TextureSet autumnWoods = new TextureSet("T_AUTUMN", tileLoc("t_autumn_forest"), tileLoc("t_autumn_forest2"), tileLoc("t_autumn_forest3"));
        TextureSet canyon = new TextureSet("T_CANYON", tileLoc("t_canyon"), tileLoc("t_canyon2"), tileLoc("t_canyon3"));
        TextureSet crag = new TextureSet("T_CRAG", tileLoc("t_crag_mountains"), tileLoc("t_crag_mountains2"), tileLoc("t_crag_mountains3"), tileLoc("t_crag_mountains4"));
        TextureSet redDesert = new TextureSet("T_RED_DESERT", tileLoc("t_red_sand"), tileLoc("t_red_sand2"), tileLoc("t_red_sand3"));
        TextureSet rockyPlateau = new TextureSet("T_ROCKY_PLATEAU", Textures.TILE_PLATEAU_GRASS, Textures.TILE_PLATEAU_GRASS2, Textures.TILE_PLATEAU_GRASS3, Textures.TILE_PLATEAU_TREES, Textures.TILE_PLATEAU_TREES_LOW);

        /* TODO:
        * aridHighlandBiome
        * badlandsBiome
        * birchForestedHillsBiome
        * cliffsBiome
        * desertShrublandBiome
        * forestedHillsBiome
        * glacierBiome
        * glacierSpikesBiome
        * lushSwampBiome
        * lushHillsBiome
        * meadowBiome
        * miniJungleBiome
        * mountainousDesertBiome
        * rockyPlainsBiome
        * snowyConiferousForestBiome
        * temperateRainforestBiome
        * thicketBiome
        * woodlandsBiome
        */

        registerTexture(api, TraverseWorld.aridHighlandBiome, TextureSet.MOUNTAINS_NAKED);
        registerTexture(api, TraverseWorld.autumnalWoodedHillsBiome, autumnHills);
        registerTexture(api, TraverseWorld.autumnalWoodsBiome, autumnWoods);
        registerTexture(api, TraverseWorld.badlandsBiome, TextureSet.MOUNTAINS_NAKED);
        registerTexture(api, TraverseWorld.birchForestedHillsBiome, TextureSet.BIRCH_HILLS);
        registerTexture(api, TraverseWorld.canyonBiome, canyon);
        registerTexture(api, TraverseWorld.cliffsBiome, TextureSet.MOUNTAINS_NAKED);
        registerTexture(api, TraverseWorld.cragCliffsBiome, crag);
        registerTexture(api, TraverseWorld.desertShrublandBiome, TextureSet.PLAINS);
        registerTexture(api, TraverseWorld.forestedHillsBiome, TextureSet.FOREST_HILLS);
        registerTexture(api, TraverseWorld.glacierBiome, TextureSet.SNOW_HILLS);
        registerTexture(api, TraverseWorld.glacierSpikesBiome, TextureSet.ICE_SPIKES);
        registerTexture(api, TraverseWorld.lushSwampBiome, TextureSet.SWAMP);
        registerTexture(api, TraverseWorld.lushHillsBiome, TextureSet.FOREST_HILLS);
        registerTexture(api, TraverseWorld.meadowBiome, TextureSet.FOREST_FLOWERS);
        registerTexture(api, TraverseWorld.miniJungleBiome, TextureSet.JUNGLE);
        registerTexture(api, TraverseWorld.mountainousDesertBiome, TextureSet.DESERT_HILLS);
        registerTexture(api, TraverseWorld.redDesertBiome, redDesert);
        registerTexture(api, TraverseWorld.rockyPlainsBiome, TextureSet.HILLS);
        registerTexture(api, TraverseWorld.rockyPlateauBiome, rockyPlateau);
        registerTexture(api, TraverseWorld.snowyConiferousForestBiome, TextureSet.PINES);
        registerTexture(api, TraverseWorld.temperateRainforestBiome, TextureSet.PINES);
        registerTexture(api, TraverseWorld.thicketBiome, TextureSet.DENSE_FOREST);
        registerTexture(api, TraverseWorld.woodlandsBiome, TextureSet.FOREST);
    }
    
    private static void registerTexture(TileAPI api, Biome biome, TextureSet texture){
        if(biome != null && TraverseWorld.biomeList.stream().anyMatch(entry -> entry.getBiome() == biome))
            api.setBiomeTexture(biome, texture);
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/artsy/traverse/"+tileName+".png");
    }
}
