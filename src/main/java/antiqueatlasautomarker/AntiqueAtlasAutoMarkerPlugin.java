package antiqueatlasautomarker;

import antiqueatlasautomarker.util.IceAndFireUtil;
import fermiumbooter.FermiumRegistryAPI;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class AntiqueAtlasAutoMarkerPlugin implements IFMLLoadingPlugin {

	public AntiqueAtlasAutoMarkerPlugin() {
		MixinBootstrap.init();

		FermiumRegistryAPI.enqueueMixin(false, "mixins.antiqueatlasautomarker.vanilla.json");
		FermiumRegistryAPI.enqueueMixin(true, "mixins.antiqueatlasautomarker.waystones.json", () -> Loader.isModLoaded("waystones"));

		FermiumRegistryAPI.enqueueMixin(true, "mixins.antiqueatlasautomarker.iceandfirerotn.json", () -> Loader.isModLoaded("iceandfire") && (IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.ROTN || IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.BASE_1_9_1));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.antiqueatlasautomarker.iceandfirerl.json", () -> Loader.isModLoaded("iceandfire") && IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.RLCRAFT);
		FermiumRegistryAPI.enqueueMixin(true, "mixins.antiqueatlasautomarker.iceandfire.json", () -> Loader.isModLoaded("iceandfire") && IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.BASE_OLD);
	}

	@Override
	public String[] getASMTransformerClass()
	{
		return new String[0];
	}
	
	@Override
	public String getModContainerClass()
	{
		return null;
	}
	
	@Override
	public String getSetupClass()
	{
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data) { }
	
	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}