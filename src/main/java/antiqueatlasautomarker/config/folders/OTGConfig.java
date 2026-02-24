package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.Tags;
import antiqueatlasautomarker.config.data.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@MixinConfig(name = Tags.MODID)
public class OTGConfig {

    @Config.Comment("Set to false to disable OTG Auto Markers. You should not have to disable this unless an OTG update causes immediate issues.")
    @Config.Name("OTG Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.otg.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "openterraingenerator", desired = true, warnIngame = false, reason = "No issue, auto disabled")
    @Config.RequiresMcRestart
    public boolean enabled = true;

    @Config.Comment({
            "List of OTG BO3 and BO4 objects to auto mark, these can be structures or entities.",
            "DEFAULT uses the corresponding lang key set in lang file or in the lang key config \"gui.aaam.marker.otg.<objectBranch>\"",
            "objectName and objectBranch can be specified like this:",
            "    objectName:objectBranch - Structure + Branch identifier, the branch must be attached to the structure",
            "    objectName - Structure match, only when the structure is spawned via biome configs from \"WorldBiomes\"",
            "    :objectBranch - Branch match, any time the branch is used",
            "Structures with a WeightedBranch option that can spawn absolutely nothing should have functional branch specified.",
            "   This prevents an \"empty\" suffix cache object from being marked.",
            "   :objectBranch can also be used in most cases, EXCEPT when a branch is used by multiple unique structures",
            "The default list primarily uses \"objectName:objectBranch\" when possible, as it shows the intended structure being marked."
    })
    @Config.Name("OTG Markers")
    public Map<String, AutoMarkSetting.Data> otgMarkers = new LinkedHashMap<>(Stream.of(
            // Safe
            new AutoMarkSetting.Data("SpawnHex:SpawnHexC9R9", true, "antiqueatlas:google", "DEFAULT"),
            new AutoMarkSetting.Data("AcaciaVillage", true, "antiqueatlas:village", "DEFAULT"), // Has too many variations, can't center properly
            new AutoMarkSetting.Data("Harbor:HarborC4R3", true, "antiqueatlas:village", "DEFAULT"),
            new AutoMarkSetting.Data("Herbalist:HerbalistC3R4", true, "antiqueatlas:village", "DEFAULT"),
            new AutoMarkSetting.Data("v_bunker_start", true, "antiqueatlas:village", "DEFAULT"), // Underground Village Bunker
            new AutoMarkSetting.Data(":c_bunker_mid_way", true, "antiqueatlas:waystone", "DEFAULT"), // 0 or more Waystone
            new AutoMarkSetting.Data(":v_bunker_mid_way", true, "antiqueatlas:waystone", "DEFAULT"),
            new AutoMarkSetting.Data(":o_bunker_mid_way", true, "antiqueatlas:waystone", "DEFAULT"),
            new AutoMarkSetting.Data("viking_main_peacefull", true, "antiqueatlas:village", "DEFAULT"), // Has too many variations, can't center properly
            // Vanilla
            new AutoMarkSetting.Data(":DeepTunnel_EntranceHighC2R1", true, "antiqueatlas:dungeon", "DEFAULT"), // Multiple variants use this entrance
            new AutoMarkSetting.Data("maintenance_hub", true, "antiqueatlas:dungeon", "DEFAULT"),
            new AutoMarkSetting.Data("Quarry:quarryC5R6", true, "antiqueatlas:pickaxe", "DEFAULT"),
            new AutoMarkSetting.Data("SwampHouseBig", true, "antiqueatlas:monsterspawner", "DEFAULT"),
            new AutoMarkSetting.Data("viking_main", true, "antiqueatlas:sword", "DEFAULT"),
            // 15k map destinations, one time and multiple spawning
            // Safe
            new AutoMarkSetting.Data("DaerocVillage:DaerocVillageC11R8", true, "antiqueatlas:village", "DEFAULT"),
            new AutoMarkSetting.Data("DaerocVillage:DaerocVillageC11R13", true, "antiqueatlas:nether_portal", "DEFAULT"), // End Portal
            new AutoMarkSetting.Data("origin:originC15R8", true, "antiqueatlas:village", "DEFAULT"),
            new AutoMarkSetting.Data("origin:originC5R4", true, "antiqueatlas:nether_portal", "DEFAULT"), // End Portal
            new AutoMarkSetting.Data("JungleVillage", true, "antiqueatlas:village", "DEFAULT"), // Has too many variations, can't center properly
            // Dungeon
            new AutoMarkSetting.Data("Church:ChurchC0R0", true, "antiqueatlas:dungeon", "DEFAULT"),
            new AutoMarkSetting.Data(":Dungeon01C1R1", true, "antiqueatlas:dungeon", "DEFAULT"),
            new AutoMarkSetting.Data(":Dungeon02C1R1", true, "antiqueatlas:dungeon", "DEFAULT"),
            new AutoMarkSetting.Data(":Dungeon03C1R1", true, "antiqueatlas:dungeon", "DEFAULT"),
            new AutoMarkSetting.Data("Mineshaft", true, "antiqueatlas:pickaxe", "DEFAULT"),
            new AutoMarkSetting.Data("W-Mineshaft", true, "antiqueatlas:pickaxe", "DEFAULT"),
            // 15 Random Stuff
            new AutoMarkSetting.Data("LegacySpawn:LegacySpawnC1R1", true, "antiqueatlas:google", "DEFAULT"),
            new AutoMarkSetting.Data("CastleRuins:CastleRuinsC2R3", true, "antiqueatlas:wizardtower", "DEFAULT"),
            new AutoMarkSetting.Data("FrozenShip-1", true, "antiqueatlas:ship", "DEFAULT"),
            new AutoMarkSetting.Data("FrozenShip-2", true, "antiqueatlas:ship", "DEFAULT"),
            new AutoMarkSetting.Data("FrozenShip-3", true, "antiqueatlas:ship", "DEFAULT"),
            new AutoMarkSetting.Data("FrozenShip-4", true, "antiqueatlas:ship", "DEFAULT"),
            new AutoMarkSetting.Data("TempleFrozen:TempleFrozenC1R2", true, "antiqueatlas:dungeon", "DEFAULT"),
            new AutoMarkSetting.Data("LavaTemple:LavaTempleC1R1", true, "antiqueatlas:dungeon", "DEFAULT"),
            // End of 15k
            // Gem Traders
            new AutoMarkSetting.Data("Trader_Castle:Trader_CastleC0R1", true, "antiqueatlas:brutalcoin", "DEFAULT"),
            new AutoMarkSetting.Data("Trader_Club:Trader_ClubC0R1", true, "antiqueatlas:brutalcoin", "DEFAULT"),
            new AutoMarkSetting.Data("Trader_Silo:Trader_SiloC1R0", true, "antiqueatlas:brutalcoin", "DEFAULT"),
            new AutoMarkSetting.Data("Trader_Swamp:Trader_SwampC1R0", true, "antiqueatlas:brutalcoin", "DEFAULT"),
            // Brutal
            new AutoMarkSetting.Data("brutal_warnpost", true, "antiqueatlas:radiation", "DEFAULT"),
            new AutoMarkSetting.Data("abyssal_tower:AbyssTowerC4R4", true, "antiqueatlas:megatower", "DEFAULT"),
            // Underneath
            new AutoMarkSetting.Data("access_duct:access_ductC1R0", true, "antiqueatlas:wrench", "DEFAULT"), // EZ Loot more bunker stuff
            new AutoMarkSetting.Data("portal_overworld_high", true, "antiqueatlas:nether_portal", "DEFAULT"),
            new AutoMarkSetting.Data("portal_overworld_low", true, "antiqueatlas:nether_portal", "DEFAULT"),
            // Nuclear Craft Bunkers
            new AutoMarkSetting.Data(":f_bunker_atrium_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_mid_nuclear_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_silo_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_apartements_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_small_01_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_small_02_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_small_03_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_small_04_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_small_05_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_small_06_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_small_07_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_small_08_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_storage_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_factory_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_mid_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_rich_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data(":f_bunker_tunnelC4R1", true, "antiqueatlas:wrench", "DEFAULT"),
            new AutoMarkSetting.Data("f_bunker_tunnel:DeepTunnel_EntranceHighC2R1", true, "antiqueatlas:wrench", "DEFAULT"), // Override other usage of DeepTunnel_EntranceHighC2R1
            // I&F
            new AutoMarkSetting.Data("cyclops_main:cyclops_mainC2R1", true, "antiqueatlas:red_x_small", "DEFAULT"),
            new AutoMarkSetting.Data("GorgonTemple:GorgonTemple_C6R6", true, "antiqueatlas:red_x_small", "DEFAULT"),
            new AutoMarkSetting.Data("GorgonTemple:GorgonTemple_02C6R6", true, "antiqueatlas:red_x_small", "DEFAULT"),
            new AutoMarkSetting.Data("GorgonTemple:GorgontempleGigiC6R6", true, "antiqueatlas:red_x_small", "DEFAULT"),
            new AutoMarkSetting.Data("Roost_Lightning:Roost_LightningC0R1", true, "antiqueatlas:dragon_gold", "DEFAULT"),
            // Flying Encounters
            // All
            new AutoMarkSetting.Data("Aimton:sub_Aimton", true, "antiqueatlas:dragon_red", "DEFAULT"),
            new AutoMarkSetting.Data("Al_Capone:sub_Al_Capone", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Bufffaton_Bill:sub_Buffaton_Bill", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("ChevaxiTon:sub_ChevaxiTon", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("GokuTon:sub_GokuTon", true, "antiqueatlas:dragon_gold", "DEFAULT"),
            new AutoMarkSetting.Data("JesterTon:sub_JesterTon", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("OldMan:sub_OldMan", true, "antiqueatlas:dragon_gold", "DEFAULT"),
            new AutoMarkSetting.Data("Tax_Collector:sub_Tax_Collector", true, "antiqueatlas:lycanites", "DEFAULT"),
            // City
            new AutoMarkSetting.Data("Banisher:sub_Banisher", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Corroder:sub_Corroder", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Decayer:sub_Decayer", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Dislocator:sub_Dislocator", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Dismounting_Raider:sub_Dismounting_Raider", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Relocator:sub_Relocator", true, "antiqueatlas:skull", "DEFAULT"),
            // Defiled
            new AutoMarkSetting.Data("Defiled_King:sub_Defiled_King", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Detonator:sub_Detonator", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Executioner:sub_Executioner", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Morbid_Skeleton:sub_Morbid_Skeleton", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Umbrium_Knight:sub_Umbrium_Knight", true, "antiqueatlas:lycanites", "DEFAULT"),
            // Desert
            new AutoMarkSetting.Data("Duster:sub_Duster", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Red_Assassin:sub_Red_Assassin", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Ruster:sub_Ruster", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Sampler:sub_Sampler", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Wanderer:sub_Wanderer", true, "antiqueatlas:skull", "DEFAULT"),
            // Ice
            new AutoMarkSetting.Data("Aerial_Dismounter:sub_Aerial_Dismounter", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Frigid_Warper:sub_Frigid_Warper", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Ice_King:sub_Ice_King", true, "antiqueatlas:dragon_blue", "DEFAULT"),
            new AutoMarkSetting.Data("NumbingTon:sub_NumbingTon", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Piercing_Stray:sub_Piercing_Stray", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Unfroster:sub_Unfroster", true, "antiqueatlas:lycanites", "DEFAULT"),
            // Jungle
            new AutoMarkSetting.Data("Aerial_Templar:sub_Aerial_Templar", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Crocodile_Skelly:sub_Crocodile_Skelly", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Decaying_Veteran:sub_Decaying_Veteran", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Flying_Rags:sub_Flying_Rags", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Knightly_Dismounter:sub_Knightly_Dismounter", true, "antiqueatlas:skull", "DEFAULT"),
            // Ocean
            new AutoMarkSetting.Data("Capiton:sub_Capiton", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Dismounting_Commotone:sub_Dismounting_Commotone", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("LuffyTon:sub_LuffyTon", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("QuartermasTon:sub_QuartermasTon", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Skelemate:sub_Skelemate", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Skeleswain:sub_Skeleswain", true, "antiqueatlas:lycanites", "DEFAULT"),
            // Parasite
            new AutoMarkSetting.Data("Dismisser:sub_Dismisser", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Dismounter:sub_Dismounter", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Grounder:sub_Grounder", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Inflicter:sub_Inflicter", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Perplexer:sub_Perplexer", true, "antiqueatlas:lycanites", "DEFAULT"),
            // Swamp
            new AutoMarkSetting.Data("Crone:sub_Crone", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("HagTon:sub_HagTon", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("PlagueTon:sub_PlagueTon", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Rancid_Skeleton:sub_Rancid_Skeleton", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Sackhead:sub_Sackhead", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Swampton:sub_Swampton", true, "antiqueatlas:lycanites", "DEFAULT"),
            // Temperate
            new AutoMarkSetting.Data("Down_Caster:sub_Down_Caster", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Fumbler:sub_Fumbler", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Impaler:sub_Impaler", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Impeder:sub_Impeder", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("Spinner:sub_Spinner", true, "antiqueatlas:lycanites", "DEFAULT"),
            // Viking
            new AutoMarkSetting.Data("Jotunn:sub_Jotunn", true, "antiqueatlas:dragon_blue", "DEFAULT"),
            new AutoMarkSetting.Data("LokiTon:sub_LokiTon", true, "antiqueatlas:dragon_blue", "DEFAULT"),
            new AutoMarkSetting.Data("Surt:sub_Surt", true, "antiqueatlas:lycanites", "DEFAULT"),
            new AutoMarkSetting.Data("ThorTon:sub_ThorTon", true, "antiqueatlas:dragon_gold", "DEFAULT"),
            new AutoMarkSetting.Data("Tyr:sub_Tyr", true, "antiqueatlas:lycanites", "DEFAULT"),
            // Wastelands
            new AutoMarkSetting.Data("Asher:sub_Asher", true, "antiqueatlas:dragon_red", "DEFAULT"),
            new AutoMarkSetting.Data("Dust_Scrapper:sub_Dust_Scrapper", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Launcher:sub_Launcher", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Piercing_Duster:sub_Piercing_Duster", true, "antiqueatlas:skull", "DEFAULT"),
            new AutoMarkSetting.Data("Scrapper:sub_Scrapper", true, "antiqueatlas:skull", "DEFAULT")
            // End of Encounters
            // Debug Mark Everything
//            new AutoMarkSetting.Data("BO3:START", true, "antiqueatlas:diamond", "DEFAULT"),
//            new AutoMarkSetting.Data("BO3:BRANCH", true, "antiqueatlas:bed", "DEFAULT"),
//            new AutoMarkSetting.Data("BO4:START", true, "antiqueatlas:diamond", "DEFAULT"),
//            new AutoMarkSetting.Data("BO4:BRANCH", true, "antiqueatlas:bed", "DEFAULT")
    ).collect(Collectors.toMap(data -> data.context, Function.identity())));
}
