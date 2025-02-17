package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding;

import antiqueatlasautomarker.structuremarkers.network.CustomPacketDispatcher;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.api.impl.MarkerApiImpl;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MarkerApiImpl.class)
public class MarkerApiImplMixin {
    @Redirect(
            method = "doPutMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V"),
            remap = false
    )
    private void sendToAllHoldingAtlas_onPut(IMessage message, @Local(argsOnly = true, ordinal = 0) int atlasID){
        CustomPacketDispatcher.sendToAllHoldingAtlas(atlasID, message);
    }

    @Redirect(
            method = "doDeleteMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V"),
            remap = false
    )
    private void sendToAllHoldingAtlas_onDelete(IMessage message, @Local(argsOnly = true, ordinal = 0) int atlasID){
        CustomPacketDispatcher.sendToAllHoldingAtlas(atlasID, message);
    }
}