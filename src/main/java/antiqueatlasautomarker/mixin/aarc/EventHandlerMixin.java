package antiqueatlasautomarker.mixin.aarc;

import aarcaddon.handlers.EventHandler;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.api.MarkerAPI;
import hunternif.mc.atlas.marker.Marker;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nonnull;

@Mixin(EventHandler.class)
public class EventHandlerMixin {
    @Redirect(
            method = "onStructureGenerationLitePost",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/api/MarkerAPI;putGlobalMarker(Lnet/minecraft/world/World;ZLjava/lang/String;Ljava/lang/String;II)Lhunternif/mc/atlas/marker/Marker;"),
            remap = false
    )
    private Marker aaam_replaceAARCglobalMarkerWithLocalMarker(MarkerAPI instance, @Nonnull World world, boolean visibleAhead, String markerType, String label, int x, int z, @Local(ordinal = 0) String structureName) {
        if (ConfigHandler.aarcaddon.enabled) {
            //Context AARCAddon gets $structureName appended in order to use client settings
            return StructureMarkersDataHandler.markStructure(world, x, z, markerType, label, "AARCAddon$"+structureName);
        } else
            //Default behavior
            return instance.putGlobalMarker(world, visibleAhead, markerType, label, x, z);
    }
}
