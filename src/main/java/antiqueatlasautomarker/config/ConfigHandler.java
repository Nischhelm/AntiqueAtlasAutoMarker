package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AntiqueAtlasAutoMarker.MODID)
public class ConfigHandler {
	@Config.Comment("Pattern: structureType(=context); enabled; marker label; marker type. \n" +
			"Clients can disable structure markers here in order to decline getting this type of structure marker, no matter if the option is enabled on server\n"+
			"The marker label and type will use the settings of the client that first discovers the structure marker, \n" +
			"except if client sets label to DEFAULT, in which case the server label (and type if its an AARC marker) is used\n" +
			"same can be done with client type in which case the server type is used"+
			"Marker types are defined in AntiqueAtlas Marker config and can be customised there using ResourceLoader mod.\n" +
			"DEFAULT Marker Label names (have to be set server side in order to work):\n" +
			"wildWaystone: DEFAULT marks the wild waystone with the name it will generate as on first activation\n" +
			"lycanite: DEFAULT marks the lyca dungeon with the default name of the dungeon, like \"Ashen Mausoleum\"" +
			"doomlikeDungeon: DEFAULT uses the name of the dungeon's theme, like \"Urban Doomlike Dungeon\"\n" +
			"battleTower: DEFAULT uses the battle tower type name and adds \"Reverse\" if it is upside down.")
	@Config.Name("Auto marked structures - needs AAAM on server")
	public static String[] structureMarkers = {
			"wildWaystone; true; gui.antiqueatlas.marker.wildWaystone; antiqueatlas:waystone",
			"fireDragon; true; entity.firedragon.name; antiqueatlas:dragon_red",
			"iceDragon; true; entity.icedragon.name; antiqueatlas:dragon_blue",
			"lightningDragon; true; entity.lightningdragon.name; antiqueatlas:dragon_gold",
			"cyclopsCave; true; entity.cyclops.name; antiqueatlas:red_x_small",
			"hydraCave; true; entity.if_hydra.name; antiqueatlas:dragon_green",
			"lycanite; true; DEFAULT; antiqueatlas:dungeon",
			"betterMineshaft; true; gui.antiqueatlas.marker.betterMineshaft; antiqueatlas:tracks",
			"doomlike; true; DEFAULT; antiqueatlas:dungeon",
			"dungeons2; true; gui.antiqueatlas.marker.dungeons2; antiqueatlas:dungeon",
			"battleTower; true; DEFAULT; antiqueatlas:tower",
			"quarkPirateShip; true; gui.antiqueatlas.marker.quarkPirateShip; antiqueatlas:skull",
			"ruins; true; See Ruins config below; Set to false to disable all ruins markers",
			"AARCAddon; true; See AARC config; Set to true on server to turn AARC Global markers into AAAM local markers"
	};

	@Config.Comment("Lycanite Dungeons don't have good displayable names. Change this if you want them to be displayed differently.")
	@Config.Name("Lycanite Dungeon Names")
	public static String[] lycaDungeonNames = {
			"aberrantstation; gui.antiqueatlas.marker.lycaAberrantStation",
			"aberrantstation_random; gui.antiqueatlas.marker.lycaAberrantStationRandom",
			"ashenmausoleum; gui.antiqueatlas.marker.lycaAshenMausoleum",
			"demonictemple; gui.antiqueatlas.marker.lycaDemonicTemple",
			"desertcrypts; gui.antiqueatlas.marker.lycaDesertCrypts",
			"lushtomb; gui.antiqueatlas.marker.lycaLushTomb",
			"shadowlabyrinth; gui.antiqueatlas.marker.lycaShadowLabyrinth",
			"shadowlabyrinth_random; gui.antiqueatlas.marker.lycaShadowLabyrinthRandom",
			"streamshrine; gui.antiqueatlas.marker.lycaStreamShrine"
	};

	@Config.Comment("Pattern: interactionType; enabled; marker label; marker type.")
	@Config.Name("Auto Markers on player interaction")
	public static String[] interactionMarkers = {
			"activatedWaystone; true; DEFAULT; antiqueatlas:waystone",
			"enchantmentTrade; true; DEFAULT; antiqueatlas:book"
	};

	@Config.Comment("Pattern: dimension; posX; posZ; discoverX; discoverZ; marker label; marker type.\n" +
					"where the first set of coordinates are where the marker will be located on the atlas,\n" +
					"and the second set of coordinates will be where the player has to be to discover that marker.\n" +
					"Will get set on server world creation. Only needs to be defined on server")
	@Config.Name("Custom Position Markers")
	@Config.RequiresWorldRestart
	public static String[] customPositionMarkers = {
			//"0;2000;0;0;0;Test Custom Position Marker;antiqueatlas:diamond"
	};

