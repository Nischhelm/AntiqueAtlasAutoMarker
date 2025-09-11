package antiqueatlasautomarker.custombiometiles;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import mod.beethoven92.betterendforge.common.init.ModBiomes;
import net.minecraft.util.ResourceLocation;

public class BetterEndBiomes {
    public static void postInit(){
        TileAPI api = AtlasAPI.getTileAPI();

        TextureSet chorusForest = new TextureSet("BE_CHORUS_FOREST", tileLoc("chorusforest"), tileLoc("chorusforest2"));
        TextureSet dragonGraveyard = new TextureSet("BE_DRAGON_GRAVEYARD", tileLoc("dragon_graveyard"), tileLoc("dragon_graveyard2") , tileLoc("dragon_graveyard2"), tileLoc("dragon_graveyard2"), tileLoc("dragon_graveyard3"));
        TextureSet dryShrubland = new TextureSet("BE_DRY_SHRUBLAND", tileLoc("dry_shrublandpng"), tileLoc("dry_shrublandpng1"), tileLoc("dry_shrublandpng2"));
        TextureSet dustWastelands = new TextureSet("BE_DUST_WASTELANDS", tileLoc("dust_wastelands"), tileLoc("dust_wastelands2"));
        TextureSet glowingGrasslands = new TextureSet("BE_GLOWING_GRASSLANDS", tileLoc("glowing_grasslands"));
        TextureSet crystalMountains = new TextureSet("BE_CRYSTAL_MOUNTAINS", tileLoc("holy_crystal_mountains"), tileLoc("holy_crystal_mountains2"));
        TextureSet iceStarfield = new TextureSet("BE_ICE_STARFIELD", tileLoc("ice_starfield"), tileLoc("ice_starfield2"));
        TextureSet megalakeGrove = new TextureSet("BE_MEGALAKE_GROVE", tileLoc("megalake_grove"), tileLoc("megalake_grove"), tileLoc("megalake_grove2"));
        TextureSet shadowForest = new TextureSet("BE_SHADOW_FOREST", tileLoc("shadowforest"), tileLoc("shadowforest"), tileLoc("shadowforest2"));
        TextureSet sulphurSprings = new TextureSet("BE_SULPHUR_SPRINGS", tileLoc("sulpher_springs"), tileLoc("sulpher_springs2"));
        TextureSet umbraValley = new TextureSet("BE_UMBRA_VALLEY", tileLoc("umbra_valley"), tileLoc("umbra_valley2"));
        TextureSet umbrellaJungle = new TextureSet("BE_UMBRELLA_JUNGLE", tileLoc("umbrellajungle"));

        TextureSet todoBiome = new TextureSet("BE_TODO", tileLoc("empty_betterend"));

        api.setBiomeTexture(ModBiomes.CHORUS_FOREST.getBiome(), chorusForest);
        api.setBiomeTexture(ModBiomes.DRAGON_GRAVEYARDS.getBiome(), dragonGraveyard);
        api.setBiomeTexture(ModBiomes.DRY_SHRUBLAND.getBiome(), dryShrubland);
        api.setBiomeTexture(ModBiomes.DUST_WASTELANDS.getBiome(), dustWastelands);
        api.setBiomeTexture(ModBiomes.GLOWING_GRASSLANDS.getBiome(), glowingGrasslands);
        api.setBiomeTexture(ModBiomes.CRYSTAL_MOUNTAINS.getBiome(), crystalMountains);
        api.setBiomeTexture(ModBiomes.ICE_STARFIELD.getBiome(), iceStarfield);
        api.setBiomeTexture(ModBiomes.MEGALAKE_GROVE.getBiome(), megalakeGrove);
        api.setBiomeTexture(ModBiomes.SHADOW_FOREST.getBiome(), shadowForest);
        api.setBiomeTexture(ModBiomes.SULPHUR_SPRINGS.getBiome(), sulphurSprings);
        api.setBiomeTexture(ModBiomes.UMBRA_VALLEY.getBiome(), umbraValley);
        api.setBiomeTexture(ModBiomes.UMBRELLA_JUNGLE.getBiome(), umbrellaJungle);

        api.setBiomeTexture(ModBiomes.MEGALAKE.getBiome(), todoBiome);
        api.setBiomeTexture(ModBiomes.FOGGY_MUSHROOMLAND.getBiome(), todoBiome);
        api.setBiomeTexture(ModBiomes.PAINTED_MOUNTAINS.getBiome(), todoBiome);
        api.setBiomeTexture(ModBiomes.BLOSSOMING_SPIRES.getBiome(), todoBiome);
        api.setBiomeTexture(ModBiomes.AMBER_LAND.getBiome(), todoBiome);
        api.setBiomeTexture(ModBiomes.LANTERN_WOODS.getBiome(), todoBiome);
        api.setBiomeTexture(ModBiomes.NEON_OASIS.getBiome(), todoBiome);
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/betterend/"+tileName+".png");
    }
}
