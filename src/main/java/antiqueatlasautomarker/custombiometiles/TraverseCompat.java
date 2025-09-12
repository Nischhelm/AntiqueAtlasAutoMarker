package antiqueatlasautomarker.custombiometiles;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.client.Textures;
import net.minecraft.util.ResourceLocation;
import prospector.traverse.world.TraverseWorld;

public class TraverseCompat {
    public static void registerTiles(){
        TileAPI api = AtlasAPI.getTileAPI();

        TextureSet autumnHills = new TextureSet("T_AUTUMN_HILLS", tileLoc("t_autumn_forest_hills"), tileLoc("t_autumn_forest_hills2"), tileLoc("t_autumn_forest_hills3"));
        TextureSet autumnWoods = new TextureSet("T_AUTUMN", tileLoc("t_autumn_forest"), tileLoc("t_autumn_forest2"), tileLoc("t_autumn_forest3"));
        TextureSet canyon = new TextureSet("T_CANYON", tileLoc("t_canyon"), tileLoc("t_canyon2"), tileLoc("t_canyon3"));
        TextureSet crag = new TextureSet("T_CRAG", tileLoc("t_crag_mountains"), tileLoc("t_crag_mountains2"), tileLoc("t_crag_mountains3"), tileLoc("t_crag_mountains4"));
        TextureSet redDesert = new TextureSet("T_RED_DESERT", tileLoc("t_red_sand"), tileLoc("t_red_sand2"), tileLoc("t_red_sand3"));
        TextureSet rockyPlateau = new TextureSet("T_ROCKY_PLATEAU", Textures.TILE_PLATEAU_GRASS, Textures.TILE_PLATEAU_GRASS2, Textures.TILE_PLATEAU_GRASS3, Textures.TILE_PLATEAU_TREES, Textures.TILE_PLATEAU_TREES_LOW);

        api.setBiomeTexture(TraverseWorld.aridHighlandBiome, TextureSet.MOUNTAINS_NAKED);
        api.setBiomeTexture(TraverseWorld.autumnalWoodedHillsBiome, autumnHills);
        api.setBiomeTexture(TraverseWorld.autumnalWoodsBiome, autumnWoods);
        api.setBiomeTexture(TraverseWorld.badlandsBiome, TextureSet.MOUNTAINS_NAKED);
        api.setBiomeTexture(TraverseWorld.birchForestedHillsBiome, TextureSet.BIRCH_HILLS);
        api.setBiomeTexture(TraverseWorld.canyonBiome, canyon);
        api.setBiomeTexture(TraverseWorld.cliffsBiome, TextureSet.MOUNTAINS_NAKED);
        api.setBiomeTexture(TraverseWorld.cragCliffsBiome, crag);
        api.setBiomeTexture(TraverseWorld.desertShrublandBiome, TextureSet.PLAINS);
        api.setBiomeTexture(TraverseWorld.forestedHillsBiome, TextureSet.FOREST_HILLS);
        api.setBiomeTexture(TraverseWorld.glacierBiome, TextureSet.SNOW_HILLS);
        api.setBiomeTexture(TraverseWorld.glacierSpikesBiome, TextureSet.ICE_SPIKES);
        api.setBiomeTexture(TraverseWorld.lushSwampBiome, TextureSet.SWAMP);
        api.setBiomeTexture(TraverseWorld.lushHillsBiome, TextureSet.FOREST_HILLS);
        api.setBiomeTexture(TraverseWorld.meadowBiome, TextureSet.FOREST_FLOWERS);
        api.setBiomeTexture(TraverseWorld.miniJungleBiome, TextureSet.JUNGLE);
        api.setBiomeTexture(TraverseWorld.mountainousDesertBiome, TextureSet.DESERT_HILLS);
        api.setBiomeTexture(TraverseWorld.redDesertBiome, redDesert);
        api.setBiomeTexture(TraverseWorld.rockyPlainsBiome, TextureSet.HILLS);
        api.setBiomeTexture(TraverseWorld.rockyPlateauBiome, rockyPlateau);
        api.setBiomeTexture(TraverseWorld.snowyConiferousForestBiome, TextureSet.PINES);
        api.setBiomeTexture(TraverseWorld.temperateRainforestBiome, TextureSet.PINES);
        api.setBiomeTexture(TraverseWorld.thicketBiome, TextureSet.DENSE_FOREST);
        api.setBiomeTexture(TraverseWorld.woodlandsBiome, TextureSet.FOREST);
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/artsy/traverse/"+tileName+".png");
    }
}
