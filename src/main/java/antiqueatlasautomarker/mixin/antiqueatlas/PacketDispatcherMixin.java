package antiqueatlasautomarker.mixin.antiqueatlas;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.structuremarkers.network.AddedStructureMarkersPacket;
import antiqueatlasautomarker.structuremarkers.network.OptionalStructureMarkerPacket;
import hunternif.mc.atlas.network.AbstractMessage;
import hunternif.mc.atlas.network.PacketDispatcher;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PacketDispatcher.class)
public abstract class PacketDispatcherMixin {
    @Shadow(remap = false) private static <T extends AbstractMessage<T> & IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz) {
        AntiqueAtlasAutoMarker.LOGGER.error("Failed to shadow Antique Atlas PacketDispatcher.registerMessage");
    }

    @Inject(
            method = "registerPackets",
            at = @At(value = "TAIL"),
            remap = false
    )
    private static void updateStructureMarkers(CallbackInfo ci){
        registerMessage(OptionalStructureMarkerPacket.class);
        registerMessage(AddedStructureMarkersPacket.class);
    }
}
