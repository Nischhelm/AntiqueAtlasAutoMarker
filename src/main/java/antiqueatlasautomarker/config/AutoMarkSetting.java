package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;

import java.util.HashMap;
import java.util.Map;

public class AutoMarkSetting {
    public final int dist;
    public final String label;
    public final boolean enabled;
    public final String type;

    public AutoMarkSetting(boolean enabled, int dist, String label, String type){
        this.enabled = enabled;
        this.dist = dist;
        this.label = label;
        this.type = type;
    }

    public AutoMarkSetting(boolean enabled, String label, String type){
        this.enabled = enabled;
        this.dist = 0; //clientside, doesn't need distance
        this.label = label;
        this.type = type;
    }

    private static final Map<String, AutoMarkSetting> autoMarkSettings = new HashMap<>();
    public static AutoMarkSetting get(String name){
        return autoMarkSettings.get(name);
    }
    public static void init(){
        for(String s : ForgeConfigHandler.server.structureMarkers){
            String[] split = s.split(";");
            if(split.length != 5) continue;
            try {
                autoMarkSettings.put(split[0].trim(),
                        new AutoMarkSetting(
                            Boolean.parseBoolean(split[1].trim()),
                            Integer.parseInt(split[2].trim()),
                            split[3].trim(),
                            split[4].trim()
                        )
                );
            } catch (Exception e){
                AntiqueAtlasAutoMarker.LOGGER.warn("Could not parse AAAM line for structure, skipping {}", s);
            }
        }
        for(String s : ForgeConfigHandler.client.interactionMarkers){
            String[] split = s.split(";");
            if(split.length != 4) continue;
            try {
                autoMarkSettings.put(split[0].trim(),
                        new AutoMarkSetting(
                                Boolean.parseBoolean(split[1].trim()),
                                split[2].trim(),
                                split[3].trim()
                        )
                );
            } catch (Exception e){
                AntiqueAtlasAutoMarker.LOGGER.warn("Could not parse AAAM line for interaction, skipping {}", s);
            }
        }
    }

    public static void reset(){
        autoMarkSettings.clear();
        init();
    }

}
