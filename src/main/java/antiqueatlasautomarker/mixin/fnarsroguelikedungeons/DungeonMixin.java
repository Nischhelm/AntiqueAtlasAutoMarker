package antiqueatlasautomarker.mixin.fnarsroguelikedungeons;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import greymerk.roguelike.dungeon.Dungeon;
import greymerk.roguelike.dungeon.settings.DungeonSettings;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.WorldEditor;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Dungeon.class)
public class DungeonMixin {
    @Shadow(remap = false) @Final private WorldEditor editor;
    @Shadow(remap = false) private Coord origin;

    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", ordinal = 1), remap = false)
    private void markRoguelike(DungeonSettings dungeonSettings, Coord coord, CallbackInfo ci){
        AutoMarkSetting setting = AutoMarkSetting.get("roguelike");
        if(setting == null || !setting.enabled) return;
        World world = ((WorldEditor1_12Accessor) editor).getWorld();
        String label = setting.label;
        if(label.equals("DEFAULT")){
            String themeName = dungeonSettings.getTower().getTheme().getClass().getSimpleName().replaceFirst("Theme","");
            String themeNameLookup = ConfigHandler.roguelike.defaultThemeNames.get(themeName);
            if(themeNameLookup != null && !themeNameLookup.isEmpty()) themeName = themeNameLookup;

            String towerName = dungeonSettings.getTower().getType().toString();
            towerName = towerName.charAt(0) + towerName.substring(1).toLowerCase(); //first letter uppercase, rest lowercase
            String towerNameLookup = ConfigHandler.roguelike.defaultTowerNames.get(towerName);
            if(towerNameLookup != null && !towerNameLookup.isEmpty()) towerName = towerNameLookup;
            
            if(themeName.contains(towerName))
                label = themeName + " Roguelike Dungeon";
            else if(towerName.contains(themeName))
                label = towerName + " Roguelike Dungeon";
            else
                label = towerName + " "+ themeName + " Roguelike Dungeon";
        }
        StructureMarkersDataHandler.markStructure(world, origin.getX(), origin.getZ(), setting.type, label, "roguelike");
    }
}
