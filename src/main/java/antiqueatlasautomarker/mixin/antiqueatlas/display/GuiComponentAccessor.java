package antiqueatlasautomarker.mixin.antiqueatlas.display;

import hunternif.mc.atlas.client.gui.core.GuiComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GuiComponent.class)
public interface GuiComponentAccessor {
    @Invoker(value = "isMouseInRegion", remap = false)
    boolean invokeIsMouseInRegion(int left, int top, int width, int height);
}
