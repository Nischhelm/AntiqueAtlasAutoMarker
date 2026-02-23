package antiqueatlasautomarker.mixin.antiqueatlas.display;

import antiqueatlasautomarker.network.PacketHandler;
import antiqueatlasautomarker.network.PacketOtherAtlasHolders;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.core.AtlasData;
import hunternif.mc.atlas.item.ItemAtlas;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemAtlas.class)
public class ItemAtlas_ShowOtherPlayers {

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void aaam_ItemAtlas_sendVisiblePlayersToClient(
            ItemStack stack, World world, Entity entity, int slot, boolean isEquipped,
            CallbackInfo ci, @Local EntityPlayer player, @Local AtlasData data
    ){
        if(!world.isRemote && data != null)
            PacketHandler.instance.sendTo(new PacketOtherAtlasHolders(player, stack.getItemDamage()), (EntityPlayerMP) player);
    }
}
