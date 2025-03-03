package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding;

import antiqueatlasautomarker.structuremarkers.network.CustomPacketDispatcher;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.api.impl.MarkerApiImpl;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MarkerApiImpl.class)
public class MarkerApiImplMixin {
    @WrapWithCondition(
            method = "doPutMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V"),
            remap = false
    )
    private boolean sendToAllHoldingAtlas_onPut(IMessage message, @Local(argsOnly = true, ordinal = 0) int atlasID){
        //Don't send to all, instead send to all holding atlas
        //Reroute Global Markers also modifies the first call here, fully disabling it
        CustomPacketDispatcher.sendToAllHoldingAtlas(atlasID, message);
        return false;
    }

    @WrapWithCondition(
            method = "doDeleteMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V"),
            remap = false
    )
    private boolean sendToAllHoldingAtlas_onDelete(IMessage message, @Local(argsOnly = true, ordinal = 0) int atlasID){
        //Don't send to all, instead send to all holding atlas
        CustomPacketDispatcher.sendToAllHoldingAtlas(atlasID, message);
        return false;
    }
}