package antiqueatlasautomarker.mixin.battletowers;

import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import atomicstryker.battletowers.common.AS_TowerDestroyer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AS_TowerDestroyer.class)
public class AS_TowerDestroyerMixin {
    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void removeMarker(World world, BlockPos coords, long time, Entity golemkiller, CallbackInfo ci){
        StructureMarkersDataHandler.removeStructureMarker(world, "battleTower",coords, 1);
    }
}
