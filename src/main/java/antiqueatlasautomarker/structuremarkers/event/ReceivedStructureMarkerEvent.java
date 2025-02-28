package antiqueatlasautomarker.structuremarkers.event;

import hunternif.mc.atlas.marker.Marker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * Fired on Forge.EVENT_BUS when a client receives a structure marker.
 * If canceled, this marker won't get added to the players atlases
 * The context denotes the AAAM custom contexts set in AAAM config
 *      "customPos" for custom position markers
 *      "AARCAddon$recurrentComplexStructureName" for AARC structure markers
 * Mods can subscribe to this event to do custom actions on the clientside when players receive a structure marker
 * None of the properties of this event can be modified
 */
@Cancelable
public class ReceivedStructureMarkerEvent extends Event {
    private final EntityPlayer player;
    private final Marker marker;
    private final String context;

    public ReceivedStructureMarkerEvent(EntityPlayer player, Marker marker, String context) {
        this.player = player;
        this.marker = marker;
        this.context = context;
    }

    public EntityPlayer getPlayer(){
        return this.player;
    }

    public Marker getMarker(){
        return this.marker;
    }

    public String getContext(){
        return this.context;
    }
}
