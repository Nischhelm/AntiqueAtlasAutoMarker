package antiqueatlasautomarker.mixin.antiqueatlas.display;

import antiqueatlasautomarker.util.IUntoggleable;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hunternif.mc.atlas.client.gui.core.GuiComponentButton;
import hunternif.mc.atlas.client.gui.core.GuiToggleButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiToggleButton.class)
public abstract class GuiToggleButtonMixin extends GuiComponentButton implements IUntoggleable {
    @Shadow(remap = false) protected abstract boolean isSelected();

    @Unique private boolean aaam$isUntoggleable = false;
    @Override public void aaam$setIsUntoggleable(){
        aaam$isUntoggleable = true;
    }

    @ModifyExpressionValue(
            method = "onClick",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiToggleButton;isSelected()Z"),
            remap = false
    )
    private boolean aaam_alsoReactToUnselect(boolean original){
        if(aaam$isUntoggleable)
            return false; //will run the code no matter what

        return original;
    }

    @WrapOperation(
            method = "onClick",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiToggleButton;setSelected(Z)V"),
            remap = false
    )
    private void aaam_setToOtherState(GuiToggleButton instance, boolean value, Operation<Void> original){
        original.call(this, !this.isSelected());
    }
}
