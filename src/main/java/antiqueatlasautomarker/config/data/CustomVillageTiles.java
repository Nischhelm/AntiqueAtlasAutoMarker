package antiqueatlasautomarker.config.data;

import meldexun.betterconfig.api.Order;
import net.minecraftforge.common.config.Config;

public class CustomVillageTiles {
    @Config.Comment("Component Names can be found in yourinstall/saves/yourworld/data/Village.dat in entries called \"id\"")
    @Config.Name("Structure Component")
    @Order(0) public String component = "";
    @Config.Comment("Texturesets are found and defined in the AntiqueAtlas config")
    @Config.Name("Texture Set")
    @Order(1) public String textureSet = "";
    @Config.Comment("Priority is used as a weight to determine which texture a chunk on the atlas will get")
    @Config.Name("Priority")
    @Config.RangeInt(min = 1)
    @Order(2) public int priority = 1;

    public CustomVillageTiles() {}
}
