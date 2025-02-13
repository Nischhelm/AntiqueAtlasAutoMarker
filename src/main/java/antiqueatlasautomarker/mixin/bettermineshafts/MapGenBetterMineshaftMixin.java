package antiqueatlasautomarker.mixin.bettermineshafts;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.util.MarkerUtil;
import com.yungnickyoung.minecraft.bettermineshafts.world.MapGenBetterMineshaft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapGenBetterMineshaft.class)
public class MapGenBetterMineshaftMixin extends MapGenMineshaft {
    @Inject(method = "getStructureStart", at = @At("HEAD"))
    private void markBetterMineshaft(int chunkX, int chunkZ, CallbackInfoReturnable<StructureStart> cir){
        MarkerUtil.markStructure(
                this.world,
                new BlockPos(chunkX << 4, 0, chunkZ << 4),
                AutoMarkSetting.get("betterMineshaft")
        );
    }
}
