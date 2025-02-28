package antiqueatlasautomarker.mixin.antiqueatlas;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.ConfigHandler;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(MarkersData.class)
public abstract class MarkersDataDebugLogMixin {
    @Shadow(remap = false) @Final private Map<Integer, Marker> idMap;

    @Inject(
            method = "loadMarker",
            at = @At("HEAD"),
            remap = false
    )
    private void debugMsg(Marker marker, CallbackInfoReturnable<Marker> cir){
        if(ConfigHandler.doDebugLogs){
            boolean alreadyExists = this.idMap.containsKey(marker.getId());
            AntiqueAtlasAutoMarker.LOGGER.info("Loading Marker into atlas, marker id {}, already exists {}", marker.getId(), alreadyExists);
            if(alreadyExists ) AntiqueAtlasAutoMarker.LOGGER.info("Existing marker is {}", this.idMap.get(marker.getId()));
        }
    }
}
