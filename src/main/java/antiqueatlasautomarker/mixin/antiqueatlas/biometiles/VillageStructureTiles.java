package antiqueatlasautomarker.mixin.antiqueatlas.biometiles;

import antiqueatlasautomarker.custombiometiles.CustomVillageTiles;
import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.ext.watcher.impl.StructureWatcherVillage;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(StructureWatcherVillage.class)
public abstract class VillageStructureTiles {
    @Shadow(remap = false) @Final private static Map<String, String> partToTileMap;

    @Inject(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;", ordinal = 0, remap = false)
    )
    private static void aaam_addCustomVillageTiles_Texture(CallbackInfo ci, @Local ImmutableMap.Builder<String, String> builder){
        CustomVillageTiles.registerVillageTiles();
        CustomVillageTiles.partToTileMap.forEach(builder::put);
    }

    @Inject(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;", ordinal = 1, remap = false)
    )
    private static void aaam_addCustomVillageTiles_Priority(CallbackInfo ci, @Local ImmutableMap.Builder<String, Integer> builder){
        CustomVillageTiles.tilePriority.forEach(builder::put);
    }

    @WrapOperation(
            method = "visitVillage",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NBTTagCompound;getString(Ljava/lang/String;)Ljava/lang/String;")
    )
    private String aaam_alsoCheckRecurrentIds(NBTTagCompound instance, String key, Operation<String> original){
        //For Recurrent Complex
        if(instance.hasKey("RcSId")) return original.call(instance, "RCSId");
        return original.call(instance, key);
    }
}
