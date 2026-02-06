package antiqueatlasautomarker.proxy;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.network.PacketHandler;

public class CommonProxy {

    public void preInit() {
        PacketHandler.registerMessages(AntiqueAtlasAutoMarker.MODID);
    }

    public void init() {
    }
}