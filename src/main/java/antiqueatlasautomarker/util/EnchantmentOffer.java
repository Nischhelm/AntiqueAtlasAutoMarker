package antiqueatlasautomarker.util;

import net.minecraft.enchantment.Enchantment;

public class EnchantmentOffer {
    public Enchantment enchantment;
    public int lvl;
    public int cost;

    public EnchantmentOffer(Enchantment ench, int lvl, int cost){
        this.enchantment = ench;
        this.lvl = lvl;
        this.cost = cost;
    }
}
