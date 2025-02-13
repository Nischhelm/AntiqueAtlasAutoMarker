package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AntiqueAtlasAutoMarker.MODID)
public class ForgeConfigHandler {

	@Config.Comment("Server-Side Options, mod needs to be on server for these options to work")
	@Config.Name("Server Options")
	public static final ServerConfig server = new ServerConfig();

	@Config.Comment("Client-Side Options")
	@Config.Name("Client Options")
	public static final ClientConfig client = new ClientConfig();

	public static class ServerConfig {
		@Config.Comment("Pattern: structureType; enabled; distance; marker label; marker type. \n" +
				"Distance is the maximum distance the game will look for players to mark the newly generated structure on their atlas.\n" +
				"Marker types are defined in AntiqueAtlas Marker config and can be customised there using ResourceLoader mod.\n" +
				"Special Marker Label names:\n" +
				"wildWaystone: DEFAULT marks the wild waystone with the name it will generate as on first activation\n" +
				"lycaniteDungeon: DEFAULT marks the lyca dungeon with the default name of the dungeon (Ashen Mausoleum etc)" +
				"doomlikeDungeon: DEFAULT uses the name of the dungeon's theme, like \"Urban Doomlike Dungeon\"")
		@Config.Name("Auto marked structures on generation")
		public String[] structureMarkers = {
				"wildWaystone; true; 320; Wild Waystone; antiqueatlas:waystone",
				"fireDragon; true; 320; Fire Dragon; antiqueatlas:dragon_red",
				"iceDragon; true; 320; Ice Dragon; antiqueatlas:dragon_blue",
				"lightningDragon; true; 320; Lightning Dragon; antiqueatlas:dragon_gold",
				"cyclopsCave; true; 320; Cyclops; antiqueatlas:red_x_small",
				"hydraCave; true; 320; Hydra; antiqueatlas:dragon_green",
				"lycaniteDungeon; true; 320; DEFAULT; antiqueatlas:dungeon",
				"betterMineshaft; true; 500; Mineshaft; antiqueatlas:tracks",
				"doomlikeDungeon; true; 320; DEFAULT; antiqueatlas:dungeon",
				"dungeons2; true; 320; Dungeon2; antiqueatlas:dungeon",
				"AARCAddonMarker; true; 320; See AARC config; Enable to turn AARC Global markers into AAAM local markers"
		};
	}

	public static class ClientConfig {
		@Config.Comment("Auto mark activated waystones")
		@Config.Name("Activated Waystones: Auto Mark")
		public boolean autoMarkActivatedWaystones = true;

		@Config.Comment("Update Waystone marker names every time a player interacts with a waystone")
		@Config.Name("Activated Waystones: Auto update")
		public boolean autoUpdateWaystones = true;

		@Config.Comment("Marker Type registry id of the marker to use for activated waystones (find them in config/antiqueatlas/markers.cfg)")
		@Config.Name("Activated Waystones: Marker Type")
		public String activatedWayStoneMarkerType = "antiqueatlas:waystone";
	}

	@Mod.EventBusSubscriber(modid = AntiqueAtlasAutoMarker.MODID)
	private static class EventHandler{

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(AntiqueAtlasAutoMarker.MODID)) {
				ConfigManager.sync(AntiqueAtlasAutoMarker.MODID, Config.Type.INSTANCE);
				AutoMarkSetting.resetAutoMarkSettings();
			}
		}
	}
}