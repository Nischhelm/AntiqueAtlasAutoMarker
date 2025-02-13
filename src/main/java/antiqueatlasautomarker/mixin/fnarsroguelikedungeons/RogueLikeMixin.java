package antiqueatlasautomarker.mixin.fnarsroguelikedungeons;

import antiqueatlasautomarker.util.StructureGenerationUtil;
import greymerk.roguelike.dungeon.Dungeon;
import greymerk.roguelike.dungeon.settings.DungeonSettings;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.WorldEditor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Dungeon.class)
public class RogueLikeMixin {
    //@Shadow(remap = false) @Final private WorldEditor editor;

    @Inject(method = "generate", at = @At(value = "TAIL"), remap = false)
    private void markRoguelike(DungeonSettings dungeonSettings, Coord coord, CallbackInfo ci){
        //StructureGenerationUtil.markStructure(
        //        this.editor.getWo.
        //);
    }
}
