package antiqueatlasautomarker.mixin.iceandfire.rotn.base191;

import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityDragonBase.class)
public abstract class EntityDragonBaseMixin extends EntityLivingBase {
    @Shadow(remap = false) public BlockPos homePos;

    public EntityDragonBaseMixin(World worldIn) {
        super(worldIn);
    }

    @Inject(
            method = "onDeath",
            at = @At("HEAD")
    )
    private void removeDragonMarker(CallbackInfo ci) {
        ResourceLocation loc = EntityList.getKey(this);
        if(loc == null) return;
        String dragonType = loc.toString();
        String context = "";
        if(dragonType.equals("iceandfire:firedragon")) context = "fireDragon";
        if(dragonType.equals("iceandfire:icedragon")) context = "iceDragon";
        if(dragonType.equals("iceandfire:lightningdragon")) context = "lightningDragon";

        if(this.homePos != null)
            StructureMarkersDataHandler.removeStructureMarker(this.world, context, this.homePos, 1);
    }
}
