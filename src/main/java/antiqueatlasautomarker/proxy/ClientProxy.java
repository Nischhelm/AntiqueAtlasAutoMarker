package antiqueatlasautomarker.proxy;

import antiqueatlasautomarker.client.handlers.KeyHandler;
import antiqueatlasautomarker.client.handlers.LibrarianMarkerHandler;
import antiqueatlasautomarker.compat.DefiledLandsKeyHandler;
import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.network.PacketHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
        PacketHandler.registerClientMessages();
    }

    @Override
    public void init() {
        if(ConfigHandler.overhaul.addKeybinds) KeyHandler.initKeybind();
        if(ConfigHandler.enchantments.enableLibrarianKey) LibrarianMarkerHandler.initKeybind();
        if(ConfigHandler.defiledlands.enabled && ModCompat.defiledlands.isLoaded()) DefiledLandsKeyHandler.initKeybind();
    }
}