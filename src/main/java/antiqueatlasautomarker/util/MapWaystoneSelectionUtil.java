package antiqueatlasautomarker.util;

import net.blay09.mods.waystones.WarpMode;
import net.blay09.mods.waystones.util.WaystoneEntry;
import net.minecraft.util.EnumHand;

public class MapWaystoneSelectionUtil {
    public static final ThreadLocal<WarpProperty> warpProperty = ThreadLocal.withInitial(() -> null);
    public static WarpProperty getAndClearThreadLocal(){
        WarpProperty currentWarpProperty = warpProperty.get();
        warpProperty.remove();
        return currentWarpProperty;
    }

    public static class WarpProperty{
        public final WaystoneEntry[] entries;
        public final EnumHand hand;
        public final WarpMode mode;
        public final WaystoneEntry fromWaystone;
        public WarpProperty(WaystoneEntry[] entries, EnumHand hand, WarpMode mode, WaystoneEntry fromWaystone){
            this.entries = entries;
            this.hand = hand;
            this.mode = mode;
            this.fromWaystone = fromWaystone;
        }
    }

    public static final ThreadLocal<Boolean> atlasFromWaystone = ThreadLocal.withInitial(() -> false);
    public static boolean getIsFromWaystone(){
        return atlasFromWaystone.get();
    }
    public static void setFromWaystone(boolean isFromWaystone){
        atlasFromWaystone.set(isFromWaystone);
    }
}
