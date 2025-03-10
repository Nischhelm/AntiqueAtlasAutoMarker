package antiqueatlasautomarker.mixin.ruins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "atomicstryker.ruins.common.RuinGenerator")
public abstract class RuinGeneratorMixin {
    @WrapOperation(
            method = "createBuilding",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBiomeForCoordsBody(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/world/biome/Biome;"),
            remap = false
    )
    private Biome rerouteRuinsBiomes(World world, BlockPos pos, Operation<Biome> original){
        if(world.provider.getDimension() == 1) return Biomes.SKY;
        if(world.provider.getDimension() == -1) return Biomes.HELL;
        return original.call(world, pos);
    }
}
