package antiqueatlasautomarker;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.ConfigProvider;
import antiqueatlasautomarker.config.EnchMarkSetting;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.handlers.RuinsHandler;
import antiqueatlasautomarker.structuremarkers.event.handlers.TestAAAMEventHandler;
import antiqueatlasautomarker.util.PlayerLogoutHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = AntiqueAtlasAutoMarker.MODID,
        version = AntiqueAtlasAutoMarker.VERSION,
        name = AntiqueAtlasAutoMarker.NAME,
        dependencies = "required-after:fermiumbooter;required-after:antiqueatlas",
        acceptableRemoteVersions = "*"
)
public class AntiqueAtlasAutoMarker {
    public static final String MODID = "antiqueatlasautomarker";
    public static final String VERSION = "1.1.4";
    public static final String NAME = "AntiqueAtlasAutoMarker";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final boolean isDebugging = false;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AutoMarkSetting.init();
        EnchMarkSetting.init();
        ConfigProvider.init();

        //Just for event testing purposes
        if(isDebugging) MinecraftForge.EVENT_BUS.register(TestAAAMEventHandler.class);
        if(ConfigHandler.overhaul.sendToAllHolding) MinecraftForge.EVENT_BUS.register(PlayerLogoutHandler.class);
        if(Loader.isModLoaded("ruins")) MinecraftForge.EVENT_BUS.register(RuinsHandler.class);
    }
}