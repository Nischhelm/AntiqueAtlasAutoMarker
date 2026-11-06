package antiqueatlasautomarker.features.custombiometiles;

import biomesoplenty.api.biome.BOPBiomes;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class BiomesOPlentyCompat {

    public static Biome getMarshBiome(){
        return BOPBiomes.marsh.isPresent() ? BOPBiomes.marsh.get() : null;
    }

    public static void registerTiles(){
        //TODO: what about BOP nether biomes

        TileAPI api = AtlasAPI.getTileAPI();

        //why so many biomes :sob:
        //TODO: could use a variant, also a bit stripey
        TextureSet bambooForest = new TextureSet("BOP_BAMBOO", tileLoc("bop_bamboo"));
        //FINE
        TextureSet bayou = new TextureSet("BOP_BAYOU", tileLoc("bop_bayou1"), tileLoc("bop_bayou2"), tileLoc("bop_bayou3"));
        //TODO: COLOR is wrong, missing mushrooms, trees should be max some stumps
        TextureSet bog = new TextureSet("BOP_BOG", tileLoc("bop_bog"));
        //TODO: color doesnt fit, birch trees are more orange
        TextureSet borealForest = new TextureSet("BOP_BOR_FOREST", tileLoc("bop_bor_forest"));
        //could use a variant, is less dense ingame
        TextureSet cherryBlossomGrove = new TextureSet("BOP_CHERRY", tileLoc("bop_cherry"));
        //TODO: seems more chaotic + plateau above + greenish above + steep
        TextureSet crag = new TextureSet("BOP_CRAG", tileLoc("bop_crag"));
        //TODO: working on that still... too dense on map, needs a variant
        TextureSet deadForest = new TextureSet("BOP_DEAD_FOREST", tileLoc("bop_dead_forest"));
        //TODO: good color, could use a variant tho
        TextureSet fen = new TextureSet("BOP_FEN", tileLoc("bop_fen"));
        //TODO: way too sparse, missing bushes
        TextureSet flowerField = new TextureSet("BOP_FLOWERFIELD", tileLoc("bop_flowerfield"));
        //kinda fineish - could be more pointy, trees are more stick-like than bushy
        TextureSet grove = new TextureSet("BOP_GROVE", tileLoc("bop_grove1"), tileLoc("bop_grove2"));
        //TODO: i guess it could be fine but its actually way less pointy, also whats that double line about
        TextureSet highland = new TextureSet("BOP_HIGHLAND", tileLoc("bop_highland"));
        //this is perfect
        TextureSet lavenderFields = new TextureSet("BOP_LAVFIELDS", tileLoc("bop_lavfields1"), tileLoc("bop_lavfields2"), tileLoc("bop_lavfields3"));
        //this is fine, could stitch to water, mutually
        TextureSet lushSwamp = new TextureSet("BOP_LUSHSWAMP", tileLoc("bop_lushswamp1"), tileLoc("bop_lushswamp2"), tileLoc("bop_lushswamp3"), tileLoc("bop_lushswamp4"));
        //TODO: maple woods are more brown, 0x7C3131
        TextureSet mapleWoods = new TextureSet("BOP_MAPLE", tileLoc("bop_maple1"), tileLoc("bop_maple2"));
        //with this stitching its great
        TextureSet marsh = new TextureSet("BOP_MARSH", tileLoc("bop_marsh1"), tileLoc("bop_marsh2"), tileLoc("bop_marsh3")).stitchTo(TextureSet.WATER);
        //TODO: in theory good but too much going on, reduce
        TextureSet moor = new TextureSet("BOP_MOOR", tileLoc("bop_moor"));
        //this looks good, it could use a variant
        TextureSet mysticGrove = new TextureSet("BOP_MYSTICAL", tileLoc("bop_mystical"));
        //this is fine, could use some red cactus/bramble, could be more foggy
        TextureSet ominousWoods = new TextureSet("BOP_OMNIOUS", tileLoc("bop_omnious1"), tileLoc("bop_omnious2"));
        //TODO: why red apples, there are no red apples. have been removed in 1.8, now white flower
        TextureSet orchard = new TextureSet("BOP_ORCHARD", tileLoc("bop_orchard1"), tileLoc("bop_orchard2"));
        //TODO: this is kinda fine, but its way more orange ingame, also has cacti
        TextureSet outback = new TextureSet("BOP_OUTBACK", tileLoc("bop_outback1"), tileLoc("bop_outback2"), tileLoc("bop_outback3"), tileLoc("bop_outback4"), tileLoc("bop_outback5"), tileLoc("bop_outback6"));
        //TODO: theres variant issues fml, also could show the more red-brown color of some of the trees
        TextureSet seasonalForest = new TextureSet("BOP_SEASONAL", tileLoc("bop_seasonal1"), tileLoc("bop_seasonal2"), tileLoc("bop_seasonal3"));
        TextureSet shield = new TextureSet("BOP_SHIELD", tileLoc("bop_shield"));
        //this is fine, could show more allium flowers but its really fine
        TextureSet shrubland = new TextureSet("BOP_SHRUBLAND", tileLoc("bop_shrubland"));
        //TODO: this really needs a variant
        TextureSet tropicalIsland = new TextureSet("BOP_TROPICS", tileLoc("bop_tropics"));
        //TODO: this looks fully different now bc its using mahagony
        TextureSet tropicalRainforest = tropicalIsland; //TODO
        //TODO: biome looks like shit, tiles are fine, could use a variant without red top
        TextureSet volcanicIsland = new TextureSet("BOP_VOLCANIC", tileLoc("bop_volcanic"));
        //TODO: biome looks like shit, tiles are fine, could use a variant, without whatever the gray shit is
        TextureSet wasteland = new TextureSet("BOP_WASTELAND", tileLoc("bop_wasteland"));
        //GOOD
        TextureSet wetland = new TextureSet("BOP_WETLAND", tileLoc("bop_wetland1"), tileLoc("bop_wetland2"), tileLoc("bop_wetland3"));

        //TODO bop biomes without a textureset
        TextureSet alps = TextureSet.MOUNTAINS_NAKED;
        TextureSet alps_foothills = TextureSet.SNOW_PINES;
        TextureSet brushland = TextureSet.SAVANNA;
        TextureSet chaparral = TextureSet.PLAINS;
        TextureSet cold_desert = TextureSet.PLAINS;
        TextureSet coniferous_forest = TextureSet.PINES;
        TextureSet coral_reef = TextureSet.WATER; //TODO
        TextureSet dead_swamp = TextureSet.SWAMP;
        TextureSet eucalyptus_forest = TextureSet.JUNGLE; //TODO
        TextureSet flower_island = TextureSet.WATER; //TODO
        TextureSet glacier = TextureSet.SNOW;
        TextureSet grassland = TextureSet.SNOW; //TODO: WHAT
        TextureSet gravel_beach = TextureSet.SHORE;
        TextureSet kelp_forest = TextureSet.WATER;
        TextureSet land_of_lakes = TextureSet.SWAMP;
        TextureSet lush_desert = TextureSet.SAVANNA;
        TextureSet mangrove = TextureSet.SWAMP;
        TextureSet oasis = TextureSet.JUNGLE;
        TextureSet origin_beach = TextureSet.SHORE;
        TextureSet origin_island = TextureSet.SWAMP; //TODO
        TextureSet overgrown_cliffs = TextureSet.JUNGLE_CLIFFS;
        TextureSet pasture = TextureSet.PLAINS;
        TextureSet prairie = TextureSet.PLAINS;
        TextureSet quagmire = TextureSet.SWAMP;
        TextureSet rainforest = TextureSet.JUNGLE_HILLS;
        TextureSet redwood_forest = TextureSet.DENSE_FOREST; //TODO!
        TextureSet redwood_forest_edge = TextureSet.DENSE_FOREST;
        TextureSet sacred_springs = TextureSet.JUNGLE;
        TextureSet snowy_coniferous_forest = TextureSet.PINES;
        TextureSet snowy_forest = TextureSet.SNOW_PINES;
        TextureSet snowy_tundra = TextureSet.SNOW;
        TextureSet steppe = TextureSet.PLAINS;
        TextureSet temperate_rainforest = TextureSet.DENSE_FOREST;
        TextureSet tundra = TextureSet.SNOW;
        TextureSet white_beach = TextureSet.SHORE;
        TextureSet woodland = TextureSet.DENSE_FOREST;
        TextureSet xeric_shrubland = TextureSet.SAVANNA;

        BOPBiomes.alps.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, alps));
        BOPBiomes.alps_foothills.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, alps_foothills));
        BOPBiomes.bamboo_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, bambooForest));
        BOPBiomes.bayou.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, bayou));
        BOPBiomes.bog.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, bog));
        BOPBiomes.boreal_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, borealForest));
        BOPBiomes.brushland.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, brushland));
        BOPBiomes.chaparral.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, chaparral));
        BOPBiomes.cherry_blossom_grove.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, cherryBlossomGrove));
        BOPBiomes.cold_desert.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, cold_desert));
        BOPBiomes.coniferous_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, coniferous_forest));
        BOPBiomes.coral_reef.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, coral_reef));
        BOPBiomes.crag.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, crag));
        BOPBiomes.dead_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, deadForest));
        BOPBiomes.dead_swamp.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, dead_swamp));
        BOPBiomes.eucalyptus_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, eucalyptus_forest));
        BOPBiomes.fen.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, fen));
        BOPBiomes.flower_field.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, flowerField));
        BOPBiomes.flower_island.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, flower_island));
        BOPBiomes.glacier.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, glacier));
        BOPBiomes.grassland.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, grassland));
        BOPBiomes.gravel_beach.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, gravel_beach));
        BOPBiomes.grove.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, grove));
        BOPBiomes.highland.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, highland));
        BOPBiomes.kelp_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, kelp_forest));
        BOPBiomes.land_of_lakes.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, land_of_lakes));
        BOPBiomes.lavender_fields.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, lavenderFields));
        BOPBiomes.lush_desert.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, lush_desert));
        BOPBiomes.lush_swamp.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, lushSwamp));
        BOPBiomes.mangrove.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, mangrove));
        BOPBiomes.maple_woods.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, mapleWoods));
        BOPBiomes.marsh.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, marsh));
        BOPBiomes.moor.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, moor));
        BOPBiomes.mystic_grove.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, mysticGrove));
        BOPBiomes.oasis.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, oasis));
        BOPBiomes.ominous_woods.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, ominousWoods));
        BOPBiomes.orchard.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, orchard));
        BOPBiomes.origin_beach.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, origin_beach));
        BOPBiomes.origin_island.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, origin_island));
        BOPBiomes.outback.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, outback));
        BOPBiomes.overgrown_cliffs.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, overgrown_cliffs));
        BOPBiomes.pasture.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, pasture));
        BOPBiomes.prairie.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, prairie));
        BOPBiomes.quagmire.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, quagmire));
        BOPBiomes.rainforest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, rainforest));
        BOPBiomes.redwood_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, redwood_forest));
        BOPBiomes.redwood_forest_edge.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, redwood_forest_edge));
        BOPBiomes.sacred_springs.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, sacred_springs));
        BOPBiomes.seasonal_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, seasonalForest));
        BOPBiomes.shield.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, shield));
        BOPBiomes.shrubland.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, shrubland));
        BOPBiomes.snowy_coniferous_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, snowy_coniferous_forest));
        BOPBiomes.snowy_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, snowy_forest));
        BOPBiomes.snowy_tundra.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, snowy_tundra));
        BOPBiomes.steppe.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, steppe));
        BOPBiomes.temperate_rainforest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, temperate_rainforest));
        BOPBiomes.tropical_island.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, tropicalIsland));
        BOPBiomes.tropical_rainforest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, tropicalRainforest));
        BOPBiomes.tundra.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, tundra));
        BOPBiomes.volcanic_island.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, volcanicIsland));
        BOPBiomes.wasteland.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, wasteland));
        BOPBiomes.wetland.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, wetland));
        BOPBiomes.white_beach.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, white_beach));
        BOPBiomes.woodland.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, woodland));
        BOPBiomes.xeric_shrubland.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, xeric_shrubland));

        //NETHER BIOMES

        TextureSet corrupted_sands = TextureSet.PLAINS;
        //TODO: this is not actually a forest but a nether biome
        TextureSet fungiForest = new TextureSet("BOP_FUNGIFOREST", tileLoc("bop_fungiforest"));
        TextureSet phantasmagoric_inferno = TextureSet.DESERT;
        TextureSet undergarden = TextureSet.PLAINS;
        TextureSet visceral_heap = TextureSet.PLAINS;

        BOPBiomes.corrupted_sands.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, corrupted_sands));
        BOPBiomes.fungi_forest.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, fungiForest));
        BOPBiomes.phantasmagoric_inferno.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, phantasmagoric_inferno));
        BOPBiomes.undergarden.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, undergarden));
        BOPBiomes.visceral_heap.toJavaUtil().ifPresent(b -> BiomeTileRegistration.setBiomeTexture(api, b, visceral_heap));

    }

    private static ResourceLocation tileLoc(String tileName){
        return tileLoc(tileName, "golrith");
    }

    private static ResourceLocation tileLoc(String tileName, String author){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/"+author+"/bop/"+tileName+".png");
    }
}
