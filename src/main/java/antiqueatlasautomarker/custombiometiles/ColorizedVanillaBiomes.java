package antiqueatlasautomarker.custombiometiles;

import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.mixin.antiqueatlas.biometiles.TextureSetAccessor;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.client.TextureSetMap;
import net.minecraft.util.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

//what a mess... but hey it works
public class ColorizedVanillaBiomes {
    //TODO: ocean monument tile maybe
    private static final Set<String> registeredSets = new HashSet<>();
    
    private static String currentArtist = "";
    
    private static TextureSet registerTile(String name, TextureSet toRegister){
        if(registeredSets.contains(name)) 
            return TextureSetMap.instance().getByName(name);
        
        TextureSetMap.instance().register(toRegister);
        registeredSets.add(name);
        return toRegister;
    }

    private static TextureSet overrideTileSet(String name, ResourceLocation... textures) {
        return registerTile(name, new TextureSet(true, name, textures));
    }

    private static TextureSet overrideTileSet(TextureSet set) {
        return registerTile(set.name, set);
    }

    public static void registerTiles() {
        String artist = ConfigHandler.overhaul.tileConfig.useColorisedVanillaTiles;

        TextureSet WATER = TextureSet.WATER;
        TextureSet BIRCH = TextureSet.BIRCH;
        TextureSet BIRCH_HILLS = TextureSet.BIRCH_HILLS;
        TextureSet BRYCE = TextureSet.BRYCE;
        TextureSet CAVE_WALLS = TextureSet.CAVE_WALLS;
        TextureSet DENSE_BIRCH = TextureSet.DENSE_BIRCH;
        TextureSet DENSE_FOREST = TextureSet.DENSE_FOREST;
        TextureSet DENSE_FOREST_HILLS = TextureSet.DENSE_FOREST_HILLS;
        TextureSet DESERT = TextureSet.DESERT;
        TextureSet DESERT_HILLS = TextureSet.DESERT_HILLS;
        TextureSet END_ISLAND = TextureSet.END_ISLAND;
        TextureSet END_ISLAND_PLANTS = TextureSet.END_ISLAND_PLANTS;
        TextureSet END_VOID = TextureSet.END_VOID;
        TextureSet FOREST = TextureSet.FOREST;
        TextureSet FOREST_FLOWERS = TextureSet.FOREST_FLOWERS;
        TextureSet FOREST_HILLS = TextureSet.FOREST_HILLS;
        TextureSet HILLS = TextureSet.HILLS;
        TextureSet ICE = TextureSet.ICE;
        TextureSet ICE_SPIKES = TextureSet.ICE_SPIKES;
        TextureSet JUNGLE = TextureSet.JUNGLE;
        TextureSet JUNGLE_CLIFFS = TextureSet.JUNGLE_CLIFFS;
        TextureSet JUNGLE_EDGE = TextureSet.JUNGLE_EDGE;
        TextureSet JUNGLE_EDGE_HILLS = TextureSet.JUNGLE_EDGE_HILLS;
        TextureSet JUNGLE_HILLS = TextureSet.JUNGLE_HILLS;
        TextureSet LAVA = TextureSet.LAVA;
        TextureSet LAVA_SHORE = TextureSet.LAVA_SHORE;
        TextureSet MEGA_SPRUCE = TextureSet.MEGA_SPRUCE;
        TextureSet MEGA_SPRUCE_HILLS = TextureSet.MEGA_SPRUCE_HILLS;
        TextureSet MEGA_TAIGA = TextureSet.MEGA_TAIGA;
        TextureSet MEGA_TAIGA_HILLS = TextureSet.MEGA_TAIGA_HILLS;
        TextureSet MESA = TextureSet.MESA;
        TextureSet MOUNTAINS = TextureSet.MOUNTAINS;
        TextureSet MOUNTAINS_ALL = TextureSet.MOUNTAINS_ALL;
        TextureSet MOUNTAINS_NAKED = TextureSet.MOUNTAINS_NAKED;
        TextureSet MOUNTAINS_SNOW_CAPS = TextureSet.MOUNTAINS_SNOW_CAPS;
        TextureSet MUSHROOM = TextureSet.MUSHROOM;
        TextureSet PINES = TextureSet.PINES;
        TextureSet PINES_HILLS = TextureSet.PINES_HILLS;
        TextureSet PLAINS = TextureSet.PLAINS;
        TextureSet PLATEAU_MESA = TextureSet.PLATEAU_MESA;
        TextureSet PLATEAU_MESA_LOW = TextureSet.PLATEAU_MESA_LOW;
        TextureSet PLATEAU_MESA_TREES = TextureSet.PLATEAU_MESA_TREES;
        TextureSet PLATEAU_MESA_TREES_LOW = TextureSet.PLATEAU_MESA_TREES_LOW;
        TextureSet PLATEAU_SAVANNA = TextureSet.PLATEAU_SAVANNA;
        TextureSet PLATEAU_SAVANNA_M = TextureSet.PLATEAU_SAVANNA_M;
        TextureSet RAVINE = TextureSet.RAVINE;
        TextureSet ROCK_SHORE = TextureSet.ROCK_SHORE;
        TextureSet SAVANNA = TextureSet.SAVANNA;
        TextureSet SAVANNA_CLIFFS = TextureSet.SAVANNA_CLIFFS;
        TextureSet SHORE = TextureSet.SHORE;
        TextureSet SNOW = TextureSet.SNOW;
        TextureSet SNOW_HILLS = TextureSet.SNOW_HILLS;
        TextureSet SNOW_PINES = TextureSet.SNOW_PINES;
        TextureSet SNOW_PINES_HILLS = TextureSet.SNOW_PINES_HILLS;
        TextureSet SPARSE_FOREST = TextureSet.SPARSE_FOREST;
        TextureSet SPARSE_FOREST_HILLS = TextureSet.SPARSE_FOREST_HILLS;
        TextureSet SUNFLOWERS = TextureSet.SUNFLOWERS;
        TextureSet SWAMP = TextureSet.SWAMP;
        TextureSet SWAMP_HILLS = TextureSet.SWAMP_HILLS;
        TextureSet TALL_BIRCH = TextureSet.TALL_BIRCH;
        TextureSet TALL_BIRCH_HILLS = TextureSet.TALL_BIRCH_HILLS;
        TextureSet HOUSE = TextureSet.HOUSE;
        TextureSet BUTCHERS_SHOP = TextureSet.BUTCHERS_SHOP;
        TextureSet CHURCH = TextureSet.CHURCH;
        TextureSet FARMLAND_LARGE = TextureSet.FARMLAND_LARGE;
        TextureSet FARMLAND_SMALL = TextureSet.FARMLAND_SMALL;
        TextureSet FENCE = TextureSet.FENCE;
        TextureSet HOUSE_SMALL = TextureSet.HOUSE_SMALL;
        TextureSet HUT = TextureSet.HUT;
        TextureSet L_HOUSE = TextureSet.L_HOUSE;
        TextureSet LIBRARY = TextureSet.LIBRARY;
        TextureSet SMITHY = TextureSet.SMITHY;
        TextureSet VILLAGE_TORCH = TextureSet.VILLAGE_TORCH;
        TextureSet WELL = TextureSet.WELL;
        TextureSet NETHER_BRIDGE = TextureSet.NETHER_BRIDGE;
        TextureSet NETHER_BRIDGE_X = TextureSet.NETHER_BRIDGE_X;
        TextureSet NETHER_BRIDGE_Z = TextureSet.NETHER_BRIDGE_Z;
        TextureSet NETHER_BRIDGE_END_X = TextureSet.NETHER_BRIDGE_END_X;
        TextureSet NETHER_BRIDGE_END_Z = TextureSet.NETHER_BRIDGE_END_Z;
        TextureSet NETHER_BRIDGE_GATE = TextureSet.NETHER_BRIDGE_GATE;
        TextureSet NETHER_TOWER = TextureSet.NETHER_TOWER;
        TextureSet NETHER_WALL = TextureSet.NETHER_WALL;
        TextureSet NETHER_HALL = TextureSet.NETHER_HALL;
        TextureSet NETHER_FORT_STAIRS = TextureSet.NETHER_FORT_STAIRS;
        TextureSet NETHER_THRONE = TextureSet.NETHER_THRONE;

        if (artist.equals("Golrith") || artist.equals("Both")) {
            currentArtist = "Golrith";

            ResourceLocation TILE_MOUNTAINS = tile("mountains");
            ResourceLocation TILE_MOUNTAINS2 = tile("mountains2");
            ResourceLocation TILE_MOUNTAINS3 = tile("mountains3");
            ResourceLocation TILE_MOUNTAINS4 = tile("mountains4");
            ResourceLocation TILE_SNOW_CAPS = tile("snow_caps");
            ResourceLocation TILE_WATER = tile("water");
            ResourceLocation TILE_WATER2 = tile("water2");
            ResourceLocation TILE_FOREST = tile("forest");
            ResourceLocation TILE_FOREST2 = tile("forest2");
            ResourceLocation TILE_FOREST3 = tile("forest3");
            ResourceLocation TILE_FOREST_HILLS = tile("forest_hills");
            ResourceLocation TILE_FOREST_HILLS2 = tile("forest_hills2");
            ResourceLocation TILE_FOREST_HILLS3 = tile("forest_hills3");
            ResourceLocation TILE_BIRCH = tile("birch");
            ResourceLocation TILE_BIRCH2 = tile("birch2");
            ResourceLocation TILE_BIRCH_HILLS = tile("birch_hills");
            ResourceLocation TILE_BIRCH_HILLS2 = tile("birch_hills2");
            ResourceLocation TILE_HILLS = tile("hills");
            ResourceLocation TILE_HILLS_BUSHES = tile("hills_bushes");
            ResourceLocation TILE_HILLS_CACTI = tile("hills_cacti");
            ResourceLocation TILE_PINES = tile("pines");
            ResourceLocation TILE_PINES2 = tile("pines2");
            ResourceLocation TILE_PINES3 = tile("pines3");
            ResourceLocation TILE_PINES_HILLS = tile("pines_hills");
            ResourceLocation TILE_PINES_HILLS2 = tile("pines_hills2");
            ResourceLocation TILE_PINES_HILLS3 = tile("pines_hills3");
            ResourceLocation TILE_MEGA_SPRUCE = tile("mega_spruce");
            ResourceLocation TILE_MEGA_SPRUCE2 = tile("mega_spruce2");
            ResourceLocation TILE_MEGA_TAIGA = tile("mega_taiga");
            ResourceLocation TILE_MEGA_TAIGA2 = tile("mega_taiga2");
            ResourceLocation TILE_MEGA_SPRUCE_HILLS = tile("mega_spruce_hills");
            ResourceLocation TILE_MEGA_SPRUCE_HILLS2 = tile("mega_spruce_hills2");
            ResourceLocation TILE_MEGA_TAIGA_HILLS = tile("mega_taiga_hills");
            ResourceLocation TILE_MEGA_TAIGA_HILLS2 = tile("mega_taiga_hills2");
            ResourceLocation TILE_SAND = tile("sand");
            ResourceLocation TILE_SAND2 = tile("sand2");
            ResourceLocation TILE_SAND3 = tile("sand3");
            ResourceLocation TILE_CACTI = tile("cacti");
            ResourceLocation TILE_SAND_BUSHES = tile("sand_bushes");
            ResourceLocation TILE_SHORE = tile("shore");
            ResourceLocation TILE_SHORE2 = tile("shore2");
            ResourceLocation TILE_ROCK_SHORE = tile("rock_shore");
            ResourceLocation TILE_SHORE3 = tile("shore3");
            ResourceLocation TILE_GRASS = tile("grass");
            ResourceLocation TILE_GRASS2 = tile("grass2");
            ResourceLocation TILE_GRASS3 = tile("grass3");
            ResourceLocation TILE_GRASS4 = tile("grass4");
            ResourceLocation TILE_SNOW = tile("snow");
            ResourceLocation TILE_SNOW1 = tile("snow1");
            ResourceLocation TILE_SNOW2 = tile("snow2");
            ResourceLocation TILE_SNOW3 = tile("snow3");
            ResourceLocation TILE_SNOW4 = tile("snow4");
            ResourceLocation TILE_SNOW5 = tile("snow5");
            ResourceLocation TILE_SNOW6 = tile("snow6");
            ResourceLocation TILE_SWAMP = tile("swamp");
            ResourceLocation TILE_SWAMP2 = tile("swamp2");
            ResourceLocation TILE_SWAMP3 = tile("swamp3");
            ResourceLocation TILE_SWAMP4 = tile("swamp4");
            ResourceLocation TILE_SWAMP5 = tile("swamp5");
            ResourceLocation TILE_SWAMP6 = tile("swamp6");
            ResourceLocation TILE_JUNGLE = tile("jungle");
            ResourceLocation TILE_JUNGLE2 = tile("jungle2");
            ResourceLocation TILE_JUNGLE_HILLS = tile("jungle_hills");
            ResourceLocation TILE_JUNGLE_HILLS2 = tile("jungle_hills2");
            ResourceLocation TILE_SAVANNA = tile("savanna");
            ResourceLocation TILE_SAVANNA2 = tile("savanna2");
            ResourceLocation TILE_SAVANNA3 = tile("savanna3");
            ResourceLocation TILE_LAVA = tile("lava");
            ResourceLocation TILE_LAVA2 = tile("lava2");

            WATER = overrideTileSet("WATER", TILE_WATER, TILE_WATER2);
            BIRCH = overrideTileSet("BIRCH", TILE_BIRCH, TILE_BIRCH2);
            BIRCH_HILLS = overrideTileSet("BIRCH_HILLS", TILE_BIRCH_HILLS, TILE_BIRCH_HILLS2);
            DESERT = overrideTileSet("DESERT", TILE_SAND, TILE_SAND, TILE_SAND2, TILE_SAND2, TILE_SAND3, TILE_SAND3, TILE_SAND_BUSHES, TILE_SAND_BUSHES, TILE_CACTI);
            DESERT_HILLS = overrideTileSet("DESERT_HILLS", TILE_HILLS, TILE_HILLS, TILE_HILLS, TILE_HILLS_BUSHES, TILE_HILLS_CACTI);
            FOREST = overrideTileSet("FOREST", TILE_FOREST, TILE_FOREST2, TILE_FOREST3);
            FOREST_HILLS = overrideTileSet("FOREST_HILLS", TILE_FOREST_HILLS, TILE_FOREST_HILLS2, TILE_FOREST_HILLS3);
            HILLS = overrideTileSet("HILLS", TILE_HILLS);
            JUNGLE = overrideTileSet("JUNGLE", TILE_JUNGLE, TILE_JUNGLE2);
            JUNGLE_HILLS = overrideTileSet("JUNGLE_HILLS", TILE_JUNGLE_HILLS, TILE_JUNGLE_HILLS2);
            LAVA = overrideTileSet("LAVA", TILE_LAVA, TILE_LAVA2);
            MEGA_SPRUCE = overrideTileSet("MEGA_SPRUCE", TILE_MEGA_SPRUCE, TILE_MEGA_SPRUCE2);
            MEGA_SPRUCE_HILLS = overrideTileSet("MEGA_SPRUCE_HILLS", TILE_MEGA_SPRUCE_HILLS, TILE_MEGA_SPRUCE_HILLS2);
            MEGA_TAIGA = overrideTileSet("MEGA_TAIGA", TILE_MEGA_TAIGA, TILE_MEGA_TAIGA2);
            MEGA_TAIGA_HILLS = overrideTileSet("MEGA_TAIGA_HILLS", TILE_MEGA_TAIGA_HILLS, TILE_MEGA_TAIGA_HILLS2);
            MOUNTAINS = overrideTileSet("MOUNTAINS", TILE_MOUNTAINS, TILE_MOUNTAINS, TILE_MOUNTAINS2, TILE_MOUNTAINS2, TILE_MOUNTAINS3, TILE_MOUNTAINS4);
            MOUNTAINS_ALL = overrideTileSet("MOUNTAINS_ALL", TILE_SNOW_CAPS, TILE_SNOW_CAPS, TILE_MOUNTAINS, TILE_MOUNTAINS2, TILE_MOUNTAINS3, TILE_MOUNTAINS4);
            MOUNTAINS_NAKED = overrideTileSet("MOUNTAINS_NAKED", TILE_MOUNTAINS, TILE_MOUNTAINS2);
            MOUNTAINS_SNOW_CAPS = overrideTileSet("MOUNTAINS_SNOW_CAPS", TILE_MOUNTAINS, TILE_SNOW_CAPS);
            PINES = overrideTileSet("PINES", TILE_PINES, TILE_PINES2, TILE_PINES3);
            PINES_HILLS = overrideTileSet("PINES_HILLS", TILE_PINES_HILLS, TILE_PINES_HILLS2, TILE_PINES_HILLS3);
            PLAINS = overrideTileSet("PLAINS", TILE_GRASS, TILE_GRASS2, TILE_GRASS3, TILE_GRASS4);
            ROCK_SHORE = overrideTileSet(new TextureSetShore("ROCK_SHORE", WATER, TILE_ROCK_SHORE));
            SAVANNA = overrideTileSet("SAVANNA", TILE_SAVANNA, TILE_SAVANNA2, TILE_SAVANNA3, TILE_GRASS, TILE_GRASS2, TILE_GRASS2, TILE_GRASS3, TILE_GRASS3, TILE_GRASS4, TILE_GRASS4);
            SHORE = overrideTileSet(new TextureSetShore("SHORE", WATER, TILE_SHORE, TILE_SHORE2, TILE_SHORE3));
            SNOW = overrideTileSet("SNOW", TILE_SNOW, TILE_SNOW, TILE_SNOW, TILE_SNOW, TILE_SNOW, TILE_SNOW1, TILE_SNOW1, TILE_SNOW1, TILE_SNOW2, TILE_SNOW2, TILE_SNOW2, TILE_SNOW3, TILE_SNOW4, TILE_SNOW5, TILE_SNOW6);
            SWAMP = overrideTileSet("SWAMP", TILE_SWAMP, TILE_SWAMP, TILE_SWAMP, TILE_SWAMP2, TILE_SWAMP3, TILE_SWAMP4, TILE_SWAMP5, TILE_SWAMP6);
        }
        
        if (artist.equals("Artsy") || artist.equals("Both")) {
            currentArtist = "Artsy";

            ResourceLocation TILE_MOUNTAINS = tile("mountains");
            ResourceLocation TILE_MOUNTAINS2 = tile("mountains2");
            ResourceLocation TILE_MOUNTAINS3 = tile("mountains3");
            ResourceLocation TILE_MOUNTAINS4 = tile("mountains4");
            ResourceLocation TILE_SNOW_CAPS = tile("snow_caps");
            ResourceLocation TILE_SNOW_HILLS = tile("snow_hills");
            ResourceLocation TILE_SNOW_HILLS2 = tile("snow_hills2");
            ResourceLocation TILE_WATER = tile("water");
            ResourceLocation TILE_WATER2 = tile("water2");
            ResourceLocation TILE_ICE_NOBORDER = tile("ice_noborder");
            ResourceLocation TILE_FOREST = tile("forest");
            ResourceLocation TILE_FOREST2 = tile("forest2");
            ResourceLocation TILE_FOREST3 = tile("forest3");
            ResourceLocation TILE_FOREST_HILLS = tile("forest_hills");
            ResourceLocation TILE_FOREST_HILLS2 = tile("forest_hills2");
            ResourceLocation TILE_FOREST_HILLS3 = tile("forest_hills3");
            ResourceLocation TILE_FOREST_FLOWERS = tile("forest_flowers");
            ResourceLocation TILE_FOREST_FLOWERS2 = tile("forest_flowers2");
            ResourceLocation TILE_FOREST_FLOWERS3 = tile("forest_flowers3");
            ResourceLocation TILE_DENSE_FOREST = tile("dense_forest");
            ResourceLocation TILE_DENSE_FOREST2 = tile("dense_forest2");
            ResourceLocation TILE_DENSE_FOREST_HILLS = tile("dense_forest_hills");
            ResourceLocation TILE_DENSE_FOREST_HILLS2 = tile("dense_forest_hills2");
            ResourceLocation TILE_SPARSE_FOREST = tile("forest_sparse");
            ResourceLocation TILE_SPARSE_FOREST2 = tile("forest_sparse2");
            ResourceLocation TILE_SPARSE_FOREST3 = tile("forest_sparse3");
            ResourceLocation TILE_SPARSE_FOREST_HILLS = tile("forest_sparse_hills");
            ResourceLocation TILE_SPARSE_FOREST_HILLS2 = tile("forest_sparse_hills2");
            ResourceLocation TILE_SPARSE_FOREST_HILLS3 = tile("forest_sparse_hills3");
            ResourceLocation TILE_BIRCH = tile("birch");
            ResourceLocation TILE_BIRCH2 = tile("birch2");
            ResourceLocation TILE_BIRCH_HILLS = tile("birch_hills");
            ResourceLocation TILE_BIRCH_HILLS2 = tile("birch_hills2");
            ResourceLocation TILE_TALL_BIRCH = tile("tall_birch");
            ResourceLocation TILE_TALL_BIRCH2 = tile("tall_birch2");
            ResourceLocation TILE_TALL_BIRCH_HILLS = tile("tall_birch_hills");
            ResourceLocation TILE_TALL_BIRCH_HILLS2 = tile("tall_birch_hills2");
            ResourceLocation TILE_DENSE_BIRCH = tile("dense_birch");
            ResourceLocation TILE_HILLS = tile("hills");
            ResourceLocation TILE_HILLS_BUSHES = tile("hills_bushes");
            ResourceLocation TILE_HILLS_CACTI = tile("hills_cacti");
            ResourceLocation TILE_HILLS_GRASS = tile("hills_grass");
            ResourceLocation TILE_PINES = tile("pines");
            ResourceLocation TILE_PINES2 = tile("pines2");
            ResourceLocation TILE_PINES3 = tile("pines3");
            ResourceLocation TILE_PINES_HILLS = tile("pines_hills");
            ResourceLocation TILE_PINES_HILLS2 = tile("pines_hills2");
            ResourceLocation TILE_PINES_HILLS3 = tile("pines_hills3");
            ResourceLocation TILE_MEGA_SPRUCE = tile("mega_spruce");
            ResourceLocation TILE_MEGA_SPRUCE2 = tile("mega_spruce2");
            ResourceLocation TILE_MEGA_TAIGA = tile("mega_taiga");
            ResourceLocation TILE_MEGA_TAIGA2 = tile("mega_taiga2");
            ResourceLocation TILE_MEGA_SPRUCE_HILLS = tile("mega_spruce_hills");
            ResourceLocation TILE_MEGA_SPRUCE_HILLS2 = tile("mega_spruce_hills2");
            ResourceLocation TILE_MEGA_TAIGA_HILLS = tile("mega_taiga_hills");
            ResourceLocation TILE_MEGA_TAIGA_HILLS2 = tile("mega_taiga_hills2");
            ResourceLocation TILE_SAND = tile("sand");
            ResourceLocation TILE_SAND2 = tile("sand2");
            ResourceLocation TILE_SAND3 = tile("sand3");
            ResourceLocation TILE_CACTI = tile("cacti");
            ResourceLocation TILE_SAND_BUSHES = tile("sand_bushes");
            ResourceLocation TILE_SHORE = tile("shore");
            ResourceLocation TILE_SHORE2 = tile("shore2");
            ResourceLocation TILE_ROCK_SHORE = tile("rock_shore");
            ResourceLocation TILE_SHORE3 = tile("shore3");
            ResourceLocation TILE_GRASS = tile("grass");
            ResourceLocation TILE_GRASS2 = tile("grass2");
            ResourceLocation TILE_GRASS3 = tile("grass3");
            ResourceLocation TILE_GRASS4 = tile("grass4");
            ResourceLocation TILE_SUNFLOWERS = tile("sunflowers");
            ResourceLocation TILE_SUNFLOWERS2 = tile("sunflowers2");
            ResourceLocation TILE_SNOW = tile("snow");
            ResourceLocation TILE_SNOW1 = tile("snow1");
            ResourceLocation TILE_SNOW2 = tile("snow2");
            ResourceLocation TILE_SNOW3 = tile("snow3");
            ResourceLocation TILE_SNOW4 = tile("snow4");
            ResourceLocation TILE_SNOW5 = tile("snow5");
            ResourceLocation TILE_SNOW6 = tile("snow6");
            ResourceLocation TILE_SNOW_PINES = tile("snow_pines");
            ResourceLocation TILE_SNOW_PINES2 = tile("snow_pines2");
            ResourceLocation TILE_SNOW_PINES3 = tile("snow_pines3");
            ResourceLocation TILE_SNOW_PINES_HILLS = tile("snow_pines_hills");
            ResourceLocation TILE_SNOW_PINES_HILLS2 = tile("snow_pines_hills2");
            ResourceLocation TILE_SNOW_PINES_HILLS3 = tile("snow_pines_hills3");
            ResourceLocation TILE_ICE_SPIKES = tile("ice_spikes");
            ResourceLocation TILE_ICE_SPIKES2 = tile("ice_spikes2");
            ResourceLocation TILE_SWAMP = tile("swamp");
            ResourceLocation TILE_SWAMP2 = tile("swamp2");
            ResourceLocation TILE_SWAMP3 = tile("swamp3");
            ResourceLocation TILE_SWAMP4 = tile("swamp4");
            ResourceLocation TILE_SWAMP5 = tile("swamp5");
            ResourceLocation TILE_SWAMP6 = tile("swamp6");
            ResourceLocation TILE_SWAMP_HILLS = tile("swamp_hills");
            ResourceLocation TILE_SWAMP_HILLS2 = tile("swamp_hills2");
            ResourceLocation TILE_SWAMP_HILLS3 = tile("swamp_hills3");
            ResourceLocation TILE_SWAMP_HILLS4 = tile("swamp_hills4");
            ResourceLocation TILE_SWAMP_HILLS5 = tile("swamp_hills5");
            ResourceLocation TILE_MUSHROOM = tile("mushroom");
            ResourceLocation TILE_MUSHROOM2 = tile("mushroom2");
            ResourceLocation TILE_JUNGLE = tile("jungle");
            ResourceLocation TILE_JUNGLE2 = tile("jungle2");
            ResourceLocation TILE_JUNGLE_HILLS = tile("jungle_hills");
            ResourceLocation TILE_JUNGLE_HILLS2 = tile("jungle_hills2");
            ResourceLocation TILE_JUNGLE_EDGE = tile("jungle_edge");
            ResourceLocation TILE_JUNGLE_EDGE2 = tile("jungle_edge2");
            ResourceLocation TILE_JUNGLE_EDGE3 = tile("jungle_edge3");
            ResourceLocation TILE_JUNGLE_EDGE_HILLS = tile("jungle_edge_hills");
            ResourceLocation TILE_JUNGLE_EDGE_HILLS2 = tile("jungle_edge_hills2");
            ResourceLocation TILE_JUNGLE_EDGE_HILLS3 = tile("jungle_edge_hills3");
            ResourceLocation TILE_JUNGLE_CLIFFS = tile("jungle_cliffs");
            ResourceLocation TILE_JUNGLE_CLIFFS2 = tile("jungle_cliffs2");
            ResourceLocation TILE_BUSHES_CLIFFS = tile("bushes_cliffs");
            ResourceLocation TILE_CLIFFS = tile("cliffs");
            ResourceLocation TILE_SAVANNA = tile("savanna");
            ResourceLocation TILE_SAVANNA2 = tile("savanna2");
            ResourceLocation TILE_SAVANNA3 = tile("savanna3");
            ResourceLocation TILE_SAVANNA_CLIFFS = tile("savanna_cliffs");
            ResourceLocation TILE_SAVANNA_CLIFFS2 = tile("savanna_cliffs2");
            ResourceLocation TILE_SAVANNA_CLIFFS3 = tile("savanna_cliffs3");
            ResourceLocation TILE_CLIFFS_CLOUDS = tile("cliffs_clouds");
            ResourceLocation TILE_SAVANNA_CLIFFS_CLOUDS = tile("savanna_cliffs_clouds");
            ResourceLocation TILE_SAVANNA_CLIFFS_CLOUDS2 = tile("savanna_cliffs_clouds2");
            ResourceLocation TILE_SAVANNA_CLIFFS_CLOUDS3 = tile("savanna_cliffs_clouds3");
            ResourceLocation TILE_MESA = tile("mesa");
            ResourceLocation TILE_MESA2 = tile("mesa2");
            ResourceLocation TILE_MESA3 = tile("mesa3");
            ResourceLocation TILE_MESA4 = tile("mesa4");
            ResourceLocation TILE_BRYCE = tile("bryce");
            ResourceLocation TILE_BRYCE2 = tile("bryce2");
            ResourceLocation TILE_BRYCE3 = tile("bryce3");
            ResourceLocation TILE_BRYCE4 = tile("bryce4");
            ResourceLocation TILE_PLATEAU_MESA = tile("plateau_mesa");
            ResourceLocation TILE_PLATEAU_MESA2 = tile("plateau_mesa2");
            ResourceLocation TILE_PLATEAU_MESA_LOW = tile("plateau_mesa_low");
            ResourceLocation TILE_PLATEAU_MESA_LOW2 = tile("plateau_mesa_low2");
            ResourceLocation TILE_PLATEAU_TREES = tile("plateau_trees");
            ResourceLocation TILE_PLATEAU_TREES_LOW = tile("plateau_trees_low");
            ResourceLocation TILE_PLATEAU_GRASS = tile("plateau_grass");
            ResourceLocation TILE_PLATEAU_GRASS2 = tile("plateau_grass2");
            ResourceLocation TILE_PLATEAU_GRASS3 = tile("plateau_grass3");
            ResourceLocation TILE_PLATEAU_SAVANNA = tile("plateau_savanna");
            ResourceLocation TILE_PLATEAU_SAVANNA2 = tile("plateau_savanna2");
            ResourceLocation TILE_PLATEAU_SAVANNA3 = tile("plateau_savanna3");
            ResourceLocation TILE_CAVE_WALLS = tile("cave_walls");
            ResourceLocation TILE_RAVINE = tile("ravine");
            ResourceLocation TILE_LAVA = tile("lava");
            ResourceLocation TILE_LAVA2 = tile("lava2");
            ResourceLocation TILE_LAVA_SHORE = tile("lava_shore");
            ResourceLocation TILE_LAVA_SHORE2 = tile("lava_shore2");
            ResourceLocation TILE_END_VOID = tile("end_void");
            ResourceLocation TILE_END_ISLAND = tile("end_island");
            ResourceLocation TILE_END_ISLAND2 = tile("end_island2");
            ResourceLocation TILE_END_ISLAND_PLANTS = tile("end_island_plants");
            ResourceLocation TILE_END_ISLAND_PLANTS2 = tile("end_island_plants2");
            ResourceLocation TILE_HOUSE = tile("house");
            ResourceLocation TILE_FENCE = tile("fence");
            ResourceLocation TILE_LIBRARY = tile("library");
            ResourceLocation TILE_SMITHY = tile("smithy");
            ResourceLocation TILE_L_HOUSE = tile("l_house");
            ResourceLocation TILE_FARMLAND_SMALL = tile("farmland_s");
            ResourceLocation TILE_FARMLAND_LARGE = tile("farmland_l");
            ResourceLocation TILE_VILLAGE_TORCH = tile("village_torch");
            ResourceLocation TILE_WELL = tile("well");
            ResourceLocation TILE_HUT = tile("hut");
            ResourceLocation TILE_HOUSE_SMALL = tile("house_small");
            ResourceLocation TILE_BUTCHERS_SHOP = tile("butchers_shop");
            ResourceLocation TILE_CHURCH = tile("church");
            ResourceLocation TILE_NETHER_BRIDGE = tile("nether_bridge");
            ResourceLocation TILE_NETHER_BRIDGE_X = tile("nether_bridge_x");
            ResourceLocation TILE_NETHER_BRIDGE_Z = tile("nether_bridge_z");
            ResourceLocation TILE_NETHER_BRIDGE_END_X = tile("nether_bridge_end_x");
            ResourceLocation TILE_NETHER_BRIDGE_END_Z = tile("nether_bridge_end_z");
            ResourceLocation TILE_NETHER_BRIDGE_GATE = tile("nether_bridge_gate");
            ResourceLocation TILE_NETHER_TOWER = tile("nether_tower");
            ResourceLocation TILE_NETHER_WALL = tile("nether_wall");
            ResourceLocation TILE_NETHER_HALL = tile("nether_hall");
            ResourceLocation TILE_NETHER_FORT_STAIRS = tile("nether_fort_stairs");
            ResourceLocation TILE_NETHER_THRONE = tile("nether_throne");

            WATER = overrideTileSet("WATER", TILE_WATER, TILE_WATER2);
            BIRCH = overrideTileSet("BIRCH", TILE_BIRCH, TILE_BIRCH2);
            BIRCH_HILLS = overrideTileSet("BIRCH_HILLS", TILE_BIRCH_HILLS, TILE_BIRCH_HILLS2);
            BRYCE = overrideTileSet("BRYCE", TILE_BRYCE, TILE_BRYCE2, TILE_BRYCE3, TILE_BRYCE4);
            CAVE_WALLS = overrideTileSet("CAVE_WALLS", TILE_CAVE_WALLS);
            DENSE_BIRCH = overrideTileSet("DENSE_BIRCH", TILE_DENSE_BIRCH);
            DENSE_FOREST = overrideTileSet("DENSE_FOREST", TILE_DENSE_FOREST, TILE_DENSE_FOREST2);
            DENSE_FOREST_HILLS = overrideTileSet("DENSE_FOREST_HILLS", TILE_DENSE_FOREST_HILLS, TILE_DENSE_FOREST_HILLS2);
            DESERT = overrideTileSet("DESERT", TILE_SAND, TILE_SAND, TILE_SAND2, TILE_SAND2, TILE_SAND3, TILE_SAND3, TILE_SAND_BUSHES, TILE_SAND_BUSHES, TILE_CACTI);
            DESERT_HILLS = overrideTileSet("DESERT_HILLS", TILE_HILLS, TILE_HILLS, TILE_HILLS, TILE_HILLS_BUSHES, TILE_HILLS_CACTI);
            END_ISLAND = overrideTileSet("END_ISLAND", TILE_END_ISLAND, TILE_END_ISLAND2);
            END_ISLAND_PLANTS = overrideTileSet("END_ISLAND_PLANTS", TILE_END_ISLAND_PLANTS, TILE_END_ISLAND_PLANTS2);
            END_VOID = overrideTileSet("END_VOID", TILE_END_VOID);
            FOREST = overrideTileSet("FOREST", TILE_FOREST, TILE_FOREST2, TILE_FOREST3);
            FOREST_FLOWERS = overrideTileSet("FOREST_FLOWERS", TILE_FOREST_FLOWERS, TILE_FOREST_FLOWERS2, TILE_FOREST_FLOWERS3);
            FOREST_HILLS = overrideTileSet("FOREST_HILLS", TILE_FOREST_HILLS, TILE_FOREST_HILLS2, TILE_FOREST_HILLS3);
            HILLS = overrideTileSet("HILLS", TILE_HILLS);
            ICE = overrideTileSet("ICE", TILE_ICE_NOBORDER);
            ICE_SPIKES = overrideTileSet("ICE_SPIKES", TILE_ICE_SPIKES, TILE_ICE_SPIKES2);
            JUNGLE = overrideTileSet("JUNGLE", TILE_JUNGLE, TILE_JUNGLE2);
            JUNGLE_CLIFFS = overrideTileSet("JUNGLE_CLIFFS", TILE_JUNGLE_CLIFFS, TILE_JUNGLE_CLIFFS2, TILE_BUSHES_CLIFFS);
            JUNGLE_EDGE = overrideTileSet("JUNGLE_EDGE", TILE_JUNGLE_EDGE, TILE_JUNGLE_EDGE2, TILE_JUNGLE_EDGE3, TILE_GRASS2, TILE_GRASS3, TILE_GRASS4);
            JUNGLE_EDGE_HILLS = overrideTileSet("JUNGLE_EDGE_HILLS", TILE_JUNGLE_EDGE_HILLS, TILE_JUNGLE_EDGE_HILLS2, TILE_JUNGLE_EDGE_HILLS3, TILE_HILLS_GRASS, TILE_HILLS_GRASS);
            JUNGLE_HILLS = overrideTileSet("JUNGLE_HILLS", TILE_JUNGLE_HILLS, TILE_JUNGLE_HILLS2);
            LAVA = overrideTileSet("LAVA", TILE_LAVA, TILE_LAVA2);
            LAVA_SHORE = overrideTileSet(new TextureSetShore("LAVA_SHORE", LAVA, TILE_LAVA_SHORE, TILE_LAVA_SHORE2));
            MEGA_SPRUCE = overrideTileSet("MEGA_SPRUCE", TILE_MEGA_SPRUCE, TILE_MEGA_SPRUCE2);
            MEGA_SPRUCE_HILLS = overrideTileSet("MEGA_SPRUCE_HILLS", TILE_MEGA_SPRUCE_HILLS, TILE_MEGA_SPRUCE_HILLS2);
            MEGA_TAIGA = overrideTileSet("MEGA_TAIGA", TILE_MEGA_TAIGA, TILE_MEGA_TAIGA2);
            MEGA_TAIGA_HILLS = overrideTileSet("MEGA_TAIGA_HILLS", TILE_MEGA_TAIGA_HILLS, TILE_MEGA_TAIGA_HILLS2);
            MESA = overrideTileSet("MESA", TILE_MESA, TILE_MESA2, TILE_MESA3, TILE_MESA4, TILE_SAND_BUSHES);
            MOUNTAINS = overrideTileSet("MOUNTAINS", TILE_MOUNTAINS, TILE_MOUNTAINS, TILE_MOUNTAINS2, TILE_MOUNTAINS2, TILE_MOUNTAINS3, TILE_MOUNTAINS4);
            MOUNTAINS_ALL = overrideTileSet("MOUNTAINS_ALL", TILE_SNOW_CAPS, TILE_SNOW_CAPS, TILE_MOUNTAINS, TILE_MOUNTAINS2, TILE_MOUNTAINS3, TILE_MOUNTAINS4);
            MOUNTAINS_NAKED = overrideTileSet("MOUNTAINS_NAKED", TILE_MOUNTAINS, TILE_MOUNTAINS2);
            MOUNTAINS_SNOW_CAPS = overrideTileSet("MOUNTAINS_SNOW_CAPS", TILE_MOUNTAINS, TILE_SNOW_CAPS);
            MUSHROOM = overrideTileSet("MUSHROOM", TILE_MUSHROOM, TILE_MUSHROOM2);
            PINES = overrideTileSet("PINES", TILE_PINES, TILE_PINES2, TILE_PINES3);
            PINES_HILLS = overrideTileSet("PINES_HILLS", TILE_PINES_HILLS, TILE_PINES_HILLS2, TILE_PINES_HILLS3);
            PLAINS = overrideTileSet("PLAINS", TILE_GRASS, TILE_GRASS2, TILE_GRASS3, TILE_GRASS4);
            PLATEAU_MESA = overrideTileSet("PLATEAU_MESA", TILE_PLATEAU_MESA, TILE_PLATEAU_MESA2);
            PLATEAU_MESA_LOW = overrideTileSet("PLATEAU_MESA_LOW", TILE_PLATEAU_MESA_LOW, TILE_PLATEAU_MESA_LOW2);
            PLATEAU_MESA_TREES = overrideTileSet("PLATEAU_MESA_TREES", TILE_PLATEAU_MESA, TILE_PLATEAU_MESA2, TILE_PLATEAU_TREES);
            PLATEAU_MESA_TREES_LOW = overrideTileSet("PLATEAU_MESA_TREES_LOW", TILE_PLATEAU_MESA_LOW, TILE_PLATEAU_MESA_LOW2, TILE_PLATEAU_TREES_LOW);
            PLATEAU_SAVANNA = overrideTileSet("PLATEAU_SAVANNA", TILE_PLATEAU_GRASS, TILE_PLATEAU_GRASS, TILE_PLATEAU_GRASS2, TILE_PLATEAU_GRASS2, TILE_PLATEAU_GRASS3, TILE_PLATEAU_GRASS3, TILE_PLATEAU_SAVANNA, TILE_PLATEAU_SAVANNA2, TILE_PLATEAU_SAVANNA3);
            PLATEAU_SAVANNA_M = overrideTileSet("PLATEAU_SAVANNA_M", TILE_PLATEAU_GRASS, TILE_PLATEAU_GRASS2, TILE_PLATEAU_GRASS3, TILE_PLATEAU_SAVANNA2, TILE_PLATEAU_SAVANNA3, TILE_CLIFFS_CLOUDS, TILE_SAVANNA_CLIFFS_CLOUDS, TILE_SAVANNA_CLIFFS_CLOUDS2, TILE_SAVANNA_CLIFFS_CLOUDS3);
            RAVINE = overrideTileSet("RAVINE", TILE_RAVINE);
            ROCK_SHORE = overrideTileSet(new TextureSetShore("ROCK_SHORE", WATER, TILE_ROCK_SHORE));
            SAVANNA = overrideTileSet("SAVANNA", TILE_SAVANNA, TILE_SAVANNA2, TILE_SAVANNA3, TILE_GRASS, TILE_GRASS2, TILE_GRASS2, TILE_GRASS3, TILE_GRASS3, TILE_GRASS4, TILE_GRASS4);
            SAVANNA_CLIFFS = overrideTileSet("SAVANNA_CLIFFS", TILE_SAVANNA_CLIFFS, TILE_SAVANNA_CLIFFS2, TILE_SAVANNA_CLIFFS3, TILE_CLIFFS);
            SHORE = overrideTileSet(new TextureSetShore("SHORE", WATER, TILE_SHORE, TILE_SHORE2, TILE_SHORE3));
            SNOW = overrideTileSet("SNOW", TILE_SNOW, TILE_SNOW, TILE_SNOW, TILE_SNOW, TILE_SNOW, TILE_SNOW1, TILE_SNOW1, TILE_SNOW1, TILE_SNOW2, TILE_SNOW2, TILE_SNOW2, TILE_SNOW3, TILE_SNOW4, TILE_SNOW5, TILE_SNOW6);
            SNOW_HILLS = overrideTileSet("SNOW_HILLS", TILE_SNOW_HILLS, TILE_SNOW_HILLS2);
            SNOW_PINES = overrideTileSet("SNOW_PINES", TILE_SNOW_PINES, TILE_SNOW_PINES2, TILE_SNOW_PINES3);
            SNOW_PINES_HILLS = overrideTileSet("SNOW_PINES_HILLS", TILE_SNOW_PINES_HILLS, TILE_SNOW_PINES_HILLS2, TILE_SNOW_PINES_HILLS3);
            SPARSE_FOREST = overrideTileSet("SPARSE_FOREST", TILE_SPARSE_FOREST, TILE_SPARSE_FOREST2, TILE_SPARSE_FOREST3);
            SPARSE_FOREST_HILLS = overrideTileSet("SPARSE_FOREST_HILLS", TILE_SPARSE_FOREST_HILLS, TILE_SPARSE_FOREST_HILLS2, TILE_SPARSE_FOREST_HILLS3);
            SUNFLOWERS = overrideTileSet("SUNFLOWERS", TILE_SUNFLOWERS, TILE_SUNFLOWERS2, TILE_GRASS3, TILE_GRASS4);
            SWAMP = overrideTileSet("SWAMP", TILE_SWAMP, TILE_SWAMP, TILE_SWAMP, TILE_SWAMP2, TILE_SWAMP3, TILE_SWAMP4, TILE_SWAMP5, TILE_SWAMP6);
            SWAMP_HILLS = overrideTileSet("SWAMP_HILLS", TILE_SWAMP_HILLS, TILE_SWAMP_HILLS2, TILE_SWAMP_HILLS3, TILE_SWAMP_HILLS4, TILE_SWAMP_HILLS5);
            TALL_BIRCH = overrideTileSet("TALL_BIRCH", TILE_TALL_BIRCH, TILE_TALL_BIRCH2);
            TALL_BIRCH_HILLS = overrideTileSet("TALL_BIRCH_HILLS", TILE_TALL_BIRCH_HILLS, TILE_TALL_BIRCH_HILLS2);

            HOUSE = overrideTileSet("HOUSE", TILE_HOUSE);
            BUTCHERS_SHOP = overrideTileSet("BUTCHERS_SHOP", TILE_BUTCHERS_SHOP);
            CHURCH = overrideTileSet("CHURCH", TILE_CHURCH);
            FARMLAND_LARGE = overrideTileSet("FARMLAND_LARGE", TILE_FARMLAND_LARGE);
            FARMLAND_SMALL = overrideTileSet("FARMLAND_SMALL", TILE_FARMLAND_SMALL);
            FENCE = overrideTileSet("FENCE", TILE_FENCE).stitchTo(HOUSE);
            HOUSE_SMALL = overrideTileSet("HOUSE_SMALL", TILE_HOUSE_SMALL);
            HUT = overrideTileSet("HUT", TILE_HUT);
            L_HOUSE = overrideTileSet("L-HOUSE", TILE_L_HOUSE);
            LIBRARY = overrideTileSet("LIBRARY", TILE_LIBRARY);
            SMITHY = overrideTileSet("SMITHY", TILE_SMITHY);
            VILLAGE_TORCH = overrideTileSet("VILLAGE_TORCH", TILE_VILLAGE_TORCH);
            WELL = overrideTileSet("WELL", TILE_WELL);

            NETHER_BRIDGE = overrideTileSet("NETHER_BRIDGE", TILE_NETHER_BRIDGE);
            NETHER_BRIDGE_X = overrideTileSet("NETHER_BRIDGE_X", TILE_NETHER_BRIDGE_X);
            NETHER_BRIDGE_Z = overrideTileSet("NETHER_BRIDGE_Z", TILE_NETHER_BRIDGE_Z);
            NETHER_BRIDGE_END_X = overrideTileSet("NETHER_BRIDGE_END_X", TILE_NETHER_BRIDGE_END_X);
            NETHER_BRIDGE_END_Z = overrideTileSet("NETHER_BRIDGE_END_Z", TILE_NETHER_BRIDGE_END_Z);
            NETHER_BRIDGE_GATE = overrideTileSet("NETHER_BRIDGE_GATE", TILE_NETHER_BRIDGE_GATE);
            NETHER_TOWER = overrideTileSet("NETHER_TOWER", TILE_NETHER_TOWER);
            NETHER_WALL = overrideTileSet("NETHER_WALL", TILE_NETHER_WALL);
            NETHER_HALL = overrideTileSet("NETHER_HALL", TILE_NETHER_HALL);
            NETHER_FORT_STAIRS = overrideTileSet("NETHER_FORT_STAIRS", TILE_NETHER_FORT_STAIRS);
            NETHER_THRONE = overrideTileSet("NETHER_THRONE", TILE_NETHER_THRONE);
        }

        TextureSet.stitchMutually(PLAINS, SUNFLOWERS);
        WATER.stitchTo(SHORE, ROCK_SHORE, SWAMP);
        LAVA.stitchTo(LAVA_SHORE);
        SWAMP.stitchTo(SWAMP_HILLS);
        SNOW.stitchTo(SNOW_PINES, SNOW_HILLS, ICE_SPIKES, SNOW_PINES_HILLS);
        SNOW_PINES.stitchTo(SNOW, SNOW_HILLS, ICE_SPIKES, SNOW_PINES_HILLS);
        TextureSet.stitchMutually(MOUNTAINS, MOUNTAINS_NAKED, MOUNTAINS_SNOW_CAPS, MOUNTAINS_ALL);
        DESERT.stitchTo(MESA, BRYCE);
        TextureSet.stitchMutually(PLATEAU_MESA, PLATEAU_MESA_TREES, PLATEAU_SAVANNA, PLATEAU_SAVANNA_M);
        TextureSet.stitchMutually(PLATEAU_MESA_LOW, PLATEAU_MESA_TREES_LOW);
        TextureSet.stitchMutually(END_ISLAND, END_ISLAND_PLANTS);
        SNOW.stitchTo(RAVINE, LAVA);
        LAVA.stitchTo(NETHER_BRIDGE, NETHER_BRIDGE_GATE, NETHER_TOWER, NETHER_WALL, NETHER_HALL, NETHER_FORT_STAIRS, NETHER_BRIDGE_X, NETHER_BRIDGE_END_X, NETHER_BRIDGE_Z, NETHER_BRIDGE_END_Z);
        TextureSet.stitchMutuallyHorizontally(NETHER_BRIDGE, NETHER_BRIDGE_GATE, NETHER_TOWER, NETHER_HALL, NETHER_FORT_STAIRS, NETHER_THRONE, NETHER_BRIDGE_X, NETHER_BRIDGE_END_X);
        TextureSet.stitchMutuallyVertically(NETHER_BRIDGE, NETHER_BRIDGE_GATE, NETHER_TOWER, NETHER_HALL, NETHER_FORT_STAIRS, NETHER_THRONE, NETHER_BRIDGE_Z, NETHER_BRIDGE_END_Z);
        TextureSet.stitchMutuallyHorizontally(NETHER_WALL, NETHER_HALL, NETHER_FORT_STAIRS);
        TextureSet.stitchMutuallyVertically(NETHER_WALL, NETHER_HALL, NETHER_FORT_STAIRS);

        if(ROCK_SHORE != TextureSet.ROCK_SHORE) ((TextureSetAccessor) ROCK_SHORE).invokeStitchesToNull();
        if(LAVA_SHORE != TextureSet.LAVA_SHORE) ((TextureSetAccessor) LAVA_SHORE).invokeStitchesToNull();
    }
    
    private static ResourceLocation tile(String fileName) {
        return new ResourceLocation("antiqueatlas:textures/gui/tiles/"+ currentArtist+ "/vanilla/" + fileName + ".png");
    }

    public static class TextureSetShore extends TextureSet {
        private final TextureSet water;

        public TextureSetShore(String name, TextureSet water, ResourceLocation... textures) {
            super(true, name, textures);
            this.water = water;
        }

        @Override
        public boolean shouldStitchToHorizontally(TextureSet otherSet) {
            return otherSet == this || !this.water.shouldStitchToHorizontally(otherSet);
        }

        @Override
        public boolean shouldStitchToVertically(TextureSet otherSet) {
            return otherSet == this || !this.water.shouldStitchToVertically(otherSet);
        }
    }
}
