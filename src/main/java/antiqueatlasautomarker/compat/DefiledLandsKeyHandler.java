package antiqueatlasautomarker.compat;

import antiqueatlasautomarker.config.ConfigHandler;
import hunternif.mc.atlas.AntiqueAtlasMod;
import hunternif.mc.atlas.api.AtlasAPI;
import hunternif.mc.atlas.marker.Marker;
import hunternif.mc.atlas.marker.MarkersData;
import hunternif.mc.atlas.registry.MarkerRegistry;
import lykrast.defiledlands.common.entity.passive.EntityBookWyrm;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefiledLandsKeyHandler {

    public static KeyBinding bookwyrmKey;

    public static void initKeybind() {
        bookwyrmKey = new KeyBinding(
                "key.aaam.goldenbookwyrm",
                KeyConflictContext.UNIVERSAL,
                KeyModifier.CONTROL,
                Keyboard.KEY_W,
                "key.antiqueatlas.category"
        );
        ClientRegistry.registerKeyBinding(bookwyrmKey);

        MinecraftForge.EVENT_BUS.register(DefiledLandsKeyHandler.class);
    }

    @SubscribeEvent
    public static void onKeyPressed(InputEvent.KeyInputEvent event){
        if(!bookwyrmKey.isKeyDown() && !bookwyrmKey.isPressed()) return;

        EntityPlayer player = Minecraft.getMinecraft().player;
        List<Integer> atlases = AtlasAPI.getPlayerAtlases(player);
        if(atlases.isEmpty()) return;

        World world = Minecraft.getMinecraft().world;
        String markerType = MarkerRegistry.hasKey(ConfigHandler.defiledlands.goldenBookwyrmMarker) ? ConfigHandler.defiledlands.goldenBookwyrmMarker : "antiqueatlas:red_x_small";
        List<EntityBookWyrm> bookWyrms = world.getEntities(EntityBookWyrm.class, e -> true).stream()
                .filter(EntityBookWyrm::isGolden)
                .collect(Collectors.toList());

        for (EntityBookWyrm bookWyrm : bookWyrms) {
            int posX = (int) bookWyrm.posX;
            int posZ = (int) bookWyrm.posZ;
            for(int atlasID : atlases) {
                List<Marker> markersAtChunk = AntiqueAtlasMod.markersData
                        .getMarkersData(atlasID, player.world)
                        .getMarkersDataInDimension(player.dimension)
                        .getMarkersAtChunk((posX >> 4) / MarkersData.CHUNK_STEP, (posZ >> 4) / MarkersData.CHUNK_STEP);
                if(markersAtChunk == null) continue;
                markersAtChunk.stream()
                        .filter(marker -> Math.abs(marker.getX() - posX) < 32 && Math.abs(marker.getZ() - posZ) < 32)
                        .filter(marker -> marker.getType().equals(markerType))
                        .filter(marker -> marker.getLabel().equals(ConfigHandler.defiledlands.goldenBookwyrmLabel))
                        .forEach(marker -> AtlasAPI.getMarkerAPI().deleteMarker(world, atlasID, marker.getId()));

                AtlasAPI.getMarkerAPI().putMarker(world, true, atlasID, markerType, ConfigHandler.defiledlands.goldenBookwyrmLabel, posX, posZ);
            }
        }
    }
}
