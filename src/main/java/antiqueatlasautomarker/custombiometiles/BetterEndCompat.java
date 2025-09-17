package antiqueatlasautomarker.custombiometiles;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.client.Textures;
import hunternif.mc.atlas.ext.ExtTileIdMap;
import mod.beethoven92.betterendforge.common.init.ModBiomes;
import net.minecraft.util.ResourceLocation;

public class BetterEndCompat {
    public static int BE_ICE_STARFIELD_VOID;

    public static void registerTiles(){
        TileAPI api = AtlasAPI.getTileAPI();

        TextureSet amberLand = new TextureSet("BE_AMBER_LAND", tileLoc("amber_land"));
        TextureSet blossomingSpires = new TextureSet("BE_BLOSSOMING_SPIRES", tileLoc("blossoming_spires"));
        TextureSet chorusForest = new TextureSet("BE_CHORUS_FOREST", tileLoc("chorusforest"), tileLoc("chorusforest2"));
        TextureSet crystalMountains = new TextureSet("BE_CRYSTAL_MOUNTAINS", tileLoc("holy_crystal_mountains"), tileLoc("holy_crystal_mountains2"));
        TextureSet dragonGraveyard = new TextureSet("BE_DRAGON_GRAVEYARD", tileLoc("dragon_graveyard"), tileLoc("dragon_graveyard2") , tileLoc("dragon_graveyard2"), tileLoc("dragon_graveyard2"), tileLoc("dragon_graveyard3"));
        TextureSet dryShrubland = new TextureSet("BE_DRY_SHRUBLAND", tileLoc("dry_shrublandpng"), tileLoc("dry_shrublandpng1"), tileLoc("dry_shrublandpng2"));
        TextureSet dustWastelands = new TextureSet("BE_DUST_WASTELANDS", tileLoc("dust_wastelands"), tileLoc("dust_wastelands2"));
        TextureSet foggyMushroomland = new TextureSet("BE_FOGGY_MUSHROOMLAND", tileLoc("foggy_mushroomland"));
        TextureSet glowingGrasslands = new TextureSet("BE_GLOWING_GRASSLANDS", tileLoc("glowing_grasslands"));
        TextureSet iceStarfield = new TextureSet("BE_ICE_STARFIELD", tileLoc("ice_starfield"), tileLoc("ice_starfield2"));
        TextureSet iceStarfieldVoid = new TextureSet("BE_ICE_STARFIELD_VOID", tileLoc("ice_starfield_void"), Textures.TILE_END_VOID).stitchToMutual(TextureSet.END_VOID);
        TextureSet lanternWoods = new TextureSet("BE_LANTERN_WOODS", tileLoc("lantern_woods"));
        TextureSet megalake = new TextureSet("BE_MEGALAKE", tileLoc("megalake"));
        TextureSet megalakeGrove = new TextureSet("BE_MEGALAKE_GROVE", tileLoc("megalake_grove"), tileLoc("megalake_grove"), tileLoc("megalake_grove2"));
        TextureSet neonOasis = new TextureSet("BE_NEON_OASIS", tileLoc("neon_oasis"));
        TextureSet paintedMountains = new TextureSet("BE_PAINTED_MOUNTAINS", tileLoc("painted_mountains"));
        TextureSet shadowForest = new TextureSet("BE_SHADOW_FOREST", tileLoc("shadowforest"), tileLoc("shadowforest"), tileLoc("shadowforest2"));
        TextureSet sulphurSprings = new TextureSet("BE_SULPHUR_SPRINGS", tileLoc("sulpher_springs"), tileLoc("sulpher_springs2"));
        TextureSet umbraValley = new TextureSet("BE_UMBRA_VALLEY", tileLoc("umbra_valley"), tileLoc("umbra_valley2"));
        TextureSet umbrellaJungle = new TextureSet("BE_UMBRELLA_JUNGLE", tileLoc("umbrellajungle"));

        api.setBiomeTexture(ModBiomes.AMBER_LAND.getBiome(), amberLand);
        api.setBiomeTexture(ModBiomes.BLOSSOMING_SPIRES.getBiome(), blossomingSpires);
        api.setBiomeTexture(ModBiomes.CHORUS_FOREST.getBiome(), chorusForest);
        api.setBiomeTexture(ModBiomes.CRYSTAL_MOUNTAINS.getBiome(), crystalMountains);
        api.setBiomeTexture(ModBiomes.DRAGON_GRAVEYARDS.getBiome(), dragonGraveyard);
        api.setBiomeTexture(ModBiomes.DRY_SHRUBLAND.getBiome(), dryShrubland);
        api.setBiomeTexture(ModBiomes.DUST_WASTELANDS.getBiome(), dustWastelands);
        api.setBiomeTexture(ModBiomes.GLOWING_GRASSLANDS.getBiome(), glowingGrasslands);
        api.setBiomeTexture(ModBiomes.FOGGY_MUSHROOMLAND.getBiome(), foggyMushroomland);
        api.setBiomeTexture(ModBiomes.ICE_STARFIELD.getBiome(), iceStarfield);
        BE_ICE_STARFIELD_VOID = registerCustomVoidTexture(api, iceStarfieldVoid);
        api.setBiomeTexture(ModBiomes.LANTERN_WOODS.getBiome(), lanternWoods);
        api.setBiomeTexture(ModBiomes.MEGALAKE.getBiome(), megalake);
        api.setBiomeTexture(ModBiomes.MEGALAKE_GROVE.getBiome(), megalakeGrove);
        api.setBiomeTexture(ModBiomes.NEON_OASIS.getBiome(), neonOasis);
        api.setBiomeTexture(ModBiomes.PAINTED_MOUNTAINS.getBiome(), paintedMountains);
        api.setBiomeTexture(ModBiomes.SHADOW_FOREST.getBiome(), shadowForest);
        api.setBiomeTexture(ModBiomes.SULPHUR_SPRINGS.getBiome(), sulphurSprings);
        api.setBiomeTexture(ModBiomes.UMBRA_VALLEY.getBiome(), umbraValley);
        api.setBiomeTexture(ModBiomes.UMBRELLA_JUNGLE.getBiome(), umbrellaJungle);
    }

    private static int registerCustomVoidTexture(TileAPI api, TextureSet set){
        api.registerTextureSet(set);
        api.setCustomTileTexture(set.name, set);
        return ExtTileIdMap.instance().getOrCreatePseudoBiomeID(set.name);
    }

    private static ResourceLocation tileLoc(String tileName){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/tiles/artsy/betterend/"+tileName+".png");
    }
}
