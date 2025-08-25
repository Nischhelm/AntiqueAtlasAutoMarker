package antiqueatlasautomarker.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyHandler {

    public static KeyBinding addButtonKey;
    public static KeyBinding deleteButtonKey;
    public static KeyBinding toggleButtonKey;

    public static void initKeybind() {
        addButtonKey = new KeyBinding(
                "gui.antiqueatlas.addMarker",
                Keyboard.KEY_A,
                "key.antiqueatlas.category"
        );
        deleteButtonKey = new KeyBinding(
                "gui.antiqueatlas.delMarker",
                Keyboard.KEY_D,
                "key.antiqueatlas.category"
        );
        toggleButtonKey = new KeyBinding(
                "gui.antiqueatlas.hideMarkers",
                Keyboard.KEY_W,
                "key.antiqueatlas.category"
        );
        ClientRegistry.registerKeyBinding(addButtonKey);
        ClientRegistry.registerKeyBinding(deleteButtonKey);
        ClientRegistry.registerKeyBinding(toggleButtonKey);
    }
}
