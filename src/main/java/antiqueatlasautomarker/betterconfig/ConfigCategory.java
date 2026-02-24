package antiqueatlasautomarker.betterconfig;

import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigCategory implements ConfigElement {

	static final String UNQUOTED_NAME = "[\\w\\.-]*";
	static final String QUOTED_NAME = "[^\"]*";
	static final String NAME = String.format("(?:(%s)|\"(%s)\")", UNQUOTED_NAME, QUOTED_NAME);
	static final String VALUE_TYPE = "([BIDS])";
	static final String LIST_TYPE = "(L?[BIDSC])";
	static final Pattern CATEGORY = Pattern.compile(String.format("%s\\s*(?=\\{)", NAME));
	static final Pattern LIST = Pattern.compile(String.format("%s:%s\\s*(?=<)", LIST_TYPE, NAME));
	static final Pattern VALUE = Pattern.compile(String.format("%s:%s=", VALUE_TYPE, NAME));
	public final Map<String, ConfigElement> elements = new LinkedHashMap<>();

	@Override
	public void read(ConfigReader reader) throws IOException {
		if (!reader.readLine().equals("{")) {
			throw new IllegalArgumentException();
		}
		while (!reader.readLineIfEqual("}")) {
			String name;
			ConfigElement element;

			Matcher matcher;
			if ((matcher = reader.readMatching(VALUE)) != null) {
				name = ObjectUtils.defaultIfNull(matcher.group(2), matcher.group(3));
				element = new ConfigValue();
			} else if ((matcher = reader.readMatching(LIST)) != null) {
				name = ObjectUtils.defaultIfNull(matcher.group(2), matcher.group(3));
				element = new ConfigList();
			} else if ((matcher = reader.readMatching(CATEGORY)) != null) {
				name = ObjectUtils.defaultIfNull(matcher.group(1), matcher.group(2));
				element = new ConfigCategory();
			} else {
				throw new IllegalArgumentException(reader.peekLine());
			}
			element.read(reader);

			this.elements.put(name, element);
		}
	}

	@Override
	public String getRaw(String configName){
		if(configName.startsWith("."))
			configName = configName.substring(1);

		String name;
		if(configName.startsWith("\"")){
			//name in quotes
			name = configName.substring(1);
			int ind = name.indexOf("\"");
			name = name.substring(0,ind);
		} else {
			//until first [ or .
			name = configName.split("[\\[.]")[0];
		}
		if(!elements.containsKey(name)){
			throw new IllegalArgumentException(configName);
		}
		return elements.get(name).getRaw(configName.replaceFirst(name, ""));
	}
}
