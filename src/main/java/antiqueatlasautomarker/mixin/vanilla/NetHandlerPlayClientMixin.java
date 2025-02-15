package antiqueatlasautomarker.mixin.vanilla;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.EnchMarkSetting;
import antiqueatlasautomarker.util.EnchantmentOffer;
import antiqueatlasautomarker.util.EnchantmentUtil;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Imagine mixin'ing into the network logic skull
@Mixin(NetHandlerPlayClient.class)
public abstract class NetHandlerPlayClientMixin {
    @Shadow private Minecraft client;

    @Inject(
            method = "handleCustomPayload",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/IMerchant;setRecipes(Lnet/minecraft/village/MerchantRecipeList;)V")
    )
    public void onMerchantTradeOffers(SPacketCustomPayload packetIn, CallbackInfo ci, @Local MerchantRecipeList tradeList, @Local IMerchant merchant) {
        AutoMarkSetting markSetting = AutoMarkSetting.get("enchantmentTrade");
        if (markSetting == null || !markSetting.enabled) return;

        EntityPlayer player = this.client.player; //bruh
        if (player == null) return;

        if (tradeList == null) return;

        //Get all enchanted book trades
        List<EnchantmentOffer> offeredEnchants = new ArrayList<>();
        for (MerchantRecipe trade : tradeList) {
            if (trade.getItemToSell().getItem() instanceof ItemEnchantedBook) {
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(trade.getItemToSell());
                ItemStack stackBuy1 = trade.getItemToBuy();
                ItemStack stackBuy2 = trade.getSecondItemToBuy();
                int cost = Math.max(stackBuy1.getCount(), stackBuy2.getCount());
                for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) //vanilla will always only have 1 enchant per book
                    offeredEnchants.add(new EnchantmentOffer(entry.getKey(), entry.getValue(), cost));
            }
        }
        if (offeredEnchants.isEmpty()) return;

        //Remove duplicate offers
        List<EnchantmentOffer> toRemove = new ArrayList<>();
        for (EnchantmentOffer offer1 : offeredEnchants) {
            for (EnchantmentOffer offer2 : offeredEnchants) {
                if (offer1 == offer2) continue;
                if (offer1.enchantment != offer2.enchantment) continue;
                //Don't mark duplicate enchants with lower lvls
                if (offer1.lvl > offer2.lvl) toRemove.add(offer2);
                //Don't mark the more expensive offer if the same enchant is sold twice
                else if((offer1.lvl == offer2.lvl) && (offer1.cost < offer2.cost)) toRemove.add(offer2);
            }
        }
        offeredEnchants.removeAll(toRemove);

        String markerLabel = "";
        boolean isFirst = true;
        for (EnchantmentOffer offer : offeredEnchants) {
            ResourceLocation enchReg = offer.enchantment.getRegistryName();
            if (enchReg == null) continue;
            EnchMarkSetting setting = EnchMarkSetting.get(enchReg.toString());
            //Check if the offer is anything we care about
            if ((setting != null && offer.lvl >= setting.minLvl) || EnchMarkSetting.acceptAll) {
                String enchLabel = "";
                if(setting != null) enchLabel = setting.abbreviation;
                //no abbreviation or accepting all
                if(enchLabel.isEmpty()) enchLabel = offer.enchantment.getTranslatedName(offer.lvl);
                //Put lvl on abbreviated enchant
                else if(offer.enchantment.getMaxLevel() > 1) enchLabel += " " + I18n.format("enchantment.level." + offer.lvl);

                //Add cost
                enchLabel += " " + TextFormatting.GREEN + offer.cost + TextFormatting.RESET;

                //Put it all on the marker label
                if(isFirst) {
                    markerLabel += enchLabel;
                    isFirst = false;
                } else
                    markerLabel += ", "+enchLabel;
            }
        }
        //Nothing we care about
        if(markerLabel.isEmpty()) return;

        EnchantmentUtil.markLibrarian(player, merchant.getPos(), markerLabel);
    }
}
