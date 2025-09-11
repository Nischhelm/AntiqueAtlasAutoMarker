package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.structurewatcher;

import hunternif.mc.atlas.ext.watcher.impl.StructureWatcherVillage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StructureWatcherVillage.class)
public interface StructureWatcherVillageAccessor {
    @Invoker(value = "visitVillage",remap = false)
    void invokeVisitVillage(World world, NBTTagCompound tag);
}
