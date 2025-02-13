package antiqueatlasautomarker.mixin.aarc;

import aarcaddon.handlers.EventHandler;
import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.util.StructureGenerationUtil;
import hunternif.mc.atlas.api.MarkerAPI;
import hunternif.mc.atlas.marker.Marker;
import net.minecraft.util.math.BlockPos;
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
    private Marker aaam_replaceAARCglobalMarkerWithLocalMarker(MarkerAPI instance, @Nonnull World world, boolean visibleAhead, String markerType, String label, int x, int z) {
        AutoMarkSetting setting = AutoMarkSetting.autoMarkSettings.get("AARCAddonMarker");
        if (setting != null && setting.enabled) {
            StructureGenerationUtil.markStructure(world, new BlockPos(x, 0, z), setting.dist, markerType, label);
            return null; //return value is unused
        } else
            //Default behavior
            return instance.putGlobalMarker(world, visibleAhead, markerType, label, x, z);
    }
}
