package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

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
        List<String> ruinsConfigs = Arrays.stream(ConfigHandler.ruins.ruinsMarkers).map(s -> "ruins_" + s).collect(Collectors.toList()); //adding "ruins_" in front of each context

        for(String s : ruinsConfigs){
            String[] split = s.split(";");
            if(split.length != 4) continue;
            try {
                registerAutoMarkSetting(
                        split[0].trim(), //context
                        Boolean.parseBoolean(split[1].trim()), //enabled
                        split[2].trim(), //label
                        split[3].trim() //type
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

    /**
     * Mods can use this function to register their own structure markers
     * The structures can be marked using the MarkStructureEvent.setContext
     * This can also be done without registering a new context here, but will force clients to use the marker settings the event uses
     */
    public static void registerAutoMarkSetting(String context, boolean enabled, String label, String type){
        autoMarkSettings.put(context,
                new AutoMarkSetting(
                        enabled,
                        label,
                        type,
                        context
                )
        );
    }

}
