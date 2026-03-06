package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
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

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using I&F RLCraft Edition")
    @Config.Name("Ice And Fire RLCraft Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infrl.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire", targetVersionRange = "[2,)")
    @Config.RequiresMcRestart
    public boolean rl_enabled = true;

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using new I&F RotN Edition (1.3.1 onwards)")
    @Config.Name("Ice And Fire RotN Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infrotn.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire: RotN Edition", targetVersionRange = "[1.9.1-1.3.1,)")
    @Config.RequiresMcRestart
    public boolean rotn_enabled = true;

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using old I&F RotN Edition (until 1.3.1)")
    @Config.Name("Ice And Fire Older RotN Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infbase191.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire: RotN Edition", targetVersionRange = "[,1.9.1-1.3.1)")
    @Config.RequiresMcRestart
    public boolean old_rotn_enabled = true;

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using newest I&F (1.9.1)")
    @Config.Name("Ice And Fire 1.9.1 Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infbase191.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire", targetVersionRange = "1.9.1")
    @Config.RequiresMcRestart
    public boolean base_191_enabled = true;

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using older I&F (before 1.9.1)")
    @Config.Name("Ice And Fire Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infbase.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire", targetVersionRange = "[,1.9.1)")
    @Config.RequiresMcRestart
    public boolean base_enabled = true;

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
