package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.structurewatcher;

import hunternif.mc.atlas.ext.watcher.impl.StructureWatcherGeneric;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StructureWatcherGeneric.class)
public interface StructureWatcherGenericAccessor {
    @Invoker(value = "visit",remap = false)
    void invokeVisit(World world, NBTTagCompound tag);
}
