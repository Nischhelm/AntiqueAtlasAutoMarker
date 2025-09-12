package antiqueatlasautomarker.compat;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import com.pg85.otg.forge.generator.structure.OTGVillageGen;
import net.minecraft.world.gen.structure.MapGenStructure;

public class OpenTerrainGeneratorCompat {
    public static boolean isOTGVillage(MapGenStructure structure){
        AntiqueAtlasAutoMarker.LOGGER.info("Trying OTG compat and its {} for {}", structure instanceof OTGVillageGen, structure.getClass().getName());
        return structure instanceof OTGVillageGen;
    }
}
