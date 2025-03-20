package antiqueatlasautomarker.config.folders;

import net.minecraftforge.common.config.Config;

public class CustomPositionConfig {
    @Config.Comment("Pattern: dimension; posX; posZ; discoverX; discoverZ; marker label; marker type.\n" +
            "where the first set of coordinates are where the marker will be located on the atlas,\n" +
            "and the second set of coordinates will be where the player has to be to discover that marker.\n" +
            "Will get set on server world creation. Only needs to be defined on server")
    @Config.Name("Custom Position Markers")
    @Config.RequiresWorldRestart
    public String[] customPositionMarkers = {
            //"0;2000;0;0;0;Test Custom Position Marker;antiqueatlas:diamond"
    };
}
