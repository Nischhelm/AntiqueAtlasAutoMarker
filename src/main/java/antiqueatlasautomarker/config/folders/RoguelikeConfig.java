package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

import java.util.HashMap;
import java.util.Map;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class RoguelikeConfig {
    @Config.Comment("Set to false to never mark Roguelike Dungeons")
    @Config.Name("Roguelike Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.roguelikedungeons.json", defaultValue = false)
    @MixinConfig.CompatHandling(modid = "roguelike", desired = true, warnIngame = false, reason = "No issue, auto disabled")
    @Config.RequiresMcRestart
    public boolean enabled = false;

    @Config.Comment("Mark Roguelike Dungeons with this Marker Type")
    @Config.Name("Marker")
    public String marker = "antiqueatlas:dungeon";

    @Config.Comment("Mark Roguelike Dungeons with this Label. Use DEFAULT to label the marker with the localised name for Roguelike Dungeons.")
    @Config.Name("Label")
    public String label = "DEFAULT";

    @Config.Comment("Use these to specify roguelike dungeon themes more specifically. Original name:Used name")
    @Config.Name("Default Theme Labels")
    public Map<String, String> defaultThemeNames = new HashMap<String, String>(){{
        put("Brick","");
        put("Bumbo","");
        put("Cave","");
        put("Checker","");
        put("Crypt","");
        put("DarkHall","Dark Hall");
        put("DarkOak","Dark Oak");
        put("Ender","");
        put("EniIce","Ice");
        put("Eniko","");
        put("Eniko2","Eniko");
        put("EniQuartz","Quartz");
        put("Etho","");
        put("EthoTower","Etho");
        put("Grey","");
        put("Hell","");
        put("House","");
        put("Ice","");
        put("Jungle","");
        put("MineShaft","Mineshafty");
        put("Mossy","");
        put("Muddy","");
        put("Nether","");
        put("NetherFortress","Nether");
        put("Oak","");
        put("Purpur","");
        put("Pyramid","");
        put("Quartz","");
        put("Rainbow","");
        put("Sandstone","");
        put("SandstoneRed","Red Sandstone");
        put("Sewer","");
        put("Snow","");
        put("Spruce","");
        put("Stone","");
        put("Temple","");
        put("Terracotta","");
        put("Tower","");
    }};

    @Config.Comment("Use these to specify roguelike dungeon towers more specifically. Original name:Used name")
    @Config.Name("Default Tower Labels")
    public Map<String, String> defaultTowerNames = new HashMap<String, String>(){{
        put("Bumbo","");
        put("Bunker","");
        put("Eniko","");
        put("Etho","");
        put("Hole","");
        put("House","Brick House");
        put("Jungle","");
        put("Pyramid","");
        put("Rogue","");
        put("Ruin","");
        put("Tree","");
        put("Villager_house","Villager House");
        put("Witch","");
    }};

    public void preInit(){
        resetSetting();
    }

    public void resetSetting(){
        AutoMarkSetting.registerAutoMarkSetting("roguelike", enabled, label, marker);
    }
}
