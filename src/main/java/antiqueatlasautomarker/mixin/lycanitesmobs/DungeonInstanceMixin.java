package antiqueatlasautomarker.mixin.lycanitesmobs;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.ConfigProvider;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.lycanitesmobs.core.dungeon.definition.DungeonSchematic;
import com.lycanitesmobs.core.dungeon.instance.DungeonInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DungeonInstance.class)
public class DungeonInstanceMixin {
    @Shadow(remap = false) public DungeonSchematic schematic;
    @Shadow(remap = false) public BlockPos originPos;

    @Inject(
            method = "buildChunk",
            at = @At(value = "FIELD", target = "Lcom/lycanitesmobs/core/dungeon/instance/DungeonInstance;complete:Z", ordinal = 1),
            remap = false
    )
    private void markLycaDungeon(World world, ChunkPos chunkPos, CallbackInfo ci){
        AutoMarkSetting setting = AutoMarkSetting.get("lycanite");

        if(setting != null && setting.enabled) {
            String label = setting.label;
            if(label.equals("DEFAULT")) {
                label = this.schematic.name;
                if (ConfigProvider.lycaDungeonNames.containsKey(label))
                    label = ConfigProvider.lycaDungeonNames.get(label);
            }
            StructureMarkersDataHandler.markStructure(
                    world,
                    originPos,
                    setting.type,
                    label,
                    setting.context
            );
        }
    }
}
