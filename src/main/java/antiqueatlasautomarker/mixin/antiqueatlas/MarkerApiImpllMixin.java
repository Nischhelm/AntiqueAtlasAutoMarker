package antiqueatlasautomarker.mixin.antiqueatlas;

import antiqueatlasautomarker.config.ForgeConfigHandler;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.api.impl.MarkerApiImpl;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import hunternif.mc.atlas.network.PacketDispatcher;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;

@Mixin(MarkerApiImpl.class)
public abstract class MarkerApiImpllMixin {
    @Redirect(
            method = "doPutMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/marker/MarkersData;createAndSaveMarker(Ljava/lang/String;Ljava/lang/String;IIIZ)Lhunternif/mc/atlas/marker/Marker;", ordinal = 0),
            remap = false
    )
    @Nullable
    private Marker rerouteGlobalMarkers(MarkersData instance, String type, String label, int dimension, int x, int z, boolean visibleAhead, @Local(argsOnly = true) World world){
        //Default behavior
        if(!ForgeConfigHandler.rerouteGlobalMarkers) instance.createAndSaveMarker(type, label, dimension, x, z, visibleAhead);

        return StructureMarkersDataHandler.markStructure(world, x, z, type, label, "aa_global");
    }

    @Redirect(
            method = "doPutMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V", ordinal = 0),
            remap = false
    )
    private void dontSendToAll(IMessage message){
        //Default behavior
        if(!ForgeConfigHandler.rerouteGlobalMarkers) PacketDispatcher.sendToAll(message);

        //else no op
    }
}
