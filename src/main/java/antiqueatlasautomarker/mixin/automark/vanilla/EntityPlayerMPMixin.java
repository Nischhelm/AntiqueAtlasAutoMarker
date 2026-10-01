package antiqueatlasautomarker.mixin.automark.vanilla;

import antiqueatlasautomarker.config.ConfigHandler;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityPlayerMP.class)
public class EntityPlayerMPMixin {

    @ModifyArg(
            method = "displayVillagerTradeGui",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/play/server/SPacketCustomPayload;<init>(Ljava/lang/String;Lnet/minecraft/network/PacketBuffer;)V")
    )
    private PacketBuffer aaam_sendVillagerPosition(PacketBuffer buf, @Local(argsOnly = true) IMerchant merchant) {
        if (!ConfigHandler.automark.enchantments.enabled) return buf;

        // Write server merchant position to the packet buffer
        // Clients without AAAM will ignore these extra bytes
        BlockPos pos = merchant.getPos();
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getZ());
        return buf;
    }
}