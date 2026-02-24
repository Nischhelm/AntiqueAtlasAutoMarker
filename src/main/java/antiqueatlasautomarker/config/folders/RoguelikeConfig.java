package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.data.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

import java.util.LinkedHashMap;
import java.util.Map;

public class RoguelikeConfig extends AutoMarkSetting.Data {
    @Config.Comment("Use these to specify roguelike dungeon themes more specifically. Original name:Used name")
    @Config.Name("Default Theme Labels")
    public Map<String, String> defaultThemeNames = new LinkedHashMap<>();
    private void initDefaultThemes() {
        defaultThemeNames.put("Brick", "");
        defaultThemeNames.put("Bumbo", "");
        defaultThemeNames.put("Cave", "");
        defaultThemeNames.put("Checker", "");
        defaultThemeNames.put("Crypt", "");
        defaultThemeNames.put("DarkHall", "Dark Hall");
        defaultThemeNames.put("DarkOak", "Dark Oak");
        defaultThemeNames.put("Ender", "");
        defaultThemeNames.put("EniIce", "Ice");
        defaultThemeNames.put("Eniko", "");
        defaultThemeNames.put("Eniko2", "Eniko");
        defaultThemeNames.put("EniQuartz", "Quartz");
        defaultThemeNames.put("Etho", "");
        defaultThemeNames.put("EthoTower", "Etho");
        defaultThemeNames.put("Grey", "");
        defaultThemeNames.put("Hell", "");
        defaultThemeNames.put("House", "");
        defaultThemeNames.put("Ice", "");
        defaultThemeNames.put("Jungle", "");
        defaultThemeNames.put("MineShaft", "Mineshafty");
        defaultThemeNames.put("Mossy", "");
        defaultThemeNames.put("Muddy", "");
        defaultThemeNames.put("Nether", "");
        defaultThemeNames.put("NetherFortress", "Nether");
        defaultThemeNames.put("Oak", "");
        defaultThemeNames.put("Purpur", "");
        defaultThemeNames.put("Pyramid", "");
        defaultThemeNames.put("Quartz", "");
        defaultThemeNames.put("Rainbow", "");
        defaultThemeNames.put("Sandstone", "");
        defaultThemeNames.put("SandstoneRed", "Red Sandstone");
        defaultThemeNames.put("Sewer", "");
        defaultThemeNames.put("Snow", "");
        defaultThemeNames.put("Spruce", "");
        defaultThemeNames.put("Stone", "");
        defaultThemeNames.put("Temple", "");
        defaultThemeNames.put("Terracotta", "");
        defaultThemeNames.put("Tower", "");
    }

    @Config.Comment("Use these to specify roguelike dungeon towers more specifically. Original name:Used name")
    @Config.Name("Default Tower Labels")
    public Map<String, String> defaultTowerNames = new LinkedHashMap<>();
    private void initDefaultTowers() {
        defaultTowerNames.put("Bumbo","");
        defaultTowerNames.put("Bunker","");
        defaultTowerNames.put("Eniko","");
        defaultTowerNames.put("Etho","");
        defaultTowerNames.put("Hole","");
        defaultTowerNames.put("House","Brick House");
        defaultTowerNames.put("Jungle","");
        defaultTowerNames.put("Pyramid","");
        defaultTowerNames.put("Rogue","");
        defaultTowerNames.put("Ruin","");
        defaultTowerNames.put("Tree","");
        defaultTowerNames.put("Villager_house","Villager House");
        defaultTowerNames.put("Witch","");
    }

    public RoguelikeConfig() {}
    public RoguelikeConfig(String context, boolean defaultEnabled, String defaultType, String defaultLabel) {
        super(context, defaultEnabled, defaultType, defaultLabel);
        initDefaultThemes();
        initDefaultTowers();
    }
}
