package antiqueatlasautomarker.handlers;

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
		@Config.Comment("Auto mark wild waystones when they generate with new chunks")
		@Config.Name("Wild Waystones: Auto Mark")
		public boolean autoMarkWildWaystones = true;

		@Config.Comment("Players this many blocks away will receive the wild waystone marker on generation")
		@Config.Name("Wild Waystones: Distance")
		public int wildWaystoneDistance = 320;

		@Config.Comment("Name new wild waystone markers with this (use DEFAULT for using the name it generates with")
		@Config.Name("Wild Waystones: Name")
		public String wildWayStoneName = "Wild Waystone";

		@Config.Comment("Marker Type registry id of the marker to use for wild waystones (find them in config/antiqueatlas/markers.cfg)")
		@Config.Name("Wild Waystones: Marker Type")
		public String wildWayStoneMarkerType = "antiqueatlas:waystone";
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
			}
		}
	}
}