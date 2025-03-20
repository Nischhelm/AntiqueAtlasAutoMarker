package antiqueatlasautomarker.config.folders;

import net.minecraftforge.common.config.Config;

public class AARCAddonConfig {
    @Config.Comment("Keep on true on server to turn AARC Global markers into AAAM local markers.\n" +
            "Warning: turning this off will have serious side effects on the structure markers in your atlases")
    @Config.Name("Enabled")
    public boolean enabled = true;
}
