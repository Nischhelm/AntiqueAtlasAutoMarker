package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.folders.*;
import meldexun.betterconfig.api.BetterConfig;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AntiqueAtlasAutoMarker.MODID)
@BetterConfig
public class ConfigHandler {
	@Config.Comment("Settings for various structures to automark")
	@Config.Name("Auto Markers")
	public static AutomarkConfig automark = new AutomarkConfig();

	@Config.Comment("Internal Config")
	@Config.Name("Internal")
	public static InternalConfig internal = new InternalConfig();

	@Config.Comment("Overhaul Antique Atlas")
	@Config.Name("Antique Atlas Overhaul")
	public static AAOverhaulConfig overhaul = new AAOverhaulConfig();

	@Mod.EventBusSubscriber(modid = AntiqueAtlasAutoMarker.MODID)
	private static class EventHandler{

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(AntiqueAtlasAutoMarker.MODID)) {
				ConfigManager.sync(AntiqueAtlasAutoMarker.MODID, Config.Type.INSTANCE);
				EnchMarkSetting.reset();
			}
		}
	}
}