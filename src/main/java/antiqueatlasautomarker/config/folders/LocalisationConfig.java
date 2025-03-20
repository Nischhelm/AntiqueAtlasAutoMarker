package antiqueatlasautomarker.config.folders;

import net.minecraftforge.common.config.Config;

public class LocalisationConfig {
    @Config.Comment("If there is both a lang key translation in the lang file and one in the default lang key config here, prioritise the config one. Enable this to adjust your localised marker labels")
    @Config.Name("Prioritise Config Lang Keys")
    public boolean prioritiseConfigLangKeys = false;

    @Config.Comment("AAAM will use these lang keys if there is no other lang file present that would translate those lang keys (internal name of the lang keys: gui.aaam.marker.xxx). Use this to rename structure markers, allows players with other languages seeing the same marker in their own language.")
    @Config.Name("Default Lang Keys")
    @Config.RequiresMcRestart
    public String[] langKeys = {
            "wildWaystone=Wild Waystone",
            "betterMineshaft=Mineshaft",
            "dungeons2=Dungeon2",
            "quarkPirateShip=Pirates",
            "Tower-ruined-short=Ruined BT Short",
            "Tower-edit=Ruined BT",
            "TowerEasy=Ruined BT",
            "TowerMedium=Ruined BT",
            "TowerHard=Ruined BT",
            "ZombieHut=Two Zombie Spawners",
            "SkyCastle=Sky Castle",
            "UnderwaterBase=Underwater Base",
            "Floater=Floater",
            "PirateShip=XP Ship",
            "StoneHouseM=Small Starter House",
            "PortalShrine=Nether Portal",
            "GraveyardHaunted=Graveyard",
            "GateUnderGlass=Nether Portal",
            "Mausoleum=Eight Zombie Spawners",
            "ArrowTrapTomb=Simple Dungeon Loot",
            "SnowCastleSpire=Blaze Spawners",
            "NetherShrine=Nether Portal"
    };
}
