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
        if(ForgeConfigHandler.client.enchantmentsToMark.length == 1 && ForgeConfigHandler.client.enchantmentsToMark[0].equals("ALL"))
            acceptAll = true;
        else {
            for (String s : ForgeConfigHandler.client.enchantmentsToMark) {
                String[] split = s.split(";");
                if (split.length != 3 && split.length != 2) continue;
                try {
                    enchMarkSettings.put(split[0].trim(),
                            new EnchMarkSetting(
                                    split[0].trim(),
                                    Integer.parseInt(split[1].trim()),
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
