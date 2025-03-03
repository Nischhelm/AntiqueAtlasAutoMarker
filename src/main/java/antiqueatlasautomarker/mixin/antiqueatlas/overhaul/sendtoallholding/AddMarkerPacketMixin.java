package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding;

import antiqueatlasautomarker.structuremarkers.network.CustomPacketDispatcher;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import hunternif.mc.atlas.network.server.AddMarkerPacket;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AddMarkerPacket.class)
public class AddMarkerPacketMixin {
    @Shadow(remap = false) private int atlasID;

    @WrapWithCondition(
            method = "process",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V"),
            remap = false
    )
    private boolean sendToAllHoldingAtlas(IMessage message){
        CustomPacketDispatcher.sendToAllHoldingAtlas(this.atlasID, message);
        return false; //Don't send to all
    }
}