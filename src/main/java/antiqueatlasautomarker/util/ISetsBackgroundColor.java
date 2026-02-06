package antiqueatlasautomarker.util;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface ISetsBackgroundColor {
    void aaam$setBackgroundColor(int outer, int inner);
}
