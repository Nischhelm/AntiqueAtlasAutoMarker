package antiqueatlasautomarker.config.betterconfigreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigList implements ConfigElement {
	static final Pattern CATEGORY = Pattern.compile("\\s*\\{");
	static final Pattern LIST = Pattern.compile("\\s*<");

	public final List<ConfigElement> list = new ArrayList<>();

	@Override
	public void read(ConfigReader reader) throws IOException {
		if (!reader.readLine().equals("<")) {
			throw new IllegalArgumentException();
		}
		while (!reader.readLineIfEqual(">")) {
			ConfigElement element;

			if ((reader.readMatching(CATEGORY)) != null) {
				element = new ConfigCategory();
			} else if ((reader.readMatching(LIST)) != null) {
				element = new ConfigList();
			} else {
				element = new ConfigValue();
			}
			element.read(reader);

			this.list.add(element);
		}
	}

	@Override
	public String getRaw(String configName) {
		if(configName.equals(".length")) return String.valueOf(this.list.size());

		Matcher matcher = Pattern.compile("\\[(\\d+)\\](.*)").matcher(configName);
		if (!matcher.find()) {
			throw new IllegalArgumentException();
		}
		int ind = Integer.parseInt(matcher.group(1));
		return this.list.get(ind).getRaw(matcher.group(2));
	}
}
