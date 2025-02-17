package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers;

import antiqueatlasautomarker.config.ForgeConfigHandler;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.api.impl.MarkerApiImpl;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        if(!ForgeConfigHandler.overhaul.rerouteGlobalMarkers) instance.createAndSaveMarker(type, label, dimension, x, z, visibleAhead);

        return StructureMarkersDataHandler.markStructure(world, x, z, type, label, "aa_global");
    }

    @Inject(
            method = "doPutMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V", ordinal = 0),
            remap = false,
            cancellable = true
    )
    private void dontSendToAll(World world, boolean visibleAhead, int atlasID, String markerType, String label, int x, int z, CallbackInfoReturnable<Marker> cir, @Local Marker marker){
        //Can't redirect bc that would conflict with sendToAllHoldingAtlas
        if(ForgeConfigHandler.overhaul.rerouteGlobalMarkers)
            cir.setReturnValue(marker);
    }
}