package antiqueatlasautomarker;

import antiqueatlasautomarker.config.MixinConfigurations;
import net.minecraftforge.fml.relauncher.CoreModManager;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class AntiqueAtlasAutoMarkerPlugin implements IFMLLoadingPlugin {

    public AntiqueAtlasAutoMarkerPlugin() {
        MixinConfigurations.enqueueMixins();
    }

    @Override public void injectData(Map<String, Object> data) {
        if (Boolean.FALSE.equals(data.get("runtimeDeobfuscationEnabled"))) {
            MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
            CoreModManager.getReparseableCoremods().removeIf(s -> StringUtils.containsIgnoreCase(s, "fermiumbooter"));
        }
    }
    @Override public String[] getASMTransformerClass() {return new String[0];}
    @Override public String getModContainerClass() {return null;}
    @Override public String getSetupClass() {return null;}
    @Override public String getAccessTransformerClass() {return null;}
}