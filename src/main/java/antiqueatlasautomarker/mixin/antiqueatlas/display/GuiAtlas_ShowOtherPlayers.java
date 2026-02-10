package antiqueatlasautomarker.mixin.antiqueatlas.display;

import antiqueatlasautomarker.overhaul.OtherPlayersData;
import antiqueatlasautomarker.overhaul.OtherPlayersDataHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
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

    @Unique
    private OtherPlayersData aaam$otherPlayersData;

    @Inject(
            method = "updateAtlasData",
            at = @At("TAIL"),
            remap = false
    )
    private void aaam_aaGuiAtlas_getReferenceToPlayerData(CallbackInfo ci, @Local(name = "atlasID") int atlasID){
        this.aaam$otherPlayersData = OtherPlayersDataHandler.INSTANCE.getData(atlasID);
        this.aaam$otherPlayersData.updateVisiblePlayer(this.player);
    }

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
            this.aaam$otherPlayersData = OtherPlayersDataHandler.INSTANCE.getData(this.stack.getItemDamage());
            this.aaam$otherPlayersData.updateVisiblePlayer(this.player);

            boolean renderPlayerHead = Keyboard.isKeyDown(this.mc.gameSettings.keyBindPlayerList.getKeyCode());
            double iconScale = renderPlayerHead
                    ? this.getIconScale() * 2 // Scale when showing player head
                    : this.getIconScale(); // Scale when showing directional arrow

            int drawXPosMod = (int) (-PLAYER_ICON_WIDTH/2D*iconScale);
            int drawYPosMod = (int) (-PLAYER_ICON_HEIGHT/2D*iconScale);
            int drawWidth = (int) Math.round(PLAYER_ICON_WIDTH*iconScale);
            int drawHeight = (int) Math.round(PLAYER_ICON_HEIGHT*iconScale);
            this.aaam$otherPlayersData.getPlayers().forEach(uuid ->  {
                double[] position = this.aaam$otherPlayersData.getPlayerPosition(uuid);
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
                if(renderPlayerHead && this.mc.player.connection.getPlayerInfo(uuid) != null){
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
                if (isMouseOver && aaam$isMouseInRegion(drawXPos + drawXPosMod, drawYPos + drawYPosMod, drawWidth, drawHeight)) {
                    NetworkPlayerInfo networkPlayerInfo = this.mc.player.connection.getPlayerInfo(uuid);
                    if(networkPlayerInfo != null) {
                        GlStateManager.color(0.5f, 0.5f, 0.5f, 1);
                        String name = networkPlayerInfo.getDisplayName() != null ? networkPlayerInfo.getDisplayName().getFormattedText() : networkPlayerInfo.getGameProfile().getName();
                        drawTooltip(Collections.singletonList(name), mc.fontRenderer);
                    }
                }
            });
            GlStateManager.color(1, 1, 1, 1);
        }
    }

    /** @see GuiComponent#isMouseInRegion(int, int, int, int) **/
    @Unique
    private boolean aaam$isMouseInRegion(int left, int top, int width, int height) {
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        return mouseX >= left && mouseX < left + width && mouseY >= top && mouseY < top + height;
    }


}
