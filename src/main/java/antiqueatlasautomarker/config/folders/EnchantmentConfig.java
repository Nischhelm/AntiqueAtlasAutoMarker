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
            "minecraft:fire_aspect;;FA",
            "minecraft:sharpness;4;Sharp",
            "minecraft:bane_of_arthropods;5;BoA",
            "minecraft:mending",
            "somanyenchantments:advancedefficiency;2;Adv Eff",
            "somanyenchantments:advancedsharpness;2;Adv Sharp",
            "somanyenchantments:fieryedge",
            "somanyenchantments:purification;2;Purif",
            "somanyenchantments:rune_piercingcapabilities;;R:PC",
            "somanyenchantments:rune_magicalblessing;;R:MB",
            "somanyenchantments:rune_revival;;R:Rev",
            "somanyenchantments:rune_ressurection;;R:Ress",
            "somanyenchantments:counter_attack;;Cnt. Att.",
            "somanyenchantments:swifterslashes;2;Swifter",
            "somanyenchantments:parry",
            "somanyenchantments:lifesteal",
            "somanyenchantments:solsblessing;2;Sols",
            "somanyenchantments:butchering;3",
            "somanyenchantments:spellbreaker;2",
            "somanyenchantments:penetratingedge;3;Pen Edge",
            "somanyenchantments:unsheathing",
            "somanyenchantments:truestrike",
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
            "somanyenchantments:advancedfireaspect;;Adv FA",
            "somanyenchantments:arcslash;;Arc Sl",
            "somanyenchantments:advancedflame;;Adv Flame",
            "somanyenchantments:splitshot;3",
            "somanyenchantments:strengthenedvitality;2;Str Vitality",
            "somanyenchantments:burningshield;3;Burn Shield",
            "somanyenchantments:naturalblocking;;Nat Block",
            "somanyenchantments:rune_arrowpiercing;2;R:AP",
            "somanyenchantments:innerberserk;3",
            "somanyenchantments:luckmagnification;;Luck Mag",
            "somanyenchantments:lightweight",
            "somanyenchantments:swiftswimming;2;UW Strider",
            "somanyenchantments:unreasonable",
            "somanyenchantments:evasion",
            "somanyenchantments:bluntness;2",
            "somanyenchantments:subjectmathematics;2;Subj Math",
            "somanyenchantments:subjectpe;2;Subj PE",
            "somanyenchantments:supremesharpness;;Sup Sharp",
            "somanyenchantments:supremesmite;4;Sup Smite",
            "somanyenchantments:curseofpossession;;C. of Poss",
            "somanyenchantments:supremefireaspect;;Sup FA",
            "somanyenchantments:advancedmending;;Adv Mend",
            "somanyenchantments:supremeflame;;Sup Flame",
            "somanyenchantments:upgradedpotentials;;Upg Pot",
            "somanyenchantments:adept",
            "somanyenchantments:magmawalker;2",
            "spartanweaponry:rapid_load;2",
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
            "mujmajnkraftsbettersurvival:multishot;2",
            "mujmajnkraftsbettersurvival:range",
            "mujmajnkraftsbettersurvival:rapidfire",
            "charm:magnetic",
            "grapplemod:doublejumpenchantment",
            "somanyenchantments:combatmedic",
            "somanyenchantments:ancientswordmastery;;Anc Sword Mastery",
            "somanyenchantments:ancientsealedcurses;;Anc Sealed C.",
            "somanyenchantments:counterattack;2",
            "somanyenchantments:mortalitas;6;Morta",
            "somanyenchantments:criticalstrike;2;Crit Strike",
            "distinctdamagedescriptions:slystrike",
            "distinctdamagedescriptions:bruteforce;3",
            "charm:curse_break",
            "somanyenchantments:advancedefficency;2;Adv Eff",
            "somanyenchantments:clearsky;3;Clearskies",
            "somanyenchantments:afa;;Adv FA",
            "somanyenchantments:swiper;;Arc Sl",
            "somanyenchantments:afl;;Adv Flame",
            "somanyenchantments:fieryshield;3;Burn Shield",
            "somanyenchantments:rune_armorpiercing;2;R:AP",
            "somanyenchantments:underwaterstrider;2;UW Strider",
            "somanyenchantments:frenzy",
            "somanyenchantments:english;2;Subj Eng",
            "somanyenchantments:pe;3;Subj PE",
            "somanyenchantments:sfa;;Sup FA",
            "somanyenchantments:sfl;;Sup Flame",
            "somanyenchantments:upgrade;;Upg Pot"
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
