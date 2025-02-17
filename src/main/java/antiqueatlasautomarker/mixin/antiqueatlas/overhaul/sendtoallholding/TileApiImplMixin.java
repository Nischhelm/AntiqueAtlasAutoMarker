package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding;

import antiqueatlasautomarker.structuremarkers.network.CustomPacketDispatcher;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.api.impl.TileApiImpl;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TileApiImpl.class)
public class TileApiImplMixin {
    //we don't redirect putCustomGlobalTile or deleteCustomGlobalTile bc those are atlas-unspecific

    @Redirect(
            method = "putCustomTile",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V"),
            remap = false
    )
    private void sendToAllHoldingAtlas(IMessage message, @Local(argsOnly = true, ordinal = 0) int atlasID){
        CustomPacketDispatcher.sendToAllHoldingAtlas(atlasID, message);
    }
}
