package antiqueatlasautomarker.structuremarkers.event.handlers;

import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CustomPositionMarkersOnWorldLoad {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void markCustomPositionsOnWorldLoad(WorldEvent.Load event) {
        if (!event.getWorld().isRemote && event.getWorld().provider.getDimension() == 0) {
            //Save them in Structure Markers list
            ConfigHandler.automark.customPosition.customPositionMarkers.forEach(data -> StructureMarkersDataHandler.markCustomPositionStructure(event.getWorld(), data));
        }
    }
}
