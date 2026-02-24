package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EarlyConfigReader {
    //Courtesy of fonnymunkey RLMixins, altered
    private static File configFile = null;
    private static String configStringString = null;
    private static Map<String,Boolean> configArrayFilledMap = null;

    public static String getString(String name, String defaultValue) {
        if (configFile == null) configFile = new File("config", AntiqueAtlasAutoMarker.MODID + ".cfg");

        if (configStringString == null) {
            if (configFile.exists() && configFile.isFile()) {
                try (Stream<String> stream = Files.lines(configFile.toPath())) {
                    configStringString = stream.filter(s -> s.trim().startsWith("S:") && s.contains("=")).collect(Collectors.joining("$$$"));
                } catch (Exception ex) {
                    AntiqueAtlasAutoMarker.LOGGER.error("Failed to parse " + AntiqueAtlasAutoMarker.NAME + " String config: {}", String.valueOf(ex));
                }
            } else configStringString = "";
        }

        int startIndex = configStringString.indexOf("S:\"" + name + "\"=");
        if (startIndex != -1) {
            try {
                startIndex = configStringString.indexOf("=", startIndex) + 1;
                int endindex = configStringString.indexOf("$$$", startIndex);
                return configStringString.substring(startIndex, endindex == -1 ? configStringString.length() : endindex).trim();
            } catch (Exception e) {
                AntiqueAtlasAutoMarker.LOGGER.error(AntiqueAtlasAutoMarker.NAME + ": Failed to parse int config {}, {}", name, e);
                return defaultValue;
            }
        }
        //If config is not generated yet or missing entries, we use the default value that will get written into it right after this
        else return defaultValue;
    }

    public static boolean isArrayFilled(String name, boolean filledByDefault) {
        if (configFile == null) configFile = new File("config", AntiqueAtlasAutoMarker.MODID + ".cfg");

        if (configArrayFilledMap == null) {
            configArrayFilledMap = new HashMap<>();
            if (configFile.exists() && configFile.isFile()) {
                try (Stream<String> lines = Files.lines(configFile.toPath())){
                    String allLines = lines.collect(Collectors.joining("\t")); //whitespace \s doesnt match \n here for whatever reason, otherwise i could easily use that for the config name special character
                    Matcher matcher = Pattern.compile("[SID]:\"([^\t]+)\" <([^>]*)>").matcher(allLines); //regex matches any config lines with any < ... > entry and a name allowing anything except tabspace (\t). had to use some special character no one would use in config name. idk if this one was a good one
                    while (matcher.find())
                        configArrayFilledMap.put(matcher.group(1), !matcher.group(2).matches("\\s*")); //save config name and whether its empty (only whitespace) or not
                } catch (Exception ex) {
                    AntiqueAtlasAutoMarker.LOGGER.error("Failed to parse " + AntiqueAtlasAutoMarker.NAME + " string list config: {}", String.valueOf(ex));
                }
            }
        }

        return configArrayFilledMap.getOrDefault(name, filledByDefault);
    }
}
