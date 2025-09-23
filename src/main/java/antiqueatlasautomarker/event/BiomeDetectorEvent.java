package antiqueatlasautomarker.event;

import hunternif.mc.atlas.ext.ExtTileIdMap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.ChunkEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Used to change what TextureSet tile to use for a specific chunk. Negative chosenBiomeIds signify ExtTiles
 * counts has various counts that were used to determine what tile to use.
 *  - Overworld: ravine, lava, water, biome
 *  - Nether: lava, wall, biome
 *  - End: void, endstone, chorus, biome
 * Where "biome" always stores the biome id of the biome that is most common in that chunk
 * Nether shows LAVA when there's mostly lava at y=31 and CAVE_WALLS (wall) when there's mostly full blocks (not air) at y=50
 * In The End in custom biomes "chorus" and "endstone" will always be 0
 */
public class BiomeDetectorEvent extends ChunkEvent {
    private final Map<String, Integer> counts = new HashMap<>();
    private final Map<String, Integer> ids = new HashMap<>();
    private Biome mainBiome = null;
    private final int dimension;
    private int chosenBiomeId;

    //TODO similar event for structurewatcher
    //TODO CT compat

    public BiomeDetectorEvent(Chunk chunk, int chosenBiomeId) {
        super(chunk);
        this.chosenBiomeId = chosenBiomeId;
        this.dimension = chunk.getWorld().provider.getDimension();
    }

    public void setCountAndIdFor(String type, int tileId, int count){
        ids.put(type, tileId);
        counts.put(type, count);
        if(type.equals("biome") && tileId >= 0) this.mainBiome = Biome.getBiomeForId(tileId);
    }

    public void setChosenBiomeId(int id) {
        if(id != ExtTileIdMap.NOT_FOUND)
            this.chosenBiomeId = id;
    }

    public int getChosenBiomeId() {
        return this.chosenBiomeId;
    }

    public Biome getMainBiome() {
        return this.mainBiome;
    }

    public int getCountFor(String type) {
        return this.counts.getOrDefault(type, 0);
    }

    public int getIdFor(String type) {
        return this.ids.getOrDefault(type, -1);
    }

    public Set<String> getCountTypes() {
        return this.counts.keySet();
    }

    public int getDimension() {
        return this.dimension;
    }
}
