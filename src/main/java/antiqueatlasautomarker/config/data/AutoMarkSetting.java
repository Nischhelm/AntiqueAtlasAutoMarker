package antiqueatlasautomarker.config.data;

import meldexun.betterconfig.api.Order;
import net.minecraftforge.common.config.Config;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class AutoMarkSetting {
    public static class Data {
        @Config.Comment("Set to false to never mark this structure")
        @Config.Name("Enabled")
        @Config.RequiresMcRestart
        @Order(0) public boolean enabled = true;
        @Config.Comment("Mark with this Marker Type")
        @Config.Name("Marker")
        @Order(1) public String type = "";
        @Config.Comment("Mark with this Label. Use DEFAULT to label the marker with a best fitting version.")
        @Config.Name("Label")
        @Order(2) public String label = "DEFAULT";

        @Config.Ignore
        public String context = "";

        public Data() {}
        public Data(String context, boolean defaultEnabled, String defaultType, String defaultLabel){
            this.context = context;
            if(context != null && !context.isEmpty())
                registerAutoMarkSetting(context, this);
            this.enabled = defaultEnabled;
            this.type = defaultType;
            this.label = defaultLabel;
        }
        public Data(boolean defaultEnabled, String defaultLabel, String defaultType, String context){
            this(context, defaultEnabled, defaultType, defaultLabel);
        }
    }

    public static final Map<String, Data> autoMarkSettings = new HashMap<>();
    @Nullable
    public static AutoMarkSetting.Data get(String name){
        return autoMarkSettings.get(name);
    }

    public static void registerAutoMarkSetting(String context, Data data){
        autoMarkSettings.put(context, data);
    }
}
