package antiqueatlasautomarker.mixin.antiqueatlas.mapselection;

import antiqueatlasautomarker.util.MapWaystoneSelectionUtil;
import hunternif.mc.atlas.client.gui.GuiBookmarkButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiBookmarkButton.class)
public abstract class GuiBookmarkButtonMixin {
    @Inject(
            method = "drawScreen",
            at = @At("HEAD"),
            cancellable = true
    )
    private void dontRender(int mouseX, int mouseY, float partialTick, CallbackInfo ci){
        if(MapWaystoneSelectionUtil.getIsFromWaystone())
            ci.cancel();
    }
}
