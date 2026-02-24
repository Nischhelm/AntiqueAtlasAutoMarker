package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.Tags;
import antiqueatlasautomarker.config.data.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = Tags.MODID)
public class WaystonesConfig {
    @Config.Comment("Activated Waystones Marker Config")
    @Config.Name("Activated Waystones")
    public WaystoneData activatedWaystones = new WaystoneData(null, true, "antiqueatlas:waystone", "DEFAULT");

    public static class WaystoneData extends AutoMarkSetting.Data {
        @Config.Comment("Update Waystone marker names every time a player interacts with a waystone (disable for performance)")
        @Config.Name("Auto update name")
        public boolean autoUpdateWaystones = true;

        @Config.Comment("Set to true to always put markers for Waystones you interact with, instead of only marking the ones you actually activate.")
        @Config.Name("Always mark")
        public boolean alwaysMarkWaystones = false;

        public WaystoneData() {
            super();
        }
        public WaystoneData(String context, boolean defaultEnabled, String defaultType, String defaultLabel){
            super(context, defaultEnabled, defaultType, defaultLabel);
        }
        public WaystoneData(boolean defaultEnabled, String defaultLabel, String defaultType, String context){
            super(context, defaultEnabled, defaultType, defaultLabel);
        }
    }

    @Config.Comment("Wild Waystones Marker Config")
    @Config.Name("Wild Waystones")
    public AutoMarkSetting.Data wildWaystones = new AutoMarkSetting.Data("wildWaystone", false, "antiqueatlas:waystone", "DEFAULT");
}
