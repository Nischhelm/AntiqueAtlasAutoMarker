package antiqueatlasautomarker.config;

import meldexun.betterconfig.api.ConfigMigrationHelper;
import meldexun.betterconfig.api.tree.*;
import org.apache.maven.artifact.versioning.ArtifactVersion;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ConfigMigrator {
    public static <T extends IConfigContext<T>> void handleMigration(IConfigCategory<T> general, T context, ArtifactVersion fileVersion) {
        if (general.getElements().isEmpty() && general.getSubCategories().isEmpty()) return; //fresh start, no cfg file -> no migration
        if (fileVersion == null) migrateTo1_0(general, context); // migrate from pre cfgVers 1.0 where there was no version entry yet
    }

    private static <T extends IConfigContext<T>> void migrateTo1_0(IConfigCategory<T> general, T context) {
        try {
            migrateAutoMarking(general, context);
            migrateOverhaul(general, context);
            renameCategory(general, "internal", "aaam internals");
        } catch (Exception e) {
            throw new RuntimeException("Config migration failed", e);
        }
    }

    // ----- Overhaul Section -----

    private static <T extends IConfigContext<T>> void migrateOverhaul(IConfigCategory<T> general, T context) {
        // Split overhaul into three categories
        IConfigCategory<T> overhaul = general.getSubCategories().remove("antique atlas overhaul");
        if (overhaul == null) return;

        //Tweaks
        IConfigCategory<T> tweaks = createTweaksCategory(overhaul, context);
        general.getSubCategories().put("antique atlas tweaks", tweaks);

        IConfigCategory<T> waystones = getNestedCategory(general, "auto marking", "waystones");
        renameElement(waystones, "Allow selecting waystone on map", "Select Waystone on map");
        moveElement(waystones, tweaks, "Select Waystone on map");

        //Fixes
        general.getSubCategories().put("antique atlas fixes", createFixesCategory(overhaul, context));

        //Tiles
        renameCategory(overhaul, "biome to tile config", "antique atlas tiles");
        moveCategory(overhaul, general, "antique atlas tiles");
    }

    private static <T extends IConfigContext<T>> IConfigCategory<T> createTweaksCategory(IConfigCategory<T> overhaul, T context) {
        IConfigCategory<T> tweaks = context.createCategory();

        renameElement(overhaul, "Allow hiding specific markers", "Allow hiding specified markers");
        moveElements(overhaul, tweaks, "Allow hiding specified markers",
                "Add Atlas Keybinds and Buttons",
                "Scroll Marker Types Vertically",
                "Shift Delete Matching Markers",
                "Show Other Players as Player Heads",
                "Show Position of Other Players"
        );

        return tweaks;
    }

    private static <T extends IConfigContext<T>> IConfigCategory<T> createFixesCategory(IConfigCategory<T> overhaul, T context) {
        IConfigCategory<T> fixes = context.createCategory();

        renameElement(overhaul, "Also check player offhand for atlases", "Check offhand for atlases");
        renameElement(overhaul, "Fix Crash with Short/IntDimensionUpdatePacket", "Fix DimensionUpdatePacket Crash");

        moveElements(overhaul, fixes, "Atlas Scanning Update Side",
                "Fix Atlas Combining Recipe Dupe",
                "Fix Atlas Marker Lang Keys",
                "Marker data in smaller packets",
                "Only send to all holding the atlas",
                "Reroute modded Global Markers",
                "Check offhand for atlases",
                "Fix DimensionUpdatePacket Crash"
        );

        return fixes;
    }

    // ----- Auto markers -----

    private static <T extends IConfigContext<T>> void migrateAutoMarking(IConfigCategory<T> general, T context) {
        IConfigCategory<T> autoMarking = general.getSubCategories().computeIfAbsent("auto marking", k -> context.createCategory());

        migrateAutomarkCfgSimple(moveCategory(general, autoMarking, "battletowers"), "BT Enabled");
        migrateAutomarkCfgSimple(moveCategory(general, autoMarking, "better mineshafts"), "YBM Enabled");
        migrateAutomarkCfgSimple(moveCategory(general, autoMarking, "doomlike dungeons"), "DLD Enabled");
        migrateAutomarkCfgSimple(moveCategory(general, autoMarking, "dungeons2"), "DG2 Enabled");
        migrateRoguelikeDungeons(moveCategory(general, autoMarking, "roguelike dungeons"));

        migrateIceAndFire(moveCategory(general, autoMarking, "ice and fire"), context);
        migrateWaystones(moveCategory(general, autoMarking, "waystones"), context);
        makeCategory(moveCategory(general, autoMarking, "ruins"), "Ruins Structure Markers", el -> transformAutoMarkList((IConfigList<T>) el, context));
        makeCategory(moveCategory(general, autoMarking, "lang keys"), "Default Lang Keys", el -> transformListToMap((IConfigList<T>) el, context));
        moveCategory(general, autoMarking, "custom positions");
        migrateAARCAddon(general, autoMarking);
        migrateEnchantments(moveCategory(general, autoMarking, "enchantments"), context);

        //Defiled Lands
        renameAutomarkCfgFull(moveCategory(general, autoMarking, "defiled lands"), "Gold Wyrm Key - Enabled", "Gold Wyrm Key - Label", "Gold Wyrm Key - Marker");
        renameCategory(autoMarking, "defiled lands", "defiled lands gold wyrm key");

        //Quark
        renameAutomarkCfgFull(moveCategory(general, autoMarking, "quark"), "Pirateship - Enabled", "Pirateship - Label", "Pirateship - Marker");
        renameCategory(autoMarking, "quark", "quark pirateship");

        //LycanitesMobs
        migrateAutomarkCfgSimple(moveCategory(general, autoMarking, "lycanitesmobs"), "LM Enabled");
        renameCategory(autoMarking, "lycanitesmobs", "lycanites dungeons");

        //Vanilla MapGenStructures
        migrateStructures(moveCategory(general, autoMarking, "structures"), context);
        renameCategory(autoMarking, "structures", "vanilla mapgenstructures");
    }

    private static <T extends IConfigContext<T>> void migrateAARCAddon(IConfigCategory<T> general, IConfigCategory<T> autoMarking) {
        IConfigCategory<T> aarcaddon = general.getSubCategories().remove("aarcaddon");
        if (aarcaddon == null) return;
        moveAndRenameElement(aarcaddon, autoMarking, "AARC Enabled", "AARC Compat Enabled");
    }

    private static <T extends IConfigContext<T>> void migrateEnchantments(IConfigCategory<T> ench, T context) {
        if (ench == null) return;
        renameElement(ench, "Marker", "Enchantment Trade Marker");
        renameElement(ench, "Enabled", "Enchantment Trade Marker Enabled");
        createAutomarkCfgFromExisting(ench, "librarian key","Enable Librarian Key", "Librarian Key Label", "Librarian Key Marker", context);
        makeCategory(ench, "Enchantments Trades to mark", el -> transformEnchantmentTrades((IConfigList<T>) el, context));
    }

    private static <T extends IConfigContext<T>> IConfigCategory<T> transformEnchantmentTrades(IConfigList<T> oldList, T context) {
        IConfigCategory<T> tradesMap = context.createCategory();

        for (IConfigElement<T> item : oldList.getList()) {
            String line = ((IConfigValue<T>) item).getValue();
            if (line.trim().isEmpty()) continue;

            // Parse: "modid:enchname;minLvl;abbreviation" or "modid:enchname;;abbreviation" or "modid:enchname;minLvl"
            String[] parts = line.split(";", -1); // -1 keeps empty strings apparently
            if (parts.length == 0) continue;

            String enchId = parts[0].trim();
            if (enchId.isEmpty()) continue;

            IConfigCategory<T> enchCat = context.createCategory();
            enchCat.getElements().put("Min Level", createValue((parts.length > 1 && !parts[1].trim().isEmpty()) ? parts[1].trim() : "1", context));
            enchCat.getElements().put("Abbreviation", createValue(parts.length > 2 ? parts[2].trim() : "", context));
            tradesMap.getSubCategories().put(enchId, enchCat);
        }

        return tradesMap;
    }

    private static <T extends IConfigContext<T>> void migrateIceAndFire(IConfigCategory<T> iaf, T context) {
        if (iaf == null) return;
        createAutomarkCfgFromExisting(iaf, "fire dragon","Fire Dragon - Enabled", "Fire Dragon - Label", "Fire Dragon - Marker", context);
        createAutomarkCfgFromExisting(iaf, "ice dragon","Ice Dragon - Enabled", "Ice Dragon - Label", "Ice Dragon - Marker", context);
        createAutomarkCfgFromExisting(iaf, "lightning dragon","Lightning Dragon - Enabled", "Lightning Dragon - Label", "Lightning Dragon - Marker", context);
        createAutomarkCfgFromExisting(iaf, "hydra cave","Hydra - Enabled", "Hydra - Label", "Hydra - Marker", context);
        createAutomarkCfgFromExisting(iaf, "cyclops den","Cyclops - Enabled", "Cyclops - Label", "Cyclops - Marker", context);
    }

    private static <T extends IConfigContext<T>> void migrateRoguelikeDungeons(IConfigCategory<T> rld) {
        if (rld == null) return;
        migrateAutomarkCfgSimple(rld, "Roguelike Enabled");
        renameCategory(rld, "default theme labels", "Default Theme Labels");
        renameCategory(rld, "default tower labels", "Default Tower Labels");
    }

    private static <T extends IConfigContext<T>> void migrateStructures(IConfigCategory<T> structures, T context) {
        if (structures == null) return;
        makeCategory(structures, "Structure Markers", el -> transformAutoMarkList((IConfigList<T>) el, context));
        renameCategory(structures, "Structure Markers", "Markers");
        renameElement(structures, "Structures Enabled", "Marking Enabled");
    }

    private static <T extends IConfigContext<T>> void migrateWaystones(IConfigCategory<T> waystones, T context) {
        if (waystones == null) return;
        IConfigCategory<T> activated = createAutomarkCfgFromExisting(waystones, "activated waystones", "Activated - Enabled", "Activated - Label", "Activated - Marker", context);
        moveAndRenameElement(waystones, activated, "Activated Waystones - Always mark", "Always mark");
        moveAndRenameElement(waystones, activated, "Activated Waystones - Auto update name", "Auto update name");
        createAutomarkCfgFromExisting(waystones, "wild waystones", "Wild - Enabled", "Wild - Label", "Wild - Marker", context);
    }

    // ----- Automark config helpers -----

    private static <T extends IConfigContext<T>> void migrateAutomarkCfgSimple(IConfigCategory<T> category, String oldEnabledName) {
        if (category == null) return;
        renameElement(category, oldEnabledName, "Enabled");
    }

    private static <T extends IConfigContext<T>> void renameAutomarkCfgFull(IConfigCategory<T> category, String oldEnabledName, String oldLabelname, String oldMarkerName) {
        if (category == null) return;
        renameElement(category, oldEnabledName, "Enabled");
        renameElement(category, oldLabelname, "Label");
        renameElement(category, oldMarkerName, "Marker");
    }

    private static <T extends IConfigContext<T>> IConfigCategory<T> createAutomarkCfgFromExisting(IConfigCategory<T> src, String newCategoryName, String oldEnabledName, String oldLabelname, String oldMarkerName, T context) {
        IConfigCategory<T> newCat = context.createCategory();
        src.getSubCategories().put(newCategoryName, newCat);
        moveElements(src, newCat, oldEnabledName, oldLabelname, oldMarkerName);
        renameAutomarkCfgFull(newCat, oldEnabledName, oldLabelname, oldMarkerName);
        return newCat;
    }

    private static <T extends IConfigContext<T>> IConfigCategory<T> transformAutoMarkList(IConfigList<T> oldList, T context) {
        IConfigCategory<T> markersMap = context.createCategory();

        for (IConfigElement<T> item : oldList.getList()) {
            String line = ((IConfigValue<T>) item).getValue();
            if (line.trim().isEmpty()) continue;

            // Parse: "name; enabled; label; marker"
            String[] parts = line.split(";");
            if (parts.length < 4) continue;

            String name = parts[0].trim();
            if (name.isEmpty()) continue;

            IConfigCategory<T> subCat = context.createCategory();
            subCat.getElements().put("Enabled", createValue(parts[1].trim(), context));
            subCat.getElements().put("Label", createValue(parts[2].trim(), context));
            subCat.getElements().put("Marker", createValue(parts[3].trim(), context));

            markersMap.getSubCategories().put(name, subCat);
        }

        return markersMap;
    }

    // ----- HELPERS -----
    @Nullable
    private static <T extends IConfigContext<T>> IConfigCategory<T> getNestedCategory(IConfigCategory<T> src, String... categoryNames) {
        IConfigCategory<T> currentCat = src;
        for (String categoryName : categoryNames) {
            currentCat = currentCat.getSubCategories().get(categoryName);
            if(currentCat == null) return null;
        }
        return currentCat;
    }

    private static <T extends IConfigContext<T>> IConfigValue<T> createValue(String value, T context){
        IConfigValue<T> configValue = context.createValue();
        configValue.setValue(value);
        return configValue;
    }

    private static <T extends IConfigContext<T>> void makeCategory(IConfigCategory<T> parent, String elementName, Function<IConfigElement<T>, IConfigCategory<T>> transformer){
        if(parent == null) return;
        parent.getSubCategories().put(elementName, transformer.apply(parent.getElements().remove(elementName)));
    }

    @Nullable
    private static <T extends IConfigContext<T>> IConfigElement<T> moveElement(IConfigCategory<T> src, IConfigCategory<T> dst, String srcName) {
        if (src != null && src.getElements().containsKey(srcName)) return ConfigMigrationHelper.moveElement(src, srcName, dst);
        return null;
    }

    private static <T extends IConfigContext<T>> void moveElements(IConfigCategory<T> src, IConfigCategory<T> dst, String... srcNames) {
        for(String srcName : srcNames)
            if (src.getElements().containsKey(srcName)) ConfigMigrationHelper.moveElement(src, srcName, dst);
    }

    private static <T extends IConfigContext<T>> IConfigElement<T> moveAndRenameElement(IConfigCategory<T> src, IConfigCategory<T> dst, String srcName, String dstName) {
        if (src.getElements().containsKey(srcName)) return ConfigMigrationHelper.moveElement(src, srcName, dst, dstName);
        return null;
    }

    private static <T extends IConfigContext<T>> IConfigElement<T> renameElement(IConfigCategory<T> category, String oldName, String newName) {
        if (category != null && category.getElements().containsKey(oldName)) return ConfigMigrationHelper.renameElement(category, oldName, newName);
        return null;
    }

    private static <T extends IConfigContext<T>> IConfigCategory<T> moveCategory(IConfigCategory<T> src, IConfigCategory<T> dst, String srcName) {
        if (src.getSubCategories().containsKey(srcName)) return ConfigMigrationHelper.moveCategory(src, srcName, dst);
        return null;
    }

    private static <T extends IConfigContext<T>> IConfigCategory<T> renameCategory(IConfigCategory<T> parent, String oldName, String newName) {
        if (parent.getSubCategories().containsKey(oldName)) return ConfigMigrationHelper.renameCategory(parent, oldName, newName);
        return null;
    }

    private static <T extends IConfigContext<T>> IConfigCategory<T> transformListToMap(IConfigList<T> oldList, T context) {
        IConfigCategory<T> newMap = context.createCategory();

        for (IConfigElement<T> item : oldList.getList()) {
            String line = ((IConfigValue<T>) item).getValue();
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split("=", 2);
            if (parts.length != 2) continue;

            String key = parts[0].trim();
            String value = parts[1].trim();

            if (!key.isEmpty()) {
                IConfigValue<T> val = context.createValue();
                val.setValue(value);
                newMap.getElements().put(key, val);
            }
        }

        return newMap;
    }
}