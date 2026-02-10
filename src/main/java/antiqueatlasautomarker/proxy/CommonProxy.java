package antiqueatlasautomarker.proxy;

import antiqueatlasautomarker.network.PacketHandler;

public class CommonProxy {

    public void preInit() {
        PacketHandler.registerMessages();
    }

    public void init() {
    }
}