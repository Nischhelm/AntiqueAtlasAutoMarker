package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.keybinds;

import hunternif.mc.atlas.client.gui.GuiBookmarkButton;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GuiBookmarkButton.class)
public interface GuiBookmarkButton_Invoker {
    @Invoker(value = "<init>", remap = false)
    static GuiBookmarkButton invokeInit(int colorIndex, ResourceLocation iconTexture, String title) {
        throw new AssertionError();
    }
}
