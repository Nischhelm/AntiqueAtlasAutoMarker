package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding;

import antiqueatlasautomarker.structuremarkers.network.CustomPacketDispatcher;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.item.ItemAtlas;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemAtlas.class)
public class ItemAtlasMixin {
    @Redirect(
            method = "onUpdate",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V", ordinal = 0, remap = false)
    )
    private void sendTileDataToAllHoldingAtlas(IMessage message, @Local(argsOnly = true) ItemStack stack) {
        CustomPacketDispatcher.sendToAllHoldingAtlas(stack.getItemDamage(), message);
    }
}
