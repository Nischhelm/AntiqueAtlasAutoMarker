package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.folders.*;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AntiqueAtlasAutoMarker.MODID)
public class ConfigHandler {
	@Config.Comment("Battletowers Marker Config")
	@Config.Name("Battletowers")
	public static BattletowersConfig battletowers = new BattletowersConfig();

	@Config.Comment("Doomlike Dungeons Marker Config")
	@Config.Name("Doomlike Dungeons")
	public static DoomlikeConfig doomlike = new DoomlikeConfig();

	@Config.Comment("Dungeons2 Marker Config")
	@Config.Name("Dungeons2")
	public static Dungeons2Config dungeons2 = new Dungeons2Config();

	@Config.Comment("Quark Marker Config")
	@Config.Name("Quark")
	public static QuarkConfig quark = new QuarkConfig();

	@Config.Comment("AARCAddon Marker Config")
	@Config.Name("AARCAddon")
	public static AARCAddonConfig aarcaddon = new AARCAddonConfig();

	@Config.Comment("Ice And Fire Marker Config")
	@Config.Name("Ice And Fire")
	public static IceAndFireConfig iceandfire = new IceAndFireConfig();

	@Config.Comment("Better Mineshafts Marker Config")
	@Config.Name("Better Mineshafts")
	public static BetterMineshaftConfig bettermineshafts = new BetterMineshaftConfig();

	@Config.Comment("LycanitesMobs Marker Config")
	@Config.Name("LycanitesMobs")
	public static LycanitesConfig lycanitesmobs = new LycanitesConfig();

	@Config.Comment("Custom Position Markers Config")
	@Config.Name("Custom Positions")
	public static CustomPositionConfig customPosition = new CustomPositionConfig();

	@Config.Comment("Ruins Marker Config")
	@Config.Name("Ruins")
	public static RuinsConfig ruins = new RuinsConfig();

	@Config.Comment("Options for custom marker labels")
	@Config.Name("Lang Keys")
	public static LocalisationConfig localisation = new LocalisationConfig();

	@Config.Comment("Internal Config")
	@Config.Name("Internal")
	public static InternalConfig internal = new InternalConfig();

	@Config.Comment("Waystone Marker Config")
	@Config.Name("Waystones")
	public static WaystonesConfig waystones = new WaystonesConfig();

	@Config.Comment("Enchantment Marker Config")
	@Config.Name("Enchantments")
	public static EnchantmentConfig enchantments = new EnchantmentConfig();

	@Config.Comment("Overhaul Antique Atlas")
	@Config.Name("Antique Atlas Overhaul")
	public static AAOverhaulConfig overhaul = new AAOverhaulConfig();

	@Mod.EventBusSubscriber(modid = AntiqueAtlasAutoMarker.MODID)
	private static class EventHandler{

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(AntiqueAtlasAutoMarker.MODID)) {
				ConfigManager.sync(AntiqueAtlasAutoMarker.MODID, Config.Type.INSTANCE);
				AutoMarkSetting.reset();
				EnchMarkSetting.reset();
				waystones.resetSetting();
				iceandfire.resetSetting();
				lycanitesmobs.resetSetting();
				bettermineshafts.resetSetting();
				quark.resetSetting();
				dungeons2.resetSetting();
				doomlike.resetSetting();
				battletowers.resetSetting();
			}
		}
	}
}