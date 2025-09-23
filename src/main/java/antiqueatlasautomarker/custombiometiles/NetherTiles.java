package antiqueatlasautomarker.custombiometiles;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.ext.ExtTileIdMap;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;

public class NetherTiles {
    public static int WALL = -1;
    public static TextureSet HELL_TEXTURE;

    public static void registerTiles(){
        TileAPI api = AtlasAPI.getTileAPI();

        HELL_TEXTURE = new TextureSet("CUSTOM_HELL", tileLoc("hell")); //Normal nether

        WALL = registerCustomTexture(api, TextureSet.CAVE_WALLS);
        api.setBiomeTexture(Biomes.HELL, HELL_TEXTURE);
    }

    private static int registerCustomTexture(TileAPI api, TextureSet set){
        api.registerTextureSet(set);
        api.setCustomTileTexture(set.name, set);
        return ExtTileIdMap.instance().getOrCreatePseudoBiomeID(set.name);
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/artsy/"+tileName+".png");
    }
}
