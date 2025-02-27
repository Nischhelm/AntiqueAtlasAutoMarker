package antiqueatlasautomarker.structuremarkers;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.util.IDeletedMarkerList;
import antiqueatlasautomarker.util.IMarkerConstructor;
import hunternif.mc.atlas.SettingsConfig;
import hunternif.mc.atlas.marker.DimensionMarkersData;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class StructureMarkersDataHandler {
    private static final String DATA_KEY = "aAtlasStructureMarkers";

    //This is technically not needed bc we already load on world load
    public static MarkersData getData(World world) {
        //Create or load the data
        MarkersData data = (MarkersData) world.loadData(MarkersData.class, DATA_KEY);
        if (data == null) {
            data = new MarkersData(DATA_KEY);
            data.markDirty();
            world.setData(DATA_KEY, data);
        }
        return data;
    }

    public static Marker markStructure(@Nonnull World world, int x, int z, String markerType, String markerName, String context, int... providedDimension) {
        if(world.isRemote){
            if(ConfigHandler.doDebugLogs) AntiqueAtlasAutoMarker.LOGGER.info("Trying to access structure marker data from clientside! context {}",context);
            return null;
        }
        MarkersData data = getData(world);
        boolean isCustomPositionMarker = providedDimension != null && providedDimension.length == 1;
        int dimension = !isCustomPositionMarker ? world.provider.getDimension() : providedDimension[0];

        markerType = IMarkerConstructor.addContext(markerType, context); //append context to marker type

        //Only for stupid things like doomlike dungeons generating the same dungeon multiple times bruh
        //Server side only comparison, so it's fine with clientside deletions/overrides
        boolean hasMarkerAlready = false;
        List<Marker> markersHere = data.getMarkersAtChunk(dimension, (x >> 4) / MarkersData.CHUNK_STEP, (z >> 4) / MarkersData.CHUNK_STEP);
        if(markersHere != null) {
            for (Marker marker : markersHere) {
                if (marker.getX() != x) continue;
                if (marker.getZ() != z) continue;
                if (!marker.getType().equals(markerType)) continue;
                if (!marker.getLabel().equals(markerName)) continue;
                hasMarkerAlready = true;
            }
        }

        if(ConfigHandler.doDebugLogs) AntiqueAtlasAutoMarker.LOGGER.info("Marking Structure at {},{} with marker {} {} {}, already exists {}",x,z,context, markerName, markerType, hasMarkerAlready);

        if (!hasMarkerAlready)
            //Use context appended at type, client overrides if not AARC and DEFAULT
            return data.createAndSaveMarker(markerType, markerName, dimension, x, z, isCustomPositionMarker);
        return null;
    }

    public static Marker markStructure(@Nonnull World world, BlockPos pos, String markerType, String markerName, String context) {
        return markStructure(world, pos.getX(), pos.getZ(), markerType, markerName, context);
    }

    public static Marker markStructure(@Nonnull World world, int x, int z, AutoMarkSetting settings) {
        if (settings != null && settings.enabled)
            return markStructure(world, x, z, settings.type, settings.label, settings.context);
        return null;
    }

    public static Marker markStructure(@Nonnull World world, BlockPos pos, AutoMarkSetting settings) {
        return markStructure(world, pos.getX(), pos.getZ(), settings);
    }

    public static Marker markCustomPositionStructure(@Nonnull World world, int dim, int x, int z, int xDiscover, int zDiscover, String markerType, String markerName){
        //set threadLocal to position where the custom marker would get discovered
        CustomPosition.set(xDiscover, zDiscover);
        //The actual marker position is where the marker will appear in the atlas
        return markStructure(world, x, z, markerType, markerName, "customPos", dim);
    }

    public static ArrayList<Marker> updateMarkersAroundPlayer(EntityPlayer player, MarkersData atlasMarkers) {
        ArrayList<Marker> updatedMarkers = new ArrayList<>();

        //Only every x ticks
        int newScanInterval = Math.round(SettingsConfig.performance.newScanInterval * 20);
        if (player.ticksExisted % newScanInterval != 0) return updatedMarkers;//no new markers

        //Truncate dividing (instead of Math.floorDiv) will do weird behavior around x=0 and z=0 but that's how AA coded it
        //So we replicate it
        int playerBigChunkX = (player.getPosition().getX() >> 4) / MarkersData.CHUNK_STEP;
        int playerBigChunkZ = (player.getPosition().getZ() >> 4) / MarkersData.CHUNK_STEP;

        //Usually = 1
        int checkRadius = SettingsConfig.performance.scanRadius / MarkersData.CHUNK_STEP;

        int dimension = player.world.provider.getDimension();
        DimensionMarkersData markersInDimension = getData(player.world).getMarkersDataInDimension(dimension);

        //Any existing markers?
        //The "big chunks" of antique atlas marker are so big that it's just a check of the 3x3 big chunks (8x8 chunks) around current position
        for (int i = -checkRadius; i <= checkRadius; i++) {
            for (int j = -checkRadius; j <= checkRadius; j++) {
                int bigChunkX = playerBigChunkX + i;
                int bigChunkZ = playerBigChunkZ + j;

                List<Marker> structureMarkers = markersInDimension.getMarkersAtChunk(bigChunkX, bigChunkZ);
                if (structureMarkers == null) continue;

                if(ConfigHandler.doDebugLogs) AntiqueAtlasAutoMarker.LOGGER.info("Found Markers to send in bigchunk {},{} count {}",bigChunkX, bigChunkZ, structureMarkers.size());


                List<Marker> existingMarkers = atlasMarkers.getMarkersAtChunk(dimension, bigChunkX, bigChunkZ);
                for (Marker marker : structureMarkers) {
                    //Check if we got the marker already, so we don't send existing markers multiple times (wouldn't get added anyway bc same id, but less networking
                    if (existingMarkers == null || !listContainsMarker(existingMarkers, marker))
                        //Check if that marker has been deleted on players atlas
                        if(!((IDeletedMarkerList) atlasMarkers).markerIsDeleted(-marker.getId())) {
                            if(ConfigHandler.doDebugLogs) AntiqueAtlasAutoMarker.LOGGER.info("Adding marker to to-send list {}",marker);
                            updatedMarkers.add(marker);
                        }
                }
            }
        }

        return updatedMarkers;
    }

    private static boolean listContainsMarker(List<Marker> list, Marker markerServer) {
        for (Marker markerPlayer : list)
            if (markerEquals(markerPlayer, markerServer))
                return true;
        return false;
    }

    private static boolean markerEquals(Marker markerPlayer, Marker markerServer) {
        //Only works for structure markers (player atlas marker id is negative)
        if (markerPlayer.getId() >= 0 ) return false;
        //Assumes that the player atlas marker id is the same id as the structure marker id just with a minus
        if (markerPlayer.getId() != -markerServer.getId()) return false;
        return true;
    }
}
