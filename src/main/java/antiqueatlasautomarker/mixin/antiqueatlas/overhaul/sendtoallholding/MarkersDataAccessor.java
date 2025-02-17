package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding;

import hunternif.mc.atlas.marker.MarkersData;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(MarkersData.class)
public interface MarkersDataAccessor {
    @Accessor(value = "playersSentTo", remap = false)
    Set<EntityPlayer> getPlayersSentTo();
}
