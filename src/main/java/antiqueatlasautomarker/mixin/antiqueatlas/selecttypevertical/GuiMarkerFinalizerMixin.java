package antiqueatlasautomarker.mixin.antiqueatlas.selecttypevertical;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.client.gui.GuiMarkerFinalizer;
import hunternif.mc.atlas.client.gui.core.GuiComponent;
import hunternif.mc.atlas.client.gui.core.GuiScrollingContainer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GuiMarkerFinalizer.class)
public abstract class GuiMarkerFinalizerMixin extends GuiComponent {
    @Unique private static final int aaam$maxMarkersPerLine = 7;
    @Unique private static final int aaam$sizePerType = 35;

    @Shadow(remap = false) @Final private GuiScrollingContainer scroller;

    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiScrollingContainer;setWheelScrollsHorizontally()V"),
            remap = false
    )
    private void aaam_setScrollsVertically(GuiScrollingContainer instance){
        //Scrolling vertically instead of hortizontally
        ((GuiScrollingContainerAccessor) instance).invokeSetWheelScrollsVertically();
    }

    @Redirect(
            method = "initGui",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiScrollingContainer;setViewportSize(II)V", remap = false)
    )
    private void aaam_changeSelectionAreaSize(GuiScrollingContainer instance, int width, int height, @Local(name = "typeCount") int typeCount){
        int maxHeight = typeCount * aaam$sizePerType - 1;
        this.scroller.setViewportSize(aaam$maxMarkersPerLine*aaam$sizePerType, Math.min(maxHeight, 3 * aaam$sizePerType - 1));
    }

    @Redirect(
            method = "initGui",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiScrollingContainer;setGuiCoords(II)V", remap = false)
    )
    private void aaam_changeSelectionAreaSize(GuiScrollingContainer instance, int width, int height){
        int scrollerWidth = aaam$maxMarkersPerLine * aaam$sizePerType;
        instance.setGuiCoords((this.width - scrollerWidth) / 2 - 3, height - 13);
    }

    @Redirect(
            method = "initGui",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiComponent;setRelativeX(I)V", remap = false)
    )
    private void aaam_wrapMarkerTypesInScroller(GuiComponent instance, int oldWidth){
        //Wrapping marker types inside the scroller
        int x = oldWidth % (aaam$sizePerType * aaam$maxMarkersPerLine);
        int y = oldWidth / (aaam$sizePerType * aaam$maxMarkersPerLine) * aaam$sizePerType;
        instance.setRelativeX(x);
        instance.setRelativeY(y);
    }

    @ModifyExpressionValue(
            method = "initGui",
            at = @At(value = "NEW", target = "(IIIIILjava/lang/String;)Lnet/minecraft/client/gui/GuiButton;")
    )
    private GuiButton aaam_moveButtonsDown(GuiButton original){
        //Move buttons 34 pixels down
        original.y += 34;
        return original;
    }

    @ModifyExpressionValue(
            method = "initGui",
            at = @At(value = "NEW", target = "(ILnet/minecraft/client/gui/FontRenderer;IIII)Lnet/minecraft/client/gui/GuiTextField;")
    )
    private GuiTextField aaam_moveTextFieldUp(GuiTextField original){
        //Move Text Input Field 1 pixel up
        original.y -= 1;
        return original;
    }

    @ModifyArgs(
            method = "drawScreen",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/GuiMarkerFinalizer;drawCenteredString(Ljava/lang/String;IIZ)V", ordinal = 1, remap = false)
    )
    private void aaam_moveTypeTextUp(Args args){
        //Move "Type" string 12 pixels up
        args.set(1, (int) args.get(1) - 12);
    }
}
