package antiqueatlasautomarker.mixin.antiqueatlas.overhaul.structurewatcher;

import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.compat.OpenTerrainGeneratorCompat;
import antiqueatlasautomarker.util.StructureWatcherReference;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(MapGenStructure.class)
public abstract class MapGenStructureMixin {

    @Unique private boolean aaam$isInCorrectChunk = false;
    @Unique private World aaam$savedWorld = null;

    @Inject(
            method = "generateStructure",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/structure/MapGenStructure;setStructureStart(IILnet/minecraft/world/gen/structure/StructureStart;)V")
    )
    private void aaam_saveCorrectChunk(World worldIn, Random randomIn, ChunkPos chunkCoord, CallbackInfoReturnable<Boolean> cir, @Local StructureStart start){
        if(chunkCoord.x == start.getChunkPosX() && chunkCoord.z == start.getChunkPosZ()) {
            this.aaam$isInCorrectChunk = true;
            this.aaam$savedWorld = worldIn;
        }
    }

    @Inject(method = "generateStructure", at = @At(value = "TAIL"))
    private void aaam_resetCache(World worldIn, Random randomIn, ChunkPos chunkCoord, CallbackInfoReturnable<Boolean> cir){
        this.aaam$isInCorrectChunk = false;
        this.aaam$savedWorld = null;
    }

    @ModifyExpressionValue(
            method = "setStructureStart",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/structure/StructureStart;writeStructureComponentsToNBT(II)Lnet/minecraft/nbt/NBTTagCompound;")
    )
    private NBTTagCompound aaam_invokeStructureWatchers(NBTTagCompound tags, @Local(argsOnly = true) StructureStart start){
        if(!this.aaam$isInCorrectChunk || this.aaam$savedWorld == null) return tags;
        MapGenStructure structGen = (MapGenStructure) (Object) this;
        if(structGen instanceof MapGenVillage || (ModCompat.isOTGLoaded() && OpenTerrainGeneratorCompat.isOTGVillage(structGen)))
            ((StructureWatcherVillageAccessor )StructureWatcherReference.villageWatcher).invokeVisitVillage(this.aaam$savedWorld, tags);
        else if(structGen instanceof MapGenNetherBridge)
            ((StructureWatcherFortressAccessor) StructureWatcherReference.fortressWatcher).invokeVisitFortress(this.aaam$savedWorld, tags);
        else if(structGen instanceof MapGenEndCity)
            ((StructureWatcherGenericAccessor) StructureWatcherReference.endCityWatcher).invokeVisit(this.aaam$savedWorld, tags);
        return tags;
    }
}