	@Config.Comment("Pattern: ruinsName; enabled; marker label; marker type.")
	@Config.Name("Ruins Structure Markers")
	public static String[] ruinsMarkers = {
			"Tower-ruined-short; false; gui.antiqueatlas.marker.btRuinedShort; antiqueatlas:ruins",
			"Tower-edit; true; gui.antiqueatlas.marker.btRuined; antiqueatlas:tower",
			"TowerEasy; true; gui.antiqueatlas.marker.btRuinedEasy; antiqueatlas:tower",
			"TowerMedium; true; gui.antiqueatlas.marker.btRuinedMedium; antiqueatlas:tower",
			"TowerHard; true; gui.antiqueatlas.marker.btRuinedHard; antiqueatlas:tower",
			"ZombieHut; false; gui.antiqueatlas.marker.zombieHut; antiqueatlas:sword",
			"SkyCastle; true; gui.antiqueatlas.marker.castleSky; antiqueatlas:diamond",
			"UnderwaterBase; false; gui.antiqueatlas.marker.baseUnderwater; antiqueatlas:diamond",
			"Floater; true; gui.antiqueatlas.marker.shipVillager; antiqueatlas:diamond",
			"PirateShip; true; gui.antiqueatlas.marker.shipPirate; antiqueatlas:ship",
			"StoneHouseM; false; gui.antiqueatlas.marker.houseStoneM; antiqueatlas:ruins",
			"PortalShrine; true; gui.antiqueatlas.marker.netherPortal; antiqueatlas:nether_portal",
			"GraveyardHaunted; true; gui.antiqueatlas.marker.graveyard; antiqueatlas:diamond",
			"GateUnderGlass; false; gui.antiqueatlas.marker.netherPortal; antiqueatlas:nether_portal",
			"Mausoleum; false; gui.antiqueatlas.marker.mausoleum; antiqueatlas:sword",
			"ArrowTrapTomb; false; gui.antiqueatlas.marker.tombArrowTrap; antiqueatlas:diamond",
			"SnowCastleSpire; false; gui.antiqueatlas.marker.castleSnowSpire; antiqueatlas:sword",
			"NetherShrine; true; gui.antiqueatlas.marker.netherPortal; antiqueatlas:nether_portal"
	};

	@Config.Comment("AAAM can fire events when players receive a structure marker to enable mods to do custom actions for specific markers. Set to false for performance if no mods in the pack subscribe to the event")
	@Config.Name("Fire ReceivedStructureMarkerEvents on client")
	public static boolean fireReceivedMarkerEvent = true;

	@Config.Comment("Update Waystone marker names every time a player interacts with a waystone (disable for performance)")
	@Config.Name("Activated Waystones: Auto update name")
	public static boolean autoUpdateWaystones = true;

	@Config.Comment("Set to true to always put markers for Waystones you interact with, instead of only marking the ones you activate.")
	@Config.Name("Activated Waystones: Always mark")
	public static boolean alwaysMarkWaystones = false;

