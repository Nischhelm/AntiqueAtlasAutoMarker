package antiqueatlasautomarker.config.folders;

import net.minecraftforge.common.config.Config;

public class RuinsConfig {
    @Config.Comment("Set to false to never mark Ruins structures")
    @Config.Name("Ruins Enabled")
    public boolean enabled = true;

    @Config.Comment("Pattern: ruinsName; enabled; marker label; marker type. DEFAULT uses the corresponding lang key set in lang file or in the lang key config")
    @Config.Name("Ruins Structure Markers")
    public String[] ruinsMarkers = {
            "Tower-ruined-short; false; DEFAULT; antiqueatlas:ruins",
            "Tower-edit; true; DEFAULT; antiqueatlas:tower",
            "TowerEasy; true; DEFAULT; antiqueatlas:tower",
            "TowerMedium; true; DEFAULT; antiqueatlas:tower",
            "TowerHard; true; DEFAULT; antiqueatlas:tower",
            "ZombieHut; false; DEFAULT; antiqueatlas:sword",
            "SkyCastle; true; DEFAULT; antiqueatlas:diamond",
            "UnderwaterBase; false; DEFAULT; antiqueatlas:diamond",
            "Floater; true; DEFAULT; antiqueatlas:diamond",
            "PirateShip; true; DEFAULT; antiqueatlas:ship",
            "StoneHouseM; false; DEFAULT; antiqueatlas:ruins",
            "PortalShrine; true; DEFAULT; antiqueatlas:nether_portal",
            "GraveyardHaunted; true; DEFAULT; antiqueatlas:diamond",
            "GateUnderGlass; false; DEFAULT; antiqueatlas:nether_portal",
            "Mausoleum; false; DEFAULT; antiqueatlas:sword",
            "ArrowTrapTomb; false; DEFAULT; antiqueatlas:diamond",
            "SnowCastleSpire; false; DEFAULT; antiqueatlas:sword",
            "NetherShrine; true; DEFAULT; antiqueatlas:nether_portal"
    };
}
