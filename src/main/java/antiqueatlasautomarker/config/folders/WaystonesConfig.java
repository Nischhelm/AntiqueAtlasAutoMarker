package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

public class WaystonesConfig {
    @Config.Comment("Set to false to never mark Activated Waystones")
    @Config.Name("Activated - Enabled")
    public boolean enabled = true;

    @Config.Comment("Mark Activated Waystones with this Marker Type")
    @Config.Name("Activated - Marker")
    public String marker = "antiqueatlas:waystone";

    @Config.Comment("Mark Activated Waystones with this Label. Use DEFAULT to label the marker with the Waystone Name")
    @Config.Name("Activated - Label")
    public String label = "DEFAULT";

    @Config.Comment("Update Waystone marker names every time a player interacts with a waystone (disable for performance)")
    @Config.Name("Activated Waystones - Auto update name")
    public boolean autoUpdateWaystones = true;

    @Config.Comment("Set to true to always put markers for Waystones you interact with, instead of only marking the ones you activate.")
    @Config.Name("Activated Waystones - Always mark")
    public boolean alwaysMarkWaystones = false;

    @Config.Comment("Set to false to never mark Wild Waystones")
    @Config.Name("Wild - Enabled")
    public boolean enabled_wild = true;

    @Config.Comment("Mark Wild Waystones with this Marker Type")
    @Config.Name("Wild - Marker")
    public String marker_wild = "antiqueatlas:waystone";

    @Config.Comment("Mark Wild Waystones with this Label. Use DEFAULT to label the marker with the lang key set in lang file or lang key config.")
    @Config.Name("Wild - Label")
    public String label_wild = "DEFAULT";

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("wildWaystone", enabled_wild, label_wild, marker_wild);
    }
}
