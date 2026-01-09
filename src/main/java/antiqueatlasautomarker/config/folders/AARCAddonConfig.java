package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class AARCAddonConfig {
    @Config.Comment("Keep on true on server to turn AARC Global markers into AAAM local markers.\n" +
            "Warning: turning this off will have serious side effects on the structure markers in your atlases")
    @Config.Name("AARC Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.aarc.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "aarcaddon", desired = true, warnIngame = false, reason = "No issue, auto disabled")
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    @Config.RequiresMcRestart
    public boolean enabled = true;
}
