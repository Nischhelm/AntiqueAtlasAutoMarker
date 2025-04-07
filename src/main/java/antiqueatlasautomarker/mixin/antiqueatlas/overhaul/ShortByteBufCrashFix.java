package antiqueatlasautomarker.mixin.antiqueatlas.overhaul;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hunternif.mc.atlas.network.client.ShortDimensionUpdatePacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShortDimensionUpdatePacket.class)
public class ShortByteBufCrashFix {
    @WrapOperation(
            method = "write",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketBuffer;writeBytes(Lio/netty/buffer/ByteBuf;)Lio/netty/buffer/ByteBuf;")
    )
    private ByteBuf aaam_fixCrash(PacketBuffer instance, ByteBuf bytes, Operation<ByteBuf> original){
        //No clue why this is necessary but it seems to fix rare crashes
        return instance.writeBytes(bytes, 0, bytes.writerIndex());
    }
}
