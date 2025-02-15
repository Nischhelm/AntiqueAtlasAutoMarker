package antiqueatlasautomarker.mixin.dungeons2;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.llamalad7.mixinextras.sugar.Local;
import com.someguyssoftware.dungeons2.model.DungeonInfo;
import com.someguyssoftware.dungeons2.tileentity.DeferredDungeonGeneratorTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeferredDungeonGeneratorTileEntity.class)
public abstract class DungeonBuilderMixin extends TileEntity {
    @Inject(
            method = "update",
            at = @At(value = "INVOKE", target = "Lcom/someguyssoftware/dungeons2/persistence/DungeonsGenSavedData;get(Lnet/minecraft/world/World;)Lcom/someguyssoftware/dungeons2/persistence/DungeonsGenSavedData;", remap = false)
    )
    private void markDungeons2Dungeon(CallbackInfo ci, @Local DungeonInfo info){
        StructureMarkersDataHandler.markStructure(
                this.world,
                this.pos.getX(),
                this.pos.getZ(),
                AutoMarkSetting.get("dungeons2")
        );
    }
}
