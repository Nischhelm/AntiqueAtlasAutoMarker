package antiqueatlasautomarker.mixin.automark.vanilla;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Collections;
import java.util.Map;

@Mixin(MapGenStructureIO.class)
public interface MapGenStructureIOAccessor {
    @Accessor("startNameToClassMap")
    static Map<String, Class<? extends StructureStart>> getStartNameToClassMap() { return Collections.emptyMap(); }
}
