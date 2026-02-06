package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.mixin.vanilla.MapGenStructureIOAccessor;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class StructuresConfig {
    @Config.Comment("Set to false to fully disable vanilla structure marker system")
    @Config.Name("Structures Enabled")
    @MixinConfig.MixinToggle(earlyMixin = "mixins.aaam.vanilla.structures.json", defaultValue = false)
    @Config.RequiresMcRestart
    public boolean enabled = false;

    @Config.Comment("Pattern: structureName; enabled; marker label; marker type. Automatically fills with entries after restart.")
    @Config.Name("Structure Markers")
    public String[] structureOptions = {};

    public void postInit(){
        if(!enabled) return;

        //gather all registered structures and put them in the config list
        int nStructs = structureOptions.length;
        List<String> toAdd = new ArrayList<>(Arrays.asList(structureOptions));
        for(String s : MapGenStructureIOAccessor.getMap().keySet())
            if (AutoMarkSetting.get(s) == null)
                toAdd.add(s + "; false; your label here; your marker type here");
        AntiqueAtlasAutoMarker.CONFIG.get("general.Structures","Structure Markers", structureOptions, "Pattern: structureName; enabled; marker label; marker type. Automatically fills with entries after restart.").set(toAdd.toArray(new String[0]));
        structureOptions = toAdd.toArray(new String[0]);
        if(structureOptions.length - nStructs > 0) {
            AntiqueAtlasAutoMarker.CONFIG.save();
        }
    }
}
