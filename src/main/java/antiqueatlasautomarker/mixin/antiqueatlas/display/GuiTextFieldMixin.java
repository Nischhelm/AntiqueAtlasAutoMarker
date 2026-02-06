package antiqueatlasautomarker.mixin.antiqueatlas.display;

import antiqueatlasautomarker.util.ISetsBackgroundColor;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Debug(export = true)
@Mixin(GuiTextField.class)
public abstract class GuiTextFieldMixin implements ISetsBackgroundColor {
    @Unique private int aaam$outerbackgroundColor = -6250336;
    @Unique private int aaam$innerbackgroundColor = -16777216;

    @Override
    public void aaam$setBackgroundColor(int outer, int inner) {
        this.aaam$outerbackgroundColor = outer;
        this.aaam$innerbackgroundColor = inner;
    }

    @ModifyConstant(
            method = "drawTextBox",
            constant = @Constant(intValue = 0xFFA0A0A0)
    )
    private int aaam_modifyOuterBackgroundColor(int outer) {
        return this.aaam$outerbackgroundColor;
    }

    @ModifyConstant(
            method = "drawTextBox",
            constant = @Constant(intValue = 0xFF000000)
    )
    private int aaam_modifyInnerBackgroundColor(int outer) {
        return this.aaam$innerbackgroundColor;
    }
}
