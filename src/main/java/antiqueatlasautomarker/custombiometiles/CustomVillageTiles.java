package antiqueatlasautomarker.custombiometiles;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.ConfigHandler;
import hunternif.mc.atlas.client.TextureSetMap;
import hunternif.mc.atlas.ext.ExtTileIdMap;

import java.util.HashMap;
import java.util.Map;

public class CustomVillageTiles {
    public static final Map<String, Integer> tilePriority = new HashMap<>();
    public static final Map<String, String> partToTileMap = new HashMap<>();

    public static void registerVillageTiles() {
        for(String configLine : ConfigHandler.overhaul.tileConfig.customVillageTiles){
            String[] split = configLine.split(",");
            String component = split[0].trim();
            String textureSet = split[1].trim();
            try {
                int priority = Integer.parseInt(split[2].trim());
                if (ExtTileIdMap.instance().getPseudoBiomeID(textureSet) == ExtTileIdMap.NOT_FOUND){
                    AntiqueAtlasAutoMarker.LOGGER.warn("AAAM unable to register custom village tile, textureSet doesnt exist in {}", configLine);
                    continue;
                }

                partToTileMap.put(component, textureSet);
                tilePriority.put(textureSet, priority);
            } catch (Exception e){
                AntiqueAtlasAutoMarker.LOGGER.warn("AAAM unable to register custom village tile, unable to parse priority, expected integer in {}", configLine);
                e.printStackTrace(System.out);
            }
        }
    }
}
