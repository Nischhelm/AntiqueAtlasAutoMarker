package antiqueatlasautomarker.mixin.antiqueatlas.mapselection;

import antiqueatlasautomarker.util.MapWaystoneSelectionUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hunternif.mc.atlas.client.gui.GuiAtlas;
import hunternif.mc.atlas.client.gui.GuiBookmarkButton;
import hunternif.mc.atlas.marker.DimensionMarkersData;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import net.blay09.mods.waystones.PlayerWaystoneHelper;
import net.blay09.mods.waystones.WarpMode;
import net.blay09.mods.waystones.WaystoneConfig;
import net.blay09.mods.waystones.network.NetworkHandler;
import net.blay09.mods.waystones.network.message.MessageTeleportToWaystone;
import net.blay09.mods.waystones.util.WaystoneEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(GuiAtlas.class)
public abstract class GuiAtlasMixin {
    @Shadow(remap = false) private Marker hoveredMarker;
    @Shadow(remap = false) private EntityPlayer player;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnExportPng;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnDelMarker;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnShowMarkers;
    @Shadow(remap = false) @Final private GuiBookmarkButton btnMarker;
    @Unique private DimensionMarkersData aaam$waystoneMarkers = null;
    @Unique private Map<Marker,WaystoneEntry> aaam$markerToWaystoneMap = null;
    @Unique private MapWaystoneSelectionUtil.WarpProperty aaam$warpProperty = null;

    @WrapOperation(
            method = "updateAtlasData",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/marker/MarkersData;getMarkersDataInDimension(I)Lhunternif/mc/atlas/marker/DimensionMarkersData;"),
            remap = false
    )
    private DimensionMarkersData aaam_rerouteIfWaystone(MarkersData instance, int dimension, Operation<DimensionMarkersData> original){
        MapWaystoneSelectionUtil.WarpProperty warpProperty = MapWaystoneSelectionUtil.getAndClearThreadLocal();
        if(warpProperty == null && this.aaam$waystoneMarkers == null) return original.call(instance, dimension);

        if(this.aaam$waystoneMarkers == null) {
            this.aaam$waystoneMarkers = new DimensionMarkersData(instance, dimension);
            this.aaam$warpProperty = warpProperty;
            this.aaam$markerToWaystoneMap = new HashMap<>();
            this.btnExportPng.setEnabled(false);
            this.btnDelMarker.setEnabled(false);
            this.btnShowMarkers.setEnabled(false);
            this.btnMarker.setEnabled(false);
            MapWaystoneSelectionUtil.setFromWaystone(true);
            int id = 0;
            boolean costsXp = aaam$getCostsXp(warpProperty.mode);
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            int playerXpLvl = player.experienceLevel;
            for (WaystoneEntry entry : warpProperty.entries)
                if (entry.getDimensionId() == dimension) {
                    int xpCost = PlayerWaystoneHelper.getTravelCostByDistance(player, entry);
                    Marker marker = new Marker(id++, "antiqueatlas:waystone", aaam$getWaystoneMarkerName(entry, costsXp, xpCost, playerXpLvl), dimension, entry.getPos().getX(), entry.getPos().getZ(), true);
                    this.aaam$waystoneMarkers.insertMarker(marker);
                    if(xpCost <= playerXpLvl || !costsXp)
                        this.aaam$markerToWaystoneMap.put(marker,entry);
                }
        }
        return this.aaam$waystoneMarkers;
    }

    @Unique
    private static String aaam$getWaystoneMarkerName(WaystoneEntry entry, boolean costsXP, int xpCost, int playerXpLvl){
        String s = entry.getName();
        if(xpCost > 0 && costsXP) {
            s+=" (";
            if(xpCost > playerXpLvl) s += TextFormatting.RED;
            else s += TextFormatting.GREEN;
            s+= "" + xpCost + TextFormatting.RESET + ")";
        }
        return s;
    }

    @Unique
    private static boolean aaam$getCostsXp(WarpMode mode){
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        switch (mode) {
            case INVENTORY_BUTTON:
                if (WaystoneConfig.general.teleportButton && !WaystoneConfig.general.teleportButtonReturnOnly && WaystoneConfig.general.teleportButtonTarget.isEmpty()) {
                    return WaystoneConfig.general.inventoryButtonXpCost && !player.capabilities.isCreativeMode;
                } else return false;
            case WARP_SCROLL: return false;
            case WARP_STONE: return WaystoneConfig.general.warpStoneXpCost && !player.capabilities.isCreativeMode;
            case WAYSTONE: return WaystoneConfig.general.waystoneXpCost && !player.capabilities.isCreativeMode;
        }
        return true;
    }

    @Inject(
            method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiStates;is(Lhunternif/mc/atlas/client/gui/core/GuiStates$IState;)Z", ordinal = 0, remap = false),
            cancellable = true
    )
    private void aaam_teleportToMarker(int mouseX, int mouseY, int mouseState, CallbackInfo ci){
        if(MapWaystoneSelectionUtil.getIsFromWaystone()) {
            WaystoneEntry entry = aaam$markerToWaystoneMap.get(hoveredMarker);
            if (entry != null) {
                NetworkHandler.channel.sendToServer(new MessageTeleportToWaystone(entry, this.aaam$warpProperty.mode, this.aaam$warpProperty.hand, this.aaam$warpProperty.fromWaystone));
                player.closeScreen();
                ci.cancel();
            }
        }
    }

    @Inject(
            method = "onGuiClosed",
            at = @At("TAIL")
    )
    private void aaam_resetGUI(CallbackInfo ci){
        this.aaam$waystoneMarkers = null;
        this.aaam$markerToWaystoneMap = null;
        this.aaam$warpProperty = null;
        this.btnExportPng.setEnabled(true);
        this.btnDelMarker.setEnabled(true);
        this.btnShowMarkers.setEnabled(true);
        this.btnMarker.setEnabled(true);
        MapWaystoneSelectionUtil.setFromWaystone(false);
    }
}
