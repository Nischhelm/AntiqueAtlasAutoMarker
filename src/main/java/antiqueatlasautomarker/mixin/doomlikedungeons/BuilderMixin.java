package antiqueatlasautomarker.mixin.doomlikedungeons;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.llamalad7.mixinextras.sugar.Local;
import jaredbgreat.dldungeons.builder.Builder;
import jaredbgreat.dldungeons.planner.Dungeon;
import jaredbgreat.dldungeons.util.cache.Coords;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Builder.class)
public class BuilderMixin {
    @Unique private static Dungeon aaam$savedDungeon = null;

    @Inject(method = "buildDungeonChunk", at = @At(value = "TAIL"), remap = false)
    private static void saveLastDungeonLol(int cx, int cz, Coords dc, World world, CallbackInfo ci, @Local Dungeon dungeon){
        aaam$savedDungeon = dungeon;
    }

    @Inject(method = "buildDungeonsChunk", at = @At(value = "TAIL"), remap = false)
    private static void markDoomlike(int cx, int cz, List<Coords> dcs, World world, CallbackInfo ci) {
        if(aaam$savedDungeon == null) return;
        AutoMarkSetting setting = AutoMarkSetting.get("doomlike");

        if (setting != null && setting.enabled) {
            String label = setting.label;
            //DEFAULT labels with lang key using the theme name (with .cfg at end of theme name removed)
            if (label.equals("DEFAULT")) label = "gui.aaam.marker.doomlike_" + aaam$savedDungeon.theme.name.replace(".cfg","");

            StructureMarkersDataHandler.markStructure(
                    world,
                    dcs.get(0).getX() << 4,
                    dcs.get(0).getZ() << 4,
                    setting.type,
                    label,
                    setting.context
            );
        }

        aaam$savedDungeon = null;
    }
}
