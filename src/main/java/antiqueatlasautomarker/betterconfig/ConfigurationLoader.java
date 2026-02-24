package antiqueatlasautomarker.betterconfig;

import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;

public class ConfigurationLoader {

	public static ConfigCategory load(File file) throws IOException {
		ConfigCategory cfg = new ConfigCategory();
		if (file.exists()) {
			try (ConfigReader reader = new ConfigReader(Files.newBufferedReader(file.toPath()))) {
				while (reader.hasNext()) {
					Matcher matcher;
					if ((matcher = reader.readMatching(ConfigCategory.CATEGORY)) != null) {
						String name = ObjectUtils.defaultIfNull(matcher.group(1), matcher.group(2));
						ConfigCategory subcategory = new ConfigCategory();
						subcategory.read(reader);
						cfg.elements.put(name, subcategory);
					} else {
						throw new IllegalArgumentException();
					}
				}
			}
		}
		return cfg;
	}

}
