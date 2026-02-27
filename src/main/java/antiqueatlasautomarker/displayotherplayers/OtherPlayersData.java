package antiqueatlasautomarker.displayotherplayers;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OtherPlayersData { // Does not need to save to disk, so not a WorldSavedData. Clientside only

    @Unique public final Map<UUID, Double[]> playerPositions; // xPos, zPos, rotYaw

    public OtherPlayersData(Map<UUID, Double[]> playerPositions) {
        this();
        this.playerPositions.putAll(playerPositions);
    }

    public OtherPlayersData() {
        this.playerPositions = new HashMap<>();
    }

    public void updateVisiblePlayer(EntityPlayer player) { //only used for current clientside player
        if (player.isEntityAlive()) {
            Double[] playerData = this.playerPositions.computeIfAbsent(player.getUniqueID(), uuid -> new Double[3]);
            playerData[0] = player.posX;
            playerData[1] = player.posZ;
            playerData[2] = (double) player.rotationYaw;
        }
    }
}
