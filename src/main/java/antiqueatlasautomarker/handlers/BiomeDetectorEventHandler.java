package antiqueatlasautomarker.handlers;

import antiqueatlasautomarker.compat.ModCompat;
import antiqueatlasautomarker.custombiometiles.BetterEndCompat;
import antiqueatlasautomarker.custombiometiles.BiomesOPlentyCompat;
import antiqueatlasautomarker.event.BiomeDetectorEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BiomeDetectorEventHandler {
    @SubscribeEvent
    public static void onBiomeDetected(BiomeDetectorEvent event){
        switch (event.getDimension()){
            case 1:
                if(ModCompat.betterEnd.isLoaded()) {
                    if (event.getChosenBiomeId() == event.getIdFor("void") && event.getMainBiome() == BetterEndCompat.getIceStarfieldBiome())
                        event.setChosenBiomeId(BetterEndCompat.BE_ICE_STARFIELD_VOID);
                }
                break;
            case 0:
                if(ModCompat.biomesOPlenty.isLoaded()) {
                    if (event.getChosenBiomeId() == event.getIdFor("water") && event.getMainBiome() == BiomesOPlentyCompat.getMarshBiome()) {
                        if (event.getCountFor("water") * 2 <= event.getCountFor("biome") * 3)
                            event.setChosenBiomeId(event.getIdFor("biome")); //give biome 1.5x priority to reduce actual ponds
                    }
                }
                break;
        }
        //TODO: blacklist for biomes that should ignore ravines / use their own ravine texture
        //also for ignoring water/pond (swamplike biomes)
    }
}
