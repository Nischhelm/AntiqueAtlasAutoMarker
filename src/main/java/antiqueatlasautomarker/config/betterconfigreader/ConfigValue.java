package antiqueatlasautomarker.config.betterconfigreader;

import java.io.IOException;

public class ConfigValue implements ConfigElement {

	public String value = "";

	@Override
	public void read(ConfigReader reader) throws IOException {
		this.value = reader.readLine();
	}

	@Override
	public String getRaw(String configName) {
		return this.value;
	}
}
