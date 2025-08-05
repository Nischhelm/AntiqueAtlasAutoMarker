package antiqueatlasautomarker.mixin.antiqueatlas.display;

import antiqueatlasautomarker.client.GuiHideMarkerTypeSelect;
import antiqueatlasautomarker.util.MapWaystoneSelectionUtil;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hunternif.mc.atlas.SettingsConfig;
import hunternif.mc.atlas.client.gui.GuiAtlas;
import hunternif.mc.atlas.client.gui.GuiBookmarkButton;
import hunternif.mc.atlas.client.gui.core.GuiComponent;
import hunternif.mc.atlas.client.gui.core.GuiComponentButton;
import hunternif.mc.atlas.client.gui.core.GuiStates;
import hunternif.mc.atlas.client.gui.core.IButtonListener;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.registry.MarkerRegistry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(GuiAtlas.class)
public abstract class DisplayDisableAtlas extends GuiComponent {
    @Shadow(remap = false) private ItemStack stack;
    @Shadow(remap = false) private GuiComponentButton selectedButton;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnShowMarkers;
    @Shadow(remap = false) @Final private GuiStates state;
    @Shadow(remap = false) @Final private GuiStates.IState HIDING_MARKERS;
    @Shadow(remap = false) @Final private GuiStates.IState NORMAL;

    @Unique private static List<String> aaam$allMarkerNames = null;

    @Unique private final GuiStates.IState DISABLING_MARKERS = new GuiStates.SimpleState() {
        @Override public void onEnterState() {
            btnShowMarkers.setSelected(false); //don't ask me why
            addChild(aaam$markerTypeHider);
        }
        @Override public void onExitState() {
            btnShowMarkers.setSelected(false); //don't ask me why
            aaam$markerTypeHider.close();
        }
    };

    @Unique private static final List<String> aaam$disabledMarkers = new ArrayList<>();
    @Unique private final GuiHideMarkerTypeSelect aaam$markerTypeHider = new GuiHideMarkerTypeSelect(aaam$disabledMarkers);

    @WrapWithCondition(
            method = "drawScreen",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/GuiAtlas;renderMarker(Lhunternif/mc/atlas/marker/Marker;D)V", remap = false)
    )
    private boolean aaam_dontShowDisabledMarkers(GuiAtlas instance, Marker marker, double scale){
        return marker != null && (MapWaystoneSelectionUtil.getIsFromWaystone() || !aaam$disabledMarkers.contains(marker.getType()));
    }
//
    @WrapOperation(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/GuiBookmarkButton;addListener(Lhunternif/mc/atlas/client/gui/core/IButtonListener;)V", ordinal = 3),
            remap = false
    )
    private void aaam_changeHideBehavior(GuiBookmarkButton instance, IButtonListener<GuiBookmarkButton> iButtonListener, Operation<Void> original){
        original.call(instance, (IButtonListener<GuiBookmarkButton>) guiBookmarkButton -> {
            if (stack == null && SettingsConfig.gameplay.itemNeeded) return;
            selectedButton = null;
            if (GuiScreen.isShiftKeyDown())
                state.switchTo(state.is(HIDING_MARKERS) ? NORMAL : HIDING_MARKERS);
            else
                state.switchTo(state.is(DISABLING_MARKERS) ? NORMAL : DISABLING_MARKERS);
        });

        this.aaam$markerTypeHider.setListener((markerType, gotEnabled) -> aaam$disableButtonBehavior(markerType.getRegistryName().toString(), gotEnabled));
    }

    @Unique private void aaam$disableButtonBehavior(String markerTypeName, boolean gotEnabled){
        if(GuiScreen.isShiftKeyDown()) {
            aaam$disabledMarkers.clear();
            if (gotEnabled){
                if(aaam$allMarkerNames == null)
                    aaam$allMarkerNames = MarkerRegistry.getValues().stream().map(m -> m.getRegistryName().toString()).collect(Collectors.toList());
                aaam$disabledMarkers.addAll(aaam$allMarkerNames);
                aaam$disabledMarkers.remove(markerTypeName);
            }
            else
                aaam$disabledMarkers.add(markerTypeName);
        } else {
            if (gotEnabled) aaam$disabledMarkers.remove(markerTypeName);
            else aaam$disabledMarkers.add(markerTypeName);
        }
    }

    @WrapOperation(
            method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiStates;is(Lhunternif/mc/atlas/client/gui/core/GuiStates$IState;)Z", ordinal = 2, remap = false)
    )
    private boolean aaam_alsoDontReturntoNORMALIfDisabling(GuiStates state, GuiStates.IState hidingMarkersState, Operation<Boolean> original){
        return original.call(state, hidingMarkersState) || state.is(DISABLING_MARKERS);
    }
}
