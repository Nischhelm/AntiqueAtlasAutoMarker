package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.ConfigHandler;
import com.google.common.base.Predicates;
import hunternif.mc.atlas.client.BiomeTextureMap;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.client.TextureSetMap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.google.common.base.Predicate;

public class BiomeTileConfig {
    @Config.Comment("If a biome doesn't have BiomeTextureSets registered, its biome dictionary types will be used to assign it a textureset. \n" +
            "The rules provided here are matched against the given types in order. The first rule that matches will be used." +
            "This order means that rules with more rare sets of biometypes need to be defined farther up, it gets more generic farther down.\n" +
            "Pattern: type1, type2, type3 = textureset\n" +
            "Where \"|\" denotes \"OR\" and \",\" is used as \"AND\".\n" +
            "Available biomedict types: HOT, COLD, SPARSE, DENSE, WET, DRY, SAVANNA, CONIFEROUS, JUNGLE, SPOOKY, DEAD, LUSH, NETHER, END, MUSHROOM, MAGICAL, RARE, OCEAN, RIVER, WATER, MESA, FOREST, PLAINS, MOUNTAIN, HILLS, SWAMP, SANDY, SNOWY, WASTELAND, BEACH, VOID\n" +
            "Available biome texturesets: ICE, SHORE, ROCK_SHORE, DESERT, PLAINS, SUNFLOWERS, HILLS, DESERT_HILLS, ICE_SPIKES, SNOW_PINES, SNOW_PINES_HILLS, SNOW_HILLS, SNOW, MOUNTAINS_NAKED, MOUNTAINS, MOUNTAINS_SNOW_CAPS, MOUNTAINS_ALL, FOREST, FOREST_HILLS, FOREST_FLOWERS, DENSE_FOREST, DENSE_FOREST_HILLS, BIRCH, BIRCH_HILLS, TALL_BIRCH, TALL_BIRCH_HILLS, DENSE_BIRCH, JUNGLE, JUNGLE_HILLS, JUNGLE_CLIFFS, JUNGLE_EDGE, JUNGLE_EDGE_HILLS, PINES, PINES_HILLS, SAVANNA, SAVANNA_CLIFFS, PLATEAU_SAVANNA_M, MESA, BRYCE, PLATEAU_MESA, PLATEAU_MESA_LOW, PLATEAU_MESA_TREES, PLATEAU_MESA_TREES_LOW, PLATEAU_SAVANNA, MEGA_SPRUCE, MEGA_SPRUCE_HILLS, MEGA_TAIGA, MEGA_TAIGA_HILLS, SWAMP, SWAMP_HILLS, MUSHROOM, WATER, LAVA, LAVA_SHORE, CAVE_WALLS, RAVINE, END_ISLAND, END_ISLAND_PLANTS, END_VOID\n" +
            "NOTE: by default, this is using the same rules that Antique Atlas is using.")
    @Config.Name("Automatic Biometype Rules")
    @Config.RequiresMcRestart
    public String[] automaticTypeRules = {
            "SWAMP, HILLS = SWAMP_HILLS",
            "SWAMP = SWAMP",
            "WATER|RIVER, FOREST|JUNGLE, HILLS = SWAMP_HILLS",
            "WATER|RIVER, FOREST|JUNGLE = SWAMP",
            "WATER|RIVER, SNOWY = ICE",
            "WATER|RIVER = WATER",
            "BEACH, MOUNTAIN = ROCK_SHORE",
            "BEACH = SHORE",
            "JUNGLE, MOUNTAIN = JUNGLE_CLIFFS",
            "JUNGLE, HILLS = JUNGLE_HILLS",
            "JUNGLE = JUNGLE",
            "SAVANNA, MOUNTAIN|HILLS = SAVANNA_CLIFFS",
            "SAVANNA = SAVANNA",
            "CONIFEROUS, MOUNTAIN|HILLS = PINES_HILLS",
            "CONIFEROUS = PINES",
            "MESA, FOREST = PLATEAU_MESA_TREES",
            "MESA = PLATEAU_MESA",
            "FOREST, SNOWY, HILLS = SNOW_PINES_HILLS",
            "FOREST, SNOWY = SNOW_PINES",
            "FOREST, SPARSE, HILLS = SPARSE_FOREST_HILLS",
            "FOREST, SPARSE = SPARSE_FOREST",
            "FOREST, DENSE, HILLS = DENSE_FOREST_HILLS",
            "FOREST, DENSE = DENSE_FOREST",
            "FOREST, HILLS = FOREST_HILLS",
            "FOREST = FOREST",
            "PLAINS|WASTELAND, SNOWY|COLD, MOUNTAIN = MOUNTAINS_SNOW_CAPS",
            "PLAINS|WASTELAND, SNOWY|COLD, HILLS = SNOW_HILLS",
            "PLAINS|WASTELAND, SNOWY|COLD = SNOW",
            "PLAINS|WASTELAND, HOT, MOUNTAIN|HILLS = DESERT_HILLS",
            "PLAINS|WASTELAND, HOT = DESERT",
            "PLAINS|WASTELAND, MOUNTAIN|HILLS = HILLS",
            "PLAINS|WASTELAND = PLAINS",
            "MOUNTAIN = MOUNTAINS_NAKED",
            "HILLS, SNOWY|COLD = SNOW_HILLS",
            "HILLS, SANDY = DESERT_HILLS",
            "HILLS = HILLS"
    };

