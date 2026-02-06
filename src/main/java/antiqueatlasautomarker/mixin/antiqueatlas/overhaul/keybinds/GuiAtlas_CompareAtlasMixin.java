package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.keybinds;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.RegistrarAntiqueAtlas;
import hunternif.mc.atlas.SettingsConfig;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.client.Textures;
import hunternif.mc.atlas.client.gui.GuiAtlas;
import hunternif.mc.atlas.client.gui.GuiBookmarkButton;
import hunternif.mc.atlas.client.gui.core.GuiComponent;
import hunternif.mc.atlas.client.gui.core.GuiComponentButton;
import hunternif.mc.atlas.client.gui.core.GuiCursor;
import hunternif.mc.atlas.client.gui.core.GuiStates;
import hunternif.mc.atlas.marker.DimensionMarkersData;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mixin(GuiAtlas.class)
public abstract class GuiAtlas_CompareAtlasMixin extends GuiComponent {

    @Shadow(remap = false) private ItemStack stack;
    @Shadow(remap = false) private GuiComponentButton selectedButton;

    @Shadow(remap = false) @Final private GuiStates state;
    @Shadow(remap = false) @Final private GuiStates.IState NORMAL;

    @Shadow(remap = false) private Marker hoveredMarker;
    @Shadow(remap = false) private EntityPlayer player;

    @Shadow(remap = false) protected abstract int getAtlasID();

    @Unique private ItemStack aaam$otherStack;
    @Unique private final Set<Marker> aaam$uniqueMarkers = new HashSet<>();
    @Unique private final GuiCursor aaam$compareMarkerCursor = new GuiCursor();
    @Unique private final GuiBookmarkButton aaam$btnCompareMarker = GuiBookmarkButton_Invoker.invokeInit(1, Textures.MARKER_SCROLL, I18n.format("gui.antiqueatlas.compareatlas"));
    @Unique private final GuiStates.IState COMPARE_ATLAS_MARKERS = new GuiStates.IState() {
        @Override
        public void onEnterState() {
            mc.mouseHelper.grabMouseCursor();
            addChild(aaam$compareMarkerCursor);
            aaam$btnCompareMarker.setSelected(true);
            aaam$setUniqueMarkers();
            if(aaam$uniqueMarkers.isEmpty())
                mc.player.sendMessage(new TextComponentTranslation("gui.antiqueatlas.compareatlas.nouniquemarkers"));
        }
        @Override
        public void onExitState() {
            mc.mouseHelper.ungrabMouseCursor();
            removeChild(aaam$compareMarkerCursor);
            aaam$btnCompareMarker.setSelected(false);
            aaam$uniqueMarkers.clear();
        }
    };

    @Inject(
            method = "<init>",
            at = @At("TAIL"),
            remap = false
    )
    public void aaam_antiqueAtlasGuiAtlas_initCompareAtlasButton(CallbackInfo ci) {
        this.addChild(aaam$btnCompareMarker).offsetGuiCoords(300, 94);
        this.aaam$btnCompareMarker.addListener(button -> {
            if (this.stack != null && SettingsConfig.gameplay.itemNeeded) { //TODO: button should prob not even exist if not itemneeded?
                if (this.state.is(COMPARE_ATLAS_MARKERS)) {
                    this.selectedButton = null;
                    this.state.switchTo(NORMAL);
                } else {
                    if(this.aaam$otherStack == null || this.stack.getItemDamage() == this.aaam$otherStack.getItemDamage()){
                        this.mc.player.sendMessage(new TextComponentTranslation("gui.antiqueatlas.compareatlas.nooffhandatlas"));
                        ((GuiBookmarkButton) button).setSelected(false);
                    } else {
                        this.selectedButton = button;
                        this.state.switchTo(COMPARE_ATLAS_MARKERS);
                    }
                }
            }
        });
        this.aaam$compareMarkerCursor.setTexture(Textures.BTN_POSITION, 12, 14, 2, 11);
    }

    @Inject(
            method = "prepareToOpen()Lhunternif/mc/atlas/client/gui/GuiAtlas;",
            at = @At("RETURN"),
            remap = false
    )
    private void aaam_antiqueAtlasGuiAtlas_prepareToOpenCompareAtlasButton(CallbackInfoReturnable<GuiAtlas> cir){
        this.aaam$uniqueMarkers.clear();
        if(this.player.getHeldItemOffhand().getItem() == RegistrarAntiqueAtlas.ATLAS)
            this.aaam$otherStack = this.player.getHeldItemOffhand();
        else
            this.aaam$otherStack = null;
    }

