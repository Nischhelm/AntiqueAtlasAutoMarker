package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.Tags;
import antiqueatlasautomarker.config.data.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

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
    public Map<String, AutoMarkSetting.Data> otgMarkers = new LinkedHashMap<>();

    public void initOTGMarkers() {
        Arrays.asList(
                // Safe
                new AutoMarkSetting.Data("otg:SpawnHex:SpawnHexC9R9", true, "antiqueatlas:google", "DEFAULT"),
                new AutoMarkSetting.Data("otg:AcaciaVillage", true, "antiqueatlas:village", "DEFAULT"), // Has too many variations, can't center properly
                new AutoMarkSetting.Data("otg:Harbor:HarborC4R3", true, "antiqueatlas:village", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Herbalist:HerbalistC3R4", true, "antiqueatlas:village", "DEFAULT"),
                new AutoMarkSetting.Data("otg:v_bunker_start", true, "antiqueatlas:village", "DEFAULT"), // Underground Village Bunker
                new AutoMarkSetting.Data("otg::c_bunker_mid_way", true, "antiqueatlas:waystone", "DEFAULT"), // 0 or more Waystone
                new AutoMarkSetting.Data("otg::v_bunker_mid_way", true, "antiqueatlas:waystone", "DEFAULT"),
                new AutoMarkSetting.Data("otg::o_bunker_mid_way", true, "antiqueatlas:waystone", "DEFAULT"),
                new AutoMarkSetting.Data("otg:viking_main_peacefull", true, "antiqueatlas:village", "DEFAULT"), // Has too many variations, can't center properly
                // Vanilla
                new AutoMarkSetting.Data("otg::DeepTunnel_EntranceHighC2R1", true, "antiqueatlas:dungeon", "DEFAULT"), // Multiple variants use this entrance
                new AutoMarkSetting.Data("otg:maintenance_hub", true, "antiqueatlas:dungeon", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Quarry:quarryC5R6", true, "antiqueatlas:pickaxe", "DEFAULT"),
                new AutoMarkSetting.Data("otg:SwampHouseBig", true, "antiqueatlas:monsterspawner", "DEFAULT"),
                new AutoMarkSetting.Data("otg:viking_main", true, "antiqueatlas:sword", "DEFAULT"),
                // 15k map destinations, one time and multiple spawning
                // Safe
                new AutoMarkSetting.Data("otg:DaerocVillage:DaerocVillageC11R8", true, "antiqueatlas:village", "DEFAULT"),
                new AutoMarkSetting.Data("otg:DaerocVillage:DaerocVillageC11R13", true, "antiqueatlas:nether_portal", "DEFAULT"), // End Portal
                new AutoMarkSetting.Data("otg:origin:originC15R8", true, "antiqueatlas:village", "DEFAULT"),
                new AutoMarkSetting.Data("otg:origin:originC5R4", true, "antiqueatlas:nether_portal", "DEFAULT"), // End Portal
                new AutoMarkSetting.Data("otg:JungleVillage", true, "antiqueatlas:village", "DEFAULT"), // Has too many variations, can't center properly
                // Dungeon
                new AutoMarkSetting.Data("otg:Church:ChurchC0R0", true, "antiqueatlas:dungeon", "DEFAULT"),
                new AutoMarkSetting.Data("otg::Dungeon01C1R1", true, "antiqueatlas:dungeon", "DEFAULT"),
                new AutoMarkSetting.Data("otg::Dungeon02C1R1", true, "antiqueatlas:dungeon", "DEFAULT"),
                new AutoMarkSetting.Data("otg::Dungeon03C1R1", true, "antiqueatlas:dungeon", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Mineshaft", true, "antiqueatlas:pickaxe", "DEFAULT"),
                new AutoMarkSetting.Data("otg:W-Mineshaft", true, "antiqueatlas:pickaxe", "DEFAULT"),
                // 15 Random Stuff
                new AutoMarkSetting.Data("otg:LegacySpawn:LegacySpawnC1R1", true, "antiqueatlas:google", "DEFAULT"),
                new AutoMarkSetting.Data("otg:CastleRuins:CastleRuinsC2R3", true, "antiqueatlas:wizardtower", "DEFAULT"),
                new AutoMarkSetting.Data("otg:FrozenShip-1", true, "antiqueatlas:ship", "DEFAULT"),
                new AutoMarkSetting.Data("otg:FrozenShip-2", true, "antiqueatlas:ship", "DEFAULT"),
                new AutoMarkSetting.Data("otg:FrozenShip-3", true, "antiqueatlas:ship", "DEFAULT"),
                new AutoMarkSetting.Data("otg:FrozenShip-4", true, "antiqueatlas:ship", "DEFAULT"),
                new AutoMarkSetting.Data("otg:TempleFrozen:TempleFrozenC1R2", true, "antiqueatlas:dungeon", "DEFAULT"),
                new AutoMarkSetting.Data("otg:LavaTemple:LavaTempleC1R1", true, "antiqueatlas:dungeon", "DEFAULT"),
                // End of 15k
                // Gem Traders
                new AutoMarkSetting.Data("otg:Trader_Castle:Trader_CastleC0R1", true, "antiqueatlas:brutalcoin", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Trader_Club:Trader_ClubC0R1", true, "antiqueatlas:brutalcoin", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Trader_Silo:Trader_SiloC1R0", true, "antiqueatlas:brutalcoin", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Trader_Swamp:Trader_SwampC1R0", true, "antiqueatlas:brutalcoin", "DEFAULT"),
                // Brutal
                new AutoMarkSetting.Data("otg:brutal_warnpost", true, "antiqueatlas:radiation", "DEFAULT"),
                new AutoMarkSetting.Data("otg:abyssal_tower:AbyssTowerC4R4", true, "antiqueatlas:megatower", "DEFAULT"),
                // Underneath
                new AutoMarkSetting.Data("otg:access_duct:access_ductC1R0", true, "antiqueatlas:wrench", "DEFAULT"), // EZ Loot more bunker stuff
                new AutoMarkSetting.Data("otg:portal_overworld_high", true, "antiqueatlas:nether_portal", "DEFAULT"),
                new AutoMarkSetting.Data("otg:portal_overworld_low", true, "antiqueatlas:nether_portal", "DEFAULT"),
                // Nuclear Craft Bunkers
                new AutoMarkSetting.Data("otg::f_bunker_atrium_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_mid_nuclear_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_silo_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_apartements_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_small_01_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_small_02_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_small_03_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_small_04_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_small_05_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_small_06_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_small_07_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_small_08_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_storage_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_factory_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_mid_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_rich_spawn", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg::f_bunker_tunnelC4R1", true, "antiqueatlas:wrench", "DEFAULT"),
                new AutoMarkSetting.Data("otg:f_bunker_tunnel:DeepTunnel_EntranceHighC2R1", true, "antiqueatlas:wrench", "DEFAULT"), // Override other usage of DeepTunnel_EntranceHighC2R1
                // I&F
                new AutoMarkSetting.Data("otg:cyclops_main:cyclops_mainC2R1", true, "antiqueatlas:red_x_small", "DEFAULT"),
                new AutoMarkSetting.Data("otg:GorgonTemple:GorgonTemple_C6R6", true, "antiqueatlas:red_x_small", "DEFAULT"),
                new AutoMarkSetting.Data("otg:GorgonTemple:GorgonTemple_02C6R6", true, "antiqueatlas:red_x_small", "DEFAULT"),
                new AutoMarkSetting.Data("otg:GorgonTemple:GorgontempleGigiC6R6", true, "antiqueatlas:red_x_small", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Roost_Lightning:Roost_LightningC0R1", true, "antiqueatlas:dragon_gold", "DEFAULT"),
                // Flying Encounters
                // All
                new AutoMarkSetting.Data("otg:Aimton:sub_Aimton", false, "antiqueatlas:dragon_red", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Al_Capone:sub_Al_Capone", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Bufffaton_Bill:sub_Buffaton_Bill", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:ChevaxiTon:sub_ChevaxiTon", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:GokuTon:sub_GokuTon", false, "antiqueatlas:dragon_gold", "DEFAULT"),
                new AutoMarkSetting.Data("otg:JesterTon:sub_JesterTon", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:OldMan:sub_OldMan", false, "antiqueatlas:dragon_gold", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Tax_Collector:sub_Tax_Collector", false, "antiqueatlas:lycanites", "DEFAULT"),
                // City
                new AutoMarkSetting.Data("otg:Banisher:sub_Banisher", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Corroder:sub_Corroder", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Decayer:sub_Decayer", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Dislocator:sub_Dislocator", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Dismounting_Raider:sub_Dismounting_Raider", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Relocator:sub_Relocator", false, "antiqueatlas:skull", "DEFAULT"),
                // Defiled
                new AutoMarkSetting.Data("otg:Defiled_King:sub_Defiled_King", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Detonator:sub_Detonator", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Executioner:sub_Executioner", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Morbid_Skeleton:sub_Morbid_Skeleton", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Umbrium_Knight:sub_Umbrium_Knight", false, "antiqueatlas:lycanites", "DEFAULT"),
                // Desert
                new AutoMarkSetting.Data("otg:Duster:sub_Duster", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Red_Assassin:sub_Red_Assassin", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Ruster:sub_Ruster", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Sampler:sub_Sampler", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Wanderer:sub_Wanderer", false, "antiqueatlas:skull", "DEFAULT"),
                // Ice
                new AutoMarkSetting.Data("otg:Aerial_Dismounter:sub_Aerial_Dismounter", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Frigid_Warper:sub_Frigid_Warper", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Ice_King:sub_Ice_King", false, "antiqueatlas:dragon_blue", "DEFAULT"),
                new AutoMarkSetting.Data("otg:NumbingTon:sub_NumbingTon", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Piercing_Stray:sub_Piercing_Stray", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Unfroster:sub_Unfroster", false, "antiqueatlas:lycanites", "DEFAULT"),
                // Jungle
                new AutoMarkSetting.Data("otg:Aerial_Templar:sub_Aerial_Templar", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Crocodile_Skelly:sub_Crocodile_Skelly", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Decaying_Veteran:sub_Decaying_Veteran", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Flying_Rags:sub_Flying_Rags", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Knightly_Dismounter:sub_Knightly_Dismounter", false, "antiqueatlas:skull", "DEFAULT"),
                // Ocean
                new AutoMarkSetting.Data("otg:Capiton:sub_Capiton", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Dismounting_Commotone:sub_Dismounting_Commotone", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:LuffyTon:sub_LuffyTon", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:QuartermasTon:sub_QuartermasTon", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Skelemate:sub_Skelemate", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Skeleswain:sub_Skeleswain", false, "antiqueatlas:lycanites", "DEFAULT"),
                // Parasite
                new AutoMarkSetting.Data("otg:Dismisser:sub_Dismisser", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Dismounter:sub_Dismounter", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Grounder:sub_Grounder", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Inflicter:sub_Inflicter", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Perplexer:sub_Perplexer", false, "antiqueatlas:lycanites", "DEFAULT"),
                // Swamp
                new AutoMarkSetting.Data("otg:Crone:sub_Crone", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:HagTon:sub_HagTon", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:PlagueTon:sub_PlagueTon", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Rancid_Skeleton:sub_Rancid_Skeleton", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Sackhead:sub_Sackhead", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Swampton:sub_Swampton", false, "antiqueatlas:lycanites", "DEFAULT"),
                // Temperate
                new AutoMarkSetting.Data("otg:Down_Caster:sub_Down_Caster", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Fumbler:sub_Fumbler", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Impaler:sub_Impaler", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Impeder:sub_Impeder", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Spinner:sub_Spinner", false, "antiqueatlas:lycanites", "DEFAULT"),
                // Viking
                new AutoMarkSetting.Data("otg:Jotunn:sub_Jotunn", false, "antiqueatlas:dragon_blue", "DEFAULT"),
                new AutoMarkSetting.Data("otg:LokiTon:sub_LokiTon", false, "antiqueatlas:dragon_blue", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Surt:sub_Surt", false, "antiqueatlas:lycanites", "DEFAULT"),
                new AutoMarkSetting.Data("otg:ThorTon:sub_ThorTon", false, "antiqueatlas:dragon_gold", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Tyr:sub_Tyr", false, "antiqueatlas:lycanites", "DEFAULT"),
                // Wastelands
                new AutoMarkSetting.Data("otg:Asher:sub_Asher", false, "antiqueatlas:dragon_red", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Dust_Scrapper:sub_Dust_Scrapper", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Launcher:sub_Launcher", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Piercing_Duster:sub_Piercing_Duster", false, "antiqueatlas:skull", "DEFAULT"),
                new AutoMarkSetting.Data("otg:Scrapper:sub_Scrapper", false, "antiqueatlas:skull", "DEFAULT")
                // End of Encounters
                // Debug Mark Everything
//            new AutoMarkSetting.Data("otg:BO3:START", true, "antiqueatlas:diamond", "DEFAULT"),
//            new AutoMarkSetting.Data("otg:BO3:BRANCH", true, "antiqueatlas:bed", "DEFAULT"),
//            new AutoMarkSetting.Data("otg:BO4:START", true, "antiqueatlas:diamond", "DEFAULT"),
//            new AutoMarkSetting.Data("otg:BO4:BRANCH", true, "antiqueatlas:bed", "DEFAULT")
        ).forEach(data -> otgMarkers.put(data.context, data));
    }

    public OTGConfig() {
        initOTGMarkers();
    }
}
