package antiqueatlasautomarker.custombiometiles;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.client.Textures;
import net.minecraft.util.ResourceLocation;
import prospector.traverse.world.TraverseWorld;

public class DregoraCompat {
    public static void registerTiles(){
        //TileAPI api = AtlasAPI.getTileAPI();

        //TextureSet dregora = new TextureSet("DREGORA", tileLoc("x"), tileLoc("x"), tileLoc("x"));

        //api.setBiomeTexture(xx, dregora);

//        "nuclearcraft:nuclear_wasteland": "DESERT",
//        "openterraingenerator:overworld_ancient_autumn_forest": "PLAINS",
//        "openterraingenerator:overworld_ancient_rainforest": "PLAINS",
//        "openterraingenerator:overworld_carnivorous_forest": "PLAINS",
//        "openterraingenerator:overworld_dead_river": "PLAINS",
//        "openterraingenerator:overworld_dead_wastelands": "PLAINS",
//        "openterraingenerator:overworld_rainforest_beach": "PLAINS",
//        "openterraingenerator:overworld_rotten_fungal_plains": "PLAINS",
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/dregora/"+tileName+".png");
    }
}
