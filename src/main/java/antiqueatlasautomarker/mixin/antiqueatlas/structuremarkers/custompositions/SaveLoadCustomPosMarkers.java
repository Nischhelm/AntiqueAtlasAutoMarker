package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers.custompositions;

import antiqueatlasautomarker.structuremarkers.CustomPosition;
import antiqueatlasautomarker.util.ICustomPosMarker;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MarkersData.class)
public abstract class SaveLoadCustomPosMarkers {
    @WrapOperation(
            method = "writeToNBT",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NBTTagCompound;setBoolean(Ljava/lang/String;Z)V")
    )
    private void aaam_writeCustomPosMarker(NBTTagCompound instance, String key, boolean value, Operation<Void> original, @Local Marker marker){
        original.call(instance, key, value);
        if(((ICustomPosMarker) marker).isCustomPosMarker()){
            CustomPosition pos = ((ICustomPosMarker) marker).aaam$getDiscoverPosition();
            instance.setInteger("aaam_discX", pos.bigChunkX);
            instance.setInteger("aaam_discZ", pos.bigChunkZ);
        }
    }

    @Inject(
            method = "readFromNBT",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/marker/MarkersData;loadMarker(Lhunternif/mc/atlas/marker/Marker;)Lhunternif/mc/atlas/marker/Marker;", remap = false)
    )
    private void aaam_readCustomPosMarker(NBTTagCompound compound, CallbackInfo ci, @Local Marker marker, @Local(name = "markerTag") NBTTagCompound markerTag){
        if(markerTag.hasKey("aaam_discX")){
            CustomPosition pos = new CustomPosition().setBigChunkCoords(markerTag.getInteger("aaam_discX"), markerTag.getInteger("aaam_discZ"));
            ((ICustomPosMarker) marker).aaam$setDiscoverPosition(pos);
        }
    }
}
