package antiqueatlasautomarker.util;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.RegistrarAntiqueAtlas;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;

import javax.annotation.Nonnull;

public class GuiButtonMap extends GuiButtonExt {
    private static final ResourceLocation ANTIQUEATLAS = new ResourceLocation("antiqueatlas:textures/items/antique_atlas.png");
    private static MapWaystoneSelectionUtil.WarpProperty tmpWarpProperty = null;

    public GuiButtonMap(int id, int x, int y, MapWaystoneSelectionUtil.WarpProperty warpProperty) {
        super(id, x, y, "");
        this.width = 20;
        this.height = 20;
        tmpWarpProperty = warpProperty;
    }

    public boolean mousePressed(@Nonnull Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            ItemStack firstAtlas = null;
            for(ItemStack stack : mc.player.inventory.offHandInventory){
                if(stack.getItem().equals(RegistrarAntiqueAtlas.ATLAS)){
                    firstAtlas = stack;
                    break;
                }
            }
            if(firstAtlas == null)
                for(ItemStack stack : mc.player.inventory.mainInventory) {
                    if(stack.getItem().equals(RegistrarAntiqueAtlas.ATLAS)){
                        firstAtlas = stack;
                        break;
                    }
                }
            if(firstAtlas == null) return false;

            mc.player.closeScreen();
            MapWaystoneSelectionUtil.warpProperty.set(tmpWarpProperty);
            tmpWarpProperty = null;
            AntiqueAtlasMod.proxy.openAtlasGUI(firstAtlas);
            return true;
        }
        return false;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partial) {
        super.drawButton(mc, mouseX, mouseY, partial);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(ANTIQUEATLAS);
        Gui.drawModalRectWithCustomSizedTexture(this.x+2, this.y+2, 0, 0, 16, 16, 16, 16);
    }
}
