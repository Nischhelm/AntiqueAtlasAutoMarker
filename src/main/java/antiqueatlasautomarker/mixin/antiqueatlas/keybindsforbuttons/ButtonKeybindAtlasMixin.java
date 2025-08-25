package antiqueatlasautomarker.mixin.antiqueatlas.keybindsforbuttons;

import antiqueatlasautomarker.client.KeyHandler;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.client.gui.GuiAtlas;
import hunternif.mc.atlas.client.gui.GuiBookmarkButton;
import hunternif.mc.atlas.client.gui.core.GuiComponent;
import hunternif.mc.atlas.client.gui.core.GuiCursor;
import hunternif.mc.atlas.client.gui.core.GuiStates;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiAtlas.class)
public abstract class ButtonKeybindAtlasMixin extends GuiComponent {

    @Shadow(remap = false) @Final private GuiBookmarkButton btnMarker;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnDelMarker;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnShowMarkers;
    @Shadow(remap = false) @Final private GuiCursor eraser;

    @Shadow (remap = false) @Mutable
    @Final private GuiStates.IState DELETING_MARKER;

    @Inject(
            method = "<init>",
            at = @At("TAIL"),
            remap = false
    )
    public void aaam_cancelDeleteResettingPosition(CallbackInfo ci){
        // I don't know why it originally needed to grab the mouse cursor, but the behavior is awkward
        DELETING_MARKER = new GuiStates.IState() {
            @Override
            public void onEnterState() {
                addChild(eraser);
                btnDelMarker.setSelected(true);
            }
            @Override
            public void onExitState() {
                removeChild(eraser);
                btnDelMarker.setSelected(false);
            }
        };
    }

    @Inject(
            method = "handleKeyboardInput",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;getKeyCode()I", ordinal = 0)
    )
    public void aaam_keybindClicksButton(CallbackInfo ci, @Local int key) {
        if(KeyHandler.addButtonKey.isActiveAndMatches(key)){
            ((GuiComponentButtonInvoker)this.btnMarker).invokeOnClick();
        }
        else if(KeyHandler.deleteButtonKey.isActiveAndMatches(key)){
            ((GuiComponentButtonInvoker)this.btnDelMarker).invokeOnClick();
        }
        else if(KeyHandler.toggleButtonKey.isActiveAndMatches(key)){
            ((GuiComponentButtonInvoker)this.btnShowMarkers).invokeOnClick();
        }
    }
}
