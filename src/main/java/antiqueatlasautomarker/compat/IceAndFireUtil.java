package antiqueatlasautomarker.compat;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.util.Arrays;

public class IceAndFireUtil {
    public enum IceAndFireVersion {
        BASE_OLD,
        BASE_1_9_1,
        RLCRAFT,
        ROTN,
        OTHER,
        NONE
    }

    public static IceAndFireVersion getIceAndFireVersion(){
        ModContainer infMod = Loader.instance().getIndexedModList().get("iceandfire");
        if(infMod == null) return IceAndFireVersion.NONE;
        String infVersion = infMod.getVersion();
        String infName = infMod.getName();

        if(infName.equals("Ice and Fire: RotN Edition")) { //rotn inf: versioning 1.9.1-x.x.x, but don't need to use it bc RotN renamed it
            String[] vers = infVersion.replace("1.9.1-", "").split("\\.");
            try {
                int major = Integer.parseInt(vers[0]);
                int minor = Integer.parseInt(vers[1]);
                int patch = Integer.parseInt(vers[2]);
                if (major > 1) return IceAndFireVersion.ROTN;
                if (major == 1 && minor > 3) return IceAndFireVersion.ROTN;
                if (major == 1 && minor == 3 && patch > 0) return IceAndFireVersion.ROTN;
                return IceAndFireVersion.BASE_1_9_1; //RotN didn't use HomePosition class until 1.3.1
            } catch (Exception e) {
                AntiqueAtlasAutoMarker.LOGGER.error("Unable to parse Ice And Fire RotN version number: {}", infVersion, e);
                return IceAndFireVersion.NONE;
            }
        }
        if(infVersion.matches("^1\\.\\d+\\.\\d+$")) //base inf: 1.x.x
            if(infVersion.equals("1.9.1")) return IceAndFireVersion.BASE_1_9_1;
            else return IceAndFireVersion.BASE_OLD;
        if(infVersion.matches("^2\\.\\d+\\.\\d+$")) //rlcraft inf: 2.x.x
            return IceAndFireVersion.RLCRAFT;

        return IceAndFireVersion.OTHER;
    }
}
