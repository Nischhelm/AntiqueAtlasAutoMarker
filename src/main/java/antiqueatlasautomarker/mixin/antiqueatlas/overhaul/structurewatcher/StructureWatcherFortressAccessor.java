package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.structurewatcher;

import hunternif.mc.atlas.ext.watcher.impl.StructureWatcherFortress;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StructureWatcherFortress.class)
public interface StructureWatcherFortressAccessor {
    @Invoker(value = "visitFortress",remap = false)
    void invokeVisitFortress(World world, NBTTagCompound tag);
}
