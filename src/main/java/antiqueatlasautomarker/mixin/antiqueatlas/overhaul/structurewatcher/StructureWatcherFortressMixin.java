package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.structurewatcher;

import antiqueatlasautomarker.util.StructureWatcherReference;
import hunternif.mc.atlas.ext.watcher.impl.StructureWatcherFortress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureWatcherFortress.class)
public abstract class StructureWatcherFortressMixin {
    @Inject(
            method = "<init>",
            at = @At("TAIL"),
            remap = false
    )
    private void aaam_saveWatcher(CallbackInfo ci){
        StructureWatcherReference.fortressWatcher = (StructureWatcherFortress) (Object) this;
    }
}
