package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;

import javax.annotation.Nullable;
import java.util.*;

public class AutoMarkSetting {
    public final String label;
    public final boolean enabled;
    public final String type;
    public final String context;

    public AutoMarkSetting(boolean enabled, String label, String type, String context){
        this.enabled = enabled;
        this.label = label;
        this.type = type;
        this.context = context;
    }

    private static final Map<String, AutoMarkSetting> autoMarkSettings = new HashMap<>();
    @Nullable
    public static AutoMarkSetting get(String name){
        return autoMarkSettings.get(name);
    }
    public static void init(){
        List<String> bothConfigs = new ArrayList<>();
        bothConfigs.addAll(Arrays.asList(ForgeConfigHandler.structureMarkers));
        bothConfigs.addAll(Arrays.asList(ForgeConfigHandler.interactionMarkers));

        for(String s : bothConfigs){
            String[] split = s.split(";");
            if(split.length != 4) continue;
            try {
                autoMarkSettings.put(split[0].trim(),
                        new AutoMarkSetting(
                                Boolean.parseBoolean(split[1].trim()), //enabled
                                split[2].trim(), //label
                                split[3].trim(), //type
                                split[0].trim()  //context
                        )
                );
            } catch (Exception e){
                AntiqueAtlasAutoMarker.LOGGER.warn("Could not parse AAAM config line, skipping {}", s);
            }
        }
    }

    public static void reset(){
        autoMarkSettings.clear();
        init();
    }

}
