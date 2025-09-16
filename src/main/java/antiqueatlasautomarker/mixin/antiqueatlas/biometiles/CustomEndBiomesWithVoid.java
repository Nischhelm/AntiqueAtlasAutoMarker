package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import antiqueatlasautomarker.event.BiomeDetectorEvent;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import hunternif.mc.atlas.core.BiomeDetectorEnd;
import hunternif.mc.atlas.ext.ExtTileIdMap;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BiomeDetectorEnd.class)
public abstract class CustomEndBiomesWithVoid {
    @Inject(
            method = "getBiomeID",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/core/BiomeDetectorEnd;priorityForBiome(Lnet/minecraft/world/biome/Biome;)I"),
            remap = false
    )
    private void aaam_countVoidForCustomEndBiomes(
            Chunk chunk,
            CallbackInfoReturnable<Integer> cir,
            @Local(name = "voidOccurences") LocalIntRef voidOccurences,
            @Local(name = "x") int x,
            @Local(name = "z") int z
    ){
        int top = chunk.getHeightValue(x, z);
        Block topBlock = chunk.getBlockState(x, top - 1, z).getBlock();
        if (topBlock == Blocks.AIR) voidOccurences.set(voidOccurences.get()+2);
    }

    @ModifyVariable(
            method = "getBiomeID",
            at = @At(value = "LOAD", ordinal = 1),
            name = "meanBiomeOccurences",
            remap = false
    )
    private int aaam_storeMaxBiomeId(
            int value,
            @Local(name = "meanBiomeId") int meanBiomeId,
            @Share("maxBiomeId") LocalIntRef maxBiomeId
    ){
        maxBiomeId.set(meanBiomeId);
        return value;
    }

    @ModifyReturnValue(
            method = "getBiomeID",
            at = @At("RETURN"),
            remap = false
    )
    private int aaam_useEventToModifyReturnValue(
            int original,
            Chunk chunk,
            @Local(name = "islandOccurences") int islandOccurences,
            @Local(name = "plantOccurences") int plantOccurences,
            @Local(name = "voidOccurences") int voidOccurences,
            @Local(name = "meanBiomeOccurences") int meanBiomeOccurences,
            @Share("maxBiomeId") LocalIntRef maxBiomeId
    ){
        BiomeDetectorEvent event = new BiomeDetectorEvent(chunk, original);
        event.setCountAndIdFor("endstone", ExtTileIdMap.instance().getPseudoBiomeID("endIsland"), islandOccurences);
        event.setCountAndIdFor("chorus", ExtTileIdMap.instance().getPseudoBiomeID("endIslandPlants"), plantOccurences);
        event.setCountAndIdFor("void", ExtTileIdMap.instance().getPseudoBiomeID("endVoid"), voidOccurences);
        event.setCountAndIdFor("biome", maxBiomeId.get(), meanBiomeOccurences);

        MinecraftForge.EVENT_BUS.post(event);
        return event.getChosenBiomeId();
    }
}
