package antiqueatlasautomarker.mixin.antiqueatlas.overhaul;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import hunternif.mc.atlas.item.RecipeAtlasCombining;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RecipeAtlasCombining.class)
public class RecipeCombiningFix {
    @ModifyReturnValue(
            method = "getCraftingResult",
            at = @At("RETURN")
    )
    private ItemStack fixStackSize(ItemStack original){
        if(!original.isEmpty()) original.setCount(1);
        return original;
    }
}
