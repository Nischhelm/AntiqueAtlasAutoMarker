package antiqueatlasautomarker.structuremarkers.event.handlers;

import antiqueatlasautomarker.structuremarkers.event.MarkStructureEvent;
import antiqueatlasautomarker.structuremarkers.event.ReceivedStructureMarkerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TestAAAMEventHandler {
    @SubscribeEvent
    public static void onReceivedStructureMarker(ReceivedStructureMarkerEvent event){
        event.getPlayer().sendMessage(new TextComponentString("Received Structure Marker: "+event.getMarker()));
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event){
        if(event.getEntityLiving() == null) return;
        if(event.getSource() == null) return;
        if(event.getSource().getTrueSource() == null) return;
        if(!(event.getSource().getTrueSource() instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
        if(player.world.isRemote) return;
        //Text without context
        MinecraftForge.EVENT_BUS.post(new MarkStructureEvent(player.world,player.getPosition(), "antiqueatlas:diamond","TestMarkerOnHurt"));
        //Test for context, will mark a fire dragon instead of a diamond
        MinecraftForge.EVENT_BUS.post(new MarkStructureEvent(player.world,player.getPosition().add(64,0,64), "antiqueatlas:diamond","TestMarkerOnHurt").setContext("fireDragon"));
    }
}
























