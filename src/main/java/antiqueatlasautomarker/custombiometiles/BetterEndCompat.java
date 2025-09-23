package antiqueatlasautomarker.custombiometiles;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.client.Textures;
import hunternif.mc.atlas.ext.ExtTileIdMap;
import mod.beethoven92.betterendforge.common.init.ModBiomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class BetterEndCompat {
    public static int BE_ICE_STARFIELD_VOID;

    public static Biome getIceStarfieldBiome(){
        return ModBiomes.ICE_STARFIELD.getBiome();
    }

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

        registerTextureIfPresent(api, ModBiomes.AMBER_LAND.getBiome(), amberLand);
        registerTextureIfPresent(api, ModBiomes.BLOSSOMING_SPIRES.getBiome(), blossomingSpires);
        registerTextureIfPresent(api, ModBiomes.CHORUS_FOREST.getBiome(), chorusForest);
        registerTextureIfPresent(api, ModBiomes.CRYSTAL_MOUNTAINS.getBiome(), crystalMountains);
        registerTextureIfPresent(api, ModBiomes.DRAGON_GRAVEYARDS.getBiome(), dragonGraveyard);
        registerTextureIfPresent(api, ModBiomes.DRY_SHRUBLAND.getBiome(), dryShrubland);
        registerTextureIfPresent(api, ModBiomes.DUST_WASTELANDS.getBiome(), dustWastelands);
        registerTextureIfPresent(api, ModBiomes.GLOWING_GRASSLANDS.getBiome(), glowingGrasslands);
        registerTextureIfPresent(api, ModBiomes.FOGGY_MUSHROOMLAND.getBiome(), foggyMushroomland);
        registerTextureIfPresent(api, ModBiomes.ICE_STARFIELD.getBiome(), iceStarfield);
        BE_ICE_STARFIELD_VOID = registerCustomVoidTexture(api, iceStarfieldVoid);
        registerTextureIfPresent(api, ModBiomes.LANTERN_WOODS.getBiome(), lanternWoods);
        registerTextureIfPresent(api, ModBiomes.MEGALAKE.getBiome(), megalake);
        registerTextureIfPresent(api, ModBiomes.MEGALAKE_GROVE.getBiome(), megalakeGrove);
        registerTextureIfPresent(api, ModBiomes.NEON_OASIS.getBiome(), neonOasis);
        registerTextureIfPresent(api, ModBiomes.PAINTED_MOUNTAINS.getBiome(), paintedMountains);
        registerTextureIfPresent(api, ModBiomes.SHADOW_FOREST.getBiome(), shadowForest);
        registerTextureIfPresent(api, ModBiomes.SULPHUR_SPRINGS.getBiome(), sulphurSprings);
        registerTextureIfPresent(api, ModBiomes.UMBRA_VALLEY.getBiome(), umbraValley);
        registerTextureIfPresent(api, ModBiomes.UMBRELLA_JUNGLE.getBiome(), umbrellaJungle);
    }

    private static void registerTextureIfPresent(TileAPI api, Biome biome, TextureSet texture){
        //disabled BetterEnd biomes have a null registryName
        if(biome.getRegistryName() != null) api.setBiomeTexture(biome, texture);
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
