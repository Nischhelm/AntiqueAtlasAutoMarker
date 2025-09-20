package antiqueatlasautomarker;

import antiqueatlasautomarker.command.PutMarkerCommand;
import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.config.EnchMarkSetting;
import antiqueatlasautomarker.config.folders.BiomeTileConfig;
import antiqueatlasautomarker.custombiometiles.*;
import antiqueatlasautomarker.handlers.LibrarianMarkerHandler;
import antiqueatlasautomarker.handlers.RuinsHandler;
import antiqueatlasautomarker.structuremarkers.event.handlers.TestAAAMEventHandler;
import antiqueatlasautomarker.util.PlayerLogoutHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = AntiqueAtlasAutoMarker.MODID,
        version = AntiqueAtlasAutoMarker.VERSION,
        name = AntiqueAtlasAutoMarker.NAME,
        dependencies = "required-after:fermiumbooter@[1.3.2,);required-after:antiqueatlas",
        acceptableRemoteVersions = "*"
)
public class AntiqueAtlasAutoMarker {
    public static final String MODID = "antiqueatlasautomarker";
    public static final String VERSION = "1.4.0";
    public static final String NAME = "AntiqueAtlasAutoMarker";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final boolean isDebugging = false;
    public static Configuration CONFIG;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CONFIG = new Configuration(event.getSuggestedConfigurationFile());
        CONFIG.load();

        ConfigHandler.battletowers.preInit();
        ConfigHandler.bettermineshafts.preInit();
        ConfigHandler.doomlike.preInit();
        ConfigHandler.dungeons2.preInit();
        ConfigHandler.iceandfire.preInit();
        ConfigHandler.lycanitesmobs.preInit();
        ConfigHandler.quark.preInit();
        ConfigHandler.waystones.preInit();

        AutoMarkSetting.init();
        EnchMarkSetting.init();

        if(event.getSide() == Side.CLIENT) BiomeTileConfig.init();

        //Just for event testing purposes
        if(isDebugging) MinecraftForge.EVENT_BUS.register(TestAAAMEventHandler.class);

        if(ConfigHandler.overhaul.sendToAllHolding) MinecraftForge.EVENT_BUS.register(PlayerLogoutHandler.class);
        if(ConfigHandler.enchantments.enableLibrarianKey && event.getSide().equals(Side.CLIENT)) MinecraftForge.EVENT_BUS.register(LibrarianMarkerHandler.class);
        if(Loader.isModLoaded("ruins")) MinecraftForge.EVENT_BUS.register(RuinsHandler.class);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        if(ConfigHandler.enchantments.enableLibrarianKey && event.getSide().equals(Side.CLIENT))
            LibrarianMarkerHandler.initKeybind();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ConfigHandler.vanillaStructs.postInit();

        if(event.getSide() == Side.CLIENT) {
            BiomeTileConfig.reset(); //Rules are only needed during startup

            if (ConfigHandler.overhaul.tileConfig.useColorisedBetterEndTiles && Loader.isModLoaded("betterendforge") && Loader.isModLoaded("nether_api")) BetterEndCompat.registerTiles();
            if (ConfigHandler.overhaul.tileConfig.useColorisedBetterNetherTiles && Loader.isModLoaded("betternether") && Loader.isModLoaded("nether_api")) BetterNetherCompat.registerTiles();
            if (ConfigHandler.overhaul.tileConfig.useColorisedDefiledLandsTiles && Loader.isModLoaded("defiledlands")) DefiledLandsCompat.registerTiles();
            if (ConfigHandler.overhaul.tileConfig.useColorisedTraverseTiles && Loader.isModLoaded("traverse")) TraverseCompat.registerTiles();
            if (ConfigHandler.overhaul.tileConfig.useColorisedThaumcraftTiles && Loader.isModLoaded("thaumcraft")) ThaumcraftCompat.registerTiles();
            if (ConfigHandler.overhaul.tileConfig.useColorisedBOPTiles && Loader.isModLoaded("biomesoplenty")) BiomesOPlentyCompat.registerTiles();
            if (ConfigHandler.overhaul.tileConfig.useColorisedDregoraTiles && ModCompat.isOTGLoaded() && Loader.isModLoaded("dregorarl")) DregoraCompat.registerTiles();
        }
    }

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        event.registerServerCommand(new PutMarkerCommand());
    }
}