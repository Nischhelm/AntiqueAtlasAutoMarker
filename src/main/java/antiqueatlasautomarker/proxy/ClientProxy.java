package antiqueatlasautomarker.proxy;

import antiqueatlasautomarker.client.handlers.KeyHandler;
import antiqueatlasautomarker.client.handlers.LibrarianMarkerHandler;
import antiqueatlasautomarker.compat.DefiledLandsKeyHandler;
import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.config.ConfigHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        if(ConfigHandler.overhaul.addKeybinds) KeyHandler.initKeybind();
        if(ConfigHandler.automark.enchantments.enableLibrarianKey) LibrarianMarkerHandler.initKeybind();
        if(ConfigHandler.automark.defiledlands.enabled && ModCompat.defiledlands.isLoaded()) DefiledLandsKeyHandler.initKeybind();
    }
}