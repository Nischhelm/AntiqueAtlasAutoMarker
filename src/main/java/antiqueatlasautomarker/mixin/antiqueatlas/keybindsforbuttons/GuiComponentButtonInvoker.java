package antiqueatlasautomarker.mixin.antiqueatlas.keybindsforbuttons;

import hunternif.mc.atlas.client.gui.core.GuiComponentButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GuiComponentButton.class)
public interface GuiComponentButtonInvoker {
    @Invoker(value = "onClick", remap = false) void invokeOnClick();
}
