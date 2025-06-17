package antiqueatlasautomarker.mixin.antiqueatlas.display;

import hunternif.mc.atlas.client.gui.core.GuiToggleButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiToggleButton.class)
public interface GuiToggleButtonAccessor {
    @Accessor(value = "selected", remap = false) boolean getIsSelected();
}
