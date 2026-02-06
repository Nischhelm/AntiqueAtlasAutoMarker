package antiqueatlasautomarker.compat;

import net.minecraftforge.fml.common.Loader;

public class ModCompat {
    public static final ModLoaded aarc = new ModLoaded("aarcaddon");
    public static final ModLoaded otg = new ModLoaded("openterraingenerator");
    public static final ModLoaded betterEnd = new ModLoaded("betterendforge");
    public static final ModLoaded biomesOPlenty = new ModLoaded("biomesoplenty");
    public static final ModLoaded defiledlands = new ModLoaded("defiledlands");

    public static class ModLoaded{
        private final String modid;
        private Boolean isLoaded = null;
        public ModLoaded(String modid){
            this.modid = modid;
        }
        public boolean isLoaded(){
            if(this.isLoaded == null) this.isLoaded = Loader.isModLoaded(modid);
            return this.isLoaded;
        }
    }
}
