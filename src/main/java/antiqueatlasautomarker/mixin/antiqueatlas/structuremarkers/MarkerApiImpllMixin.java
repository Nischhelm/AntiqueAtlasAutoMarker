package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers;

import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Cancellable;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.api.impl.MarkerApiImpl;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MarkerApiImpl.class, priority = 2000)
public abstract class MarkerApiImpllMixin {
    @WrapOperation(
            method = "doPutMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/marker/MarkersData;createAndSaveMarker(Ljava/lang/String;Ljava/lang/String;IIIZ)Lhunternif/mc/atlas/marker/Marker;", ordinal = 0),
            remap = false
    )
    private Marker aaam_rerouteGlobalMarkers(MarkersData instance, String type, String label, int dimension, int x, int z, boolean visibleAhead, Operation<Marker> original, @Local(argsOnly = true) World world){
        //Mark as structure instead of making a global marker
        if(ConfigHandler.overhaul.rerouteGlobalMarkers)
            return StructureMarkersDataHandler.markStructure(world, x, z, type, label, "aa_global");
        //Default behavior
        else return original.call(instance, type, label, dimension, x, z, visibleAhead);
    }

    @WrapWithCondition(
            method = "doPutMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/network/PacketDispatcher;sendToAll(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V", ordinal = 0),
            remap = false
    )
    private boolean aaam_dontSendToAll(IMessage message, @Local Marker marker){
        //Don't send global markers as global markers on creation to everyone, instead send them when finding them as structure markers
        return !ConfigHandler.overhaul.rerouteGlobalMarkers;
    }

    @ModifyExpressionValue(
            method = "doPutMarker",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/marker/MarkersData;createAndSaveMarker(Ljava/lang/String;Ljava/lang/String;IIIZ)Lhunternif/mc/atlas/marker/Marker;"),
            remap = false
    )
    private Marker aaam_dontSendNullPackets(Marker original, @Cancellable CallbackInfoReturnable<Marker> cir){
        if(original == null) cir.setReturnValue(null);
        return original;
    }
}