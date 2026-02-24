package antiqueatlasautomarker;

import antiqueatlasautomarker.command.AAAMCommand;
import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.compat.crafttweaker.CT_BiomeDetectorEvent;
import antiqueatlasautomarker.config.ConfigHandler;
import antiqueatlasautomarker.config.folders.TileConfig;
import antiqueatlasautomarker.custombiometiles.*;
import antiqueatlasautomarker.displayotherplayers.OtherPlayersDataHandler;
import antiqueatlasautomarker.handlers.PlayerLogoutHandler;
import antiqueatlasautomarker.handlers.RuinsHandler;
import antiqueatlasautomarker.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = Tags.MODID,
        version = Tags.VERSION,
        name = Tags.NAME,
        dependencies =
                "required-after:fermiumbooter@[1.5.2,);" +
                "required-after:antiqueatlas;" +
                "required:betterconfig@[1.3.0,)",
        acceptableRemoteVersions = "*"
)
public class AntiqueAtlasAutoMarker {

    @SidedProxy(clientSide = "antiqueatlasautomarker.proxy.ClientProxy", serverSide = "antiqueatlasautomarker.proxy.CommonProxy")
    public static CommonProxy PROXY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(event.getSide() == Side.CLIENT) TileConfig.init();

        if(ConfigHandler.fixes.sendToAllHolding) MinecraftForge.EVENT_BUS.register(PlayerLogoutHandler.class);
        if(Loader.isModLoaded("ruins")) MinecraftForge.EVENT_BUS.register(RuinsHandler.class);
        if(Loader.isModLoaded("crafttweaker")) MinecraftForge.EVENT_BUS.register(CT_BiomeDetectorEvent.CT_EventForwarder.class);
        PROXY.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        if(ConfigHandler.tweaks.showOtherPlayers && event.getSide() == Side.CLIENT) MinecraftForge.EVENT_BUS.register(OtherPlayersDataHandler.class);
        PROXY.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ConfigHandler.automark.mapGenStructs.postInit();

        if(event.getSide() == Side.CLIENT) {
            if (ConfigHandler.tiles.modifyBiomeDetection) NetherTiles.registerTiles();
            if (ConfigHandler.tiles.useCustomMarkers) CustomMarkers.registerMarkers();
            if (ConfigHandler.tiles.useColorisedBetterEndTiles && ModCompat.betterEnd.isLoaded() && Loader.isModLoaded("nether_api")) BetterEndCompat.registerTiles();
            if (ConfigHandler.tiles.useColorisedBetterNetherTiles && Loader.isModLoaded("betternether") && Loader.isModLoaded("nether_api")) BetterNetherCompat.registerTiles();
            if (ConfigHandler.tiles.useColorisedDefiledLandsTiles && Loader.isModLoaded("defiledlands")) DefiledLandsCompat.registerTiles();
            if (ConfigHandler.tiles.useColorisedTraverseTiles && Loader.isModLoaded("traverse")) TraverseCompat.registerTiles();
            if (ConfigHandler.tiles.useColorisedThaumcraftTiles && Loader.isModLoaded("thaumcraft")) ThaumcraftCompat.registerTiles();
            if (ConfigHandler.tiles.useColorisedBOPTiles && ModCompat.biomesOPlenty.isLoaded()) BiomesOPlentyCompat.registerTiles();
            if (ConfigHandler.tiles.useColorisedDregoraTiles && ModCompat.otg.isLoaded() && Loader.isModLoaded("dregorarl")) DregoraCompat.registerTiles();
        }
    }

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        event.registerServerCommand(new AAAMCommand());
    }
}