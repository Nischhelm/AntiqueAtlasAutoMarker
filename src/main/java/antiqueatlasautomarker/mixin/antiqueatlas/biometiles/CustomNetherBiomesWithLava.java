package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import hunternif.mc.atlas.core.BiomeDetectorNether;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BiomeDetectorNether.class)
public abstract class CustomNetherBiomesWithLava {
    @Shadow(remap = false) @Final private static int priorityLava;
    @Shadow(remap = false) @Final private static int airProbeLevel;
    @Shadow(remap = false) @Final private static int lavaSeaLevel;

    @ModifyExpressionValue(
            method = "getBiomeID",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/core/BiomeDetectorNether;priorityForBiome(Lnet/minecraft/world/biome/Biome;)I"),
            remap = false
    )
    private int aaam_countVoidForCustomEndBiomes(
            int original,
            Chunk chunk, //target method param
            @Local(name = "groundOccurences") LocalIntRef groundOccurences,
            @Local(name = "lavaOccurences") LocalIntRef lavaOccurences,
            @Local(name = "hellID") int hellID,
            @Local(name = "biomeOccurrences") int[] biomeOccurrences,
            @Local(name = "x") int x,
            @Local(name = "z") int z
    ){
        Block netherBlock = chunk.getBlockState(x, lavaSeaLevel, z).getBlock();
        if (netherBlock == Blocks.LAVA) {
            lavaOccurences.set(lavaOccurences.get() + priorityLava);
            return 0;
        } else {
            netherBlock = chunk.getBlockState(x, airProbeLevel, z).getBlock();
            if (netherBlock == Blocks.AIR){
                groundOccurences.set(groundOccurences.get() + original * 2); //count ground = not lava & not in wall
                return original; //count the actual modded nether biome if we're not in a wall
            }
            else {
                biomeOccurrences[hellID]++; //count cave walls which is registered for Hell biome for whatever reason
                return 0; //don't show custom biome if in a wall
            }
        }
    }

    @ModifyExpressionValue(
            method = "getBiomeID",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/ext/ExtTileIdMap;getPseudoBiomeID(Ljava/lang/String;)I", ordinal = 1),
            remap = false
    )
    private int aaam_countLavaForCustomNetherBiomes(
            int original,
            @Local(name = "meanBiomeId") int meanBiomeId,
            @Local(name = "hellID") int hellID
    ){
        if(meanBiomeId == hellID) return original;
        return meanBiomeId;
    }
}
