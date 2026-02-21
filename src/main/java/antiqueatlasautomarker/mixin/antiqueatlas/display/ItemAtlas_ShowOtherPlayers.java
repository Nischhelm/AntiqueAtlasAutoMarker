package antiqueatlasautomarker.mixin.antiqueatlas.display;

import antiqueatlasautomarker.network.PacketHandler;
import antiqueatlasautomarker.network.PacketOtherAtlasHolders;
import hunternif.mc.atlas.item.ItemAtlas;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemAtlas.class)
public class ItemAtlas_ShowOtherPlayers {

    @Inject(
            method = "onUpdate",
            at = @At("TAIL")
    )
    private void aaam_ItemAtlas_sendVisiblePlayersToClient(ItemStack stack, World world, Entity entity, int slot, boolean isEquipped, CallbackInfo ci){
        if(!world.isRemote && entity instanceof EntityPlayerMP){
            PacketHandler.instance.sendTo(new PacketOtherAtlasHolders((EntityPlayerMP) entity, stack.getItemDamage()), (EntityPlayerMP) entity);
        }
    }
}
