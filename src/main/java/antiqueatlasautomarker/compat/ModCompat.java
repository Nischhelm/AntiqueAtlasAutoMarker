package antiqueatlasautomarker.compat;

import net.minecraftforge.fml.common.Loader;

public class ModCompat {
    private static Boolean isAtlasLoaded = null;
    private static Boolean isAARCLoaded = null;
    private static Boolean isOTGLoaded = null;

    public static boolean isAntiqueAtlasLoaded(){
        if(isAtlasLoaded == null) isAtlasLoaded = Loader.isModLoaded("antiqueatlas");
        return isAtlasLoaded;
    }

    public static boolean isAARCLoaded(){
        if(isAARCLoaded == null) isAARCLoaded = Loader.isModLoaded("aarcaddon");
        return isAARCLoaded;
    }

    public static boolean isOTGLoaded(){
        if(isOTGLoaded == null) isOTGLoaded = Loader.isModLoaded("openterraingenerator");
        return isOTGLoaded;
    }
}
