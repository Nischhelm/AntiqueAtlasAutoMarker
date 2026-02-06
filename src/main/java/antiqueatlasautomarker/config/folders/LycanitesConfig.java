package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class LycanitesConfig {
    @Config.Comment("Set to false to never mark Lycanites Dungeons")
    @Config.Name("LM Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.lycanitesmobs.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "lycanitesmobs", desired = true, warnIngame = false, reason = "No issue, auto disabled")
    @Config.RequiresMcRestart
    public boolean enabled = true;

    @Config.Comment("Mark Lycanites Dungeons with this Marker Type")
    @Config.Name("Marker")
    public String marker = "antiqueatlas:dungeon";

    @Config.Comment("Mark Lycanites Dungeons with this Label. Use DEFAULT to mark the lyca dungeon with the default name of the dungeon, like \"Ashen Mausoleum\".")
    @Config.Name("Label")
    public String label = "DEFAULT";

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("lycanite", enabled, label, marker);
    }
}
