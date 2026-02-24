package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.Tags;
import antiqueatlasautomarker.config.data.AutoMarkSetting;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = Tags.MODID)
public class IceAndFireConfig {
    @Config.Comment("Fire Dragon Marker Config")
    @Config.Name("Fire Dragon")
    public AutoMarkSetting.Data firedragon = new AutoMarkSetting.Data("fireDragon", true, "antiqueatlas:dragon_red", "DEFAULT");

    @Config.Comment("Ice Dragon Marker Config")
    @Config.Name("Ice Dragon")
    public AutoMarkSetting.Data icedragon = new AutoMarkSetting.Data("iceDragon", true, "antiqueatlas:dragon_blue", "DEFAULT");

    @Config.Comment("Lightning Dragon Marker Config")
    @Config.Name("Lightning Dragon")
    public AutoMarkSetting.Data lightningdragon = new AutoMarkSetting.Data("lightningDragon", true, "antiqueatlas:dragon_gold", "DEFAULT");

    @Config.Comment("Hydra Cave Marker Config")
    @Config.Name("Hydra Cave")
    public AutoMarkSetting.Data hydra = new AutoMarkSetting.Data("cyclopsCave", true, "antiqueatlas:dragon_green", "DEFAULT");

    @Config.Comment("Cyclops Den Marker Config")
    @Config.Name("Cyclops Den")
    public AutoMarkSetting.Data cyclops = new AutoMarkSetting.Data("hydraCave", false, "antiqueatlas:red_x_small", "DEFAULT");

    //TODO: move mixintoggles into the data

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using I&F RLCraft Edition")
    @Config.Name("Ice And Fire RLCraft Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infrl.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire", targetVersionRange = "[2,)")
    @Config.RequiresMcRestart
    public boolean rl_enabled = true;

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using new I&F RotN Edition (1.3.1 onwards)")
    @Config.Name("Ice And Fire RotN Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infrotn.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire: RotN Edition", targetVersionRange = "[1.9.1-1.3.1,)")
    @Config.RequiresMcRestart
    public boolean rotn_enabled = true;

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using old I&F RotN Edition (until 1.3.1)")
    @Config.Name("Ice And Fire Older RotN Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infbase191.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire: RotN Edition", targetVersionRange = "[,1.9.1-1.3.1)")
    @Config.RequiresMcRestart
    public boolean old_rotn_enabled = true;

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using newest I&F (1.9.1)")
    @Config.Name("Ice And Fire 1.9.1 Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infbase191.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire", targetVersionRange = "1.9.1")
    @Config.RequiresMcRestart
    public boolean base_191_enabled = true;

    @Config.Comment("Set to false to never mark Ice and Fire Structures - using older I&F (before 1.9.1)")
    @Config.Name("Ice And Fire Enabled")
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.infbase.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "iceandfire", desired = true, warnIngame = false, reason = "No issue, auto disabled", modName = "Ice And Fire", targetVersionRange = "[,1.9.1)")
    @Config.RequiresMcRestart
    public boolean base_enabled = true;
}
