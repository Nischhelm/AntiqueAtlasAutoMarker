package antiqueatlasautomarker.structuremarkers.network;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.mixin.antiqueatlas.overhaul.sendtoallholding.MarkersDataAccessor;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.network.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CustomPacketDispatcher {
    public static void sendToAllHoldingAtlas(int atlasId, IMessage message) {
        WorldServer world = DimensionManager.getWorld(0, false);
        if(world == null) return; //Only happens if overworld isn't loaded, which should never happen

        for(EntityPlayer player : ((MarkersDataAccessor) AntiqueAtlasMod.markersData.getMarkersData(atlasId, world)).getPlayersSentTo())
            if(player instanceof EntityPlayerMP)
                PacketDispatcher.sendTo(message, (EntityPlayerMP) player);
    }
}