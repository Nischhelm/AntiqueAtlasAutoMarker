package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;

import java.util.HashMap;
import java.util.Map;

public class ConfigProvider {
	public static Map<String, String> lycaDungeonNames = new HashMap<>();

	public static void init() {
		setupLycaDungeonNames();
	}

	private static void setupLycaDungeonNames() {
		lycaDungeonNames.clear();
		for (String s : ConfigHandler.lycaDungeonNames) {
			String[] split = s.split(";");
			if (split.length != 2) {
				AntiqueAtlasAutoMarker.LOGGER.info("AAAM: Could not parse Lyca dungeon name " + s);
				continue;
			}
			lycaDungeonNames.put(split[0].trim(), split[1].trim());
		}
	}
}