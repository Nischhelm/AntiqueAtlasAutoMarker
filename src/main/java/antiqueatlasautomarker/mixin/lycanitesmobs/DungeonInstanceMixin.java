package antiqueatlasautomarker.mixin.lycanitesmobs;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import com.lycanitesmobs.core.dungeon.definition.DungeonSchematic;
import com.lycanitesmobs.core.dungeon.instance.DungeonInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(DungeonInstance.class)
public class DungeonInstanceMixin {
    @Unique private static final Map<String, String> aaam$lycaDungeonNames = new HashMap<>();
    static {
        aaam$lycaDungeonNames.put("aberrantstation", "gui.aaam.marker.lycaAberrantStation");
        aaam$lycaDungeonNames.put("aberrantstation_random", "gui.aaam.marker.lycaAberrantStationRandom");
        aaam$lycaDungeonNames.put("ashenmausoleum", "gui.aaam.marker.lycaAshenMausoleum");
        aaam$lycaDungeonNames.put("demonictemple", "gui.aaam.marker.lycaDemonicTemple");
        aaam$lycaDungeonNames.put("desertcrypts", "gui.aaam.marker.lycaDesertCrypts");
        aaam$lycaDungeonNames.put("lushtomb", "gui.aaam.marker.lycaLushTomb");
        aaam$lycaDungeonNames.put("shadowlabyrinth", "gui.aaam.marker.lycaShadowLabyrinth");
        aaam$lycaDungeonNames.put("shadowlabyrinth_random", "gui.aaam.marker.lycaShadowLabyrinthRandom");
        aaam$lycaDungeonNames.put("streamshrine", "gui.aaam.marker.lycaStreamShrine");
    }

    @Shadow(remap = false) public DungeonSchematic schematic;
    @Shadow(remap = false) public BlockPos originPos;
    @Shadow(remap = false) public int chunksBuilt;

    @Inject(method = "buildChunk", at = @At("HEAD"), remap = false)
    private void aaam$markLycanitesDungeon(World world, ChunkPos chunkPos, CallbackInfo ci) {
        if (this.chunksBuilt != 0) return;
        AutoMarkSetting setting = AutoMarkSetting.get("lycanite");
        if (setting != null && setting.enabled) {
            String label = setting.label;
            if ("DEFAULT".equals(label)) {
                label = this.schematic.name;
                if (aaam$lycaDungeonNames.containsKey(label)) {
                    label = aaam$lycaDungeonNames.get(label);
                }
            }
            StructureMarkersDataHandler.markStructure(
                    world,
                    this.originPos,
                    setting.type,
                    label,
                    setting.context
            );
        }

    }
   
}
