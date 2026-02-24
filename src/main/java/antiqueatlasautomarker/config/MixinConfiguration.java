package antiqueatlasautomarker.config;

import antiqueatlasautomarker.config.betterconfigreader.ConfigCategory;
import fermiumbooter.FermiumRegistryAPI;
import fermiumbooter.util.FermiumJarScanner;

import java.util.function.Predicate;

public class MixinConfiguration {
    private final String json;
    private final String dependency;
    private final String configLocation;
    private final boolean defaultEnabled;

    private boolean late = true;
    private Predicate<String> checkIfEnabled = string -> string.equals("true");

    public MixinConfiguration(String json, String dependency, String configLocation, boolean defaultEnabled) {
        this.json = json;
        this.dependency = dependency;
        this.configLocation = configLocation;
        this.defaultEnabled = defaultEnabled;
    }

    public MixinConfiguration setEarly(){
        this.late = false;
        return this;
    }
    public MixinConfiguration setPredicate(Predicate<String> predicate){
        this.checkIfEnabled = predicate;
        return this;
    }

    public boolean isEnabled(ConfigCategory earlyConfig){
        try {
            return this.checkIfEnabled.test(earlyConfig.getRaw(this.configLocation));
        } catch (Exception e) {
            return this.defaultEnabled;
        }
    }

    public void enqueue(){
        FermiumRegistryAPI.enqueueMixin(this.late, this.json, () -> FermiumJarScanner.isModPresent(this.dependency));
    }
}
