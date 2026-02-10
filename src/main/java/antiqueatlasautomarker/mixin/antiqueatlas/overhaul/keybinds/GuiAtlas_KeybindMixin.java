package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.keybinds;

import antiqueatlasautomarker.client.handlers.KeyHandler;
import antiqueatlasautomarker.network.PacketExportPutMarker;
import antiqueatlasautomarker.network.PacketHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.SettingsConfig;
import hunternif.mc.atlas.client.Textures;
import hunternif.mc.atlas.client.gui.GuiAtlas;
import hunternif.mc.atlas.client.gui.GuiBookmarkButton;
import hunternif.mc.atlas.client.gui.GuiPositionButton;
import hunternif.mc.atlas.client.gui.core.GuiComponent;
import hunternif.mc.atlas.client.gui.core.GuiComponentButton;
import hunternif.mc.atlas.client.gui.core.GuiCursor;
import hunternif.mc.atlas.client.gui.core.GuiStates;
import hunternif.mc.atlas.marker.Marker;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiAtlas.class)
public abstract class GuiAtlas_KeybindMixin extends GuiComponent {
    @Unique private static final ResourceLocation COPY_TEXTURE = new ResourceLocation("antiqueatlas:textures/gui/icons/aaam_copyshare.png");

    @Shadow(remap = false) private ItemStack stack;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnMarker;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnDelMarker;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnShowMarkers;
    @Shadow(remap = false) @Final private GuiPositionButton btnPosition;
    @Shadow(remap = false) private GuiComponentButton selectedButton;

    @Shadow(remap = false) @Final private GuiStates state;
    @Shadow(remap = false) @Final private GuiStates.IState NORMAL;

    @Shadow(remap = false) private Marker hoveredMarker;
    @Shadow(remap = false) private EntityPlayer player;

    @Unique private final GuiCursor aaam$copyMarkerCursor = new GuiCursor();

    @Unique private final GuiBookmarkButton aaam$btnCopyMarker = GuiBookmarkButton_Invoker.invokeInit(1, COPY_TEXTURE, I18n.format("gui.antiqueatlas.copymarker"));
    @Unique private final GuiStates.IState COPY_MARKER = new GuiStates.IState() {
        @Override
        public void onEnterState() {
            mc.mouseHelper.grabMouseCursor();
            addChild(aaam$copyMarkerCursor);
            aaam$btnCopyMarker.setSelected(true);
        }
        @Override
        public void onExitState() {
            mc.mouseHelper.ungrabMouseCursor();
            removeChild(aaam$copyMarkerCursor);
            aaam$btnCopyMarker.setSelected(false);
        }
    };

    @Inject(
            method = "<init>",
            at = @At("TAIL"),
            remap = false
    )
    public void aaam_antiqueAtlasGuiAtlas_initCancelDeleteReset(CallbackInfo ci){
        this.addChild(aaam$btnCopyMarker).offsetGuiCoords(300, -5);
        this.aaam$btnCopyMarker.addListener(button -> {
            if (this.stack != null || !SettingsConfig.gameplay.itemNeeded) {
                if (this.state.is(COPY_MARKER)) {
                    this.selectedButton = null;
                    this.state.switchTo(NORMAL);
                } else {
                    this.selectedButton = button;
                    this.state.switchTo(COPY_MARKER);
                }
            }
        });

        this.aaam$copyMarkerCursor.setTexture(Textures.BOOK, 12, 14, 2, 11);
    }

    @WrapOperation(
            method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiStates;switchTo(Lhunternif/mc/atlas/client/gui/core/GuiStates$IState;)V", remap = false)
    )
    private void aaam_antiqueAtlasGuiAtlas_mouseClickedCopyData(GuiStates instance, GuiStates.IState state, Operation<Void> original, @Local(name = "mouseState") int mouseState, @Local(name = "isMouseOverMap") boolean isMouseOverMap){
        if (this.state.is(COPY_MARKER) && isMouseOverMap && mouseState == 0){
            aaam$doCopyMarker(this.hoveredMarker, this.player);
        }
        else {
            original.call(instance, state);
        }
    }

    @Inject(
            method = "handleKeyboardInput",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;getKeyCode()I", ordinal = 0)
    )
    public void aaam_antiqueAtlasGuiAtlas_handleKeyboardInputClicksButton(CallbackInfo ci, @Local(name = "key") int key) {
        if(KeyHandler.addButtonKey.getKeyCode() == key) ((GuiComponentButton_Invoker)this.btnMarker).invokeOnClick();
        else if(KeyHandler.deleteButtonKey.getKeyCode() == key) ((GuiComponentButton_Invoker)this.btnDelMarker).invokeOnClick();
        else if(KeyHandler.toggleButtonKey.getKeyCode() == key) ((GuiComponentButton_Invoker)this.btnShowMarkers).invokeOnClick();
        else if(KeyHandler.toggleFollowPlayer.getKeyCode() == key) ((GuiComponentButton_Invoker)this.btnPosition).invokeOnClick();
        else if(KeyHandler.copyMarkerButtonKey.getKeyCode() == key) ((GuiComponentButton_Invoker)this.aaam$btnCopyMarker).invokeOnClick();
    }

    @Inject(method = "handleKeyboardInput", at = @At("TAIL"))
    private void aaam_renameCopyBookmarkButton(CallbackInfo ci){
        ((GuiBookmarkButton_Invoker)this.aaam$btnCopyMarker).invokeSetTitle(I18n.format("gui.antiqueatlas.copymarker" + (GuiScreen.isShiftKeyDown() ? ".shiftdown" : "")));
    }

    @Unique
    private void aaam$doCopyMarker(Marker selectedMarker, EntityPlayer atlasPlayer){
        if(selectedMarker != null && !selectedMarker.isGlobal()){
            String labelForMessage = selectedMarker.getLabel().isEmpty()
                    ? I18n.format("gui.antiqueatlas.defaultlabel")
                    : I18n.format(selectedMarker.getLabel());
            StringBuilder command = new StringBuilder("/aaam putmarker");
            command.append(" ").append(selectedMarker.getX())
                    .append(" ").append(selectedMarker.getZ())
                    .append(" ").append(selectedMarker.getType());
            if(selectedMarker.getLabel().isEmpty()){
                command.append(" ").append("_");
            }
            else {
                command.append(" ").append(selectedMarker.getLabel());
            }

            if(!GuiScreen.isShiftKeyDown()){
                PacketHandler.instance.sendToServer(new PacketExportPutMarker(
                        atlasPlayer.getName(),
                        selectedMarker.getX(),
                        selectedMarker.getZ(),
                        selectedMarker.getType(),
                        selectedMarker.getLabel().isEmpty() ? "_" : selectedMarker.getLabel()
                ));
            } else {
                GuiScreen.setClipboardString(command.toString().replaceAll("§.", ""));
                atlasPlayer.sendMessage(new TextComponentTranslation("gui.antiqueatlas.copymarker.clipboard", labelForMessage));
            }
        }
        else {
            atlasPlayer.sendMessage(new TextComponentTranslation("gui.antiqueatlas.nomarker"));
        }
    }

}
