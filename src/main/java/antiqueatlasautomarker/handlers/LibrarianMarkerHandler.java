package antiqueatlasautomarker.handlers;

import antiqueatlasautomarker.config.ConfigHandler;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

import java.util.List;
import java.util.stream.Collectors;

@SideOnly(Side.CLIENT)
public class LibrarianMarkerHandler {
    @SubscribeEvent
    public static void onKeyPressed(InputEvent.KeyInputEvent event){
        if(!librarianKey.isPressed()) return;

        EntityPlayer player = Minecraft.getMinecraft().player;
        List<Integer> atlases = AtlasAPI.getPlayerAtlases(player);
        if(atlases.isEmpty()) return;

        World world = Minecraft.getMinecraft().world;
        List<EntityVillager> villagers = world.getLoadedEntityList().stream()
                .filter(v -> v instanceof EntityVillager)
                .map(v -> (EntityVillager) v)
                .filter(v -> VillagerRegistry.getId(v.getProfessionForge()) == 1)
                .collect(Collectors.toList());

        for (EntityVillager villager : villagers) {
            int villX = (int) villager.posX;
            int villZ = (int) villager.posZ;
            for(int atlasID : atlases) {
                AntiqueAtlasMod.markersData
                        .getMarkersData(atlasID, player.world)
                        .getMarkersDataInDimension(player.world.provider.getDimension())
                        .getAllMarkers()
                        .stream()
                        .filter(marker -> Math.abs(marker.getX() - villX) < 64 && Math.abs(marker.getZ() - villZ) < 64)
                        .filter(marker -> marker.getType().equals(ConfigHandler.enchantments.librarianKeyMarker))
                        .filter(marker -> marker.getLabel().equals(ConfigHandler.enchantments.librarianKeyLabel))
                        .forEach(marker -> AtlasAPI.getMarkerAPI().deleteMarker(world, atlasID, marker.getId()));

                AtlasAPI.getMarkerAPI().putMarker(world, true, atlasID, "antiqueatlas:anvil", "Whiteshirt", villX, villZ);
            }
        }
    }

    public static KeyBinding librarianKey;

    public static void initKeybind() {
        librarianKey = new KeyBinding("key.aaam.librarian", Keyboard.KEY_NUMPAD4, "key.aaam.librarian");
        ClientRegistry.registerKeyBinding(librarianKey);
    }
}
