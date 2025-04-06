package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

public class BattletowersConfig {
    @Config.Comment("Set to false to never mark Battletowers")
    @Config.Name("Enabled")
    public boolean enabled = true;

    @Config.Comment("Mark Battletowers with this Marker Type")
    @Config.Name("Marker")
    public String marker = "antiqueatlas:wizardtower";

    @Config.Comment("Mark Battletowers with this Label. Use DEFAULT to label the marker with the battle tower type name and adding \"Reverse\" if it is upside down.")
    @Config.Name("Label")
    public String label = "DEFAULT";

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("battleTower", enabled, label, marker);
    }
}
