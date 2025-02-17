package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers;

import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import hunternif.mc.atlas.api.MarkerAPI;
import hunternif.mc.atlas.ext.watcher.impl.StructureWatcherGeneric;
import hunternif.mc.atlas.marker.Marker;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(StructureWatcherGeneric.class)
public abstract class StructureWatcherGenericMixin {
    @Redirect(
            method = "visit",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/api/MarkerAPI;putGlobalMarker(Lnet/minecraft/world/World;ZLjava/lang/String;Ljava/lang/String;II)Lhunternif/mc/atlas/marker/Marker;"),
            remap = false
    )
    private Marker makeGlobalMarkerStructureMarker(MarkerAPI instance, World world, boolean visibleAhead, String type, String label, int x, int z){
        //dont put the global marker lol

        //don't need to check for enabled in AA config bc that was already checked

        //instead mark it in structure markers list
        return StructureMarkersDataHandler.markStructure(
                world,
                x,
                z,
                type,
                label,
                "aa_generic"
        );
    }
}
