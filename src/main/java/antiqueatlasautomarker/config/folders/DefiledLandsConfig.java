package antiqueatlasautomarker.config.folders;

import net.minecraftforge.common.config.Config;

public class DefiledLandsConfig {
    @Config.Comment("Enable to allow players to set a hotkey to mark nearby gold bookwyrms with a hotkey")
    @Config.Name("Gold Wyrm Key - Enabled")
    public boolean enabled = false;

    @Config.Comment("Marker to use for Golden Book Wyrms")
    @Config.Name("Gold Wyrm Key - Marker")
    public String goldenBookwyrmMarker = "antiqueatlas:defiled";

    @Config.Comment("Label to use for Golden Book Wyrms")
    @Config.Name("Gold Wyrm Key - Label")
    public String goldenBookwyrmLabel = "Gold Bookwyrm";
}
