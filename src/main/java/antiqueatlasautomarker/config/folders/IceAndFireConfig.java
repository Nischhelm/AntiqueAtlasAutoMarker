package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

public class IceAndFireConfig {
    @Config.Comment("Set to false to never mark Fire Dragons")
    @Config.Name("Fire Dragon - Enabled")
    public boolean enabled_firedragon = true;

    @Config.Comment("Mark Fire Dragons with this Marker Type")
    @Config.Name("Fire Dragon - Marker")
    public String marker_firedragon = "antiqueatlas:dragon_red";

    @Config.Comment("Mark Fire Dragons with this Label. Use DEFAULT to label the marker with the localised name for Fire Dragons.")
    @Config.Name("Fire Dragon - Label")
    public String label_firedragon = "DEFAULT";

    @Config.Comment("Set to false to never mark Ice Dragons")
    @Config.Name("Ice Dragon - Enabled")
    public boolean enabled_icedragon = true;

    @Config.Comment("Mark Ice Dragons with this Marker Type")
    @Config.Name("Ice Dragon - Marker")
    public String marker_icedragon = "antiqueatlas:dragon_blue";

    @Config.Comment("Mark Ice Dragons with this Label. Use DEFAULT to label the marker with the localised name for Ice Dragons.")
    @Config.Name("Ice Dragon - Label")
    public String label_icedragon = "DEFAULT";

    @Config.Comment("Set to false to never mark Lightning Dragons")
    @Config.Name("Lightning Dragon - Enabled")
    public boolean enabled_lightningdragon = true;

    @Config.Comment("Mark Lightning Dragons with this Marker Type")
    @Config.Name("Lightning Dragon - Marker")
    public String marker_lightningdragon = "antiqueatlas:dragon_gold";

    @Config.Comment("Mark Lightning Dragons with this Label. Use DEFAULT to label the marker with the localised name for Lightning Dragons.")
    @Config.Name("Lightning Dragon - Label")
    public String label_lightningdragon = "DEFAULT";

    @Config.Comment("Set to false to never mark Cyclops Caves")
    @Config.Name("Cyclops - Enabled")
    public boolean enabled_cyclops = false;

    @Config.Comment("Mark Cyclops Caves with this Marker Type")
    @Config.Name("Cyclops - Marker")
    public String marker_cyclops = "antiqueatlas:red_x_small";

    @Config.Comment("Mark Cyclops Caves with this Label. Use DEFAULT to label the marker with the localised name for Cyclops.")
    @Config.Name("Hydra - Label")
    public String label_cyclops = "DEFAULT";

    @Config.Comment("Set to false to never mark Hydra Caves")
    @Config.Name("Hydra - Enabled")
    public boolean enabled_hydra = false;

    @Config.Comment("Mark Hydra Caves with this Marker Type")
    @Config.Name("Hydra - Marker")
    public String marker_hydra = "antiqueatlas:dragon_green";

    @Config.Comment("Mark Hydra Caves with this Label. Use DEFAULT to label the marker with the localised name for Hydra.")
    @Config.Name("Hydra - Label")
    public String label_hydra = "DEFAULT";

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("fireDragon", enabled_firedragon, label_firedragon, marker_firedragon);
        AutoMarkSetting.registerAutoMarkSetting("iceDragon", enabled_icedragon, label_icedragon, marker_icedragon);
        AutoMarkSetting.registerAutoMarkSetting("lightningDragon", enabled_lightningdragon, label_lightningdragon, marker_lightningdragon);
        AutoMarkSetting.registerAutoMarkSetting("cyclopsCave", enabled_cyclops, label_cyclops, marker_cyclops);
        AutoMarkSetting.registerAutoMarkSetting("hydraCave", enabled_hydra, label_hydra, marker_hydra);
    }
}
