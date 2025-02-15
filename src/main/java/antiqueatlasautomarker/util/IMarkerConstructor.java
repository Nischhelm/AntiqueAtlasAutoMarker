package antiqueatlasautomarker.util;

import hunternif.mc.atlas.marker.Marker;

public interface IMarkerConstructor {
    Marker structureMarkerCopy(String clientSettingType, String clientSettingLabel);

    static String addContext(String type, String context){
        return context + ";" + type;
    }

    /**
     * @param type server side marker type (assumes pattern: context;serverMarkerType)
     * @return String array length 2, first entry context (empty if no context, failsafe), second entry serverside marker type
     */
    static String[] splitContext(String type) {
        String[] split = type.split(";");
        //Guarantees an array of length 2
        //Doesn't guarantee the failsafe to be a valid marker type
        return split.length==2 ? split : new String[]{"", type};
    }
}
