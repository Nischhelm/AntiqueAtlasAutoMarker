package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import antiqueatlasautomarker.custombiometiles.NetherTiles;
import antiqueatlasautomarker.event.BiomeDetectorEvent;
import hunternif.mc.atlas.core.BiomeDetectorBase;
import hunternif.mc.atlas.core.BiomeDetectorNether;
import hunternif.mc.atlas.ext.ExtTileIdMap;
import hunternif.mc.atlas.util.ByteUtil;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.*;

import java.lang.reflect.InvocationTargetException;

/**
 * this whole class only makes sense with NetherTiles.registerTiles(), otherwise texture for Hell biome is CAVE_WALLS
 */
@Mixin(BiomeDetectorNether.class)
public abstract class CustomNetherBiomesWithLava extends BiomeDetectorBase {
    @Shadow(remap = false) @Final private static int airProbeLevel;
    @Shadow(remap = false) @Final private static int lavaSeaLevel;
    @Unique private static final int aaam$priorityBiome = 3;
    @Unique private static final int aaam$priorityLava = 4;
    @Unique private static final int aaam$priorityWall = 4;

    /**
     * @author Nischhelm
     * @reason wall and biome mixed up makes this really funky
     */
    @Overwrite(remap = false)
    public int getBiomeID(Chunk chunk) {
        int biomesCount = Biome.REGISTRY.getKeys().size();
        int[] chunkBiomes;
        try {
            chunkBiomes = ByteUtil.unsignedByteToIntArray(biomeArrayMethod.invoke(chunk));
        }
        catch (IllegalAccessException | InvocationTargetException e) { throw new RuntimeException(e); }

        int[] biomeOccurrences = new int[biomesCount];

        int lavaOccurrences = 0;
        int wallOccurrences = 0;

        int hellID = Biome.getIdForBiome(Biomes.HELL);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int biomeID = chunkBiomes[x << 4 | z];

                if (chunk.getBlockState(x, lavaSeaLevel, z).getBlock() == Blocks.LAVA)
                    lavaOccurrences += aaam$priorityLava;
                if (chunk.getBlockState(x, airProbeLevel, z).getBlock() != Blocks.AIR)
                    wallOccurrences += aaam$priorityWall;

                if(biomeID == hellID || biomeID >= 0 && biomeID < biomesCount && Biome.getBiomeForId(biomeID) != null)
                    biomeOccurrences[biomeID] += aaam$priorityBiome;
            }
        }

        int meanBiomeId = NOT_FOUND;
        int meanBiomeOccurences = 0;
        for (int i = 0; i < biomeOccurrences.length; i++) {
            if (biomeOccurrences[i] > meanBiomeOccurences) {
                meanBiomeId = i;
                meanBiomeOccurences = biomeOccurrences[i];
            }
        }

        int retValue = meanBiomeId;

        int lavaId = ExtTileIdMap.instance().getPseudoBiomeID(ExtTileIdMap.TILE_LAVA);
        if(wallOccurrences > lavaOccurrences && wallOccurrences >= meanBiomeOccurences) //mostly wall
            retValue = NetherTiles.WALL;
        else if(lavaOccurrences >= meanBiomeOccurences) //mostly lava
            retValue = lavaId;

        BiomeDetectorEvent event = new BiomeDetectorEvent(chunk, retValue);
        event.setCountAndIdFor("lava", lavaId, lavaOccurrences);
        event.setCountAndIdFor("wall", NetherTiles.WALL, wallOccurrences);
        event.setCountAndIdFor("biome", meanBiomeId, meanBiomeOccurences);

        MinecraftForge.EVENT_BUS.post(event);
        return event.getChosenBiomeId();
    }
}
