package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import hunternif.mc.atlas.core.BiomeDetectorEnd;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
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
}