    @WrapWithCondition(
            method = "drawScreen",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/GuiAtlas;renderMarker(Lhunternif/mc/atlas/marker/Marker;D)V", remap = false)
    )
    private boolean aaam_antiqueAtlasGuiAtlas_drawScreenCompareAtlasMarkers(GuiAtlas instance, Marker marker, double scale, @Local(name = "x") int x, @Local(name = "z") int z) {
        if(this.state.is(COMPARE_ATLAS_MARKERS))
            return this.aaam$uniqueMarkers.contains(marker);
        return true;
    }

    @WrapOperation(
            method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiStates;switchTo(Lhunternif/mc/atlas/client/gui/core/GuiStates$IState;)V", remap = false)
    )
    private void aaam_antiqueAtlasGuiAtlas_mouseClickedCopyOverMarker(GuiStates instance, GuiStates.IState state, Operation<Void> original, @Local(name = "mouseState") int mouseState, @Local(name = "isMouseOverMap") boolean isMouseOverMap){
        if (this.state.is(COMPARE_ATLAS_MARKERS) && mouseState == 0){
            if(this.hoveredMarker != null && !this.hoveredMarker.isGlobal() && isMouseOverMap) { // If clicked on a marker, export it:
                if (this.aaam$uniqueMarkers.contains(this.hoveredMarker)) {
                    if (aaam$otherStack != null) {
                        String markerName = this.hoveredMarker.getLabel().isEmpty() ? this.hoveredMarker.getType() : this.hoveredMarker.getLocalizedLabel();
                        this.player.sendMessage(new TextComponentTranslation("gui.antiqueatlas.compareatlas.copy", markerName, aaam$otherStack.getItemDamage()));
                        AtlasAPI.getMarkerAPI().putMarker(this.player.world, true, aaam$otherStack.getItemDamage(), this.hoveredMarker.getType(), this.hoveredMarker.getLabel(), this.hoveredMarker.getX(), this.hoveredMarker.getZ());
                        this.aaam$uniqueMarkers.remove(this.hoveredMarker);

                        if(aaam$uniqueMarkers.isEmpty())
                            mc.player.sendMessage(new TextComponentTranslation("gui.antiqueatlas.compareatlas.nouniquemarkers"));
                    }
                }
            }
        }
        else {
            original.call(instance, state);
        }
    }

    @Unique
    private void aaam$setUniqueMarkers() {
        if (this.stack == null) return;
        if (this.aaam$otherStack == null) return;
        if (this.stack.getItemDamage() == this.aaam$otherStack.getItemDamage()) return;

        MarkersData markersData = AntiqueAtlasMod.markersData.getMarkersData(this.aaam$otherStack.getItemDamage(), this.player.world);
        if (markersData == null) return; //shouldn't happen
        DimensionMarkersData offhandMarkerData = markersData.getMarkersDataInDimension(this.player.dimension);

        markersData = AntiqueAtlasMod.markersData.getMarkersData(getAtlasID(), this.player.world);
        if (markersData == null) return;
        DimensionMarkersData mainhandMarkerData = markersData.getMarkersDataInDimension(this.player.dimension);

        //unique (mainhand) markers are markers in the mainhand atlas where there isn't any similar marker in the offhand atlas
        this.aaam$uniqueMarkers.addAll(mainhandMarkerData.getAllMarkers());
        this.aaam$uniqueMarkers.removeIf(mainmarker -> {
            List<Marker> offhandMarkersAtChunk = offhandMarkerData.getMarkersAtChunk(mainmarker.getChunkX() / MarkersData.CHUNK_STEP, mainmarker.getChunkZ() / MarkersData.CHUNK_STEP);
            return offhandMarkersAtChunk != null && //this is O(n²) even though its a hashset rip
                    offhandMarkersAtChunk.stream().anyMatch(offmarker -> aaam$markersAreSimilar(mainmarker, offmarker));
        });
    }

    @Unique
    private static boolean aaam$markersAreSimilar(Marker marker1, Marker marker2) {
        if (Math.abs(marker1.getX() - marker2.getX()) > 5 || Math.abs(marker1.getZ() - marker2.getZ()) > 5) return false;
        if (!marker1.getType().equals(marker2.getType())) return false;
        return marker1.getLabel().equals(marker2.getLabel());
    }
}