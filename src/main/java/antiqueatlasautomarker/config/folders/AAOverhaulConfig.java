package antiqueatlasautomarker.config.folders;

import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

public class AAOverhaulConfig {
    @Config.Comment("AA Global Markers are bugged + laggy. Built-in global markers (village + end city) already get rerouted to AAAM structure markers. Keep this enabled to reroute any other modded markers to AAAM structure markers. Disabling this can lead to unexpected behavior.")
    @Config.Name("Reroute modded Global Markers")
    public boolean rerouteGlobalMarkers = true;

    @Config.Comment("Antique Atlas sends packets to all players whenever anything is added to or removed from any atlas (markers/tiles). Set to true to only send packets to players with the modified atlas in inventory.")
    @Config.Name("Only send to all holding the atlas")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.aaam.antiqueatlas.overhaul.sendtoallholding.json")
    @MixinConfig.CompatHandling(modid = "antiqueatlas")
    public boolean sendToAllHolding = true;

    @Config.Comment("AA doesn't allocate the correct size for some packets which can lead to crashes. This fixes it.")
    @Config.Name("Fix Crash with Short/IntDimensionUpdatePacket")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.aaam.antiqueatlas.overhaul.bytebufcrashfix.json")
    @MixinConfig.CompatHandling(modid = "antiqueatlas")
    public boolean byteBufCrashFix = true;

    @Config.Comment("Whenever Antique Atlas checks for atlases in a players inventory it forgets to also check the offhand. Set to true to check offhand as well.")
    @Config.Name("Also check player offhand for atlases")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.aaam.antiqueatlas.overhaul.offhand.json")
    @MixinConfig.CompatHandling(modid = "antiqueatlas")
    public boolean checkOffhand = true;

    @Config.Comment("Antique Atlas uses a questionable regex to check if a marker label is a lang key (not allowing numbers for example), instead of using I18n.hasKey. It also only allows one parameter for parameterised lang keys. Both get fixed by this.")
    @Config.Name("Fix Atlas Marker Lang Keys")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.aaam.antiqueatlas.overhaul.langkey.json")
    @MixinConfig.CompatHandling(modid = "antiqueatlas")
    public boolean fixLangKeys = true;

    @Config.Comment("When combining atlases, the stack size of the output slot is not set correctly, resulting in a dupe. This fixes it.")
    @Config.Name("Fix Atlas Combining Recipe Dupe")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.aaam.antiqueatlas.overhaul.recipedupe.json")
    @MixinConfig.CompatHandling(modid = "antiqueatlas")
    public boolean fixCombiningRecipe = true;

    @Config.Comment("Markers data is sent in one packet per dimension, which can get really large and lag the server. Keep this enabled to send the markers in chunks of 100 markers per packet to reduce lag on player login.")
    @Config.Name("Marker data in smaller packets")
    @Config.RequiresMcRestart
    @MixinConfig.LateMixin(name = "mixins.aaam.antiqueatlas.overhaul.markerpacketchunking.json")
    @MixinConfig.CompatHandling(modid = "antiqueatlas")
    public boolean markerPacketChunking = true;
}
