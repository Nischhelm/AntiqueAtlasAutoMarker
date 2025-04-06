package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

public class Dungeons2Config {
    @Config.Comment("Set to false to never mark Dungeons2 Dungeons")
    @Config.Name("Enabled")
    public boolean enabled = true;

    @Config.Comment("Mark Dungeons2 Dungeons with this Marker Type")
    @Config.Name("Marker")
    public String marker = "antiqueatlas:dungeon";

    @Config.Comment("Mark Dungeons2 Dungeons with this Label. Use DEFAULT to label the marker with the localised name for Dungeons2 Dungeons.")
    @Config.Name("Label")
    public String label = "DEFAULT";

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("dungeons2", enabled, label, marker);
    }
}
