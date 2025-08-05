package antiqueatlasautomarker.mixin.antiqueatlas.selecttypevertical;

import hunternif.mc.atlas.client.gui.core.GuiScrollingContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GuiScrollingContainer.class)
public interface GuiScrollingContainerAccessor {
    @Invoker(value = "setWheelScrollsVertially", remap = false)
    void invokeSetWheelScrollsVertically();
}
