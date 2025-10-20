package antiqueatlasautomarker.compat.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.event.IEventHandle;
import crafttweaker.api.event.IEventManager;
import crafttweaker.util.EventList;
import crafttweaker.util.IEventHandler;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("unused")
@ZenExpansion("crafttweaker.events.IEventManager")
@ZenRegister
public class CT_BiomeDetectorExpansion {
    private static final EventList<CT_BiomeDetectorEvent> HANDLERS = new EventList<>();

    @ZenMethod
    public static IEventHandle onBiomeDetector(IEventManager manager, IEventHandler<CT_BiomeDetectorEvent> handler) {
        return HANDLERS.add(handler);
    }

    public static boolean hasBiomeDetectorHandlers() {
        return HANDLERS.hasHandlers();
    }

    public static void publishBiomeDetector(CT_BiomeDetectorEvent event) {
        HANDLERS.publish(event);
    }

    @ZenMethod
    public static void clearBiomeDetectorHandlers(IEventManager manager) {
        HANDLERS.clear();
    }
}
