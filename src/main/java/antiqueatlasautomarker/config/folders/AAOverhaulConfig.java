package antiqueatlasautomarker.config.folders;

import net.minecraftforge.common.config.Config;

public class AAOverhaulConfig {
    @Config.Comment("AA Global Markers are bugged + laggy. Built-in global markers (village + end city) already get rerouted to AAAM structure markers. Keep this enabled to reroute any other modded markers to AAAM structure markers. Disabling this can lead to unexpected behavior.")
    @Config.Name("Reroute modded Global Markers")
    public boolean rerouteGlobalMarkers = true;

    @Config.Comment("Antique Atlas sends packets to all players whenever anything is added to or removed from any atlas (markers/tiles). Set to true to only send packets to players with the modified atlas in inventory.")
    @Config.Name("Only send to all holding the atlas")
    @Config.RequiresMcRestart
    public boolean sendToAllHolding = true;

    @Config.Comment("Whenever Antique Atlas checks for atlases in a players inventory it forgets to also check the offhand. Set to true to check offhand as well.")
    @Config.Name("Also check player offhand for atlases")
    @Config.RequiresMcRestart
    public boolean checkOffhand = true;

    @Config.Comment("Antique Atlas uses a questionable regex to check if a marker label is a lang key (not allowing numbers for example), instead of using I18n.hasKey. It also only allows one parameter for parameterised lang keys. Both get fixed by this.")
    @Config.Name("Fix Atlas Marker Lang Keys")
    @Config.RequiresMcRestart
    public boolean fixLangKeys = true;

    @Config.Comment("When combining atlases, the stack size of the output slot is not set correctly, resulting in a dupe. This fixes it.")
    @Config.Name("Fix Atlas Combining Recipe Dupe")
    @Config.RequiresMcRestart
    public boolean fixCombiningRecipe = true;

    @Config.Comment("Markers data is sent in one packet per dimension, which can get really large and lag the server. Keep this enabled to send the markers in chunks of 100 markers per packet to reduce lag on player login.")
    @Config.Name("Marker data in smaller packets")
    @Config.RequiresMcRestart
    public boolean markerPacketChunking = true;
}
