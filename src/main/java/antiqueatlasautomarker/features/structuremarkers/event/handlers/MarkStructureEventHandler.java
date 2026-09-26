package antiqueatlasautomarker.features.structuremarkers.event.handlers;

import antiqueatlasautomarker.features.structuremarkers.StructureMarkersDataHandler;
import antiqueatlasautomarker.features.structuremarkers.event.MarkStructureEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class MarkStructureEventHandler {
    @SubscribeEvent
    public static void onMarkStructure(MarkStructureEvent event){
        if(event.getWorld().isRemote) return;

        StructureMarkersDataHandler.markStructure(
                event.getWorld(),
                event.getX(),
                event.getZ(),
                event.getType(),
                event.getLabel(),
                event.getContext()
        );
    }
}
