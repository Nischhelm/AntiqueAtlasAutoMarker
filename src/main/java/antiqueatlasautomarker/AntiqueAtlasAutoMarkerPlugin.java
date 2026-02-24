package antiqueatlasautomarker;

import antiqueatlasautomarker.config.betterconfigreader.ConfigCategory;
import antiqueatlasautomarker.config.betterconfigreader.ConfigurationLoader;
import antiqueatlasautomarker.compat.IceAndFireUtil;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.config.EarlyConfigReader;
import antiqueatlasautomarker.config.MixinConfiguration;
import fermiumbooter.FermiumRegistryAPI;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class AntiqueAtlasAutoMarkerPlugin implements IFMLLoadingPlugin {

	private static final List<MixinConfiguration> configs = new ArrayList<>();

	public AntiqueAtlasAutoMarkerPlugin() {
		MixinBootstrap.init();

		configs.add(new MixinConfiguration("mixins.aaam.battletowers.json", "battletowers", "general.Auto Marking.Battletowers.Enabled", true));
		configs.add(new MixinConfiguration("mixins.aaam.doomlikedungeons.json", "dldungeonsjbg", "general.Auto Marking.Doomlike Dungeons.Enabled", true));
		configs.add(new MixinConfiguration("mixins.aaam.dungeons2.json", "dungeons2", "general.Auto Marking.Dungeons2.Enabled", true));
		configs.add(new MixinConfiguration("mixins.aaam.quark.json", "quark", "general.Auto Marking.Quark Pirateship.Enabled", true));
		configs.add(new MixinConfiguration("mixins.aaam.bettermineshafts.json", "bettermineshafts", "general.Auto Marking.Better Mineshafts.Enabled", false));
		configs.add(new MixinConfiguration("mixins.aaam.lycanitesmobs.json", "lycanitesmobs", "general.Auto Marking.Lycanites Dungeons.Enabled", true));
		configs.add(new MixinConfiguration("mixins.aaam.waystones.wild.json", "waystones", "general.Auto Marking.Waystones.Wild Waystones.Enabled", false));
		configs.add(new MixinConfiguration("mixins.aaam.waystones.activated.json", "waystones", "general.Auto Marking.Waystones.Activated Waystones.Enabled", true));

		try{
			ConfigCategory cat = ConfigurationLoader.load(new File("config", AntiqueAtlasAutoMarker.MODID + ".cfg"));
			configs.removeIf(cfg -> !cfg.isEnabled(cat));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		configs.forEach(MixinConfiguration::enqueue);

		//Vanilla
		FermiumRegistryAPI.enqueueMixin(false, "mixins.aaam.vanilla.json");
		//FermiumRegistryAPI.enqueueMixin(false, "mixins.aaam.forge.json");
		//Antique Atlas Structure Markers
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.structuremarkers.json");

		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.overhaul.updateside.json", () -> !EarlyConfigReader.getString("Atlas Scanning Update Side", ConfigHandler.fixes.updateSide.toString()).equals("DISABLE_MIXIN"));

		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.overhaul.structurewatchers.json"); //TODO add toggle
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.customvillagetiles.json"); //TODO add toggle
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.autobiomerules.json", () -> EarlyConfigReader.isArrayFilled("Automatic Biometype Rules", !ConfigHandler.tiles.automaticTypeRules.isEmpty()));
		FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.stitchtonull.json", () -> EarlyConfigReader.isArrayFilled("TextureSets stitch to null", !ConfigHandler.tiles.stitchToNullSets.isEmpty()));
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