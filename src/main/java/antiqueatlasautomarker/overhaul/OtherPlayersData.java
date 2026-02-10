package antiqueatlasautomarker.overhaul;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class OtherPlayersData { // Does not need to save to disk, so not a WorldSavedData

    @Unique
    private final Map<UUID, double[]> playerPositions; // xPos, zPos, rotYaw

    public OtherPlayersData(Map<UUID, double[]> playerPositions) {
        this();
        this.playerPositions.putAll(playerPositions);
    }

    public OtherPlayersData() {
        this.playerPositions = new HashMap<>();
    }

    public void removePlayer(UUID uuid){
        this.playerPositions.remove(uuid);
    }

    public Set<UUID> getOtherPlayers() {
        return this.playerPositions.keySet();
    }

    public double[] getOtherPlayerPosition(UUID uuid) {
        return this.playerPositions.getOrDefault(uuid, new double[] { 0, 0, 0 });
    }

    public void updateVisiblePlayer(EntityPlayer player) {
        if (player.isEntityAlive()) {
            this.playerPositions.put(player.getUniqueID(), new double[] { player.posX, player.posZ, player.rotationYaw });
        }
    }
}
