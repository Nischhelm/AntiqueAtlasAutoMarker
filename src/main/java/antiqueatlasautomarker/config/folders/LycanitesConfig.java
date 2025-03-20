package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

public class LycanitesConfig {
    @Config.Comment("Set to false to never mark Lycanites Dungeons")
    @Config.Name("Enabled")
    public boolean enabled = true;

    @Config.Comment("Mark Lycanites Dungeons with this Marker Type")
    @Config.Name("Marker")
    public String marker = "antiqueatlas:dungeon";

    @Config.Comment("Mark Lycanites Dungeons with this Label. Use DEFAULT to mark the lyca dungeon with the default name of the dungeon, like \"Ashen Mausoleum\".")
    @Config.Name("Label")
    public String label = "DEFAULT";

    public LycanitesConfig(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("lycanite", enabled, label, marker);
    }
}
