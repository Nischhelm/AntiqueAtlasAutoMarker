package antiqueatlasautomarker.mixin.fnarsroguelikedungeons;

import com.github.fnar.minecraft.WorldEditor1_12;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WorldEditor1_12.class)
public interface WorldEditor1_12Accessor {
    @Accessor(value = "world", remap = false)
    World getWorld();
}
