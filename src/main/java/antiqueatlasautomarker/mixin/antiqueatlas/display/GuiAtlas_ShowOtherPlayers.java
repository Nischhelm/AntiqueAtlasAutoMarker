package antiqueatlasautomarker.mixin.antiqueatlas.display;

import antiqueatlasautomarker.overhaul.OtherPlayersData;
import antiqueatlasautomarker.overhaul.OtherPlayersDataHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import hunternif.mc.atlas.SettingsConfig;
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

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Mixin(GuiAtlas.class)
public abstract class GuiAtlas_ShowOtherPlayers extends GuiComponent {

    @Shadow(remap = false) @Final private static int MAP_WIDTH;
    @Shadow(remap = false) @Final private static int MAP_HEIGHT;
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

    @ModifyExpressionValue(
            method = "drawScreen",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/client/gui/core/GuiStates;is(Lhunternif/mc/atlas/client/gui/core/GuiStates$IState;)Z", ordinal = 1, remap = false)
    )
    private boolean aaam_replaceDrawPlayer(boolean isHidingMarkers){
        if(!SettingsConfig.gameplay.itemNeeded) return isHidingMarkers; //everyone got their own atlas -> no shared atlas
        if(isHidingMarkers) return true;

        OtherPlayersData data = OtherPlayersDataHandler.getData(this.stack.getItemDamage());
        data.updateVisiblePlayer(this.player);

        boolean renderPlayerHead = Keyboard.isKeyDown(this.mc.gameSettings.keyBindPlayerList.getKeyCode());
        double iconScale = renderPlayerHead
                ? this.getIconScale() * 2 // Scale when showing player head
                : this.getIconScale(); // Scale when showing directional arrow

        int drawXPosMod = (int) (-PLAYER_ICON_WIDTH/2D*iconScale);
        int drawYPosMod = (int) (-PLAYER_ICON_HEIGHT/2D*iconScale);
        int drawWidth = (int) Math.round(PLAYER_ICON_WIDTH*iconScale);
        int drawHeight = (int) Math.round(PLAYER_ICON_HEIGHT*iconScale);

        for(Map.Entry<UUID, double[]> entries : data.playerPositions.entrySet()) {
            UUID uuid = entries.getKey();
            double[] position = entries.getValue();

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

            NetworkPlayerInfo networkPlayerInfo = this.mc.player.connection.getPlayerInfo(uuid);

            if(renderPlayerHead && networkPlayerInfo != null)
                aaam$drawPlayerHead(uuid, networkPlayerInfo, drawXPos + drawXPosMod, drawYPos + drawYPosMod, drawWidth, drawHeight);
            else {
                //Draw player arrow
                GlStateManager.translate(drawXPos, drawYPos, 0);
                float playerRotation = (float) Math.round(position[2] / 360f * PLAYER_ROTATION_STEPS) / PLAYER_ROTATION_STEPS * 360f;
                GlStateManager.rotate(180 + playerRotation, 0, 0, 1);
                GlStateManager.translate(drawXPosMod, drawYPosMod, 0);
                AtlasRenderHelper.drawFullTexture(Textures.PLAYER, 0, 0,
                        (int)Math.round(PLAYER_ICON_WIDTH*iconScale), (int)Math.round(PLAYER_ICON_HEIGHT*iconScale));
            }

            GlStateManager.popMatrix();

            //Draw player name
            if (isMouseOver && ((GuiComponentAccessor) this).invokeIsMouseInRegion(drawXPos + drawXPosMod, drawYPos + drawYPosMod, drawWidth, drawHeight) && networkPlayerInfo != null) {
                GlStateManager.color(0.5f, 0.5f, 0.5f, 1);
                String name = networkPlayerInfo.getDisplayName() != null ? networkPlayerInfo.getDisplayName().getFormattedText() : networkPlayerInfo.getGameProfile().getName();
                drawTooltip(Collections.singletonList(name), mc.fontRenderer);
            }

        }
        GlStateManager.color(1, 1, 1, 1);

        return true; //don't draw original
    }

    @Unique
    private void aaam$drawPlayerHead(UUID uuid, NetworkPlayerInfo info, int x, int y, int width, int height) {
        EntityPlayer entityPlayer = this.mc.world.getPlayerEntityByUUID(uuid);
        boolean wearingHat = entityPlayer != null && entityPlayer.isWearing(EnumPlayerModelParts.HAT);
        this.mc.getTextureManager().bindTexture(info.getLocationSkin());
        Gui.drawScaledCustomSizeModalRect(
                x, y,
                8, 8,
                8, 8,
                width, height,
                64, 64
        );
        if (wearingHat) {
            Gui.drawScaledCustomSizeModalRect(
                    x, y,
                    40, 8,
                    8, 8,
                    width, height,
                    64, 64
            );
        }
    }
}
