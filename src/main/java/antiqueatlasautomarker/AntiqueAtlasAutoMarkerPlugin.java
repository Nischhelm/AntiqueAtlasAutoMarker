package antiqueatlasautomarker;

import antiqueatlasautomarker.compat.IceAndFireUtil;
import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.config.EarlyConfigReader;
import fermiumbooter.FermiumRegistryAPI;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class AntiqueAtlasAutoMarkerPlugin implements IFMLLoadingPlugin {

	public AntiqueAtlasAutoMarkerPlugin() {
		MixinBootstrap.init();

		//Vanilla
		FermiumRegistryAPI.enqueueMixin(false, "mixins.aaam.vanilla.json");
		//Antique Atlas
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.json", () -> Loader.isModLoaded("antiqueatlas"));

		//Antique Atlas sendToAll overhaul
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.sendtoallholding.json", () ->
				Loader.isModLoaded("antiqueatlas") &&
				EarlyConfigReader.getBoolean("Only send to all holding the atlas", ConfigHandler.overhaul.sendToAllHolding)
		);
		//Lang key fix
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.sendtoallholding.json", () ->
				Loader.isModLoaded("antiqueatlas") &&
						EarlyConfigReader.getBoolean("Fix Atlas Marker Lang Keys", ConfigHandler.overhaul.fixLangKeys)
		);

		//Waystones
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.waystones.json", () -> Loader.isModLoaded("waystones"));

		//Ice and Fire
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.infrotn.json", () -> Loader.isModLoaded("iceandfire") && (IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.ROTN || IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.BASE_1_9_1));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.infrl.json", () -> Loader.isModLoaded("iceandfire") && IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.RLCRAFT);
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.infbase.json", () -> Loader.isModLoaded("iceandfire") && IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.BASE_OLD);
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.inf.easter.json", () -> Loader.isModLoaded("iceandfire"));

		//AARC Addon
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.aarc.json", ModCompat::isAARCLoaded);

		//Dungeons
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.bettermineshafts.json", () -> Loader.isModLoaded("bettermineshafts"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.dungeons2.json", () -> Loader.isModLoaded("dungeons2"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.doomlikedungeons.json", () -> Loader.isModLoaded("dldungeonsjbg"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.lycanitesmobs.json", () -> Loader.isModLoaded("lycanitesmobs"));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.battletowers.json", () -> Loader.isModLoaded("battletowers"));
		//FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.ruins.json", () -> Loader.isModLoaded("ruins"));
		//FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.roguelike.json", () -> Loader.isModLoaded("roguelike"));
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