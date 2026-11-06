package antiqueatlasautomarker.features.custombiometiles;

import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.config.ConfigHandler;
import hunternif.mc.atlas.api.TileAPI;
import hunternif.mc.atlas.client.TextureSet;
import hunternif.mc.atlas.client.TextureSetMap;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Antique Atlas loads biome_textures.json in its init, so the custom texture sets have to exist before that,
 * or every biome using one logs "Unknown texture set" and later "Overwriting texture set".
 * Biomes are all registered by the end of the biome registry event, which fires before any mod's init.
 */
public class BiomeTileRegistration {
    private static boolean registered = false;

    /** Antique Atlas never registers these two default texture sets by name, so neither the biome config nor the automatic rules can find them */
    public static void registerMissingDefaultTextureSets() {
        TextureSetMap.instance().register(TextureSet.SPARSE_FOREST);
        TextureSetMap.instance().register(TextureSet.SPARSE_FOREST_HILLS);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBiomeRegistry(RegistryEvent.Register<Biome> event) {
        registerBiomeTiles();
    }

    /** Only runs once, postInit calls it again in case the registry event was missed */
    public static void registerBiomeTiles() {
        if (registered) return;
        registered = true;
        if (ConfigHandler.tiles.modifyBiomeDetection) NetherTiles.registerTiles();
        if (ConfigHandler.tiles.useColorisedDefiledLandsTiles && Loader.isModLoaded("defiledlands")) DefiledLandsCompat.registerTiles();
        if (ConfigHandler.tiles.useColorisedTraverseTiles && Loader.isModLoaded("traverse")) TraverseCompat.registerTiles();
        if (ConfigHandler.tiles.useColorisedThaumcraftTiles && Loader.isModLoaded("thaumcraft")) ThaumcraftCompat.registerTiles();
        if (ConfigHandler.tiles.useColorisedBOPTiles && ModCompat.biomesOPlenty.isLoaded()) BiomesOPlentyCompat.registerTiles();
        if (ConfigHandler.tiles.useColorisedDregoraTiles && ModCompat.otg.isLoaded() && Loader.isModLoaded("dregorarl")) DregoraCompat.registerTiles();
    }

    /** Registers the texture set by name (so biome_textures.json can find it) and assigns it to the biome */
    public static void setBiomeTexture(TileAPI api, Biome biome, TextureSet textureSet) {
        api.registerTextureSet(textureSet);
        api.setBiomeTexture(biome, textureSet);
    }
}
