package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class OTGConfig {

    @Config.Comment("Set to false to disable OTG Auto Markers. You should not have to disable this unless an OTG update causes immediate issues.")
    @Config.Name("OTG Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.otg.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "openterraingenerator", desired = true, warnIngame = false, reason = "No issue, auto disabled")
    @Config.RequiresMcRestart
    public boolean enabled = true;

    @Config.Comment({
            "List of OTG BO3 and BO4 objects to auto mark, these can be structures or entities.",
            "Pattern: objectName:objectBranch; marker label; marker type.",
            "    objectName - File name or name from \"/otg structure\"",
            "    objectBranch - File name or name from \"/otg structure\", if provided, is where the marker gets placed",
            "    marker labl - Hover over marker text, \"DEFAULT\" will use the lag key \"gui.aaam.marker.otg.<objectBranch>\"",
            "    market type - Marker Type Icon",
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
    public String[] otgMarkers = {
            // Safe
            "SpawnHex:SpawnHexC9R9;DEFAULT;antiqueatlas:google",
            "AcaciaVillage;DEFAULT;antiqueatlas:village", // Has too many variations, can't center properly
            "Harbor:HarborC4R3;DEFAULT;antiqueatlas:village",
            "Herbalist:HerbalistC3R4;DEFAULT;antiqueatlas:village",
            "v_bunker_start;DEFAULT;antiqueatlas:village", // Underground Village Bunker
            ":c_bunker_mid_way;DEFAULT;antiqueatlas:waystone", // 0 or more Waystone
            ":v_bunker_mid_way;DEFAULT;antiqueatlas:waystone",
            ":o_bunker_mid_way;DEFAULT;antiqueatlas:waystone",
            "viking_main_peacefull;DEFAULT;antiqueatlas:village", // Has too many variations, can't center properly
            // Vanilla
            ":DeepTunnel_EntranceHighC2R1;DEFAULT;antiqueatlas:dungeon", // Multiple variants use this entrance
            "maintenance_hub;DEFAULT;antiqueatlas:dungeon",
            "Quarry:quarryC5R6;DEFAULT;antiqueatlas:pickaxe",
            "SwampHouseBig;DEFAULT;antiqueatlas:monsterspawner",
            "viking_main;DEFAULT;antiqueatlas:sword",
            // 15k map destinations, one time and multiple spawning
            // Safe
            "DaerocVillage:DaerocVillageC11R8;DEFAULT;antiqueatlas:village",
            "DaerocVillage:DaerocVillageC11R13;DEFAULT;antiqueatlas:nether_portal", // End Portal
            "origin:originC15R8;DEFAULT;antiqueatlas:village",
            "origin:originC5R4;DEFAULT;antiqueatlas:nether_portal", // End Portal
            "JungleVillage;DEFAULT;antiqueatlas:village", // Has too many variations, can't center properly
            // Dungeon
            "Church:ChurchC0R0;DEFAULT;antiqueatlas:dungeon",
            ":Dungeon01C1R1;DEFAULT;antiqueatlas:dungeon ",
            ":Dungeon02C1R1;DEFAULT;antiqueatlas:dungeon",
            ":Dungeon03C1R1;DEFAULT;antiqueatlas:dungeon",
            "Mineshaft;DEFAULT;antiqueatlas:pickaxe",
            "W-Mineshaft;DEFAULT;antiqueatlas:pickaxe",
            // 15 Random Stuff
            "LegacySpawn:LegacySpawnC1R1;DEFAULT;antiqueatlas:google",
            "CastleRuins:CastleRuinsC2R3;DEFAULT;antiqueatlas:wizardtower",
            "FrozenShip-1;DEFAULT;antiqueatlas:ship",
            "FrozenShip-2;DEFAULT;antiqueatlas:ship",
            "FrozenShip-3;DEFAULT;antiqueatlas:ship",
            "FrozenShip-4;DEFAULT;antiqueatlas:ship",
            "TempleFrozen:TempleFrozenC1R2;DEFAULT;antiqueatlas:dungeon",
            "LavaTemple:LavaTempleC1R1;DEFAULT;antiqueatlas:dungeon",
            // End of 15k
            // Gem Traders
            "Trader_Castle:Trader_CastleC0R1;DEFAULT;antiqueatlas:brutalcoin",
            "Trader_Club:Trader_ClubC0R1;DEFAULT;antiqueatlas:brutalcoin",
            "Trader_Silo:Trader_SiloC1R0;DEFAULT;antiqueatlas:brutalcoin",
            "Trader_Swamp:Trader_SwampC1R0;DEFAULT;antiqueatlas:brutalcoin",
            // Brutal
            "brutal_warnpost;DEFAULT;antiqueatlas:radiation",
            "abyssal_tower:AbyssTowerC4R4;DEFAULT;antiqueatlas:megatower",
            // Underneath
            "access_duct:access_ductC1R0;DEFAULT;antiqueatlas:wrench", // EZ Loot more bunker stuff
            "portal_overworld_high;DEFAULT;antiqueatlas:nether_portal",
            "portal_overworld_low;DEFAULT;antiqueatlas:nether_portal",
            // Nuclear Craft Bunkers
            ":f_bunker_atrium_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_mid_nuclear_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_silo_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_apartements_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_small_01_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_small_02_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_small_03_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_small_04_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_small_05_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_small_06_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_small_07_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_small_08_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_storage_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_factory_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_mid_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_rich_spawn;DEFAULT;antiqueatlas:wrench",
            ":f_bunker_tunnelC4R1;DEFAULT;antiqueatlas:wrench",
            "f_bunker_tunnel:DeepTunnel_EntranceHighC2R1;DEFAULT;antiqueatlas:wrench", // Override other usage of DeepTunnel_EntranceHighC2R1
            // I&F
            "cyclops_main:cyclops_mainC2R1;DEFAULT;antiqueatlas:red_x_small",
            "GorgonTemple:GorgonTemple_C6R6;DEFAULT;antiqueatlas:red_x_small",
            "GorgonTemple:GorgonTemple_02C6R6;DEFAULT;antiqueatlas:red_x_small",
            "GorgonTemple:GorgontempleGigiC6R6;DEFAULT;antiqueatlas:red_x_small",
            "Roost_Lightning:Roost_LightningC0R1;DEFAULT;antiqueatlas:dragon_gold",
            // Flying Encounters
            // All
            "Aimton:sub_Aimton;DEFAULT;antiqueatlas:dragon_red",
            "Al_Capone:sub_Al_Capone;DEFAULT;antiqueatlas:lycanites",
            "Bufffaton_Bill:sub_Buffaton_Bill;DEFAULT;antiqueatlas:lycanites",
            "ChevaxiTon:sub_ChevaxiTon;DEFAULT;antiqueatlas:lycanites",
            "GokuTon:sub_GokuTon;DEFAULT;antiqueatlas:dragon_gold",
            "JesterTon:sub_JesterTon;DEFAULT;antiqueatlas:lycanites",
            "OldMan:sub_OldMan;DEFAULT;antiqueatlas:dragon_gold",
            "Tax_Collector:sub_Tax_Collector;DEFAULT;antiqueatlas:lycanites",
            // City
            "Banisher:sub_Banisher;DEFAULT;antiqueatlas:skull",
            "Corroder:sub_Corroder;DEFAULT;antiqueatlas:skull",
            "Decayer:sub_Decayer;DEFAULT;antiqueatlas:skull",
            "Dislocator:sub_Dislocator;DEFAULT;antiqueatlas:skull",
            "Dismounting_Raider:sub_Dismounting_Raider;DEFAULT;antiqueatlas:skull",
            "Relocator:sub_Relocator;DEFAULT;antiqueatlas:skull",
            // Defiled
            "Defiled_King:sub_Defiled_King;DEFAULT;antiqueatlas:lycanites",
            "Detonator:sub_Detonator;DEFAULT;antiqueatlas:lycanites",
            "Executioner:sub_Executioner;DEFAULT;antiqueatlas:lycanites",
            "Morbid_Skeleton:sub_Morbid_Skeleton;DEFAULT;antiqueatlas:lycanites",
            "Umbrium_Knight:sub_Umbrium_Knight;DEFAULT;antiqueatlas:lycanites",
            // Desert
            "Duster:sub_Duster;DEFAULT;antiqueatlas:lycanites",
            "Red_Assassin:sub_Red_Assassin;DEFAULT;antiqueatlas:lycanites",
            "Ruster:sub_Ruster;DEFAULT;antiqueatlas:skull",
            "Sampler:sub_Sampler;DEFAULT;antiqueatlas:lycanites",
            "Wanderer:sub_Wanderer;DEFAULT;antiqueatlas:skull",
            // Ice
            "Aerial_Dismounter:sub_Aerial_Dismounter;DEFAULT;antiqueatlas:lycanites",
            "Frigid_Warper:sub_Frigid_Warper;DEFAULT;antiqueatlas:lycanites",
            "Ice_King:sub_Ice_King;DEFAULT;antiqueatlas:dragon_blue",
            "NumbingTon:sub_NumbingTon;DEFAULT;antiqueatlas:lycanites",
            "Piercing_Stray:sub_Piercing_Stray;DEFAULT;antiqueatlas:lycanites",
            "Unfroster:sub_Unfroster;DEFAULT;antiqueatlas:lycanites",
            // Jungle
            "Aerial_Templar:sub_Aerial_Templar;DEFAULT;antiqueatlas:skull",
            "Crocodile_Skelly:sub_Crocodile_Skelly;DEFAULT;antiqueatlas:lycanites",
            "Decaying_Veteran:sub_Decaying_Veteran;DEFAULT;antiqueatlas:skull",
            "Flying_Rags:sub_Flying_Rags;DEFAULT;antiqueatlas:skull",
            "Knightly_Dismounter:sub_Knightly_Dismounter;DEFAULT;antiqueatlas:skull",
            // Ocean
            "Capiton:sub_Capiton;DEFAULT;antiqueatlas:lycanites",
            "Dismounting_Commotone:sub_Dismounting_Commotone;DEFAULT;antiqueatlas:lycanites",
            "LuffyTon:sub_LuffyTon;DEFAULT;antiqueatlas:lycanites",
            "QuartermasTon:sub_QuartermasTon;DEFAULT;antiqueatlas:lycanites",
            "Skelemate:sub_Skelemate;DEFAULT;antiqueatlas:lycanites",
            "Skeleswain:sub_Skeleswain;DEFAULT;antiqueatlas:lycanites",
            // Parasite
            "Dismisser:sub_Dismisser;DEFAULT;antiqueatlas:lycanites",
            "Dismounter:sub_Dismounter;DEFAULT;antiqueatlas:lycanites",
            "Grounder:sub_Grounder;DEFAULT;antiqueatlas:lycanites",
            "Inflicter:sub_Inflicter;DEFAULT;antiqueatlas:lycanites",
            "Perplexer:sub_Perplexer;DEFAULT;antiqueatlas:lycanites",
            // Swamp
            "Crone:sub_Crone;DEFAULT;antiqueatlas:lycanites",
            "HagTon:sub_HagTon;DEFAULT;antiqueatlas:lycanites",
            "PlagueTon:sub_PlagueTon;DEFAULT;antiqueatlas:lycanites",
            "Rancid_Skeleton:sub_Rancid_Skeleton;DEFAULT;antiqueatlas:lycanites",
            "Sackhead:sub_Sackhead;DEFAULT;antiqueatlas:lycanites",
            "Swampton:sub_Swampton;DEFAULT;antiqueatlas:lycanites",
            // Temperate
            "Down_Caster:sub_Down_Caster;DEFAULT;antiqueatlas:lycanites",
            "Fumbler:sub_Fumbler;DEFAULT;antiqueatlas:lycanites",
            "Impaler:sub_Impaler;DEFAULT;antiqueatlas:lycanites",
            "Impeder:sub_Impeder;DEFAULT;antiqueatlas:lycanites",
            "Spinner:sub_Spinner;DEFAULT;antiqueatlas:lycanites",
            // Viking
            "Jotunn:sub_Jotunn;DEFAULT;antiqueatlas:dragon_blue",
            "LokiTon:sub_LokiTon;DEFAULT;antiqueatlas:dragon_blue",
            "Surt:sub_Surt;DEFAULT;antiqueatlas:lycanites",
            "ThorTon:sub_ThorTon;DEFAULT;antiqueatlas:dragon_gold",
            "Tyr:sub_Tyr;DEFAULT;antiqueatlas:lycanites",
            // Wastelands
            "Asher:sub_Asher;DEFAULT;antiqueatlas:dragon_red",
            "Dust_Scrapper:sub_Dust_Scrapper;DEFAULT;antiqueatlas:skull",
            "Launcher:sub_Launcher;DEFAULT;antiqueatlas:skull",
            "Piercing_Duster:sub_Piercing_Duster;DEFAULT;antiqueatlas:skull",
            "Scrapper:sub_Scrapper;DEFAULT;antiqueatlas:skull",
            // End of Encounters
            // Debug Mark Everything
//            "BO3:START;DEFAULT;antiqueatlas:diamond",
//            "BO3:BRANCH;DEFAULT;antiqueatlas:bed",
//            "BO4:START;DEFAULT;antiqueatlas:diamond",
//            "BO4:BRANCH;DEFAULT;antiqueatlas:bed"
    };

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        for(String config : otgMarkers) {
            String[] entries = config.split(";");
            if(entries.length < 3)
                break;

            AutoMarkSetting.registerAutoMarkSetting(entries[0].trim(), true, entries[1].trim(), entries[2].trim());
        }
    }
}
