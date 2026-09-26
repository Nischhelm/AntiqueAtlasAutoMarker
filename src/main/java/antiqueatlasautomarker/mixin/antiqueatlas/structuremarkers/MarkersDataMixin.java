package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers;

import antiqueatlasautomarker.mixinwrapper.IDeletedMarkerList;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import hunternif.mc.atlas.marker.MarkersData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.WorldSavedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(MarkersData.class)
public abstract class MarkersDataMixin extends WorldSavedData implements IDeletedMarkerList {
    //Saves and loads deleted marker ids
    public MarkersDataMixin(String name) { super(name); }

    @Unique private List<Integer> aaam$removedMarkerIds = null;

    @Override
    public boolean aaam$markerIsDeleted(int markerID) {
        return aaam$removedMarkerIds != null && aaam$removedMarkerIds.contains(markerID);
    }

    @Override
    public void aaam$addDeletedMarker(int markerID) {
        if(aaam$removedMarkerIds == null) aaam$removedMarkerIds = new ArrayList<>();
        aaam$removedMarkerIds.add(markerID);
        this.markDirty();
    }

    @ModifyReturnValue(
            method = "writeToNBT",
            at = @At("RETURN")
    )
    private NBTTagCompound aaam_writeDeletedIdsToNBT(NBTTagCompound original){
        if(aaam$removedMarkerIds == null || aaam$removedMarkerIds.isEmpty()) return original;
        NBTTagList idList = new NBTTagList();
        for(Integer removedId : aaam$removedMarkerIds)
            idList.appendTag(new NBTTagInt(removedId));
        original.setTag("aaam_removedIds", idList);
        return original;
    }


    @Inject(
            method = "readFromNBT",
            at = @At("TAIL")
    )
    private void aaam_readDeletedIdsFromNBT(NBTTagCompound compound, CallbackInfo ci){
        if(!compound.hasKey("aaam_removedIds")) return;
        NBTTagList idList = compound.getTagList("aaam_removedIds", 3);

        if(aaam$removedMarkerIds == null) aaam$removedMarkerIds = new ArrayList<>();
        for(int i=0; i<idList.tagCount(); i++)
            aaam$removedMarkerIds.add(idList.getIntAt(i));

        this.markDirty();
    }
}
