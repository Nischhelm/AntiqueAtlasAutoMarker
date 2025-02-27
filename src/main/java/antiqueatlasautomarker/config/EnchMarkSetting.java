package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;

import java.util.HashMap;
import java.util.Map;

public class EnchMarkSetting {
    public final String enchId;
    public final int minLvl;
    public final String abbreviation;
    public static boolean acceptAll = false;

    public EnchMarkSetting(String enchId, int minLvl, String abbreviation) {
        this.enchId = enchId;
        this.minLvl = minLvl;
        this.abbreviation = abbreviation;
    }

    private static final Map<String, EnchMarkSetting> enchMarkSettings = new HashMap<>();

    public static EnchMarkSetting get(String enchId) {
        return enchMarkSettings.get(enchId);
    }

    public static boolean contains(String enchId) {
        return enchMarkSettings.containsKey(enchId);
    }

    public static void init() {
        if(ConfigHandler.enchantmentsToMark.length == 1 && ConfigHandler.enchantmentsToMark[0].equals("ALL"))
            acceptAll = true;
        else {
            for (String s : ConfigHandler.enchantmentsToMark) {
                String[] split = s.split(";");
                if (split.length < 1 || split.length > 3) continue;
                try {
                    enchMarkSettings.put(split[0].trim(),
                            new EnchMarkSetting(
                                    split[0].trim(),
                                    split.length > 1 && !split[1].isEmpty() ? Integer.parseInt(split[1].trim()) : 0,
                                    split.length == 3 ? split[2].trim() : ""
                            )
                    );
                } catch (Exception e) {
                    AntiqueAtlasAutoMarker.LOGGER.warn("Could not parse AAAM line for enchantment, skipping {}", s);
                }
            }
        }
    }

    public static void reset() {
        enchMarkSettings.clear();
        acceptAll = false;
        init();
    }
}
