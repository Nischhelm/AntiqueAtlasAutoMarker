package antiqueatlasautomarker.mixin.waystones;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.compat.WaystoneUtil;
import com.llamalad7.mixinextras.sugar.Local;
import hunternif.mc.atlas.api.AtlasAPI;
import net.blay09.mods.waystones.block.BlockWaystone;
import net.blay09.mods.waystones.block.TileWaystone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(BlockWaystone.class)
public abstract class BlockWaystoneMixin {
    @ModifyArgs(
            method = "activateWaystone",
            at = @At(value = "INVOKE", target = "Lnet/blay09/mods/waystones/util/WaystoneActivatedEvent;<init>(Ljava/lang/String;Lnet/minecraft/util/math/BlockPos;I)V"),
            remap = false
    )
    private void aaam_markActivatedWaystone(Args args, @Local(argsOnly = true) EntityPlayer player, @Local(argsOnly = true) World world) {
        AutoMarkSetting setting = AutoMarkSetting.get("activatedWaystone");
        if (setting == null || !setting.enabled) return;

        String waystoneName = args.get(0);
        BlockPos waystonePos = args.get(1);

        String label = setting.label.equals("DEFAULT") ? waystoneName : setting.label;

        //Search for atlases in player inventory and mark waystone
        for (int atlasID : AtlasAPI.getPlayerAtlases(player))
            AtlasAPI.getMarkerAPI().putMarker(world, true, atlasID, setting.type, label, waystonePos.getX(), waystonePos.getZ());
    }

    @Inject(
            method = "onBlockActivated",
            at = @At(value = "INVOKE", target = "Lnet/blay09/mods/waystones/CommonProxy;openWaystoneSelection(Lnet/minecraft/entity/player/EntityPlayer;Lnet/blay09/mods/waystones/WarpMode;Lnet/minecraft/util/EnumHand;Lnet/blay09/mods/waystones/util/WaystoneEntry;)V", remap = false)
    )
    private void aaam_renameWaystoneMarker(World world, BlockPos waystonePos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir, @Local TileWaystone tile) {
        WaystoneUtil.updateRenamedWaystoneMarker(player, world, waystonePos, tile.getWaystoneName());
    }
}
