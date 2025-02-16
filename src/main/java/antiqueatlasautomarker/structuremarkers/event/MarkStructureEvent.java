package antiqueatlasautomarker.structuremarkers.event;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.Nonnull;

/**
 * Mods can fire this event on the Forge.EVENT_BUS server side in order to mark a structure in the AAAM structure marker list
 * with the specified settings (world=dimension, position, marker type (check config/antiqueatlas/markers.json), marker name
 * Only AAAM subscribes to this event in order to mark the structure, do not subscribe (it's useless)
 * Setting the context using setContext(context) allows clients to override the label+type defined here with their own AAAM settings
 * this requires mods to register that context in AutoMarkSetting.registerAutoMarkSetting though, otherwise those markers will never be added to atlases
 */
public class MarkStructureEvent extends Event {
    @Nonnull private final World world;
    private final int x;
    private final int z;
    private final String markerType;
    private final String markerName;
    private String context = "";

    /**
     * using x and z block coordinates
     */
    public MarkStructureEvent(@Nonnull World world, int x, int z, String markerType, String markerName) {
        this.world = world;
        this.x = x;
        this.z = z;
        this.markerType = markerType;
        this.markerName = markerName;
    }

    /**
     * using block position
     */
    public MarkStructureEvent(@Nonnull World world, BlockPos pos, String markerType, String markerName) {
        this.world = world;
        this.x = pos.getX();
        this.z = pos.getZ();
        this.markerType = markerType;
        this.markerName = markerName;
    }

    /**
     * Using chunk position, will mark at center of chunk
     */
    public MarkStructureEvent(@Nonnull World world, ChunkPos pos, String markerType, String markerName) {
        this.world = world;
        this.x = (pos.x << 4) + 8;
        this.z = (pos.z << 4) + 8;
        this.markerType = markerType;
        this.markerName = markerName;
    }

    /**
     * only set the context for the event if you also register AutoMarkSettings for that context on the client
     * otherwise the marker won't get added to any clients atlas
     * Usage: MinecraftForge.EVENT_BUS.post(new MarkStructureEvent(xyz).setContext(context));
     */
    public MarkStructureEvent setContext(String context){
        this.context = context;
        return this;
    }


    //------------------ GETTERS --------------//

    @Nonnull
    public World getWorld(){
        return this.world;
    }

    public int getX(){
        return this.x;
    }

    public int getZ(){
        return this.z;
    }

    public String getLabel(){
        return this.markerName;
    }

    public String getType(){
        return this.markerType;
    }

    public String getContext(){
        return this.context;
    }
}
