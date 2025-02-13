package antiqueatlasautomarker.mixin.doomlikedungeons;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.util.MarkerUtil;
import jaredbgreat.dldungeons.planner.Dungeon;
import jaredbgreat.dldungeons.themes.Theme;
import jaredbgreat.dldungeons.util.cache.Coords;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Dungeon.class)
public class BuilderMixin {
    @Shadow(remap = false) public Theme theme;

    @Inject(method = "<init>(Lnet/minecraft/world/World;Ljaredbgreat/dldungeons/util/cache/Coords;)V", at = @At(value = "TAIL"), remap = false)
    private void markDoomlike(World world, Coords coords, CallbackInfo ci) {
        AutoMarkSetting setting = AutoMarkSetting.get("doomlikeDungeon");

        if (setting != null && setting.enabled) {
            String label = setting.label;
            if (label.equals("DEFAULT")){
                label = this.theme.name;
                //remove .cfg and turn firsts letter to upper case
                label = label.substring(0,1).toUpperCase() + label.substring(1, label.length()-4)+ " Doomlike Dungeon";
            }

            MarkerUtil.markStructure(
                    world,
                    new BlockPos(coords.getX() << 4, 0, coords.getZ() << 4),
                    setting.dist,
                    setting.type,
                    label
            );
        }
    }
}