    @Config.Comment("Define custom village tiles for custom village components here. \n" +
            "Pattern: componentName, textureSetName, priority\n" +
            "Component Names can be found in saves/yourworld/data/Village.dat in entries called \"id\"\n" +
            "This also allows Recurrent Complex village components, use their \"RcSId\"\n" +
            "Waystone Id is \"waystones:village_waystone\"")
    @Config.Name("Custom Village Tiles")
    @Config.RequiresMcRestart
    public String[] customVillageTiles = {};

    @Config.Comment("Will only apply if BetterEnd Backport and NetherAPI are present. By Artsy (2025), slightly modified by Nischhelm.")
    @Config.Name("Use Colorised BetterEnd Tiles")
    @Config.RequiresMcRestart
    public boolean useColorisedBetterEndTiles = true;

//    @Config.Comment("Will only apply if BetterNether and NetherAPI are present. Not made yet.")
//    @Config.Name("Use Colorised BetterNether Tiles")
//    @Config.RequiresMcRestart
    public boolean useColorisedBetterNetherTiles = true;

//    @Config.Comment("Will only apply if DregoraRL and OTG are present. Not made yet.")
//    @Config.Name("Use Colorised Dregora Tiles")
//    @Config.RequiresMcRestart
    public boolean useColorisedDregoraTiles = true;

    @Config.Comment("Will only apply if DefiledLands is present. By Artsy (2021), modified original tiles by AA.")
    @Config.Name("Use Colorised DefiledLands Tiles")
    @Config.RequiresMcRestart
    public boolean useColorisedDefiledLandsTiles = true;

    @Config.Comment("Will only apply if Traverse is present. By Artsy (2021), modified original tiles by AA.")
    @Config.Name("Use Colorised Traverse Tiles")
    @Config.RequiresMcRestart
    public boolean useColorisedTraverseTiles = true;

    @Config.Comment("Will only apply if Biomes'O'Plenty is present. Mostly by Golrith (2015), currently being edited by Nischhelm.")
    @Config.Name("Use Colorised BOP Tiles")
    @Config.RequiresMcRestart
    public boolean useColorisedBOPTiles = true;

    @Config.Comment("Will only apply if Thaumcraft is present. By Golrith (2015).")
    @Config.Name("Use Colorised Thaumcraft Tiles")
    @Config.RequiresMcRestart
    public boolean useColorisedThaumcraftTiles = true;

    @Config.Comment("Will apply colorised vanilla tiles by either Artsy, Golrith or both, where Golrith is prioritised as that set has missing tiles for various biomes for which Artsy textures are used. By Golrith (2015) and Artsy (2021).")
    @Config.Name("Use Colorised Vanilla Tiles")
    @Config.RequiresMcRestart
    public EnumTextureSetArtist useColorisedVanillaTiles = EnumTextureSetArtist.ARTSY;
    public enum EnumTextureSetArtist{ ARTSY, GOLRITH, BOTH, NONE}

    @Config.Comment("Stitching to null (undiscovered chunks) was meant to exist in Antique Atlas but never correctly implemented. AAAM fixes it. \n" +
            "Add texture set names here to make them stitch to null.\n" +
            "By default, AA has ROCK_SHORE and LAVA_SHORE stitch to null (without that doing anything).")
    @Config.Name("TextureSets stitch to null")
    @Config.RequiresMcRestart
    public String[] stitchToNullSets = {
            "END_VOID",
            "LAVA_SHORE",
            "LAVA"
    };

    public static void init() {
        for (String configLine : ConfigHandler.overhaul.tileConfig.automaticTypeRules)
            new TextureSetRule(configLine);
    }
    public static void reset(){
        allRules.clear();
    }
    private static final List<TextureSetRule> allRules = new ArrayList<>();
    public static TextureSet getTextureSet(Set<BiomeDictionary.Type> types){
        for(TextureSetRule rule : allRules)
            if(rule.matcher.apply(types)) return rule.textureSet;
        return BiomeTextureMap.defaultTexture; //no match
    }

    public static class TextureSetRule{
        private final Predicate<Set<BiomeDictionary.Type>> matcher;
        private final TextureSet textureSet;

        public TextureSetRule(String configLine){
            if(!configLine.matches("^[\\w,|\\s_]+\\s*=\\s*[\\w_]+$")){
                matcher = types -> false;
                textureSet = BiomeTextureMap.defaultTexture;
                AntiqueAtlasAutoMarker.LOGGER.warn("AAAM: Automatic TextureSet rule doesn't match expected pattern, ignoring {}", configLine);
            } else {
                String[] split = configLine.split("=");
                textureSet = TextureSetMap.instance().getByName(split[1].trim());

                //type1|type2|type3, type4, type5 etc
                configLine = split[0].trim();
                split = configLine.split(",");
                Predicate<Set<BiomeDictionary.Type>> hasToMatchAllGivenTypes = types -> true;
                for(String entry : split){
                    if(entry.contains("|")) {
                        String[] split2 = entry.split("\\|");
                        Predicate<Set<BiomeDictionary.Type>> matchesMultipleTypes = types -> false;
                        for (String inner : split2) {
                            BiomeDictionary.Type typeToCheckFor = BiomeDictionary.Type.getType(inner.trim());
                            matchesMultipleTypes = Predicates.or(matchesMultipleTypes, types -> types.contains(typeToCheckFor));
                        }
                        hasToMatchAllGivenTypes = Predicates.and(hasToMatchAllGivenTypes, matchesMultipleTypes);
                    } else {
                        BiomeDictionary.Type typeToCheckFor = BiomeDictionary.Type.getType(entry.trim());
                        hasToMatchAllGivenTypes = Predicates.and(hasToMatchAllGivenTypes, types -> types.contains(typeToCheckFor));
                    }
                }
                matcher = hasToMatchAllGivenTypes;

                allRules.add(this);
            }
        }
    }
}
