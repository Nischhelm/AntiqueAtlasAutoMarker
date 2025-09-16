package antiqueatlasautomarker.handlers;

import antiqueatlasautomarker.custombiometiles.BetterEndCompat;
import antiqueatlasautomarker.event.BiomeDetectorEvent;
import biomesoplenty.api.biome.BOPBiomes;
import mod.beethoven92.betterendforge.common.init.ModBiomes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BiomeDetectorEventHandler {
    @SubscribeEvent
    public static void onBiomeDetected(BiomeDetectorEvent event){
        switch (event.getDimension()){
            case 1:
                if(event.getChosenBiomeId() == event.getIdFor("void") && event.getMainBiome() == ModBiomes.ICE_STARFIELD.getBiome())
                    event.setChosenBiomeId(BetterEndCompat.BE_ICE_STARFIELD_VOID);
                break;
            case 0:
                if(event.getChosenBiomeId() == event.getIdFor("water") && BOPBiomes.marsh.toJavaUtil().filter(b -> event.getMainBiome() == b).isPresent()) {
                    if(event.getCountFor("water") * 2 <= event.getCountFor("biome") * 3)
                        event.setChosenBiomeId(event.getIdFor("biome")); //give biome 1.5x priority to reduce actual ponds
                }
                break;
        }
        //TODO: blacklist for biomes that should ignore ravines / use their own ravine texture
        //also for ignoring water/pond (swamplike biomes)
    }
}
