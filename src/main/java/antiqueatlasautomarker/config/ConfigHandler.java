package antiqueatlasautomarker.config;

import antiqueatlasautomarker.Tags;
import antiqueatlasautomarker.config.folders.*;
import meldexun.betterconfig.api.BetterConfig;
import meldexun.betterconfig.api.BetterConfigManager;
import meldexun.betterconfig.api.LoadEarly;
import meldexun.betterconfig.api.Order;
import meldexun.betterconfig.api.tree.IConfigCategory;
import meldexun.betterconfig.api.tree.IConfigContext;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.maven.artifact.versioning.ArtifactVersion;

@BetterConfig(
		modid = Tags.MODID,
		version = Tags.CFG_VERSION,
		bigCategoryComments = false
)
@LoadEarly
public class ConfigHandler {
	@Config.Comment("Settings for various structures to automark")
	@Config.Name("Auto Marking")
	@Order(0) public static AutoMarkConfig automark = new AutoMarkConfig();

	@Config.Comment("Modifications for Quality of life")
	@Config.Name("Antique Atlas Tweaks")
	@Order(1) public static TweakConfig tweaks = new TweakConfig();

	@Config.Comment("Bug fixes and performance")
	@Config.Name("Antique Atlas Fixes")
	@Order(2) public static FixConfig fixes = new FixConfig();

	@Config.Comment("Various modifications of which atlas tiles are used when")
	@Config.Name("Antique Atlas Tiles")
	@Order(3) public static TileConfig tiles = new TileConfig();

	@Config.Comment("Internal Config")
	@Config.Name("AAAM Internals")
	@Order(4) public static InternalConfig internal = new InternalConfig();

	@SuppressWarnings("unused")
	@Mod.EventBusSubscriber
	private static class EventHandler{
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(Tags.MODID))
				BetterConfigManager.sync(Tags.MODID);
		}
	}

	@SuppressWarnings("unused")
	@LoadEarly.Callback
	public static void afterEarlyLoad(){
		MixinConfigurations.enqueueMixins();
	}

	@SuppressWarnings("unused")
	@BetterConfig.AfterRead
	public static <T extends IConfigContext<T>> void migrateConfigs(IConfigCategory<T> category, T context, ArtifactVersion version){
		ConfigMigrator.handleMigration(category, context, version);
	}
}