package antiqueatlasautomarker.overhaul;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class OtherPlayersData { // Does not need to save to disk, so not a WorldSavedData. Clientside only

    @Unique public final Map<UUID, double[]> playerPositions; // xPos, zPos, rotYaw

    public OtherPlayersData(Map<UUID, double[]> playerPositions) {
        this();
        this.playerPositions.putAll(playerPositions);
    }

    public OtherPlayersData() {
        this.playerPositions = new HashMap<>();
    }

    public void updateVisiblePlayer(EntityPlayer player) { //only used for current clientside player
        if (player.isEntityAlive()) {
            double[] playerData = this.playerPositions.computeIfAbsent(player.getUniqueID(), uuid -> new double[3]);
            playerData[0] = player.posX;
            playerData[1] = player.posZ;
            playerData[2] = player.rotationYaw;
        }
    }
}