	@Config.Comment("Use \"ALL\" to mark all trades. Otherwise pattern is modid:enchname; minLvlToMark; optionalAbbreviation")
	@Config.Name("Enchantments Trades to mark")
	public static String[] enchantmentsToMark = {
			"minecraft:feather_falling;4;FF",
			"minecraft:respiration;3",
			"minecraft:aqua_affinity;;Aqua Aff",
			"minecraft:depth_strider;3;Depth Str",
			"minecraft:sweeping;2;Sweep Edge",
			"minecraft:efficiency;3;Eff",
			"minecraft:silk_touch",
			"minecraft:unbreaking;2",
			"minecraft:fortune;3",
			"minecraft:power;3",
			"minecraft:infinity",
			"minecraft:mending",
			"somanyenchantments:advancedefficiency;3;Adv Eff",
			"somanyenchantments:advancedsharpness;3;Adv Sharp",
			"somanyenchantments:advancedsmite;3;Adv Smite",
			"somanyenchantments:fieryedge",
			"somanyenchantments:purification;3;Purif",
			"somanyenchantments:rune_piercingcapabilities;3;R:PC",
			"somanyenchantments:swifterslashes;3;Swifter",
			"somanyenchantments:parry",
			"somanyenchantments:lifesteal",
			"somanyenchantments:clearsky;4;Clearskies",
			"somanyenchantments:smelter",
			"somanyenchantments:empowereddefence;;Emp Def",
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
			"somanyenchantments:afa;;Adv FA",
			"somanyenchantments:swiper;2;Arc Sl",
			"somanyenchantments:afl;;Adv Flame",
			"somanyenchantments:splitshot;3",
			"somanyenchantments:strengthenedvitality;2;Str Vitality",
			"somanyenchantments:burningshield;3;Burn Shield",
			"somanyenchantments:naturalblocking;;Nat Block",
			"somanyenchantments:rune_arrowpiercing;2;R:AP",
			"somanyenchantments:innerberserk;4",
			"somanyenchantments:luckmagnification;;Luck Mag",
			"somanyenchantments:lightweight;2",
			"somanyenchantments:underwaterstrider;2;UW Strider",
			"somanyenchantments:frenzy",
			"somanyenchantments:evasion",
			"somanyenchantments:bluntness;3",
			"somanyenchantments:english;3;Subj Eng",
			"somanyenchantments:pe;3;Subj PE",
			"somanyenchantments:supremesharpness;;Sup Sharp",
			"somanyenchantments:supremesmite;;Sup Smite",
			"somanyenchantments:curseofpossession;;C. of Poss",
			"somanyenchantments:sfa;;Sup FA",
			"somanyenchantments:advancedmending;;Adv Mend",
			"somanyenchantments:sfl",
			"somanyenchantments:upgradedpotentials;;Upg Pot",
			"somanyenchantments:adept",
			"somanyenchantments:magmawalker;2",
			"spartanweaponry:rapid_load;2",
			"spartanweaponry:spreadshot",
			"spartanweaponry:sharpshooter;2",
			"spartanshields:spikes;2",
			"mujmajnkraftsbettersurvival:vampirism",
			"mujmajnkraftsbettersurvival:agility",
			"mujmajnkraftsbettersurvival:arrowrecovery;3;Arr Rec",
			"mujmajnkraftsbettersurvival:combo;2",
			"mujmajnkraftsbettersurvival:highjump;2",
			"mujmajnkraftsbettersurvival:smelting",
			"mujmajnkraftsbettersurvival:education;2",
			"mujmajnkraftsbettersurvival:versatility;;Versa",
			"mujmajnkraftsbettersurvival:multishot;3",
			"mujmajnkraftsbettersurvival:range",
			"mujmajnkraftsbettersurvival:rapidfire",
			"charm:magnetic",
			"grapplinghook:doublejumpenchantment"
	};

	@Config.Comment("Overhaul Antique Atlas")
	@Config.Name("Antique Atlas Overhaul ")
	public static AAOverhaulConfig overhaul = new AAOverhaulConfig();

	public static class AAOverhaulConfig {
		@Config.Comment("AA Global Markers are bugged + laggy. Built-in global markers (village + end city) already get rerouted to AAAM structure markers. Keep this enabled to reroute any other modded markers to AAAM structure markers. Disabling this can lead to unexpected behavior.")
		@Config.Name("Reroute modded Global Markers")
		public boolean rerouteGlobalMarkers = true;

		@Config.Comment("Antique Atlas sends packets to all players whenever anything is added to or removed from any atlas (markers/tiles). Set to true to only send packets to players with the modified atlas in inventory.")
		@Config.Name("Only send to all holding the atlas")
		@Config.RequiresMcRestart
		public boolean sendToAllHolding = true;

		@Config.Comment("Whenever Antique Atlas checks for atlases in a players inventory it forgets to also check the offhand. Set to true to check offhand as well.")
		@Config.Name("Also check player offhand for atlases")
		@Config.RequiresMcRestart
		public boolean checkOffhand = true;

		@Config.Comment("Antique Atlas uses a questionable regex to check if a marker label is a lang key (not allowing numbers for example), instead of using I18n.hasKey. It also only allows one parameter for parameterised lang keys. Both get fixed by this.")
		@Config.Name("Fix Atlas Marker Lang Keys")
		@Config.RequiresMcRestart
		public boolean fixLangKeys = true;

		@Config.Comment("When combining atlases, the stack size of the output slot is not set correctly, resulting in a dupe. This fixes it.")
		@Config.Name("Fix Atlas Combining Recipe Dupe")
		@Config.RequiresMcRestart
        public boolean fixCombiningRecipe = true;

		@Config.Comment("Markers data is sent in one packet per dimension, which can get really large and lag the server. Keep this enabled to send the markers in chunks of 100 markers per packet to reduce lag on player login.")
		@Config.Name("Marker data in smaller packets")
		@Config.RequiresMcRestart
		public boolean markerPacketChunking = true;
	}

	@Config.Name("Send Debug Messages")
	public static boolean doDebugLogs = false;

	@Mod.EventBusSubscriber(modid = AntiqueAtlasAutoMarker.MODID)
	private static class EventHandler{

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(AntiqueAtlasAutoMarker.MODID)) {
				ConfigManager.sync(AntiqueAtlasAutoMarker.MODID, Config.Type.INSTANCE);
				AutoMarkSetting.reset();
				EnchMarkSetting.reset();
				ConfigProvider.init();
			}
		}
	}
}