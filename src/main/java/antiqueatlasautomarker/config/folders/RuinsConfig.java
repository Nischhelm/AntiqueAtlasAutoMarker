package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.data.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RuinsConfig {
    @Config.Comment("Set to false to never mark any Ruins structures")
    @Config.Name("Ruins Enabled")
    public boolean enabled = true;

    @Config.Comment({
            "DEFAULT uses the corresponding lang key set in lang file or in the lang key config",
            "Ruins structure names can be found in /yourinstall/config/ruins_config/some_biome/[StructureName].tml"
    })
    @Config.Name("Ruins Structure Markers")
    public Map<String, AutoMarkSetting.Data> ruinsMarkers = Stream.of(
            new AutoMarkSetting.Data("Tower-ruined-short",false,"antiqueatlas:ruins","DEFAULT"),
            new AutoMarkSetting.Data("Tower-edit",true,"antiqueatlas:tower","DEFAULT"),
            new AutoMarkSetting.Data("TowerEasy",true,"antiqueatlas:tower","DEFAULT"),
            new AutoMarkSetting.Data("TowerMedium",true,"antiqueatlas:tower","DEFAULT"),
            new AutoMarkSetting.Data("TowerHard",true,"antiqueatlas:tower","DEFAULT"),
            new AutoMarkSetting.Data("ZombieHut",false,"antiqueatlas:sword","DEFAULT"),
            new AutoMarkSetting.Data("SkyCastle",true,"antiqueatlas:diamond","DEFAULT"),
            new AutoMarkSetting.Data("UnderwaterBase",false,"antiqueatlas:diamond","DEFAULT"),
            new AutoMarkSetting.Data("Floater",true,"antiqueatlas:diamond","DEFAULT"),
            new AutoMarkSetting.Data("PirateShip",true,"antiqueatlas:ship","DEFAULT"),
            new AutoMarkSetting.Data("StoneHouseM",false,"antiqueatlas:ruins","DEFAULT"),
            new AutoMarkSetting.Data("PortalShrine",true,"antiqueatlas:nether_portal","DEFAULT"),
            new AutoMarkSetting.Data("GraveyardHaunted",true,"antiqueatlas:diamond","DEFAULT"),
            new AutoMarkSetting.Data("GateUnderGlass",false,"antiqueatlas:nether_portal","DEFAULT"),
            new AutoMarkSetting.Data("Mausoleum",false,"antiqueatlas:sword","DEFAULT"),
            new AutoMarkSetting.Data("ArrowTrapTomb",false,"antiqueatlas:diamond","DEFAULT"),
            new AutoMarkSetting.Data("SnowCastleSpire",false,"antiqueatlas:sword","DEFAULT"),
            new AutoMarkSetting.Data("NetherShrine",true,"antiqueatlas:nether_portal","DEFAULT")
    ).collect(Collectors.toMap(data -> data.context, Function.identity()));
}
