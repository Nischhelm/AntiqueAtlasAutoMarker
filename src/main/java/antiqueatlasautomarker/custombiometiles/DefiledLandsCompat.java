package antiqueatlasautomarker.custombiometiles;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import lykrast.defiledlands.common.init.ModBiomes;
import net.minecraft.util.ResourceLocation;

public class DefiledLandsCompat {
    public static void registerTiles(){
        TileAPI api = AtlasAPI.getTileAPI();

        TextureSet desert = new TextureSet("DL_DESERT", tileLoc("dl_sand"), tileLoc("dl_sand2"), tileLoc("dl_sand3"));
        TextureSet hills = new TextureSet("DL_HILLS", tileLoc("dl_forest_hills"), tileLoc("dl_forest_hills2") , tileLoc("dl_forest_hills3"));
        TextureSet plains = new TextureSet("DL_PLAINS", tileLoc("dl_grass"), tileLoc("dl_grass2"), tileLoc("dl_grass3"));
        TextureSet swamp = new TextureSet("DL_SWAMP", tileLoc("dl_swamp"), tileLoc("dl_swamp2"), tileLoc("dl_swamp3"), tileLoc("dl_swamp4"), tileLoc("dl_swamp5"), tileLoc("dl_swamp6"));
        TextureSet icePlains = new TextureSet("DL_ICE", tileLoc("dl_snow"), tileLoc("dl_snow1"), tileLoc("dl_snow2"), tileLoc("dl_snow3"), tileLoc("dl_snow4"), tileLoc("dl_snow5"), tileLoc("dl_snow6"));
        TextureSet forest = new TextureSet("DL_TENEBRA", tileLoc("dl_forest"), tileLoc("dl_forest2"), tileLoc("dl_forest3"));
        //TextureSet vilespineForest = new TextureSet("DL_VILESPINE", tileLoc("dl_forest"), tileLoc("dl_forest2"), tileLoc("dl_forest3"));

        api.setBiomeTexture(ModBiomes.desertDefiled, desert);
        api.setBiomeTexture(ModBiomes.hillsDefiled, hills);
        api.setBiomeTexture(ModBiomes.plainsDefiled, plains);
        api.setBiomeTexture(ModBiomes.swampDefiled, swamp);
        api.setBiomeTexture(ModBiomes.icePlainsDefiled, icePlains);
        api.setBiomeTexture(ModBiomes.forestTenebra, forest); //TODO: distinguish tenebra from vilespine and both from normal trees
        api.setBiomeTexture(ModBiomes.forestVilespine, forest);
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/artsy/defiledlands/"+tileName+".png");
    }
}
