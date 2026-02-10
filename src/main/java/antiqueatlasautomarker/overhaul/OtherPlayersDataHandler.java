package antiqueatlasautomarker.overhaul;

import hunternif.mc.atlas.RegistrarAntiqueAtlas;
import hunternif.mc.atlas.api.AtlasAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OtherPlayersDataHandler {

    public static final OtherPlayersDataHandler INSTANCE = new OtherPlayersDataHandler();

    private final Map<Integer, OtherPlayersData> otherPlayersDataCache = new ConcurrentHashMap<>();

    /** Loads data for the given atlas ID or creates a new one. */
    public OtherPlayersData getData(ItemStack stack) {
        if (stack.getItem() == RegistrarAntiqueAtlas.ATLAS) {
            return getData(stack.getItemDamage());
        } else {
            return null;
        }
    }

    /** Loads data for the given atlas or creates a new one. */
    public OtherPlayersData getData(int atlasID) {
        this.otherPlayersDataCache.putIfAbsent(atlasID, new OtherPlayersData());
        return this.otherPlayersDataCache.get(atlasID);
    }

    public void setData(int atlasID, OtherPlayersData data){
        this.otherPlayersDataCache.put(atlasID, data);
    }

    /** Always used in a context where the queryPlayer has the provided atlasID */
    public boolean canSeeOtherPlayer(EntityPlayer queryPlayer, EntityPlayer otherPlayer, int atlasID){
        if(queryPlayer == otherPlayer) return false;
        if(!queryPlayer.isEntityAlive() || !otherPlayer.isEntityAlive()) return false;
        if(queryPlayer.dimension != otherPlayer.dimension) return false;
        if(!AtlasAPI.getPlayerAtlases(otherPlayer).contains(atlasID)) return false;

        return true;
    }

    @SubscribeEvent
    public void onClientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        this.otherPlayersDataCache.clear();
    }

    /** Clear players that logout on serverside */
    @SubscribeEvent
    public void onPlayerLogOut(PlayerEvent.PlayerLoggedOutEvent event){
        INSTANCE.otherPlayersDataCache.values()
                .forEach(otherPlayersData -> otherPlayersData
                .removePlayer(event.player.getUniqueID()));
    }

    /** Maps players to Atlases in inventory and check all known serverside Atlases. Based on net.minecraft.item.ItemMap#onUpdate() */
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.phase != TickEvent.Phase.END) return;
        if(event.side != Side.SERVER) return;
        if(event.player == null) return;

        Set<Integer> ownedAtlases = new HashSet<>();
        NonNullList<ItemStack> inventory = NonNullList.create();
        inventory.addAll(event.player.inventory.offHandInventory);
        inventory.addAll(event.player.inventory.mainInventory);
        inventory.stream().filter(itemStack -> itemStack.getItem() == RegistrarAntiqueAtlas.ATLAS)
                .forEach(itemStack -> {
                    INSTANCE.getData(itemStack).updateVisiblePlayer(event.player);
                    ownedAtlases.add(itemStack.getItemDamage());
                });
        INSTANCE.otherPlayersDataCache.keySet().stream()
                .filter(atlasID -> !ownedAtlases.contains(atlasID))
                .forEach(atlasID -> INSTANCE.getData(atlasID).removePlayer(event.player.getUniqueID()));
    }
}
