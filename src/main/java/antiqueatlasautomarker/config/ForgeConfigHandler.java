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
				"wildWaystone; true; 500; Wild Waystone; antiqueatlas:waystone",
				"fireDragon; true; 500; Fire Dragon; antiqueatlas:dragon_red",
				"iceDragon; true; 500; Ice Dragon; antiqueatlas:dragon_blue",
				"lightningDragon; true; 500; Lightning Dragon; antiqueatlas:dragon_gold",
				"cyclopsCave; true; 500; Cyclops; antiqueatlas:red_x_small",
				"hydraCave; true; 500; Hydra; antiqueatlas:dragon_green",
				"lycaniteDungeon; true; 500; DEFAULT; antiqueatlas:dungeon",
				"betterMineshaft; true; 500; Mineshaft; antiqueatlas:tracks",
				"doomlikeDungeon; true; 500; DEFAULT; antiqueatlas:dungeon",
				"dungeons2; true; 500; Dungeon2; antiqueatlas:dungeon",
				"AARCAddonMarker; true; 500; See AARC config; Enable to turn AARC Global markers into AAAM local markers"
		};
	}

	public static class ClientConfig {
		@Config.Comment("Pattern: interactionType; enabled; marker label; marker type. \n")
		@Config.Name("Auto Markers on player interaction")
		public String[] interactionMarkers = {
				"activatedWaystone; true; DEFAULT; antiqueatlas:waystone",
				"enchantmentTrade; true; DEFAULT; antiqueatlas:book"
		};

		@Config.Comment("Update Waystone marker names every time a player interacts with a waystone (disable for performance)")
		@Config.Name("Activated Waystones: Auto update name")
		public boolean autoUpdateWaystones = true;

		@Config.Comment("Set to true to always put markers for Waystones you interact with, instead of only marking the ones you activate.")
		@Config.Name("Activated Waystones: Always mark")
		public boolean alwaysMarkWaystones = false;

		@Config.Comment("Use \"ALL\" to mark all trades. Otherwise pattern is modid:enchname; minLvlToMark; optionalAbbreviation")
		@Config.Name("Enchantments Trades to mark")
		public String[] enchantmentsToMark = {
				"minecraft:feather_falling;4;FF",
				"minecraft:respiration;3",
				"minecraft:aqua_affinity;1;Aqua Aff",
				"minecraft:depth_strider;3;Depth Str",
				"minecraft:sweeping;2;Sweep Edge",
				"minecraft:efficiency;3;Eff",
				"minecraft:silk_touch;1",
				"minecraft:unbreaking;2",
				"minecraft:fortune;3",
				"minecraft:power;3",
				"minecraft:infinity;1",
				"minecraft:mending;1",
				"somanyenchantments:advancedefficiency;3;Adv Eff",
				"somanyenchantments:advancedsharpness;3;Adv Sharp",
				"somanyenchantments:advancedsmite;3;Adv Smite",
				"somanyenchantments:fieryedge;1",
				"somanyenchantments:purification;3;Purif",
				"somanyenchantments:rune_piercingcapabilities;3;R:PC",
				"somanyenchantments:swifterslashes;3;Swifter",
				"somanyenchantments:parry;1",
				"somanyenchantments:lifesteal;1",
				"somanyenchantments:clearsky;4;Clearskies",
				"somanyenchantments:smelter;1",
				"somanyenchantments:empowereddefence;1;Emp Def",
				"somanyenchantments:strafe;2",
				"somanyenchantments:advancedlooting;2;Adv Loot",
				"somanyenchantments:ashdestroyer;3",
				"somanyenchantments:desolator;3",
				"somanyenchantments:purgingblade;3",
				"somanyenchantments:viper;3",
				"somanyenchantments:advancedpower;3",
				"somanyenchantments:envenomed;2",
				"somanyenchantments:advancedlure;2;Adv Lure",
				"somanyenchantments:advancedluckofthesea;2;Adv LotS",
				"somanyenchantments:advancedfeatherfalling;3;Adv FF",
				"somanyenchantments:advancedthorns;2;Adv Thorns",
				"somanyenchantments:advancedprotection;2;Adv Prot",
				"somanyenchantments:atomicdeconstructor;2;At Dec",
				"somanyenchantments:disarmament;4;Disarma",
				"somanyenchantments:afa;1;Adv FA",
				"somanyenchantments:swiper;2;Arc Sl",
				"somanyenchantments:afl;1;Adv Flame",
				"somanyenchantments:splitshot;3",
				"somanyenchantments:strengthenedvitality;2;Str Vitality",
				"somanyenchantments:burningshield;3;Burn Shield",
				"somanyenchantments:naturalblocking;1;Nat Block",
				"somanyenchantments:rune_arrowpiercing;2;R:AP",
				"somanyenchantments:innerberserk;4",
				"somanyenchantments:luckmagnification;1;Luck Mag",
				"somanyenchantments:lightweight;2",
				"somanyenchantments:underwaterstrider;2;UW Strider",
				"somanyenchantments:frenzy;1",
				"somanyenchantments:evasion;1",
				"somanyenchantments:bluntness;3",
				"somanyenchantments:english;3;Subj Eng",
				"somanyenchantments:pe;3;Subj PE",
				"somanyenchantments:supremesharpness;1;Sup Sharp",
				"somanyenchantments:supremesmite;1;Sup Smite",
				"somanyenchantments:curseofpossession;1;C. of Poss",
				"somanyenchantments:sfa;1;Sup FA",
				"somanyenchantments:advancedmending;1;Adv Mend",
				"somanyenchantments:sfl;1",
				"somanyenchantments:upgradedpotentials;1;Upg Pot",
				"somanyenchantments:adept;1",
				"somanyenchantments:magmawalker;2",
				"spartanweaponry:rapid_load;2",
				"spartanweaponry:spreadshot;1",
				"spartanweaponry:sharpshooter;2",
				"spartanshields:spikes;2",
				"mujmajnkraftsbettersurvival:vampirism;1",
				"mujmajnkraftsbettersurvival:agility;1",
				"mujmajnkraftsbettersurvival:arrowrecovery;3;Arr Rec",
				"mujmajnkraftsbettersurvival:combo;2",
				"mujmajnkraftsbettersurvival:highjump;2",
				"mujmajnkraftsbettersurvival:smelting;1",
				"mujmajnkraftsbettersurvival:education;2",
				"mujmajnkraftsbettersurvival:versatility;1;Versa",
				"mujmajnkraftsbettersurvival:multishot;3",
				"mujmajnkraftsbettersurvival:range;1",
				"mujmajnkraftsbettersurvival:rapidfire;1",
				"charm:magnetic;1",
				"grapplinghook:doublejumpenchantment;1"
		};
	}

	@Mod.EventBusSubscriber(modid = AntiqueAtlasAutoMarker.MODID)
	private static class EventHandler{

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(AntiqueAtlasAutoMarker.MODID)) {
				ConfigManager.sync(AntiqueAtlasAutoMarker.MODID, Config.Type.INSTANCE);
				AutoMarkSetting.reset();
				EnchMarkSetting.reset();
			}
		}
	}
}