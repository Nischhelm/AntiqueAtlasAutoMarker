package antiqueatlasautomarker;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.EnchMarkSetting;
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
    public static final String VERSION = "1.0.3";
    public static final String NAME = "AntiqueAtlasAutoMarker";
    public static final Logger LOGGER = LogManager.getLogger();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AutoMarkSetting.init();
        EnchMarkSetting.init();
    }
}