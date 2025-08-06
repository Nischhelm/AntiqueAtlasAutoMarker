package antiqueatlasautomarker;

import antiqueatlasautomarker.command.PutMarkerCommand;
import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.config.EnchMarkSetting;
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
        dependencies = "required-after:fermiumbooter@[1.3.0,);required-after:antiqueatlas",
        acceptableRemoteVersions = "*"
)
public class AntiqueAtlasAutoMarker {
    public static final String MODID = "antiqueatlasautomarker";
    public static final String VERSION = "1.3.1"; 
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
    }

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        event.registerServerCommand(new PutMarkerCommand());
    }
}