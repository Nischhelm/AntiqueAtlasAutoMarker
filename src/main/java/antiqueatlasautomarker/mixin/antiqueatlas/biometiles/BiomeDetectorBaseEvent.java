package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import antiqueatlasautomarker.event.BiomeDetectorEvent;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.core.BiomeDetectorBase;
import hunternif.mc.atlas.ext.ExtTileIdMap;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.Comparator;
import java.util.Map;

@Mixin(BiomeDetectorBase.class)
public abstract class BiomeDetectorBaseEvent {
    @Shadow(remap = false) @Final private static int waterPoolBiomeID;

    @ModifyReturnValue(
            method = "getBiomeID",
            at = @At("RETURN"),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Ljava/util/Collections;max(Ljava/util/Collection;Ljava/util/Comparator;)Ljava/lang/Object;"),
                    to = @At(value = "FIELD", target = "Lnet/minecraft/init/Biomes;DEFAULT:Lnet/minecraft/world/biome/Biome;")
            )
    )
    private int aaam_useEventToModifyReturnValue(
            int original,
            Chunk chunk,
            @Local(name = "lavaOccurrences") int lavaOccurences, //complains but works
            @Local(name = "ravineOccurences") int ravineOccurences,
            @Local(name = "meanBiomeOccurrences") int meanBiomeOccurrences,
            @Local(name = "biomeOccurrences") Map<Integer, Integer> biomeOccurrences,
            @Local(name = "meanBiomeId") int meanBiomeId
    ){
        BiomeDetectorEvent event = new BiomeDetectorEvent(chunk, original);
        event.setCountAndIdFor("lava", ExtTileIdMap.instance().getPseudoBiomeID("lava"), lavaOccurences);
        event.setCountAndIdFor("water", waterPoolBiomeID, biomeOccurrences.getOrDefault(waterPoolBiomeID, 0));
        event.setCountAndIdFor("ravine", ExtTileIdMap.instance().getPseudoBiomeID("ravine"), ravineOccurences);
        if(meanBiomeId == waterPoolBiomeID){
            //Special case since water is its own biome
            meanBiomeId = biomeOccurrences.entrySet().stream().filter(entry -> entry.getKey() != waterPoolBiomeID).max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
            meanBiomeOccurrences = biomeOccurrences.get(meanBiomeId);
        }
        event.setCountAndIdFor("biome", meanBiomeId, meanBiomeOccurrences);

        MinecraftForge.EVENT_BUS.post(event);
        return event.getChosenBiomeId();
    }
}
