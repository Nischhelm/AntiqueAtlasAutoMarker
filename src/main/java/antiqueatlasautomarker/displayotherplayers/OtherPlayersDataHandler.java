package antiqueatlasautomarker.displayotherplayers;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.HashMap;
import java.util.Map;

public class OtherPlayersDataHandler {
    private static final Map<Integer, OtherPlayersData> otherPlayersDataCache = new HashMap<>();

    /** Loads data for the given atlas or creates a new one. */
    public static OtherPlayersData getData(int atlasID) {
        return otherPlayersDataCache.computeIfAbsent(atlasID, i -> new OtherPlayersData());
    }

    public static void setData(int atlasID, OtherPlayersData data){
        otherPlayersDataCache.put(atlasID, data);
    }

    @SubscribeEvent
    public static void onClientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        otherPlayersDataCache.clear();
    }
}
