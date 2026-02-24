package antiqueatlasautomarker.config.folders;

import meldexun.betterconfig.api.Order;
import net.minecraftforge.common.config.Config;

import java.util.ArrayList;
import java.util.List;

public class CustomPositionConfig {
    @Config.Comment("Pattern: dimension; posX; posZ; discoverX; discoverZ; marker label; marker type.\n" +
            "where the first set of coordinates are where the marker will be located on the atlas,\n" +
            "and the second set of coordinates will be where the player has to be to discover that marker.\n" +
            "Will get set on server world creation. Only needs to be defined on server")
    @Config.Name("Custom Position Markers")
    @Config.RequiresWorldRestart
    public List<Data> customPositionMarkers = new ArrayList<>();
            //"0;2000;0;0;0;Test Custom Position Marker;antiqueatlas:diamond"

    public static class Data {
        @Order(0) @Config.Name("Marker Type")
        public String type = "antiqueatlas:red_x_small";
        @Order(1) @Config.Name("Marker Label")
        public String label = "MyLabel";
        @Order(2) @Config.Name("Dimension")
        public int dimension = 0;
        @Order(3) public int xMarker = 0;
        @Order(4) public int zMarker = 0;
        @Order(5) public int xDiscover = 0;
        @Order(6) public int zDiscover = 0;

        public Data(){}
    }
}
