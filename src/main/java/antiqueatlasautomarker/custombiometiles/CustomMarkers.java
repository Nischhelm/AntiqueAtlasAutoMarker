package antiqueatlasautomarker.custombiometiles;

import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.registry.MarkerType;
import net.minecraft.util.ResourceLocation;

import java.util.stream.Stream;

public class CustomMarkers {

    public static void registerMarkers(){
        Stream.of(
                "anvil",
                "bed",
                "book",
                "cave",
                "defiled",
                "diamond",
                "dragon_blue",
                "dragon_gold",
                "dragon_green",
                "dragon_red",
                "dungeon",
                "end_city",
                "end_city_far",
                "end_city_mipped_16",
                "end_city_mipped_32",
                "farm",
                "nether_portal",
                "pickaxe",
                "ruins",
                "ship",
                "sword",
                "tower",
                "tracks",
                "waystone",
                "wizardtower"
        ).forEach(name -> registerCustomMarker(name, name, "artsy"));

        Stream.of(
                "dragon_purple",
                "lycanites",
                "megatower",
                "monsterspawner",
                "parasite",
                "quadtower"
        ).forEach(name -> registerCustomMarker(name, name, "knightgallade"));
    }

    private static void registerCustomMarker(String typeName, String loc, String author){
        AtlasAPI.getMarkerAPI().registerMarker(new MarkerType(new ResourceLocation(AntiqueAtlasMod.ID, typeName), markerLoc(loc, author)));
    }

    private static ResourceLocation markerLoc(String markerName, String author){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/markers/"+author+"/"+markerName+".png");
    }
}