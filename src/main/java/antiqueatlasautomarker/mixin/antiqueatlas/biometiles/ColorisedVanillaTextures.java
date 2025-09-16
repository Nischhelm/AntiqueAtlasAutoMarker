package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.config.folders.BiomeTileConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.client.Textures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashSet;
import java.util.Set;

@Mixin(Textures.class)
public abstract class ColorisedVanillaTextures {
    //TODO: maybe a tile for ocean monuments?

    @ModifyExpressionValue(
            method = "tile",
            at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;toString()Ljava/lang/String;"),
            remap = false
    )
    private static String aaam_swapForColorised(String original, @Local(argsOnly = true) String fileName){
        BiomeTileConfig.EnumTextureSetArtist config = ConfigHandler.overhaul.tileConfig.useColorisedVanillaTiles;
        if(config == BiomeTileConfig.EnumTextureSetArtist.NONE) return original;
        boolean isForBoth = config == BiomeTileConfig.EnumTextureSetArtist.BOTH;
        if(config == BiomeTileConfig.EnumTextureSetArtist.GOLRITH || isForBoth){
            aaam$setupLookupSets(false, isForBoth);
            if(aaam$golrithTextures.contains(fileName))
                return aaam$replacedFileName(fileName, "golrith");
        }
        if(config == BiomeTileConfig.EnumTextureSetArtist.ARTSY || isForBoth){
            aaam$setupLookupSets(true, isForBoth);
            if (aaam$artsyTextures.contains(fileName))
                return aaam$replacedFileName(fileName, "artsy");
        }
        return original;
    }

    @Unique
    private static String aaam$replacedFileName(String fileName, String author) {
        return "antiqueatlas:textures/gui/tiles/"+ author + "/vanilla/" + fileName;
    }

    @Unique private static Set<String> aaam$golrithTextures;
    @Unique private static Set<String> aaam$artsyTextures;

