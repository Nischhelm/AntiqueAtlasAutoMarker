package antiqueatlasautomarker.network;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketHandler {

    public static SimpleNetworkWrapper instance = null;

    public static void registerMessages() {
        instance = NetworkRegistry.INSTANCE.newSimpleChannel(AntiqueAtlasAutoMarker.NETWORK_CHANNEL_NAME);
        instance.registerMessage(PacketExportPutMarker.ServerHandler.class, PacketExportPutMarker.class, 1, Side.SERVER);
        instance.registerMessage(PacketOtherAtlasHolders.ServerHandler.class, PacketOtherAtlasHolders.class, 2, Side.SERVER);
    }

    @SideOnly(Side.CLIENT)
    public static void registerClientMessages() {
        instance.registerMessage(PacketExportPutMarker.ClientHandler.class, PacketExportPutMarker.class, 1, Side.CLIENT);
        instance.registerMessage(PacketOtherAtlasHolders.ClientHandler.class, PacketOtherAtlasHolders.class, 2, Side.CLIENT);
    }
}
