package antiqueatlasautomarker.mixin.antiqueatlas.overhaul;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hunternif.mc.atlas.network.client.IntDimensionUpdatePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IntDimensionUpdatePacket.class)
public class IntByteBufCrashFix {
    @WrapOperation(
            method = "write",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketBuffer;writeBytes(Lio/netty/buffer/ByteBuf;)Lio/netty/buffer/ByteBuf;")
    )
    private ByteBuf aaam_fixCrash(PacketBuffer instance, ByteBuf bytes, Operation<ByteBuf> original){
        return instance.writeBytes(bytes, 0, bytes.writerIndex());
    }
}