    @Unique
    private static void aaam$setupLookupSets(boolean forArtsy, boolean forBoth){
        if(!forArtsy && aaam$golrithTextures == null){
            aaam$golrithTextures = new HashSet<>();
            aaam$golrithTextures.add("birch.png");
            aaam$golrithTextures.add("birch_hills.png");
            aaam$golrithTextures.add("birch_hills2.png");
            aaam$golrithTextures.add("birch2.png");
            aaam$golrithTextures.add("cacti.png");
            aaam$golrithTextures.add("forest.png");
            aaam$golrithTextures.add("forest_hills.png");
            aaam$golrithTextures.add("forest_hills2.png");
            aaam$golrithTextures.add("forest_hills3.png");
            aaam$golrithTextures.add("forest2.png");
            aaam$golrithTextures.add("forest3.png");
            aaam$golrithTextures.add("grass.png");
            aaam$golrithTextures.add("grass2.png");
            aaam$golrithTextures.add("grass3.png");
            aaam$golrithTextures.add("grass4.png");
            aaam$golrithTextures.add("hills.png");
            aaam$golrithTextures.add("hills_bushes.png");
            aaam$golrithTextures.add("hills_cacti.png");
            aaam$golrithTextures.add("jungle.png");
            aaam$golrithTextures.add("jungle_hills.png");
            aaam$golrithTextures.add("jungle_hills2.png");
            aaam$golrithTextures.add("jungle2.png");
            aaam$golrithTextures.add("lava.png");
            aaam$golrithTextures.add("lava2.png");
            aaam$golrithTextures.add("mega_spruce.png");
            aaam$golrithTextures.add("mega_spruce_hills.png");
            aaam$golrithTextures.add("mega_spruce_hills2.png");
            aaam$golrithTextures.add("mega_spruce2.png");
            aaam$golrithTextures.add("mega_taiga.png");
            aaam$golrithTextures.add("mega_taiga_hills.png");
            aaam$golrithTextures.add("mega_taiga_hills2.png");
            aaam$golrithTextures.add("mega_taiga2.png");
            aaam$golrithTextures.add("mountains.png");
            aaam$golrithTextures.add("mountains2.png");
            aaam$golrithTextures.add("mountains3.png");
            aaam$golrithTextures.add("mountains4.png");
            aaam$golrithTextures.add("pines.png");
            aaam$golrithTextures.add("pines_hills.png");
            aaam$golrithTextures.add("pines_hills2.png");
            aaam$golrithTextures.add("pines_hills3.png");
            aaam$golrithTextures.add("pines2.png");
            aaam$golrithTextures.add("pines3.png");
            aaam$golrithTextures.add("rock_shore.png");
            aaam$golrithTextures.add("sand.png");
            aaam$golrithTextures.add("sand_bushes.png");
            aaam$golrithTextures.add("sand2.png");
            aaam$golrithTextures.add("sand3.png");
            aaam$golrithTextures.add("savanna.png");
            aaam$golrithTextures.add("savanna2.png");
            aaam$golrithTextures.add("savanna3.png");
            aaam$golrithTextures.add("shore.png");
            aaam$golrithTextures.add("shore2.png");
            aaam$golrithTextures.add("shore3.png");
            if(!forBoth) { //snow has 5 more textures added which don't mix well in "BOTH" mode
                aaam$golrithTextures.add("snow_caps.png");
                aaam$golrithTextures.add("snow1.png");
                aaam$golrithTextures.add("snow2.png");
            }
            aaam$golrithTextures.add("swamp.png");
            aaam$golrithTextures.add("swamp2.png");
            aaam$golrithTextures.add("swamp3.png");
            aaam$golrithTextures.add("swamp4.png");
            aaam$golrithTextures.add("swamp5.png");
            aaam$golrithTextures.add("swamp6.png");
            aaam$golrithTextures.add("water.png");
            aaam$golrithTextures.add("water2.png");
        } else if(forArtsy && aaam$artsyTextures == null){
            aaam$artsyTextures = new HashSet<>();
            aaam$artsyTextures.add("mountains.png");
            aaam$artsyTextures.add("mountains2.png");
            aaam$artsyTextures.add("mountains3.png");
            aaam$artsyTextures.add("mountains4.png");
            aaam$artsyTextures.add("snow_caps.png");
            aaam$artsyTextures.add("snow_hills.png");
            aaam$artsyTextures.add("snow_hills2.png");
            aaam$artsyTextures.add("water.png");
            aaam$artsyTextures.add("water2.png");
            aaam$artsyTextures.add("ice_noborder.png");
            aaam$artsyTextures.add("forest.png");
            aaam$artsyTextures.add("forest2.png");
            aaam$artsyTextures.add("forest3.png");
            aaam$artsyTextures.add("forest_hills.png");
            aaam$artsyTextures.add("forest_hills2.png");
            aaam$artsyTextures.add("forest_hills3.png");
            aaam$artsyTextures.add("forest_flowers.png");
            aaam$artsyTextures.add("forest_flowers2.png");
            aaam$artsyTextures.add("forest_flowers3.png");
            aaam$artsyTextures.add("dense_forest.png");
            aaam$artsyTextures.add("dense_forest2.png");
            aaam$artsyTextures.add("dense_forest_hills.png");
            aaam$artsyTextures.add("dense_forest_hills2.png");
            aaam$artsyTextures.add("forest_sparse.png");
            aaam$artsyTextures.add("forest_sparse2.png");
            aaam$artsyTextures.add("forest_sparse3.png");
            aaam$artsyTextures.add("forest_sparse_hills.png");
            aaam$artsyTextures.add("forest_sparse_hills2.png");
            aaam$artsyTextures.add("forest_sparse_hills3.png");
            aaam$artsyTextures.add("birch.png");
            aaam$artsyTextures.add("birch2.png");
            aaam$artsyTextures.add("birch_hills.png");
            aaam$artsyTextures.add("birch_hills2.png");
            aaam$artsyTextures.add("tall_birch.png");
            aaam$artsyTextures.add("tall_birch2.png");
            aaam$artsyTextures.add("tall_birch_hills.png");
            aaam$artsyTextures.add("tall_birch_hills2.png");
            aaam$artsyTextures.add("dense_birch.png");
            aaam$artsyTextures.add("hills.png");
            aaam$artsyTextures.add("hills_bushes.png");
            aaam$artsyTextures.add("hills_cacti.png");
            aaam$artsyTextures.add("hills_grass.png");
            aaam$artsyTextures.add("pines.png");
            aaam$artsyTextures.add("pines2.png");
            aaam$artsyTextures.add("pines3.png");
            aaam$artsyTextures.add("pines_hills.png");
            aaam$artsyTextures.add("pines_hills2.png");
            aaam$artsyTextures.add("pines_hills3.png");
            aaam$artsyTextures.add("mega_spruce.png");
            aaam$artsyTextures.add("mega_spruce2.png");
            aaam$artsyTextures.add("mega_taiga.png");
            aaam$artsyTextures.add("mega_taiga2.png");
            aaam$artsyTextures.add("mega_spruce_hills.png");
            aaam$artsyTextures.add("mega_spruce_hills2.png");
            aaam$artsyTextures.add("mega_taiga_hills.png");
            aaam$artsyTextures.add("mega_taiga_hills2.png");
            aaam$artsyTextures.add("sand.png");
            aaam$artsyTextures.add("sand2.png");
            aaam$artsyTextures.add("sand3.png");
            aaam$artsyTextures.add("cacti.png");
            aaam$artsyTextures.add("sand_bushes.png");
            aaam$artsyTextures.add("shore.png");
            aaam$artsyTextures.add("shore2.png");
            aaam$artsyTextures.add("rock_shore.png");
            aaam$artsyTextures.add("shore3.png");
            aaam$artsyTextures.add("grass.png");
            aaam$artsyTextures.add("grass2.png");
            aaam$artsyTextures.add("grass3.png");
            aaam$artsyTextures.add("grass4.png");
            aaam$artsyTextures.add("sunflowers.png");
            aaam$artsyTextures.add("sunflowers2.png");
            aaam$artsyTextures.add("snow.png");
            aaam$artsyTextures.add("snow1.png");
            aaam$artsyTextures.add("snow2.png");
            aaam$artsyTextures.add("snow3.png");
            aaam$artsyTextures.add("snow4.png");
            aaam$artsyTextures.add("snow5.png");
            aaam$artsyTextures.add("snow6.png");
            aaam$artsyTextures.add("snow_pines.png");
            aaam$artsyTextures.add("snow_pines2.png");
            aaam$artsyTextures.add("snow_pines3.png");
            aaam$artsyTextures.add("snow_pines_hills.png");
            aaam$artsyTextures.add("snow_pines_hills2.png");
            aaam$artsyTextures.add("snow_pines_hills3.png");
            aaam$artsyTextures.add("ice_spikes.png");
            aaam$artsyTextures.add("ice_spikes2.png");
            aaam$artsyTextures.add("swamp.png");
            aaam$artsyTextures.add("swamp2.png");
            aaam$artsyTextures.add("swamp3.png");
            aaam$artsyTextures.add("swamp4.png");
            aaam$artsyTextures.add("swamp5.png");
            aaam$artsyTextures.add("swamp6.png");
            aaam$artsyTextures.add("swamp_hills.png");
            aaam$artsyTextures.add("swamp_hills2.png");
            aaam$artsyTextures.add("swamp_hills3.png");
            aaam$artsyTextures.add("swamp_hills4.png");
            aaam$artsyTextures.add("swamp_hills5.png");
            aaam$artsyTextures.add("mushroom.png");
            aaam$artsyTextures.add("mushroom2.png");
            aaam$artsyTextures.add("jungle.png");
            aaam$artsyTextures.add("jungle2.png");
            aaam$artsyTextures.add("jungle_hills.png");
            aaam$artsyTextures.add("jungle_hills2.png");
            aaam$artsyTextures.add("jungle_edge.png");
            aaam$artsyTextures.add("jungle_edge2.png");
            aaam$artsyTextures.add("jungle_edge3.png");
            aaam$artsyTextures.add("jungle_edge_hills.png");
            aaam$artsyTextures.add("jungle_edge_hills2.png");
            aaam$artsyTextures.add("jungle_edge_hills3.png");
            aaam$artsyTextures.add("jungle_cliffs.png");
            aaam$artsyTextures.add("jungle_cliffs2.png");
            aaam$artsyTextures.add("bushes_cliffs.png");
            aaam$artsyTextures.add("cliffs.png");
            aaam$artsyTextures.add("savanna.png");
            aaam$artsyTextures.add("savanna2.png");
            aaam$artsyTextures.add("savanna3.png");
            aaam$artsyTextures.add("savanna_cliffs.png");
            aaam$artsyTextures.add("savanna_cliffs2.png");
            aaam$artsyTextures.add("savanna_cliffs3.png");
            aaam$artsyTextures.add("cliffs_clouds.png");
            aaam$artsyTextures.add("savanna_cliffs_clouds.png");
            aaam$artsyTextures.add("savanna_cliffs_clouds2.png");
            aaam$artsyTextures.add("savanna_cliffs_clouds3.png");
            aaam$artsyTextures.add("mesa.png");
            aaam$artsyTextures.add("mesa2.png");
            aaam$artsyTextures.add("mesa3.png");
            aaam$artsyTextures.add("mesa4.png");
            aaam$artsyTextures.add("bryce.png");
            aaam$artsyTextures.add("bryce2.png");
            aaam$artsyTextures.add("bryce3.png");
            aaam$artsyTextures.add("bryce4.png");
            aaam$artsyTextures.add("plateau_mesa.png");
            aaam$artsyTextures.add("plateau_mesa2.png");
            aaam$artsyTextures.add("plateau_mesa_low.png");
            aaam$artsyTextures.add("plateau_mesa_low2.png");
            aaam$artsyTextures.add("plateau_trees.png");
            aaam$artsyTextures.add("plateau_trees_low.png");
            aaam$artsyTextures.add("plateau_grass.png");
            aaam$artsyTextures.add("plateau_grass2.png");
            aaam$artsyTextures.add("plateau_grass3.png");
            aaam$artsyTextures.add("plateau_savanna.png");
            aaam$artsyTextures.add("plateau_savanna2.png");
            aaam$artsyTextures.add("plateau_savanna3.png");
            aaam$artsyTextures.add("cave_walls.png");
            aaam$artsyTextures.add("ravine.png");
            aaam$artsyTextures.add("lava.png");
            aaam$artsyTextures.add("lava2.png");
            aaam$artsyTextures.add("lava_shore.png");
            aaam$artsyTextures.add("lava_shore2.png");
            aaam$artsyTextures.add("house.png");
            aaam$artsyTextures.add("fence.png");
            aaam$artsyTextures.add("library.png");
            aaam$artsyTextures.add("smithy.png");
            aaam$artsyTextures.add("l_house.png");
            aaam$artsyTextures.add("farmland_s.png");
            aaam$artsyTextures.add("farmland_l.png");
            aaam$artsyTextures.add("village_torch.png");
            aaam$artsyTextures.add("well.png");
            aaam$artsyTextures.add("hut.png");
            aaam$artsyTextures.add("house_small.png");
            aaam$artsyTextures.add("butchers_shop.png");
            aaam$artsyTextures.add("church.png");
            aaam$artsyTextures.add("nether_bridge.png");
            aaam$artsyTextures.add("nether_bridge_x.png");
            aaam$artsyTextures.add("nether_bridge_z.png");
            aaam$artsyTextures.add("nether_bridge_end_x.png");
            aaam$artsyTextures.add("nether_bridge_end_z.png");
            aaam$artsyTextures.add("nether_bridge_gate.png");
            aaam$artsyTextures.add("nether_tower.png");
            aaam$artsyTextures.add("nether_wall.png");
            aaam$artsyTextures.add("nether_hall.png");
            aaam$artsyTextures.add("nether_fort_stairs.png");
            aaam$artsyTextures.add("nether_throne.png");
        }
    }
}
