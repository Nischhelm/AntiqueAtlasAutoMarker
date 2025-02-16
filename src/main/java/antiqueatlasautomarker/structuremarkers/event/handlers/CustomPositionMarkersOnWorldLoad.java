package antiqueatlasautomarker.structuremarkers.event.handlers;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.ForgeConfigHandler;
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
            for(String s : ForgeConfigHandler.customPositionMarkers){
                try {
                    //Read config line
                    String[] split = s.split(";");
                    int dim = Integer.parseInt(split[0].trim());
                    int x = Integer.parseInt(split[1].trim());
                    int z = Integer.parseInt(split[2].trim());
                    int xDisc = Integer.parseInt(split[3].trim());
                    int zDisc = Integer.parseInt(split[4].trim());
                    String label = split[5].trim();
                    String type = split[6].trim();

                    //Save it in Structure Markers list
                    StructureMarkersDataHandler.markCustomPositionStructure(event.getWorld(), dim, x, z, xDisc, zDisc, type, label);
                } catch (Exception e) {
                    AntiqueAtlasAutoMarker.LOGGER.warn("Could not parse Custom Position Marker, skipping {}", s);
                }
            }
        }
    }
}
