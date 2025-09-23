package antiqueatlasautomarker.structuremarkers;

import hunternif.mc.atlas.marker.MarkersData;

public class CustomPosition {
    public int bigChunkX;
    public int bigChunkZ;

    public CustomPosition(int x, int z) {
        //AA Big Chunk units (8x8 chunks, weird truncation at x = 0 or z = 0)
        this.bigChunkX = (x >> 4) / MarkersData.CHUNK_STEP;
        this.bigChunkZ = (z >> 4) / MarkersData.CHUNK_STEP;
    }

    public CustomPosition(){}

    public CustomPosition setBigChunkCoords(int bigX, int bigZ){
        this.bigChunkX = bigX;
        this.bigChunkZ = bigZ;
        return this;
    }


    //-------------- THREAD LOCAL ------------- //

    private static final ThreadLocal<CustomPosition> customPositionThreadLocal = ThreadLocal.withInitial(() -> null);

    public static CustomPosition get() {
        return customPositionThreadLocal.get();
    }

    public static void clear(){
        customPositionThreadLocal.remove();
    }

    public static boolean isEmpty() {
        return customPositionThreadLocal.get() == null;
    }

    /**
     * Sets the threadlocal position to the given blockpos coordinates
     */
    public static void set(int x, int z) {
        customPositionThreadLocal.set(new CustomPosition(x, z));
    }
}
