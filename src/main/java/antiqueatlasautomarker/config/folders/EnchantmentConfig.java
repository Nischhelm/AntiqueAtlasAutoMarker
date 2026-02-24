package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.data.AutoMarkSetting;
import antiqueatlasautomarker.config.data.EnchMarkSetting;
import net.minecraftforge.common.config.Config;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnchantmentConfig {
    @Config.Comment("Set to false to never mark Enchantment Trades")
    @Config.Name("Enchantment Trade Marker Enabled")
    public boolean enabled = true;

    @Config.Comment("Mark Enchantment Trades with this Marker Type")
    @Config.Name("Enchantment Trade Marker")
    public String marker = "antiqueatlas:book";

    @Config.Comment("Add entry with \"ALL\" as enchantment id to mark all trades, ignoring the rest of the config. Otherwise pattern is modid:enchname; minLvlToMark; optionalAbbreviation") //TODO
    @Config.Name("Enchantments Trades to mark")
    public Map<String, EnchMarkSetting> enchantmentsToMark = Stream.of(
            new EnchMarkSetting("minecraft:protection", 2, "Prot"),
            new EnchMarkSetting("minecraft:feather_falling", 4, "FF"),
            new EnchMarkSetting("minecraft:respiration", 3),
            new EnchMarkSetting("minecraft:aqua_affinity", 1, "Aqua Aff"),
            new EnchMarkSetting("minecraft:depth_strider", 3, "Depth Str"),
            new EnchMarkSetting("minecraft:sweeping", 2, "Sweep Edge"),
            new EnchMarkSetting("minecraft:efficiency", 3, "Eff"),
            new EnchMarkSetting("minecraft:silk_touch"),
            new EnchMarkSetting("minecraft:unbreaking", 2),
            new EnchMarkSetting("minecraft:fortune", 2),
            new EnchMarkSetting("minecraft:power", 3),
            new EnchMarkSetting("minecraft:infinity"),
            new EnchMarkSetting("minecraft:fire_aspect", 1, "FA"),
            new EnchMarkSetting("minecraft:sharpness", 4, "Sharp"),
            new EnchMarkSetting("minecraft:bane_of_arthropods", 5, "BoA"),
            new EnchMarkSetting("minecraft:mending"),
            new EnchMarkSetting("somanyenchantments:advancedefficiency", 2, "Adv Eff"),
            new EnchMarkSetting("somanyenchantments:advancedsharpness", 2, "Adv Sharp"),
            new EnchMarkSetting("somanyenchantments:fieryedge"),
            new EnchMarkSetting("somanyenchantments:purification", 2, "Purif"),
            new EnchMarkSetting("somanyenchantments:rune_piercingcapabilities", 1, "R:PC"),
            new EnchMarkSetting("somanyenchantments:rune_magicalblessing", 1, "R:MB"),
            new EnchMarkSetting("somanyenchantments:rune_revival", 1, "R:Rev"),
            new EnchMarkSetting("somanyenchantments:rune_ressurection", 1, "R:Ress"),
            new EnchMarkSetting("somanyenchantments:counter_attack", 1, "Cnt. Att."),
            new EnchMarkSetting("somanyenchantments:swifterslashes", 2, "Swifter"),
            new EnchMarkSetting("somanyenchantments:parry"),
            new EnchMarkSetting("somanyenchantments:lifesteal"),
            new EnchMarkSetting("somanyenchantments:solsblessing", 2, "Sols"),
            new EnchMarkSetting("somanyenchantments:butchering", 3),
            new EnchMarkSetting("somanyenchantments:spellbreaker", 2),
            new EnchMarkSetting("somanyenchantments:penetratingedge", 3, "Pen Edge"),
            new EnchMarkSetting("somanyenchantments:unsheathing"),
            new EnchMarkSetting("somanyenchantments:truestrike"),
            new EnchMarkSetting("somanyenchantments:smelter"),
            new EnchMarkSetting("somanyenchantments:empowereddefence", 1, "Emp Def"),
            new EnchMarkSetting("somanyenchantments:strafe", 2),
            new EnchMarkSetting("somanyenchantments:advancedlooting", 1, "Adv Loot"),
            new EnchMarkSetting("somanyenchantments:ashdestroyer", 2),
            new EnchMarkSetting("somanyenchantments:desolator", 2),
            new EnchMarkSetting("somanyenchantments:purgingblade", 2, "Purging"),
            new EnchMarkSetting("somanyenchantments:viper", 2),
            new EnchMarkSetting("somanyenchantments:advancedpower", 3),
            new EnchMarkSetting("somanyenchantments:envenomed", 2),
            new EnchMarkSetting("somanyenchantments:advancedlure", 2, "Adv Lure"),
            new EnchMarkSetting("somanyenchantments:advancedluckofthesea", 2, "Adv LotS"),
            new EnchMarkSetting("somanyenchantments:advancedfeatherfalling", 3, "Adv FF"),
            new EnchMarkSetting("somanyenchantments:advancedthorns", 2, "Adv Thorns"),
            new EnchMarkSetting("somanyenchantments:advancedprotection", 2, "Adv Prot"),
            new EnchMarkSetting("somanyenchantments:atomicdeconstructor", 1, "Atomic"),
            new EnchMarkSetting("somanyenchantments:disarmament", 4, "Disarma"),
            new EnchMarkSetting("somanyenchantments:advancedfireaspect", 1, "Adv FA"),
            new EnchMarkSetting("somanyenchantments:arcslash", 1, "Arc Sl"),
            new EnchMarkSetting("somanyenchantments:advancedflame", 1, "Adv Flame"),
            new EnchMarkSetting("somanyenchantments:splitshot", 3),
            new EnchMarkSetting("somanyenchantments:strengthenedvitality", 2, "Str Vitality"),
            new EnchMarkSetting("somanyenchantments:burningshield", 3, "Burn Shield"),
            new EnchMarkSetting("somanyenchantments:naturalblocking", 1, "Nat Block"),
            new EnchMarkSetting("somanyenchantments:rune_arrowpiercing", 2, "R:AP"),
            new EnchMarkSetting("somanyenchantments:innerberserk", 3),
            new EnchMarkSetting("somanyenchantments:luckmagnification", 1, "Luck Mag"),
            new EnchMarkSetting("somanyenchantments:lightweight"),
            new EnchMarkSetting("somanyenchantments:swiftswimming", 2, "UW Strider"),
            new EnchMarkSetting("somanyenchantments:unreasonable"),
            new EnchMarkSetting("somanyenchantments:evasion"),
            new EnchMarkSetting("somanyenchantments:bluntness", 2),
            new EnchMarkSetting("somanyenchantments:subjectmathematics", 2, "Subj Math"),
            new EnchMarkSetting("somanyenchantments:subjectpe", 2, "Subj PE"),
            new EnchMarkSetting("somanyenchantments:supremesharpness", 1, "Sup Sharp"),
            new EnchMarkSetting("somanyenchantments:supremesmite", 4, "Sup Smite"),
            new EnchMarkSetting("somanyenchantments:curseofpossession", 1, "C. of Poss"),
            new EnchMarkSetting("somanyenchantments:supremefireaspect", 1, "Sup FA"),
            new EnchMarkSetting("somanyenchantments:advancedmending", 1, "Adv Mend"),
            new EnchMarkSetting("somanyenchantments:supremeflame", 1, "Sup Flame"),
            new EnchMarkSetting("somanyenchantments:upgradedpotentials", 1, "Upg Pot"),
            new EnchMarkSetting("somanyenchantments:adept"),
            new EnchMarkSetting("somanyenchantments:magmawalker", 2),
            new EnchMarkSetting("spartanweaponry:rapid_load", 2),
            new EnchMarkSetting("spartanweaponry:sharpshooter", 2),
            new EnchMarkSetting("spartanshields:spikes", 2),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:vampirism"),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:agility"),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:arrowrecovery", 3, "Arr Rec"),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:combo"),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:highjump", 2),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:smelting"),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:education"),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:versatility", 1, "Versa"),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:multishot", 2),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:range"),
            new EnchMarkSetting("mujmajnkraftsbettersurvival:rapidfire"),
            new EnchMarkSetting("charm:magnetic"),
            new EnchMarkSetting("grapplemod:doublejumpenchantment"),
            new EnchMarkSetting("somanyenchantments:combatmedic"),
            new EnchMarkSetting("somanyenchantments:ancientswordmastery", 1, "Anc Sword Mastery"),
            new EnchMarkSetting("somanyenchantments:ancientsealedcurses", 1, "Anc Sealed C."),
            new EnchMarkSetting("somanyenchantments:counterattack", 2),
            new EnchMarkSetting("somanyenchantments:mortalitas", 5, "Morta"),
            new EnchMarkSetting("somanyenchantments:criticalstrike", 2, "Crit Strike"),
            new EnchMarkSetting("distinctdamagedescriptions:slystrike"),
            new EnchMarkSetting("distinctdamagedescriptions:bruteforce", 3),
            new EnchMarkSetting("charm:curse_break"),
            new EnchMarkSetting("somanyenchantments:advancedefficency", 2, "Adv Eff"),
            new EnchMarkSetting("somanyenchantments:clearsky", 3, "Clearskies"),
            new EnchMarkSetting("somanyenchantments:afa", 1, "Adv FA"),
            new EnchMarkSetting("somanyenchantments:swiper", 1, "Arc Sl"),
            new EnchMarkSetting("somanyenchantments:afl", 1, "Adv Flame"),
            new EnchMarkSetting("somanyenchantments:fieryshield", 3, "Burn Shield"),
            new EnchMarkSetting("somanyenchantments:rune_armorpiercing", 2, "R:AP"),
            new EnchMarkSetting("somanyenchantments:underwaterstrider", 2, "UW Strider"),
            new EnchMarkSetting("somanyenchantments:frenzy"),
            new EnchMarkSetting("somanyenchantments:english", 2, "Subj Eng"),
            new EnchMarkSetting("somanyenchantments:pe", 3, "Subj PE"),
            new EnchMarkSetting("somanyenchantments:sfa", 1, "Sup FA"),
            new EnchMarkSetting("somanyenchantments:sfl", 1, "Sup Flame"),
            new EnchMarkSetting("somanyenchantments:upgrade", 1, "Upg Pot")
    ).collect(Collectors.toMap(data -> data.enchId, Function.identity()));

    @Config.Comment("Clicking a specified key will mark all the whiteshirt villagers in the players loaded area in the players atlases")
    @Config.Name("Librarian Key")
    public AutoMarkSetting.Data librarianKey = new AutoMarkSetting.Data(null, true, "antiqueatlas:anvil", "Whiteshirt");
}
