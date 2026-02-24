package antiqueatlasautomarker.betterconfig;

import java.io.IOException;

public interface ConfigElement {
	void read(ConfigReader reader) throws IOException;

	String getRaw(String configName);
}
