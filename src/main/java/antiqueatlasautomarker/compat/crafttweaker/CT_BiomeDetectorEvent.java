package antiqueatlasautomarker.compat.crafttweaker;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import antiqueatlasautomarker.event.BiomeDetectorEvent;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.block.IBlock;
import crafttweaker.api.event.IWorldEvent;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBiome;
import crafttweaker.api.world.IWorld;
import crafttweaker.mc1120.world.MCBiome;
import hunternif.mc.atlas.ext.ExtTileIdMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@ZenRegister
@ZenClass("mods." + AntiqueAtlasAutoMarker.MODID + ".BiomeDetectorEvent")
public class CT_BiomeDetectorEvent implements IWorldEvent {

    private final BiomeDetectorEvent internal;

    public CT_BiomeDetectorEvent(BiomeDetectorEvent internal) {
        this.internal = internal;
    }

    @ZenMethod
    @ZenGetter("x")
    public int getX() {
        return this.internal.getChunk().x;
    }

    @ZenMethod
    @ZenGetter("z")
    public int getZ() {
        return this.internal.getChunk().z;
    }

    @ZenMethod
    public IBlock getTopBlock(int x, int z){
        return CraftTweakerMC.getBlock(this.internal.getWorld(), x, this.internal.getChunk().getHeightValue(x, z), z);
    }

    @ZenMethod
    public int getIdFor(String type) {
        return internal.getIdFor(type);
    }

    @ZenMethod
    @ZenGetter("types")
    public String[] getCountTypes() {
        return internal.getCountTypes().toArray(new String[0]);
    }

    @ZenMethod
    @ZenGetter("world")
    public IWorld getWorld() {
        return CraftTweakerMC.getIWorld(this.internal.getWorld());
    }

    @ZenMethod
    @ZenGetter("mainBiome")
    public IBiome getMainBiome() {
        return new MCBiome(this.internal.getMainBiome());
    }

    @ZenMethod
    public int getCountFor(String type) {
        return internal.getCountFor(type);
    }

    @ZenMethod
    @ZenGetter("chosenType")
    public String getChosenType(){
        return this.internal.getChosenType();
    }

    @ZenMethod
    @ZenSetter("chosenType")
    public void setChosenType(String type){
        if(!this.internal.setChosenType(type))
            CraftTweakerAPI.logWarning("AAAM BiomeDetectorEvent setting type to " + type + ", not valid, ignoring");
    }

    @ZenMethod
    @ZenGetter("chosenId")
    public int getChosenId() {
        return internal.getChosenBiomeId();
    }

    @ZenMethod
    @ZenSetter("chosenId")
    public void setChosenId(int id) {
        internal.setChosenBiomeId(id);
    }

    private static final Map<String, ResourceLocation> locs = new HashMap<>();

    @ZenMethod
    @ZenSetter("chosenBiome")
    public void setChosenBiome(String biomeName){
        Biome biome = ForgeRegistries.BIOMES.getValue(locs.computeIfAbsent(biomeName, ResourceLocation::new));
        if(biome != null) this.setChosenId(Biome.getIdForBiome(biome));
        else CraftTweakerAPI.logWarning("AAAM BiomeDetectorEvent didnt find biome named " + biomeName + ", ignoring");
    }

    @ZenMethod
    @ZenSetter("chosenTile")
    public void setChosenTile(String tileName) {
        int id = ExtTileIdMap.instance().getPseudoBiomeID(tileName);
        if(id != ExtTileIdMap.NOT_FOUND) this.setChosenId(id);
        else CraftTweakerAPI.logWarning("AAAM BiomeDetectorEvent didnt find id for tile named " + tileName + ", ignoring");
    }

    @ZenMethod
    @ZenGetter("chosenTile")
    public String getChosenTile() {
        int id = this.getChosenId();
        if(id >= 0){
            Biome biome = Biome.getBiome(id);
            if(biome == null || biome.getRegistryName() == null) return "BIOME_NOT_FOUND";
            return biome.getRegistryName().toString();
        }
        else if(id == -1) return "NOT_FOUND";
        else return ExtTileIdMap.instance().getPseudoBiomeName(id);
    }

    public static class CT_EventForwarder {
        @SubscribeEvent
        public static void onBiomeDetect(BiomeDetectorEvent event) {
            if (CT_BiomeDetectorExpansion.hasBiomeDetectorHandlers())
                CT_BiomeDetectorExpansion.publishBiomeDetector(new CT_BiomeDetectorEvent(event));
        }
    }
}
