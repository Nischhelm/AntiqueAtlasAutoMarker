package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.config.AutoMarkSetting;
import antiqueatlasautomarker.mixin.vanilla.MapGenStructureIOAccessor;
import net.minecraftforge.common.config.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StructuresConfig {
    @Config.Comment("Pattern: structureName; enabled; marker label; marker type. Automatically fills with entries after restart.")
    @Config.Name("Structure Markers")
    public String[] structureOptions = {};

    public void postInit(){
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
