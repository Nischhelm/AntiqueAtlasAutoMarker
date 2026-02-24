package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.AutoMarkSetting.Data;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class WaystonesConfig {
    @Config.Comment("Activated Waystones Marker Config")
    @Config.Name("Activated Waystones")
    public AutoMarkSetting.Data activatedWaystones = new AutoMarkSetting.Data(null, true, "antiqueatlas:waystone", "DEFAULT");

    @Config.Comment("Update Waystone marker names every time a player interacts with a waystone (disable for performance)")
    @Config.Name("Activated Waystones - Auto update name")
    public boolean autoUpdateWaystones = true;

    @Config.Comment("Set to true to always put markers for Waystones you interact with, instead of only marking the ones you activate.")
    @Config.Name("Activated Waystones - Always mark")
    public boolean alwaysMarkWaystones = false;

    @Config.Comment("Wild Waystones Marker Config")
    @Config.Name("Wild Waystones")
    public AutoMarkSetting.Data wildWaystones = new AutoMarkSetting.Data("wildWaystone", false, "antiqueatlas:waystone", "DEFAULT");

    @Config.Comment("Enabling this will show an atlas button in waystone selection screens (when rightclicking a waystone or using a warp scroll). \n" +
            "Clicking on the button will display all available waystones in the current dimension on the current held atlas (requires having an atlas). Clicking on a waystone marker will tp to it.")
    @Config.Name("Allow selecting waystone on map")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.mapselect.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "waystones", desired = true, warnIngame = false, reason = "No issue, auto disabled")
    public boolean disableSpecificMarkers = true;
}
