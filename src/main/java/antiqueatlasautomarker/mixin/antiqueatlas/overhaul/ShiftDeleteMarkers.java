package antiqueatlasautomarker.mixin.antiqueatlas.overhaul;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hunternif.mc.atlas.api.MarkerAPI;
import hunternif.mc.atlas.client.gui.GuiAtlas;
import hunternif.mc.atlas.marker.Marker;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiAtlas.class)
public abstract class ShiftDeleteMarkers {
    @Shadow(remap = false) private EntityPlayer player;
    @Shadow(remap = false) private Marker hoveredMarker;

    @WrapOperation(
            method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/api/MarkerAPI;deleteMarker(Lnet/minecraft/world/World;II)V", remap = false)
    )
    private void aaam_shiftDeleteMarkers(MarkerAPI instance, World world, int atlasID, int markerID, Operation<Void> original) {
        if(!GuiScreen.isShiftKeyDown()) original.call(instance, world, atlasID, markerID);
        else {
            String command = "/aaam removemarkers" +
                    " " + hoveredMarker.getType() +
                    " " + atlasID +
                    " -1" +
                    " " + hoveredMarker.getLabel();
            ITextComponent clickableLink = new TextComponentTranslation(hoveredMarker.getLocalizedLabel());
            clickableLink.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
            clickableLink.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("gui.antiqueatlas.shiftdelete.hover")));
            clickableLink.getStyle().setColor(TextFormatting.AQUA);
            clickableLink.getStyle().setUnderlined(true);
            player.sendMessage(new TextComponentTranslation("gui.antiqueatlas.shiftdelete", clickableLink, atlasID));
        }
    }
}
