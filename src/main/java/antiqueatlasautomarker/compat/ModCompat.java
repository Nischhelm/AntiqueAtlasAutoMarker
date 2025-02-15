package antiqueatlasautomarker.compat;

import net.minecraftforge.fml.common.Loader;

public class ModCompat {
    private static Boolean isAARCLoaded = null;

    public static boolean isAARCLoaded(){
        if(isAARCLoaded == null)
            isAARCLoaded = Loader.isModLoaded("aarcaddon");
        return isAARCLoaded;
    }
}
