package antiqueatlasautomarker.config.betterconfigreader;

import java.io.IOException;

public interface ConfigElement {
	void read(ConfigReader reader) throws IOException;

	String getRaw(String configName);
}
