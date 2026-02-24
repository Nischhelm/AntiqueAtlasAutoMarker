package antiqueatlasautomarker.config.data;

import meldexun.betterconfig.api.Order;
import net.minecraftforge.common.config.Config;

public class EnchMarkSetting {
    @Config.Ignore
    @Order(0) public String enchId = "";
    @Config.Comment("Optional. If above 1: Minimum level needed to mark a librarian with this enchant")
    @Config.Name("Min Level")
    @Config.RangeInt(min = 1)
    @Order(1) public int minLvl = 1;
    @Config.Comment("Optional. If set, uses this abbreviation on the marker labels instead of the full enchantment name")
    @Config.Name("Abbreviation")
    @Order(2) public String abbreviation = "";

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
