package antiqueatlasautomarker.config.folders;

import net.minecraftforge.common.config.Config;

import java.util.LinkedHashMap;
import java.util.Map;

public class LocalisationConfig {
    @Config.Comment("If there is both a lang key translation in the lang file and one in the default lang key config here, prioritise the config one. Enable this to adjust your localised marker labels")
    @Config.Name("Prioritise Config Lang Keys")
    public boolean prioritiseConfigLangKeys = false;

    @Config.Comment("AAAM will use these lang keys if there is no other lang file present that would translate those lang keys (internal name of the lang keys: gui.aaam.marker.xxx). Use this to rename structure markers, allows players with other languages seeing the same marker in their own language.")
    @Config.Name("Default Lang Keys")
    @Config.RequiresMcRestart
    public Map<String,String> langKeys = new LinkedHashMap<>();

    public LocalisationConfig() {
        langKeys.put("wildWaystone", "Wild Waystone");
        langKeys.put("betterMineshaft", "Mineshaft");
        langKeys.put("dungeons2", "Dungeon2");
        langKeys.put("quarkPirateShip", "Pirates");
        langKeys.put("Tower-ruined-short", "Ruined BT Short");
        langKeys.put("Tower-edit", "Ruined BT");
        langKeys.put("TowerEasy", "Ruined BT");
        langKeys.put("TowerMedium", "Ruined BT");
        langKeys.put("TowerHard", "Ruined BT");
        langKeys.put("ZombieHut", "Two Zombie Spawners");
        langKeys.put("SkyCastle", "Sky Castle");
        langKeys.put("UnderwaterBase", "Underwater Base");
        langKeys.put("Floater", "Floater");
        langKeys.put("PirateShip", "XP Ship");
        langKeys.put("StoneHouseM", "Small Starter House");
        langKeys.put("PortalShrine", "Nether Portal");
        langKeys.put("GraveyardHaunted", "Graveyard");
        langKeys.put("GateUnderGlass", "Nether Portal");
        langKeys.put("Mausoleum", "Eight Zombie Spawners");
        langKeys.put("ArrowTrapTomb", "Simple Dungeon Loot");
        langKeys.put("SnowCastleSpire", "Blaze Spawners");
        langKeys.put("NetherShrine", "Nether Portal");
    }
}
