package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.config.AutoMarkSetting;
import net.minecraftforge.common.config.Config;

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
}
