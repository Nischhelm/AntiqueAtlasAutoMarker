package antiqueatlasautomarker.api;

import antiqueatlasautomarker.config.data.AutoMarkSetting;
import antiqueatlasautomarker.features.structuremarkers.StructureMarkersDataHandler;
import hunternif.mc.atlas.marker.Marker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * AAAAM Structure markers work similar to Antique Atlas Global Markers.
 * They are saved in the servers structure marker atlas (aAtlasStructureMarkers.dat).
 * Different to global markers, players can delete them from their atlas indefinitely.
 * They are also not all immediately synced to the client on login, but properly discovered on chunk discover.
 * They can use a config-driven context allowing clients to decide how they want these structures to be marked (label/type).
 */
public class StructureMarkersAPI {
    /**
     * Mark a structure in the servers structure marker atlas.
     * @param world decides in which dimension to mark
     * @param x, z, pos where to mark
     * @param markerType the default type of the marker
     * @param markerName the default label of the marker
     * @param context a name for this type of structure marker. Context needs to be registered, otherwise use ""
     * @return the new marker object
     */
    public static Marker markStructure(@Nonnull World world, int x, int z, String markerType, String markerName, String context) {
        return StructureMarkersDataHandler.markStructure(world, x, z, markerType, markerName, context);
    }

    public static Marker markStructure(@Nonnull World world, BlockPos pos, String markerType, String markerName, String context) {
        return StructureMarkersDataHandler.markStructure(world, pos, markerType, markerName, context);
    }

    /**
     * Same as above but using a registered AutoMarkSetting.Data
     */
    public static Marker markStructure(@Nonnull World world, int x, int z, AutoMarkSetting.Data settings) {
        return StructureMarkersDataHandler.markStructure(world, x, z, settings);
    }

    public static Marker markStructure(@Nonnull World world, BlockPos pos, AutoMarkSetting.Data settings) {
        return StructureMarkersDataHandler.markStructure(world, pos, settings);
    }

    /**
     * Removes all structure markers of the given context in a square radius around the given position
     */
    public static void removeStructureMarkersNearby(World world, String context, BlockPos coords, int radius) {
        StructureMarkersDataHandler.removeStructureMarker(world, context, coords, radius);
    }

    /**
     * Register a configurable setting of how a structure should be marked.
     * @param context a name for this type of structure marker, usually the name of the structure and possibly the mod/feature context
     * @param settings the settings, can automatically be used as a @Config category
     */
    public static void registerAutoMarkSetting(String context, AutoMarkSetting.Data settings){
        AutoMarkSetting.registerAutoMarkSetting(context, settings);
    }

    /**
     * Register how a structure should be marked. This avoids using a config, losing out on the clients ability to change how they want your structure to be marked.
     * @param context a name for this type of structure marker, usually the name of the structure and possibly the mod/feature context
     * @param markerType the name of the marker, e.g. antiqueatlas:bed
     * @param label how the marker should be labeled. can use lang keys
     * @return the setting that was just registered
     */
    public static AutoMarkSetting.Data registerAutoMarkSetting(String context, String markerType, String label){
        AutoMarkSetting.Data settings = new AutoMarkSetting.Data(context, true, markerType, label);
        AutoMarkSetting.registerAutoMarkSetting(context, settings);
        return settings;
    }
}
