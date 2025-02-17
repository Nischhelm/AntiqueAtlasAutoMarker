package antiqueatlasautomarker.config;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EarlyConfigReader {
    //Early config boolean getter copied from RLMixins
    private static File configFile = null;
    private static String configBooleanString = "";

    public static boolean getBoolean(String name) {
        if(configFile==null) {
            configFile = new File("config", AntiqueAtlasAutoMarker.MODID + ".cfg");
            if(configFile.exists() && configFile.isFile()) {
                try (Stream<String> stream = Files.lines(configFile.toPath())) {
                    configBooleanString = stream.filter(s -> s.trim().startsWith("B:")).collect(Collectors.joining());
                }
                catch(Exception ex) {
                    AntiqueAtlasAutoMarker.LOGGER.error("Failed to parse AntiqueAtlasAutoMarker config: " + ex);
                }
            }
        }
        //If config is not generated or missing entries, don't enable injection on first run
        return configBooleanString.contains("B:\"" + name + "\"=true");
    }
}
