package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.structurewatcher;

import antiqueatlasautomarker.util.StructureWatcherReference;
import hunternif.mc.atlas.ext.watcher.impl.StructureWatcherVillage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureWatcherVillage.class)
public abstract class StructureWatcherVillageMixin {
    @Inject(
            method = "<init>",
            at = @At("TAIL"),
            remap = false
    )
    private void aaam_saveWatcher(CallbackInfo ci){
        StructureWatcherReference.villageWatcher = (StructureWatcherVillage) (Object) this;
    }
}
