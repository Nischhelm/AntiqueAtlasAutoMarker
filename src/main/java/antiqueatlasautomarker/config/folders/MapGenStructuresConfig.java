package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.mixin.vanilla.MapGenStructureIOAccessor;
import fermiumbooter.annotations.MixinConfig;
import meldexun.betterconfig.ConfigCategory;
import meldexun.betterconfig.ConfigurationManger;
import meldexun.betterconfig.api.Unmodifiable;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.*;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class MapGenStructuresConfig {
    @Config.Comment("Set to false to fully disable vanilla MapGenStructure marker system")
    @Config.Name("Marking Enabled")
    @MixinConfig.MixinToggle(earlyMixin = "mixins.aaam.vanilla.structures.json", defaultValue = false)
    @Config.RequiresMcRestart
    public boolean enabled = false;

    @Config.Comment("Automatically fills with entries after restart.")
    @Config.Name("Markers")
    public Map<String, AutoMarkSetting.Data> structureOptions = new HashMap<>();

    public void postInit(){
        if(!enabled) return;

        //gather all registered structures and put them in the config list
        int nStructs = structureOptions.size();
        for(String s : MapGenStructureIOAccessor.getMap().keySet())
            if (!structureOptions.containsKey(s))
                structureOptions.put(s, new AutoMarkSetting.Data(s, false, "antiqueatlas:google", "DEFAULT"));

        if(structureOptions.size() - nStructs > 0) //if structures were added
            AntiqueAtlasAutoMarker.CONFIG.save();
    }
}
