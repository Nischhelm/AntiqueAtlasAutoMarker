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
	@Config.Name("Auto Marking")
	public static AutoMarkConfig automark = new AutoMarkConfig();

	@Config.Comment("Internal Config")
	@Config.Name("AAAM Internals")
	public static InternalConfig internal = new InternalConfig();

	@Config.Comment("Various modifications of which atlas tiles are used when")
	@Config.Name("Antique Atlas Tiles")
	public static TileConfig tiles = new TileConfig();

	@Config.Comment("Bug fixes and performance")
	@Config.Name("Antique Atlas Fixes")
	public static FixConfig fixes = new FixConfig();

	@Config.Comment("Modifications for Quality of life")
	@Config.Name("Antique Atlas Tweaks")
	public static TweakConfig tweaks = new TweakConfig();

	@Mod.EventBusSubscriber(modid = AntiqueAtlasAutoMarker.MODID)
	private static class EventHandler{
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(AntiqueAtlasAutoMarker.MODID)) {
				ConfigManager.sync(AntiqueAtlasAutoMarker.MODID, Config.Type.INSTANCE);
			}
		}
	}
}