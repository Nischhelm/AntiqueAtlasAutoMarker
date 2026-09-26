package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.Tags;
import antiqueatlasautomarker.config.data.AutoMarkSetting;
import antiqueatlasautomarker.mixin.vanilla.MapGenStructureIOAccessor;
import fermiumbooter.annotations.MixinConfig;
import meldexun.betterconfig.api.Unmodifiable;
import net.minecraftforge.common.config.Config;

import java.util.LinkedHashMap;
import java.util.Map;

@MixinConfig(name = Tags.MODID)
public class MapGenStructuresConfig {
    @Config.Comment({
            "Keeping this on false fully disables automarkers for vanilla-type MapGenStructures.",
            "Setting to true and restarting will fill this list with all registered MapGenStructures (vanilla and modded)."
    })
    @Config.Name("Marking Enabled")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(earlyMixin = "mixins.aaam.vanilla.structures.json", defaultValue = false)
    public boolean enabled = false;

    @Config.Comment("Cannot add or remove entries. This fills automatically after a restart with \"Marking Enabled\"=true.")
    @Config.Name("Markers")
    @Unmodifiable
    public Map<String, AutoMarkSetting.Data> structureOptions = new LinkedHashMap<>();
    public void initMapGenStructureMarkersFromRegistry(){
        // This is deliberately not ran on config init but on Mod postInit to catch as many registered MapGenStructures as possible
        if(!enabled) return;

        int nStructsBefore = structureOptions.size();
        for(String s : MapGenStructureIOAccessor.getStartNameToClassMap().keySet())
            if (!structureOptions.containsKey(s))
                structureOptions.put(s, new AutoMarkSetting.Data(s, false, "antiqueatlas:google", "DEFAULT"));

        if(structureOptions.size() > nStructsBefore) { // sync cfg to file if structures were added
            AntiqueAtlasAutoMarker.configWasChangedInternally = true;
        }
    }
}
