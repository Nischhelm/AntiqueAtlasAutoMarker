package antiqueatlasautomarker.mixin.waystones;

import antiqueatlasautomarker.util.WaystoneUtil;
import net.blay09.mods.waystones.client.gui.GuiEditWaystone;
import net.blay09.mods.waystones.util.WaystoneEntry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiEditWaystone.class)
public abstract class GuiEditWaystoneMixin extends GuiScreen {
    @Shadow(remap = false) @Final private WaystoneEntry tileWaystone;
    @Shadow(remap = false) private GuiTextField textField;

    @Inject(
            method = "actionPerformed",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/network/simpleimpl/SimpleNetworkWrapper;sendToServer(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;)V", remap = false)
    )
    private void aaam_renameWaystoneMarker(GuiButton button, CallbackInfo ci) {
        WaystoneUtil.updateRenamedWaystoneMarker(this.mc.player, this.mc.world, this.tileWaystone.getPos(), this.textField.getText());
    }
}
