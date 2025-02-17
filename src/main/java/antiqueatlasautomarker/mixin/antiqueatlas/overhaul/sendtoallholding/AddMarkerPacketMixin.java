package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding;

import antiqueatlasautomarker.structuremarkers.network.CustomPacketDispatcher;
import hunternif.mc.atlas.network.server.AddMarkerPacket;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AddMarkerPacket.class)
public class AddMarkerPacketMixin {
    @Shadow(remap = false) private int atlasID;

    @Redirect(
            method = "process",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V"),
            remap = false
    )
    private void sendToAllHoldingAtlas(IMessage message){
        CustomPacketDispatcher.sendToAllHoldingAtlas(this.atlasID, message);
    }
}