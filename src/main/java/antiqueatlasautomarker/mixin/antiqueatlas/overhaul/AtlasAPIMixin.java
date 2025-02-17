package antiqueatlasautomarker.mixin.antiqueatlas.overhaul;

import antiqueatlasautomarker.config.ForgeConfigHandler;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.SettingsConfig;
import hunternif.mc.atlas.api.AtlasAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

import static hunternif.mc.atlas.api.AtlasAPI.ATLAS_ITEM;

@Mixin(AtlasAPI.class)
public abstract class AtlasAPIMixin{
    @ModifyReturnValue(
            method = "getPlayerAtlases",
            at = @At(value = "RETURN"),
            remap = false
    )
    private static List<Integer> alsoCheckOffhand(List<Integer> original, @Local(argsOnly = true) EntityPlayer player){
        if (!SettingsConfig.gameplay.itemNeeded) return original;
        if (!ForgeConfigHandler.overhaul.checkOffhand) return original;

        //Check if player has atlas in offhand
        ItemStack offhandStack = player.getHeldItemOffhand();
        if (!offhandStack.isEmpty() && offhandStack.getItem() == ATLAS_ITEM)
            original.add(offhandStack.getItemDamage());

        return original;
    }
}
