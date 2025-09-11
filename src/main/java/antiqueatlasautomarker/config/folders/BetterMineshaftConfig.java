package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class BetterMineshaftConfig {
    @Config.Comment("Set to false to never mark Yungs Better Mineshafts")
    @Config.Name("YBM Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.bettermineshafts.json", defaultValue = false)
    @MixinConfig.CompatHandling(modid = "bettermineshafts", desired = true, warnIngame = false)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    @Config.RequiresMcRestart
    public boolean enabled = false;

    @Config.Comment("Mark Better Mineshafts with this Marker Type")
    @Config.Name("Marker")
    public String marker = "antiqueatlas:tracks";

    @Config.Comment("Mark Better Mineshafts with this Label. Use DEFAULT to mark the mineshaft with the lang key defined in lang file or lang key config.")
    @Config.Name("Label")
    public String label = "DEFAULT";

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("betterMineshaft", enabled, label, marker);
    }
}
