package antiqueatlasautomarker.handlers;

import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.structuremarkers.StructureMarkersDataHandler;
import atomicstryker.ruins.common.EventRuinTemplateSpawn;
import atomicstryker.ruins.common.RuinTemplate;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RuinsHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRuinsGeneration(EventRuinTemplateSpawn event){
        if(event.isPrePhase) return;
        if(event.isCanceled()) return;
        AutoMarkSetting generalSetting = AutoMarkSetting.get("ruins");
        if(generalSetting == null || !generalSetting.enabled) return;

        RuinTemplate template = event.template;
        String name = template.getName().replace(".tml","");
        BlockPos pos = new BlockPos(event.x, 0, event.z);

        AutoMarkSetting ruinsSetting = AutoMarkSetting.get("ruins_"+name);
        if(ruinsSetting == null || !ruinsSetting.enabled) return;

        String usedLabel = ruinsSetting.label;
        //DEFAULT labels with lang key using the structure name
        if(usedLabel.equals("DEFAULT")) usedLabel = "gui.aaam.marker."+name;

        StructureMarkersDataHandler.markStructure(
                event.getWorld(),
                pos,
                ruinsSetting.type,
                usedLabel,
                ruinsSetting.context
        );
    }
}
