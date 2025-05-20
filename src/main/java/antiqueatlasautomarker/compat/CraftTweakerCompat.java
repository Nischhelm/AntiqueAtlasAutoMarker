package antiqueatlasautomarker.compat;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.structuremarkers.event.MarkStructureEvent;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("unused")
@ZenRegister
@ZenClass(AntiqueAtlasAutoMarker.MODID+".StructureMarker")
public class CraftTweakerCompat {
    /**
     * Puts a structure marker at the specified location in the Structure Marker list for people to discover
     *
     * @param dimension dimension ID (0 for overworld)
     * @param x coordinate of the structure
     * @param z coordinate of the structure
     * @param markerType valid marker types can be found in config/antiqueatlas/markers.json
     * @param markerLabel appears when hovering over the marker
     * Example:
     * <pre>
     *     StructureMarker.markStructure(0, 100, 200, "diamond", "Some Structure");
     * </pre>
     */
    @ZenMethod
    public static void markStructure(int dimension, int x, int z, String markerType, String markerLabel) {
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) return;
        World world = DimensionManager.getWorld(dimension);
        if (world == null) {
            CraftTweakerAPI.logWarning("World for dimension " + dimension + " is not loaded.");
            return;
        }
        MinecraftForge.EVENT_BUS.post(new MarkStructureEvent(world, x, z, markerType, markerLabel));
    }
}
