package antiqueatlasautomarker.proxy;

import antiqueatlasautomarker.client.handlers.KeyHandler;
import antiqueatlasautomarker.client.handlers.LibrarianMarkerHandler;
import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.handlers.DefiledLandsKeyHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void init() {
        if(ConfigHandler.tweaks.addKeybinds) KeyHandler.initKeybind();
        if(ConfigHandler.automark.enchantments.librarianKey.enabled) LibrarianMarkerHandler.initKeybind();
        if(ConfigHandler.automark.defiledlands.enabled && ModCompat.defiledlands.isLoaded()) DefiledLandsKeyHandler.initKeybind();
    }
}