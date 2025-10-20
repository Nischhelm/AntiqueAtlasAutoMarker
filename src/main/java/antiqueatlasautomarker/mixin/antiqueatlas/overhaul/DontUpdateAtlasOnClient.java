package antiqueatlasautomarker.mixin.antiqueatlas.overhaul;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import hunternif.mc.atlas.core.AtlasData;
import hunternif.mc.atlas.core.TileInfo;
import hunternif.mc.atlas.item.ItemAtlas;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;

@Mixin(ItemAtlas.class)
public class DontUpdateAtlasOnClient {
    @WrapOperation(
            method = "onUpdate",
            at = @At(value = "INVOKE", target = "Lhunternif/mc/atlas/core/AtlasData;updateMapAroundPlayer(Lnet/minecraft/entity/player/EntityPlayer;)Ljava/util/ArrayList;", remap = false)
    )
    private ArrayList<TileInfo> aaam_cancelOnClient(AtlasData data, EntityPlayer player, Operation<ArrayList<TileInfo>> original){
        if(player.world.isRemote) return null;
        return original.call(data, player);
    }
}
