package antiqueatlasautomarker.compat;

import aarcaddon.handlers.ConfigHandler;
import antiqueatlasautomarker.config.AutoMarkSetting;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class AARCCompat {
    private static final Map<String, AutoMarkSetting> cachedAarcSettings = new HashMap<>();

    @Nullable
    private static AutoMarkSetting getCachedSetting(String structureName){
        if(!cachedAarcSettings.containsKey(structureName)){
            String[] aarcSetting = ConfigHandler.structureDeclarationList.get(structureName);
            String type = aarcSetting[1];
            if(!type.contains(":")) type = "antiqueatlas:" + type;
            cachedAarcSettings.put(structureName, new AutoMarkSetting(true, aarcSetting[2],type,""));
        }
        return cachedAarcSettings.get(structureName);
    }

    @Nullable
    public static AutoMarkSetting getAARCSetting(String context){
        String[] context_structureName = context.split("\\$");
        if(context_structureName.length == 2) {
            //context = context_structureName[0]; //--> not used after this point
            String structureName = context_structureName[1];

            AutoMarkSetting clientSetting = getCachedSetting(structureName);

            //Clientside setting found, use that one
            if(clientSetting != null) return clientSetting;
            //use server settings if client doesn't have a setting
            else return new AutoMarkSetting(true, "DEFAULT", "DEFAULT", "AARCAddon");
        } else
            //don't mark anything if message context doesn't have correct form AARCAddon$structureName
            return null;
    }
}
