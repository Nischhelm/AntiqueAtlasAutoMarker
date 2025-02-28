package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EarlyConfigReader {
    //Courtesy of fonnymunkey RLMixins, slightly altered
    private static File configFile = null;
    private static String configBooleanString = "";

    public static boolean getBoolean(String name, boolean defaultValue) {
        if (configFile == null) configFile = new File("config", AntiqueAtlasAutoMarker.MODID + ".cfg");

        if (configBooleanString == null && configFile.exists() && configFile.isFile()) {
            try (Stream<String> stream = Files.lines(configFile.toPath())) {
                //All lines starting with "B:"
                configBooleanString = stream.filter(s -> s.trim().startsWith("B:")).collect(Collectors.joining());
            } catch (Exception e) {
                AntiqueAtlasAutoMarker.LOGGER.error("Failed to parse " + AntiqueAtlasAutoMarker.NAME + " config: " + e);
            }
        } else configBooleanString = "";

        if (configBooleanString.contains("B:\"" + name + "\"="))
            return configBooleanString.contains("B:\"" + name + "\"=true");
        //If config is not generated yet or missing entries, we use the default value that would be written into it
        else return defaultValue;
    }
}
