package antiqueatlasautomarker.mixin.waystones.mapselection;

import antiqueatlasautomarker.util.GuiButtonMap;
import antiqueatlasautomarker.util.MapWaystoneSelectionUtil;
import net.blay09.mods.waystones.WarpMode;
import net.blay09.mods.waystones.client.gui.GuiWaystoneList;
import net.blay09.mods.waystones.util.WaystoneEntry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiWaystoneList.class)
public abstract class GuiWaystoneListMixin extends GuiScreen {
    @Shadow(remap = false) private WaystoneEntry[] entries;
    @Shadow(remap = false) @Final private EnumHand hand;
    @Shadow(remap = false) @Final private WarpMode warpMode;
    @Shadow(remap = false) @Final private WaystoneEntry fromWaystone;
    @Shadow(remap = false) private int headerY;
    @Unique private GuiButton btnMap;

    @Inject(
            method = "initGui",
            at = @At(value = "INVOKE", target = "Lnet/blay09/mods/waystones/client/gui/GuiWaystoneList;updateList()V", remap = false)
    )
    private void initButton(CallbackInfo ci){
        this.btnMap = new GuiButtonMap(2, this.width / 2 + 80, this.headerY + 18, new MapWaystoneSelectionUtil.WarpProperty(entries, hand, warpMode, fromWaystone));
        this.buttonList.add(this.btnMap);
    }

    @ModifyConstant(
            method = "updateList",
            constant = @Constant(intValue = 2, ordinal = 2),
            remap = false
    )
    private int incrementIds(int constant){
        return 3;
    }

    @Inject(
            method = "updateList",
            at = @At("TAIL"),
            remap = false
    )
    private void positionButtonCorrectly(CallbackInfo ci){
        this.btnMap.y = this.headerY + 18;
    }
}
