package antiqueatlasautomarker.mixin.fnarsroguelikedungeons;

import greymerk.roguelike.dungeon.Dungeon;
import greymerk.roguelike.dungeon.settings.DungeonSettings;
import greymerk.roguelike.worldgen.Coord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Dungeon.class)
public class RogueLikeMixin {
    //@Shadow(remap = false) @Final private WorldEditor editor;

    @Inject(method = "generate", at = @At(value = "TAIL"), remap = false)
    private void markRoguelike(DungeonSettings dungeonSettings, Coord coord, CallbackInfo ci){
        //StructureMarkersDataHandler.markStructure(
        //        this.editor.getWo.
        //);
    }
}
