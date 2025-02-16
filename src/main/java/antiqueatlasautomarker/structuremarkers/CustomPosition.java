package antiqueatlasautomarker.structuremarkers;

import hunternif.mc.atlas.marker.MarkersData;

public class CustomPosition {
    public int bigChunkX;
    public int bigChunkZ;

    public CustomPosition(int x, int z) {
        //AA Big Chunk units (8x8 chunks)
        this.bigChunkX = (x << 4) / MarkersData.CHUNK_STEP;
        this.bigChunkZ = (z << 4) / MarkersData.CHUNK_STEP;
    }


    //-------------- THREAD LOCAL ------------- //

    private static final ThreadLocal<CustomPosition> customPositionThreadLocal = ThreadLocal.withInitial(() -> null);

    /**
     * reads and destroys the threadlocal
     */
    public static CustomPosition getAndRemove() {
        CustomPosition value = customPositionThreadLocal.get();
        customPositionThreadLocal.remove();
        return value;
    }

    /**
     * Checks if the thread local has been set recently
     */
    public static boolean isEmpty() {
        return customPositionThreadLocal.get() == null;
    }

    /**
     * Sets the threadlocal position to the given blockpos coordinates (at big chunk belonging to it
     */
    public static void set(int x, int z) {
        customPositionThreadLocal.set(new CustomPosition(x, z));
    }
}
