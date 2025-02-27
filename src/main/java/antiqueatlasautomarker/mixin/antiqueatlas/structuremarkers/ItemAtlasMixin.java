package antiqueatlasautomarker.mixin.antiqueatlas.structuremarkers;

import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import antiqueatlasautomarker.structuremarkers.network.CustomPacketDispatcher;
import antiqueatlasautomarker.structuremarkers.network.OptionalStructureMarkerPacket;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.item.ItemAtlas;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import hunternif.mc.atlas.network.PacketDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(ItemAtlas.class)
public abstract class ItemAtlasMixin {
    @Inject(
            method = "onUpdate",
            at = @At(value = "TAIL")
    )
    private void updateStructureMarkers(ItemStack stack, World world, Entity entity, int slot, boolean isEquipped, CallbackInfo ci, @Local EntityPlayer player) {
        if (world.isRemote) return;
        if (!(entity instanceof EntityPlayerMP)) return;
        MarkersData atlasMarkers = AntiqueAtlasMod.markersData.getMarkersData(stack, world);
        ArrayList<Marker> newMarkers = StructureMarkersDataHandler.updateMarkersAroundPlayer(player, atlasMarkers);

        if (!newMarkers.isEmpty()) {
            OptionalStructureMarkerPacket packet = new OptionalStructureMarkerPacket(stack.getItemDamage(), player.dimension);
            for (Marker m : newMarkers)
                packet.putMarker(m);
            if(ConfigHandler.overhaul.sendToAllHolding)
                CustomPacketDispatcher.sendToAllHoldingAtlas(stack.getItemDamage(), packet);
            else
                PacketDispatcher.sendToAll(packet);
        }
    }
}
