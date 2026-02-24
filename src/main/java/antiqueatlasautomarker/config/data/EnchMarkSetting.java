package antiqueatlasautomarker.config.data;

import net.minecraftforge.common.config.Config;

public class EnchMarkSetting {
    @Config.Ignore
    public String enchId = "";
    @Config.Comment("Optional. If above 1, minimum level needed to mark a librarian with this enchant")
    @Config.Name("Min Level")
    public int minLvl = 1;
    @Config.Comment("Optional. If set, uses this abbreviation on the marker labels instead of the full enchantment name")
    @Config.Name("Abbreviation")
    public String abbreviation = "";

    public EnchMarkSetting() {}
    public EnchMarkSetting(String enchId) {
        this.enchId = enchId;
    }
    public EnchMarkSetting(String enchId, int minLvl) {
        this(enchId);
        this.minLvl = minLvl;
    }
    public EnchMarkSetting(String enchId, int minLvl, String abbreviation) {
        this(enchId, minLvl);
        this.abbreviation = abbreviation;
    }
}
