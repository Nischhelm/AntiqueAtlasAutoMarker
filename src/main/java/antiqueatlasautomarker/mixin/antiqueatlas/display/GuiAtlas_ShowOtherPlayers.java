package antiqueatlasautomarker.mixin.antiqueatlas.display;

import antiqueatlasautomarker.overhaul.OtherPlayersDataHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import hunternif.mc.atlas.client.Textures;
import hunternif.mc.atlas.client.gui.GuiAtlas;
import hunternif.mc.atlas.client.gui.core.GuiComponent;
import hunternif.mc.atlas.client.gui.core.GuiStates;
import hunternif.mc.atlas.util.AtlasRenderHelper;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mixin(GuiAtlas.class)
public abstract class GuiAtlas_ShowOtherPlayers extends GuiComponent {

    @Shadow(remap = false) private static final int MAP_WIDTH = GuiAtlas.WIDTH - 17*2;
    @Shadow(remap = false) private static final int MAP_HEIGHT = 194;
    @Shadow(remap = false) @Final private static float PLAYER_ROTATION_STEPS;
    @Shadow(remap = false) @Final private static int PLAYER_ICON_WIDTH;
    @Shadow(remap = false) @Final private static int PLAYER_ICON_HEIGHT;

    @Shadow(remap = false) private int mapOffsetX;
    @Shadow(remap = false) private int mapOffsetY;
    @Shadow(remap = false) private double mapScale;

    @Shadow(remap = false) private EntityPlayer player;
    @Shadow(remap = false) private ItemStack stack;

    @Shadow(remap = false) @Final private GuiStates state;
    @Shadow(remap = false) @Final private GuiStates.IState PLACING_MARKER;

    @Shadow(remap = false) protected abstract double getIconScale();

    @Unique
    private boolean aaam$isHidingMarkers = false;

    @ModifyExpressionValue(
            method = "drawScreen",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiStates;is(Lhunternif/mc/atlas/client/gui/core/GuiStates$IState;)Z", ordinal = 1, remap = false)
    )
    private boolean aaam_aaGuiAtlas_cancelOriginalDrawPlayer(boolean isHidingMarkers){
        this.aaam$isHidingMarkers = isHidingMarkers;
        return true;
    }

