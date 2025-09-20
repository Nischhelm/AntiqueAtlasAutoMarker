package antiqueatlasautomarker.compat;

import com.pg85.otg.forge.generator.structure.OTGVillageGen;
import net.minecraft.world.gen.structure.MapGenStructure;

public class OpenTerrainGeneratorCompat {
    public static boolean isOTGVillage(MapGenStructure structure){
        return structure instanceof OTGVillageGen;
    }
}
