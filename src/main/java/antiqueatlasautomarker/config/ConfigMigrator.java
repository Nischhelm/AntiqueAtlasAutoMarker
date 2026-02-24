package antiqueatlasautomarker.config;

import meldexun.betterconfig.api.tree.IConfigCategory;
import meldexun.betterconfig.api.tree.IConfigContext;
import org.apache.maven.artifact.versioning.ArtifactVersion;

public class ConfigMigrator {
    public static <T extends IConfigContext<T>> void handleMigration(IConfigCategory<T> general, T context, ArtifactVersion fileVersion) {
        if(general.getElements().isEmpty() && general.getSubCategories().isEmpty()) return;
        if (fileVersion == null) migrateTo1_0_0(general, context); // migrate from pre cfgvers 1.0.0 where there was no version entry yet
    }

    private static <T extends IConfigContext<T>> void migrateTo1_0_0(IConfigCategory<T> general, T context) {
        //TODO
    }
}