    @Inject(
            method = "drawScreen",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiComponent;drawScreen(IIF)V")
    )
    private void aaam_aaGuiAtlas_replaceDrawPlayer(int mouseX, int mouseY, float partialTick, CallbackInfo ci){
        if(!this.aaam$isHidingMarkers){
            boolean renderPlayerHead = Keyboard.isKeyDown(this.mc.gameSettings.keyBindPlayerList.getKeyCode());
            double iconScale = renderPlayerHead
                    ? this.getIconScale() * 2 // Scale when showing player head
                    : this.getIconScale(); // Scale when showing directional arrow

            int drawXPosMod = (int) (-PLAYER_ICON_WIDTH/2D*iconScale);
            int drawYPosMod = (int) (-PLAYER_ICON_HEIGHT/2D*iconScale);
            int drawWidth = (int) Math.round(PLAYER_ICON_WIDTH*iconScale);
            int drawHeight = (int) Math.round(PLAYER_ICON_HEIGHT*iconScale);
            this.aaam$filterNetworkPlayers().forEach(uuid -> {
                double[] position = this.player.getUniqueID().equals(uuid)
                        ? new double[] { this.player.posX, this.player.posZ, this.player.rotationYaw }
                        : OtherPlayersDataHandler.INSTANCE.getData(this.stack).getOtherPlayerPosition(uuid);
                // How much the player has moved from the top left corner of the map, in pixels:
                int playerOffsetX = (int)(position[0] * this.mapScale) + this.mapOffsetX;
                int playerOffsetZ = (int)(position[1] * mapScale) + this.mapOffsetY;
                if (playerOffsetX < -MAP_WIDTH/2) playerOffsetX = -MAP_WIDTH/2;
                if (playerOffsetX > MAP_WIDTH/2) playerOffsetX = MAP_WIDTH/2;
                if (playerOffsetZ < -MAP_HEIGHT/2) playerOffsetZ = -MAP_HEIGHT/2;
                if (playerOffsetZ > MAP_HEIGHT/2 - 2) playerOffsetZ = MAP_HEIGHT/2 - 2;
                // Draw the icon:
                GlStateManager.color(1, 1, 1, this.state.is(PLACING_MARKER) ? 0.5f : 1);

                int drawXPos = getGuiX() + GuiAtlas.WIDTH/2 + playerOffsetX;
                int drawYPos = getGuiY() + GuiAtlas.HEIGHT/2 + playerOffsetZ;

                GlStateManager.pushMatrix();
                if(renderPlayerHead){
                    NetworkPlayerInfo networkPlayerInfo = this.mc.player.connection.getPlayerInfo(uuid);
                    EntityPlayer entityPlayer = this.mc.world.getPlayerEntityByUUID(uuid);
                    boolean wearingHat = entityPlayer != null && entityPlayer.isWearing(EnumPlayerModelParts.HAT);
                    this.mc.getTextureManager().bindTexture(networkPlayerInfo.getLocationSkin());
                    Gui.drawScaledCustomSizeModalRect(
                            drawXPos + drawXPosMod,
                            drawYPos + drawYPosMod,
                            8.0F,
                            8,
                            8,
                            8,
                            drawWidth,
                            drawHeight,
                            64.0F,
                            64.0F
                    );
                    if (wearingHat) {
                        Gui.drawScaledCustomSizeModalRect(
                                drawXPos + drawXPosMod,
                                drawYPos + drawYPosMod,
                                40.0F,
                                8,
                                8,
                                8,
                                drawWidth,
                                drawHeight,
                                64.0F,
                                64.0F
                        );
                    }
                }
                else {
                    GlStateManager.translate(drawXPos, drawYPos, 0);
                    float playerRotation = (float) Math.round(position[2] / 360f * PLAYER_ROTATION_STEPS) / PLAYER_ROTATION_STEPS * 360f;
                    GlStateManager.rotate(180 + playerRotation, 0, 0, 1);
                    GlStateManager.translate(drawXPosMod, drawYPosMod, 0);
                    AtlasRenderHelper.drawFullTexture(Textures.PLAYER, 0, 0,
                            (int)Math.round(PLAYER_ICON_WIDTH*iconScale), (int)Math.round(PLAYER_ICON_HEIGHT*iconScale));
                }
                GlStateManager.popMatrix();
                if (aaam$isMouseInRegion(drawXPos + drawXPosMod, drawYPos + drawYPosMod, drawWidth, drawHeight)) {
                    GlStateManager.color(0.5f, 0.5f, 0.5f, 1);
                    if (isMouseOver) {
                        NetworkPlayerInfo networkPlayerInfo = this.mc.player.connection.getPlayerInfo(uuid);
                        String name = networkPlayerInfo.getDisplayName() != null ? networkPlayerInfo.getDisplayName().getFormattedText() : networkPlayerInfo.getGameProfile().getName();
                        drawTooltip(Collections.singletonList(name), mc.fontRenderer);
                    }
                }
            });
            GlStateManager.color(1, 1, 1, 1);
        }
    }

    /** Get UUIDs provided by Server and ensure they are on Client's network connections **/
    @Unique
    private Set<UUID> aaam$filterNetworkPlayers(){
        Set<UUID> stackPlayers = new HashSet<>(OtherPlayersDataHandler.INSTANCE.getData(this.stack).getOtherPlayers());
        if(this.mc.getConnection() != null){
            Set<UUID> networkPlayers = this.mc.getConnection().getPlayerInfoMap().stream().map(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getId()).collect(Collectors.toSet());
            stackPlayers.removeIf(uuid -> !networkPlayers.contains(uuid));
        }
        stackPlayers.add(this.player.getUniqueID());
        return stackPlayers;
    }

    /** @see GuiComponent#isMouseInRegion(int, int, int, int) **/
    @Unique
    private boolean aaam$isMouseInRegion(int left, int top, int width, int height) {
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        return mouseX >= left && mouseX < left + width && mouseY >= top && mouseY < top + height;
    }


}
