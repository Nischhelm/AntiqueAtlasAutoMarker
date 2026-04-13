package antiqueatlasautomarker.custombiometiles;

import antiqueatlasautomarker.config.ConfigHandler;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.registry.MarkerType;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomMarkers {

    public static final List<String> markersArtsy = Arrays.asList("anvil", "bed", "book", "cave", "defiled", "diamond",
            "dragon_blue", "dragon_gold", "dragon_green", "dragon_red", "dungeon",
            "end_city", "end_city_far", "end_city_mipped_16", "end_city_mipped_32", "farm",
            "nether_portal", "pickaxe", "ruins", "ship", "sword",
            "tower", "tracks", "waystone", "wizardtower");

    public static final List<String> markersGallade = Arrays.asList("dragon_purple", "lycanites", "megatower",
                                                                    "monsterspawner", "parasite", "quadtower");

    public static void registerMarkers(){
        Set<String> allowedMarkers = new HashSet<>(Arrays.asList(ConfigHandler.overhaul.tileConfig.usedCustomMarkers));
        markersArtsy.stream().filter(allowedMarkers::contains).forEach(name -> registerCustomMarker(name, name, "artsy"));
        markersGallade.stream().filter(allowedMarkers::contains).forEach(name -> registerCustomMarker(name, name, "knightgallade"));
    }

    private static void registerCustomMarker(String typeName, String loc, String author){
        AtlasAPI.getMarkerAPI().registerMarker(new MarkerType(new ResourceLocation(AntiqueAtlasMod.ID, typeName), markerLoc(loc, author))
                .setIsTechnical(typeName.startsWith("end_city"))
        );
    }

    private static ResourceLocation markerLoc(String markerName, String author){
        return new ResourceLocation(AntiqueAtlasMod.ID, "textures/gui/markers/"+author+"/"+markerName+".png");
    }
}
