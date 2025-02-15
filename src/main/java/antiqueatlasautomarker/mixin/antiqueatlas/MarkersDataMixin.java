package antiqueatlasautomarker.mixin.antiqueatlas;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.util.IDeletedMarkerList;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import hunternif.mc.atlas.marker.MarkersData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.WorldSavedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(MarkersData.class)
public abstract class MarkersDataMixin extends WorldSavedData implements IDeletedMarkerList {
    public MarkersDataMixin(String name) { super(name); }

    private List<Integer> removedMarkerIds = null;

    @Override
    public boolean markerIsDeleted(int markerID) {
        return removedMarkerIds != null && removedMarkerIds.contains(markerID);
    }

    @Override
    public void addDeletedMarker(int markerID) {
        if(removedMarkerIds == null) removedMarkerIds = new ArrayList<>();
        removedMarkerIds.add(markerID);
        this.markDirty();
    }

    @ModifyReturnValue(
            method = "writeToNBT",
            at = @At("RETURN")
    )
    private NBTTagCompound writeDeletedIdsToNBT(NBTTagCompound original){
        if(removedMarkerIds == null || removedMarkerIds.isEmpty()) return original;
        NBTTagList idList = new NBTTagList();
        for(Integer removedId : removedMarkerIds)
            idList.appendTag(new NBTTagInt(removedId));
        original.setTag("aaam_removedIds", idList);
        return original;
    }


    @Inject(
            method = "readFromNBT",
            at = @At("TAIL")
    )
    private void readDeletedIdsFromNBT(NBTTagCompound compound, CallbackInfo ci){
        if(!compound.hasKey("aaam_removedIds")) return;
        NBTTagList idList = compound.getTagList("aaam_removedIds", 3);

        if(removedMarkerIds == null) removedMarkerIds = new ArrayList<>();
        for(int i=0; i<idList.tagCount(); i++)
            removedMarkerIds.add(idList.getIntAt(i));

        this.markDirty();
    }
}
