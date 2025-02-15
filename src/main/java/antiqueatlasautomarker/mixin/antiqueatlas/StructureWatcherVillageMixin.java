package antiqueatlasautomarker.mixin.antiqueatlas;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import hunternif.mc.atlas.api.MarkerAPI;
import hunternif.mc.atlas.ext.watcher.impl.StructureWatcherVillage;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.registry.MarkerType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StructureWatcherVillage.class)
public abstract class StructureWatcherVillageMixin {
    @Redirect(
            method = "visitVillage",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/api/MarkerAPI;putGlobalMarker(Lnet/minecraft/world/World;ZLhunternif/mc/atlas/registry/MarkerType;Ljava/lang/String;II)Lhunternif/mc/atlas/marker/Marker;"),
            remap = false
    )
    private Marker makeVillageMarkerStructureMarker(MarkerAPI instance, World world, boolean visibleAhead, MarkerType markerType, String label, int x, int z){
        //dont put the global marker lol

        //don't need to check for AA config bc that was already checked

        //instead mark it in structure markers list
        return StructureMarkersDataHandler.markStructure(
                world,
                x,
                z,
                "antiqueatlas:village",
                label,
                "aa_village"
        );
    }
}
