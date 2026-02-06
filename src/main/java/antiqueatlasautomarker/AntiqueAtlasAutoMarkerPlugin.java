package antiqueatlasautomarker;

import antiqueatlasautomarker.compat.IceAndFireUtil;
import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.config.EarlyConfigReader;
import antiqueatlasautomarker.config.folders.BiomeTileConfig;
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
		//FermiumRegistryAPI.enqueueMixin(false, "mixins.aaam.forge.json");
		//Antique Atlas Structure Markers
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.structuremarkers.json");

		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.overhaul.structurewatchers.json"); //TODO add toggle
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.customvillagetiles.json"); //TODO add toggle
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.autobiomerules.json", () -> EarlyConfigReader.isArrayFilled("Automatic Biometype Rules", ConfigHandler.overhaul.tileConfig.automaticTypeRules.length != 0));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.stitchtonull.json", () -> EarlyConfigReader.isArrayFilled("TextureSets stitch to null", ConfigHandler.overhaul.tileConfig.stitchToNullSets.length != 0));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.custom_nether_end_biomes.json"); //TODO add toggle

		//Ice and Fire
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.infrotn.json", () -> Loader.isModLoaded("iceandfire") && IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.ROTN);
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.infrl.json", () -> Loader.isModLoaded("iceandfire") && IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.RLCRAFT);
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.infbase.json", () -> Loader.isModLoaded("iceandfire") && IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.BASE_OLD);
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.infbase191.json", () -> Loader.isModLoaded("iceandfire") && IceAndFireUtil.getIceAndFireVersion() == IceAndFireUtil.IceAndFireVersion.BASE_1_9_1);
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.inf.easter.json", () -> Loader.isModLoaded("iceandfire"));

		//Dungeons
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