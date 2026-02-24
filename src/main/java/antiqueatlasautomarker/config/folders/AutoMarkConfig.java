package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.data.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class AutoMarkConfig {
    @Config.Comment("Battletowers Marker Config")
    @Config.Name("Battletowers")
    public AutoMarkSetting.Data battletowers = new AutoMarkSetting.Data("battleTower", true, "antiqueatlas:wizardtower", "DEFAULT");

    @Config.Comment("Settings for marking Gold Wyrms using a hotkey")
    @Config.Name("Defiled Lands Gold Wyrm Key")
    public AutoMarkSetting.Data defiledlands = new AutoMarkSetting.Data(null, false, "antiqueatlas:defiled", "Gold Bookwyrm");

    @Config.Comment("Doomlike Dungeons Marker Config")
    @Config.Name("Doomlike Dungeons")
    public AutoMarkSetting.Data doomlike = new AutoMarkSetting.Data("doomlike", true, "antiqueatlas:dungeon", "DEFAULT");

    @Config.Comment("Dungeons2 Marker Config")
    @Config.Name("Dungeons2")
    public AutoMarkSetting.Data dungeons2 = new AutoMarkSetting.Data("dungeons2", true, "antiqueatlas:dungeon", "DEFAULT");

    @Config.Comment("Settings for marking Quark Piratship")
    @Config.Name("Quark Pirateship")
    public AutoMarkSetting.Data quark = new AutoMarkSetting.Data("quarkPirateShip", true, "antiqueatlas:skull", "DEFAULT");

    @Config.Comment("Ice And Fire Marker Config")
    @Config.Name("Ice And Fire")
    public IceAndFireConfig iceandfire = new IceAndFireConfig();

    @Config.Comment("Better Mineshafts Marker Config")
    @Config.Name("Better Mineshafts")
    public AutoMarkSetting.Data bettermineshafts = new AutoMarkSetting.Data("betterMineshaft", false, "antiqueatlas:tracks", "DEFAULT");

    @Config.Comment("Lycanites Mobs Marker Config")
    @Config.Name("Lycanites Dungeons")
    public AutoMarkSetting.Data lycanitesmobs = new AutoMarkSetting.Data("lycanite", true, "antiqueatlas:dungeon", "DEFAULT");

    @Config.Comment("Custom Position Markers Config")
    @Config.Name("Custom Positions")
    public CustomPositionConfig customPosition = new CustomPositionConfig();

    @Config.Comment("Ruins Marker Config")
    @Config.Name("Ruins")
    public RuinsConfig ruins = new RuinsConfig();

    @Config.Comment("Waystone Marker Config")
    @Config.Name("Waystones")
    public WaystonesConfig waystones = new WaystonesConfig();

    @Config.Comment({
            "MapGenStructure Marker Config",
            "This gives marker configs for modded and vanilla structures that use the vanilla MapGenStructure system"
    })
    @Config.Name("Vanilla MapGenStructures")
    public MapGenStructuresConfig mapGenStructs = new MapGenStructuresConfig();

    @Config.Comment("Enchantment Marker Config")
    @Config.Name("Enchantments")
    public EnchantmentConfig enchantments = new EnchantmentConfig();

    @Config.Comment("Options for custom marker labels")
    @Config.Name("Lang Keys")
    public LocalisationConfig localisation = new LocalisationConfig();

    @Config.Comment("Keep on true on server to turn Antique Atlas RecurrentComplex Compatibility (AARC) Global markers into AAAM local markers.\n" +
            "Warning: turning this off will have serious side effects on the structure markers in your atlases")
    @Config.Name("AARC Compat Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.aarc.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "aarcaddon", desired = true, warnIngame = false, reason = "No issue, auto disabled")
    @Config.RequiresMcRestart
    public boolean aarcEnabled = true;

}
