package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

public class DoomlikeConfig {
    @Config.Comment("Set to false to never mark Doomlike Dungeons")
    @Config.Name("Enabled")
    public boolean enabled = true;

    @Config.Comment("Mark Doomlike Dungeons with this Marker Type")
    @Config.Name("Marker")
    public String marker = "antiqueatlas:dungeon";

    @Config.Comment("Mark Doomlike Dungeons with this Label. Use DEFAULT to label the marker with the name of the dungeon's theme, like \"Urban Doomlike Dungeon\".")
    @Config.Name("Label")
    public String label = "DEFAULT";

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("doomlike", enabled, label, marker);
    }
}
