package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.Tags;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = Tags.MODID)
public class AARCAddonConfig {
    @Config.Comment("Keep on true on server to turn Antique Atlas RecurrentComplex Compatibility (AARC) Global markers into AAAM local markers.\n" +
            "Warning: turning this off will have serious side effects on the structure markers in your atlases")
    @Config.Name("AARC Compat Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.aarc.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "aarcaddon", desired = true, warnIngame = false, reason = "No issue, auto disabled")
    @Config.RequiresMcRestart
    public boolean enabled = true;

    @Config.Comment({
            "If AAAM and Recurrent Complex are installed, but AARC Addon is missing, will notify users on login.",
            "Disable if you dont want AARC Addon and dont want to be reminded of it either."
    })
    @Config.Name("Warn if not installed")
    public boolean warnNotInstalled = true;
}
