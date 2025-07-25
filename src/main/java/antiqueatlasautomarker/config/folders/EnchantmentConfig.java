package antiqueatlasautomarker.config.folders;

import net.minecraftforge.common.config.Config;

public class EnchantmentConfig {
    @Config.Comment("Set to false to never mark Enchantments")
    @Config.Name("Enabled")
    public boolean enabled = true;

    @Config.Comment("Mark Enchantments with this Marker Type")
    @Config.Name("Marker")
    public String marker = "antiqueatlas:book";

    @Config.Comment("Use \"ALL\" to mark all trades. Otherwise pattern is modid:enchname; minLvlToMark; optionalAbbreviation")
    @Config.Name("Enchantments Trades to mark")
    public String[] enchantmentsToMark = {
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

    @Config.Comment("Clicking a specified key will mark all the whiteshirts in the players loaded area in their atlases")
    @Config.Name("Enable Librarian Key")
    public boolean enableLibrarianKey = true;

    @Config.Comment("Marker to use for unchecked whiteshirts")
    @Config.Name("Librarian Key Marker")
    public String librarianKeyMarker = "antiqueatlas:anvil";

    @Config.Comment("Label to use for unchecked whiteshirts")
    @Config.Name("Librarian Key Label")
    public String librarianKeyLabel = "Whiteshirt";
}
