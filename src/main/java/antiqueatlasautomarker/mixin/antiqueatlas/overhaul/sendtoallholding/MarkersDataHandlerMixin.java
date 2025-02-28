package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding;

import antiqueatlasautomarker.util.ILoadedDataList;
import hunternif.mc.atlas.marker.MarkersData;
import hunternif.mc.atlas.marker.MarkersDataHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(MarkersDataHandler.class)
public abstract class MarkersDataHandlerMixin implements ILoadedDataList {
    @Unique private final List<Integer> loadedAtlasIdList = new ArrayList<>();
    @Unique public List<Integer> getLoadedIds(){
        return loadedAtlasIdList;
    }

    @Inject(
            method = "getMarkersData(ILnet/minecraft/world/World;)Lhunternif/mc/atlas/marker/MarkersData;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;loadData(Ljava/lang/Class;Ljava/lang/String;)Lnet/minecraft/world/storage/WorldSavedData;")
    )
    private void storeLoadedAtlasId(int atlasID, World world, CallbackInfoReturnable<MarkersData> cir){
        //Keep a list of all loaded atlas ids on this server since last server restart
        if(world.isRemote) return;
        if(!loadedAtlasIdList.contains(atlasID))
            loadedAtlasIdList.add(atlasID);
    }
}
