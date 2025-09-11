package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.structurewatcher;

import antiqueatlasautomarker.util.StructureWatcherReference;
import hunternif.mc.atlas.ext.watcher.impl.StructureWatcherGeneric;
import hunternif.mc.atlas.registry.MarkerType;
import net.minecraft.world.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureWatcherGeneric.class)
public abstract class StructureWatcherGenericMixin {
    @Inject(
            method = "<init>(Ljava/lang/String;Lnet/minecraft/world/DimensionType;Lhunternif/mc/atlas/registry/MarkerType;Ljava/lang/String;)V",
            at = @At("TAIL"),
            remap = false
    )
    private void aaam_saveWatcher(String datFileName, DimensionType dimType, MarkerType marker, String label, CallbackInfo ci){
        if(datFileName.equals("EndCity"))
            StructureWatcherReference.endCityWatcher = (StructureWatcherGeneric) (Object) this;
    }
}
