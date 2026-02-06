package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class DoomlikeConfig {
    @Config.Comment("Set to false to never mark Doomlike Dungeons")
    @Config.Name("DLD Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.doomlikedungeons.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "dldungeonsjbg", desired = true, warnIngame = false, reason = "No issue, auto disabled")
    @Config.RequiresMcRestart
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
