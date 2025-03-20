package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.folders.AAOverhaulConfig;
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
			"same can be done with client type in which case the server type is used\n"+
			"Marker types are defined in AntiqueAtlas Marker config and can be customised there using ResourceLoader mod.\n" +
			"DEFAULT Marker Label names (have to be set server side in order to work):\n" +
			"lycanite: DEFAULT marks the lyca dungeon with the default name of the dungeon, like \"Ashen Mausoleum\"" +
			"doomlikeDungeon: DEFAULT uses the name of the dungeon's theme, like \"Urban Doomlike Dungeon\"\n" +
			"battleTower: DEFAULT uses the battle tower type name and adds \"Reverse\" if it is upside down." +
			"inf mobs: DEFAULT uses their localised mob name (ex: Fire Dragon)\n" +
			"for all other ones, DEFAULT will use the lang key defined in the lang file or in the lang key config below")
	@Config.Name("Auto marked structures - needs AAAM on server")
	public static String[] structureMarkers = {
			"wildWaystone; true; DEFAULT; antiqueatlas:waystone",
			"fireDragon; true; DEFAULT; antiqueatlas:dragon_red",
			"iceDragon; true; DEFAULT; antiqueatlas:dragon_blue",
			"lightningDragon; true; DEFAULT; antiqueatlas:dragon_gold",
			"cyclopsCave; true; DEFAULT; antiqueatlas:red_x_small",
			"hydraCave; true; DEFAULT; antiqueatlas:dragon_green",
			"lycanite; true; DEFAULT; antiqueatlas:dungeon",
			"betterMineshaft; false; DEFAULT; antiqueatlas:tracks",
			"doomlike; false; DEFAULT; antiqueatlas:dungeon",
			"dungeons2; false; DEFAULT; antiqueatlas:dungeon",
			"battleTower; true; DEFAULT; antiqueatlas:tower",
			"quarkPirateShip; true; DEFAULT; antiqueatlas:skull",
			"ruins; true; See Ruins config below; Set to false to disable all ruins markers",
			"AARCAddon; true; See AARC config; Set to true on server to turn AARC Global markers into AAAM local markers"
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

	@Config.Comment("Pattern: ruinsName; enabled; marker label; marker type. DEFAULT uses the corresponding lang key set in lang file or in the lang config here")
	@Config.Name("Ruins Structure Markers")
	public static String[] ruinsMarkers = {
			"Tower-ruined-short; false; DEFAULT; antiqueatlas:ruins",
			"Tower-edit; true; DEFAULT; antiqueatlas:tower",
			"TowerEasy; true; DEFAULT; antiqueatlas:tower",
			"TowerMedium; true; DEFAULT; antiqueatlas:tower",
			"TowerHard; true; DEFAULT; antiqueatlas:tower",
			"ZombieHut; false; DEFAULT; antiqueatlas:sword",
			"SkyCastle; true; DEFAULT; antiqueatlas:diamond",
			"UnderwaterBase; false; DEFAULT; antiqueatlas:diamond",
			"Floater; true; DEFAULT; antiqueatlas:diamond",
			"PirateShip; true; DEFAULT; antiqueatlas:ship",
			"StoneHouseM; false; DEFAULT; antiqueatlas:ruins",
			"PortalShrine; true; DEFAULT; antiqueatlas:nether_portal",
			"GraveyardHaunted; true; DEFAULT; antiqueatlas:diamond",
			"GateUnderGlass; false; DEFAULT; antiqueatlas:nether_portal",
			"Mausoleum; false; DEFAULT; antiqueatlas:sword",
			"ArrowTrapTomb; false; DEFAULT; antiqueatlas:diamond",
			"SnowCastleSpire; false; DEFAULT; antiqueatlas:sword",
			"NetherShrine; true; DEFAULT; antiqueatlas:nether_portal"
	};

	@Config.Comment("AAAM will use these lang keys if there is no other lang file present that would translate those lang keys (internal name of the lang keys: gui.aaam.marker.xxx). Use this to rename structure markers, allows players with other languages seeing the same marker in their own language.")
	@Config.Name("Default Lang Keys")
	public static String[] langKeys = {
			"wildWaystone=Wild Waystone",
			"betterMineshaft=Mineshaft",
			"dungeons2=Dungeon2",
			"quarkPirateShip=Pirates",
			"Tower-ruined-short=Ruined BT Short",
			"Tower-edit=Ruined BT",
			"TowerEasy=Ruined BT",
			"TowerMedium=Ruined BT",
			"TowerHard=Ruined BT",
			"ZombieHut=Two Zombie Spawners",
			"SkyCastle=Sky Castle",
			"UnderwaterBase=Underwater Base",
			"Floater=Floater",
			"PirateShip=XP Ship",
			"StoneHouseM=Small Starter House",
			"PortalShrine=Nether Portal",
			"GraveyardHaunted=Graveyard",
			"GateUnderGlass=Nether Portal",
			"Mausoleum=Eight Zombie Spawners",
			"ArrowTrapTomb=Simple Dungeon Loot",
			"SnowCastleSpire=Blaze Spawners",
			"NetherShrine=Nether Portal"
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
			"minecraft:protection;2;Prot",
			"minecraft:feather_falling;4;FF",
			"minecraft:respiration;3",
			"minecraft:aqua_affinity;;Aqua Aff",
			"minecraft:depth_strider;3;Depth Str",
			"minecraft:sweeping;2;Sweep Edge",
			"minecraft:efficiency;3;Eff",
			"minecraft:silk_touch",
			"minecraft:unbreaking;2",
			"minecraft:fortune;2",
			"minecraft:power;3",
			"minecraft:infinity",
			"minecraft:mending",
			"somanyenchantments:advancedefficency;2;Adv Eff",
			"somanyenchantments:advancedsharpness;2;Adv Sharp",
			"somanyenchantments:fieryedge",
			"somanyenchantments:purification;2;Purif",
			"somanyenchantments:rune_piercingcapabilities;;R:PC",
			"somanyenchantments:swifterslashes;2;Swifter",
			"somanyenchantments:parry",
			"somanyenchantments:lifesteal",
			"somanyenchantments:clearsky;3;Clearskies",
			"somanyenchantments:smelter",
			"somanyenchantments:empowereddefence;;Emp Def",
			"somanyenchantments:strafe;2",
			"somanyenchantments:advancedlooting;;Adv Loot",
			"somanyenchantments:ashdestroyer;2",
			"somanyenchantments:desolator;2",
			"somanyenchantments:purgingblade;2;Purging",
			"somanyenchantments:viper;2",
			"somanyenchantments:advancedpower;3",
			"somanyenchantments:envenomed;2",
			"somanyenchantments:advancedlure;2;Adv Lure",
			"somanyenchantments:advancedluckofthesea;2;Adv LotS",
			"somanyenchantments:advancedfeatherfalling;3;Adv FF",
			"somanyenchantments:advancedthorns;2;Adv Thorns",
			"somanyenchantments:advancedprotection;2;Adv Prot",
			"somanyenchantments:atomicdeconstructor;;Atomic",
			"somanyenchantments:disarmament;4;Disarma",
			"somanyenchantments:afa;;Adv FA",
			"somanyenchantments:swiper;;Arc Sl",
			"somanyenchantments:afl;;Adv Flame",
			"somanyenchantments:splitshot;3",
			"somanyenchantments:strengthenedvitality;2;Str Vitality",
			"somanyenchantments:burningshield;3;Burn Shield",
			"somanyenchantments:naturalblocking;;Nat Block",
			"somanyenchantments:rune_arrowpiercing;2;R:AP",
			"somanyenchantments:innerberserk;3",
			"somanyenchantments:luckmagnification;;Luck Mag",
			"somanyenchantments:lightweight",
			"somanyenchantments:underwaterstrider;2;UW Strider",
			"somanyenchantments:frenzy",
			"somanyenchantments:evasion",
			"somanyenchantments:bluntness;2",
			"somanyenchantments:english;2;Subj Eng",
			"somanyenchantments:pe;3;Subj PE",
			"somanyenchantments:supremesharpness;;Sup Sharp",
			"somanyenchantments:supremesmite;4;Sup Smite",
			"somanyenchantments:curseofpossession;;C. of Poss",
			"somanyenchantments:sfa;;Sup FA",
			"somanyenchantments:advancedmending;;Adv Mend",
			"somanyenchantments:sfl;;Sup Flame",
			"somanyenchantments:upgrade;;Upg Pot",
			"somanyenchantments:adept",
			"somanyenchantments:magmawalker;2",
			"spartanweaponry:rapid_load;2",
			"spartanweaponry:spreadshot",
			"spartanweaponry:sharpshooter;2",
			"spartanshields:spikes;2",
			"mujmajnkraftsbettersurvival:vampirism",
			"mujmajnkraftsbettersurvival:agility",
			"mujmajnkraftsbettersurvival:arrowrecovery;3;Arr Rec",
			"mujmajnkraftsbettersurvival:combo",
			"mujmajnkraftsbettersurvival:highjump;2",
			"mujmajnkraftsbettersurvival:smelting",
			"mujmajnkraftsbettersurvival:education",
			"mujmajnkraftsbettersurvival:versatility;;Versa",
			"mujmajnkraftsbettersurvival:multishot;3",
			"mujmajnkraftsbettersurvival:range",
			"mujmajnkraftsbettersurvival:rapidfire",
			"charm:magnetic",
			"grapplemod:doublejumpenchantment"
	};

	@Config.Comment("Overhaul Antique Atlas")
	@Config.Name("Antique Atlas Overhaul ")
	public static AAOverhaulConfig overhaul = new AAOverhaulConfig();

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
			}
		}
	}
}