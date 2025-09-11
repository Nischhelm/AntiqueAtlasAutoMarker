package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.structurewatcher;

import hunternif.mc.atlas.ext.watcher.StructureWatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureWatcher.class)
public abstract class StructureWatcherMixin {
    @Inject(
            method = {"onPopulateChunk", "onWorldLoad"},
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private void aaam_cancelOldHandling(CallbackInfo ci){
        ci.cancel();
    }
}
