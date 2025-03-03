package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding;

import antiqueatlasautomarker.structuremarkers.network.CustomPacketDispatcher;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.item.ItemAtlas;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemAtlas.class)
public class ItemAtlasMixin {
    @WrapWithCondition(
            method = "onUpdate",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V", ordinal = 0, remap = false)
    )
    private boolean sendTileDataToAllHoldingAtlas(IMessage message, @Local(argsOnly = true) ItemStack stack) {
        CustomPacketDispatcher.sendToAllHoldingAtlas(stack.getItemDamage(), message);
        return false; //Don't send to all
    }

    @ModifyExpressionValue(
            method = "onUpdate",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/marker/MarkersData;isEmpty()Z", remap = false)
    )
    private boolean syncMarkersEvenIfEmpty(boolean original){
        //Need to sync even if empty cause we're depending on the players being added to Data.playersSentTo
        //if the data is actually empty, there won't be much of a packet anyway
        return false; //Don't do the isEmpty check
    }

    @ModifyExpressionValue(
            method = "onUpdate",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/core/AtlasData;isEmpty()Z", remap = false)
    )
    private boolean syncAtlasEvenIfEmpty(boolean original){
        //Need to sync even if empty cause we're depending on the players being added to Data.playersSentTo
        //if the data is actually empty, there won't be much of a packet anyway
        return false; //Don't do the isEmpty check
    }
}
