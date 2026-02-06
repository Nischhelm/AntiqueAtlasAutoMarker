package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.keybinds;

import hunternif.mc.atlas.client.gui.core.GuiComponentButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GuiComponentButton.class)
public interface GuiComponentButton_Invoker {
    @Invoker(value = "onClick", remap = false) void invokeOnClick();
}
