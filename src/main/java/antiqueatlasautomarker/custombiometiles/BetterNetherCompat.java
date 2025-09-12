package antiqueatlasautomarker.custombiometiles;

import git.jbredwards.nether_api.mod.common.compat.betternether.BetterNetherHandler;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import net.minecraft.util.ResourceLocation;
import paulevs.betternether.biomes.BiomeRegister;

public class BetterNetherCompat {
    public static void registerTiles(){
        TileAPI api = AtlasAPI.getTileAPI();

        TextureSet emptyNether = TextureSet.CAVE_WALLS; //Normal nether
//        TextureSet gravelDesert = new TextureSet("BN_xx", tileLoc("xx"));
//        TextureSet netherJungle = new TextureSet("BN_xx", tileLoc("xx"));
//        TextureSet boneReef = new TextureSet("BN_xx", tileLoc("xx"));
//        TextureSet grasslands = new TextureSet("BN_xx", tileLoc("xx"));
//        TextureSet mushroom = new TextureSet("BN_xx", tileLoc("xx"));
//        TextureSet mushroomEdge = new TextureSet("BN_xx", tileLoc("xx"));
//        TextureSet poorGrasslands = new TextureSet("BN_xx", tileLoc("xx"));
//        TextureSet wartForest = new TextureSet("BN_xx", tileLoc("xx"));
//        TextureSet wartForestEdge = new TextureSet("BN_xx", tileLoc("xx"));

        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_EMPTY_NETHER), emptyNether);
        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_GRAVEL_DESERT), emptyNether);
        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_NETHER_JUNGLE), emptyNether);
        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_BONE_REEF), emptyNether);
        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_GRASSLANDS), emptyNether);
        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_MUSHROOM_FOREST), emptyNether);
        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_MUSHROOM_FOREST_EDGE), emptyNether);
        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_POOR_GRASSLANDS), emptyNether);
        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_WART_FOREST), emptyNether);
        api.setBiomeTexture(BetterNetherHandler.getBiomeFromLookup(BiomeRegister.BIOME_WART_FOREST_EDGE), emptyNether);
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/betternether/"+tileName+".png");
    }
}
