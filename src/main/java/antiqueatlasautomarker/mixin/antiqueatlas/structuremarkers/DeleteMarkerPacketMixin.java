package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers;

import antiqueatlasautomarker.util.IDeletedMarkerList;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.marker.MarkersData;
import hunternif.mc.atlas.network.bidirectional.DeleteMarkerPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeleteMarkerPacket.class)
public abstract class DeleteMarkerPacketMixin implements IDeletedMarkerList {
    @Shadow(remap = false) private int markerID;
    @Shadow(remap = false) protected abstract boolean isGlobal();
    @Shadow(remap = false) private int atlasID;

    @Inject(
            method = "process",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/api/MarkerAPI;deleteMarker(Lnet/minecraft/world/World;II)V"),
            remap = false
    )
    private void saveDeletedMarkerId(EntityPlayer player, Side side, CallbackInfo ci){
        //Saving what Structure Markers got deleted
        if(side.isClient()) return;
        if(this.markerID >= 0) return;
        if(this.isGlobal()) return; //can't remove global markers anyway but just safety check
        MarkersData data = AntiqueAtlasMod.markersData.getMarkersData(this.atlasID, player.getEntityWorld());
        if(data == null) return; //shouldn't be necessary, since AA would crash anyway
        ((IDeletedMarkerList) data).addDeletedMarker(this.markerID);
    }
}
