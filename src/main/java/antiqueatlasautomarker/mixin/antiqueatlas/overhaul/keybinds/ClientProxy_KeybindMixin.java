package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.keybinds;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import hunternif.mc.atlas.ClientProxy;
import hunternif.mc.atlas.RegistrarAntiqueAtlas;
import hunternif.mc.atlas.SettingsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientProxy.class)
public abstract class ClientProxy_KeybindMixin {

    @Shadow(remap = false) public abstract void openAtlasGUI(ItemStack stack);

    @ModifyExpressionValue(
            method = "init",
            at = @At(value = "FIELD", target = "Lhunternif/mc/atlas/SettingsConfig$Gameplay;itemNeeded:Z"),
            remap = false
    )
    private boolean aaam_antiqueAtlasClientProxy_initKeybind(boolean itemNeeded){
        return false;
    }

    @WrapMethod(
            method = "openAtlasGUI()V",
            remap = false
    )
    private void aaam_antiqueAtlasClientProxy_openAtlasGUIKeybind(Operation<Void> original) {
        if (!SettingsConfig.gameplay.itemNeeded) {
            original.call();
        }
        else {
            ItemStack firstAtlas = null;
            Minecraft mc = Minecraft.getMinecraft();
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
            if(firstAtlas != null) {
                mc.player.closeScreen();
                this.openAtlasGUI(firstAtlas);
            }
        }
    }
}
