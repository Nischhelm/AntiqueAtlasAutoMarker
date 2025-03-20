package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

public class BetterMineshaftConfig {
    @Config.Comment("Set to false to never mark Better Mineshafts")
    @Config.Name("Enabled")
    public boolean enabled = false;

    @Config.Comment("Mark Better Mineshafts with this Marker Type")
    @Config.Name("Marker")
    public String marker = "antiqueatlas:tracks";

    @Config.Comment("Mark Better Mineshafts with this Label. Use DEFAULT to mark the mineshaft with the lang key defined in lang file or lang key config.")
    @Config.Name("Label")
    public String label = "DEFAULT";

    public BetterMineshaftConfig(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("betterMineshaft", enabled, label, marker);
    }
}
