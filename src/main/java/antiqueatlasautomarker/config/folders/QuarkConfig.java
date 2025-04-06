package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

public class QuarkConfig {
    @Config.Comment("Set to false to never mark Pirateships")
    @Config.Name("Pirateship - Enabled")
    public boolean enabled_pirateship = true;

    @Config.Comment("Mark Pirateships with this Marker Type")
    @Config.Name("Pirateship - Marker")
    public String marker_pirateship = "antiqueatlas:skull";

    @Config.Comment("Mark Pirateships with this Label. Use DEFAULT to label the marker with the localised name for Pirateships.")
    @Config.Name("Pirateship - Label")
    public String label_pirateship = "DEFAULT";

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("quarkPirateShip", enabled_pirateship, label_pirateship, marker_pirateship);
    }
}
