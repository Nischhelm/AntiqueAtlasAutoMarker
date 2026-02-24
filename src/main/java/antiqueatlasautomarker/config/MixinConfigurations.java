package antiqueatlasautomarker.config;

import antiqueatlasautomarker.Tags;
import antiqueatlasautomarker.config.folders.FixConfig;
import antiqueatlasautomarker.config.folders.TileConfig;
import fermiumbooter.FermiumBooter;
import fermiumbooter.FermiumRegistryAPI;
import fermiumbooter.util.FermiumJarScanner;
import net.minecraftforge.fml.common.Loader;

import java.util.stream.Stream;

class MixinConfigurations {
    public static void enqueueMixins(){
        if(!FermiumBooter.NAME.equals("FermiumBooter"))
            Tags.LOGGER.warn("Running on modified FermiumBooter. This is not recommended and can cause crashes. FermiumBooter is compatible with all known other Mixin providers.");

        //TODO: test if AutomarkSetting.defaultenabled = false works
        Stream.of(
                new Data("mixins.aaam.battletowers.json", "battletowers", ConfigHandler.automark.battletowers.enabled),
                new Data("mixins.aaam.doomlikedungeons.json", "dldungeonsjbg", ConfigHandler.automark.doomlike.enabled),
                new Data("mixins.aaam.dungeons2.json", "dungeons2", ConfigHandler.automark.dungeons2.enabled),
                new Data("mixins.aaam.roguelikedungeons.json", "roguelike", ConfigHandler.automark.roguelike.enabled),
                new Data("mixins.aaam.quark.json", "quark", ConfigHandler.automark.quark.enabled),
                new Data("mixins.aaam.bettermineshafts.json", "bettermineshafts", ConfigHandler.automark.bettermineshafts.enabled),
                new Data("mixins.aaam.lycanitesmobs.json", "lycanitesmobs", ConfigHandler.automark.lycanitesmobs.enabled),
                new Data("mixins.aaam.waystones.wild.json", "waystones", ConfigHandler.automark.waystones.wildWaystones.enabled),
                new Data("mixins.aaam.waystones.activated.json", "waystones", ConfigHandler.automark.waystones.activatedWaystones.enabled)
        )
        .filter(c -> c.isEnabled)
        .forEach(Data::enqueue);

        //Vanilla
        FermiumRegistryAPI.enqueueMixin(false, "mixins.aaam.vanilla.json");

        //Antique Atlas Structure Markers
        FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.structuremarkers.json");

        FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.colorisedvanillatiles.json", () -> ConfigHandler.tiles.useColorisedVanillaTiles != TileConfig.EnumTextureSetArtist.NONE);
        FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.overhaul.updateside.json", () -> ConfigHandler.fixes.updateSide != FixConfig.UpdateSide.DISABLE_MIXIN);

        FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.overhaul.structurewatchers.json"); //TODO add toggle
        FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.customvillagetiles.json"); //TODO add toggle
        FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.autobiomerules.json", () -> !ConfigHandler.tiles.automaticTypeRules.isEmpty());
        FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.antiqueatlas.tiles.stitchtonull.json", () -> !ConfigHandler.tiles.stitchToNullSets.isEmpty());

        //Ice and Fire
        FermiumRegistryAPI.enqueueMixin(true, "mixins.aaam.inf.easter.json", () -> Loader.isModLoaded("iceandfire"));
    }

    static class Data {
        final String json;
        final String dependency;
        final boolean isEnabled;

        boolean late = true;

        Data(String json, String dependency, boolean isEnabled) {
            this.json = json;
            this.dependency = dependency;
            this.isEnabled = isEnabled;
        }

        Data setEarly(){
            this.late = false;
            return this;
        }

        void enqueue(){
            FermiumRegistryAPI.enqueueMixin(this.late, this.json, () -> FermiumJarScanner.isModPresent(this.dependency));
        }
    }
}
