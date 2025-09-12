package antiqueatlasautomarker.custombiometiles;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import net.minecraft.util.ResourceLocation;
import thaumcraft.common.world.biomes.BiomeHandler;

public class ThaumcraftCompat {
    public static void registerTiles(){
        TileAPI api = AtlasAPI.getTileAPI();

        TextureSet eerie = new TextureSet("TC_EERIE", tileLoc("tc_eeire"));
        TextureSet magicalForesst = new TextureSet("TC_MAGICAL", tileLoc("tc_magicalforest1"), tileLoc("tc_magicalforest2"), tileLoc("tc_magicalforest3"), tileLoc("tc_magicalforest4"), tileLoc("tc_magicalforest5"));
        //TextureSet eldritch = new TextureSet("TC_ELDRITCH", tileLoc("tc_eldritch"));
        //TextureSet taint = new TextureSet("TC_TAINT", tileLoc("tc_taint1"), tileLoc("tc_taint2"), tileLoc("tc_taint3"), tileLoc("tc_taint4"), tileLoc("tc_taint5"));

        api.setBiomeTexture(BiomeHandler.EERIE, eerie);
        api.setBiomeTexture(BiomeHandler.MAGICAL_FOREST, magicalForesst);
        //api.setBiomeTexture(BiomeHandler.ELDRITCH, eldritch);  //Golrith didn't make a TextureSet for this biome
        //api.setBiomeTexture(BiomeHandler.biomeTaint, taint);  //taint biome doesnt exist as a separate biome anymore since TC 6.0
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/golrith/thaumcraft/"+tileName+".png");
    }
}
