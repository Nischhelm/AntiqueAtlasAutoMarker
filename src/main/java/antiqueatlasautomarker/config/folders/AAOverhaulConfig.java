package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class AAOverhaulConfig {
    @Config.Comment("AA Global Markers are bugged + laggy. Built-in global markers (village + end city) already get rerouted to AAAM structure markers. Keep this enabled to reroute any other modded markers to AAAM structure markers. Disabling this can lead to unexpected behavior.")
    @Config.Name("Reroute modded Global Markers")
    public boolean rerouteGlobalMarkers = true;

    @Config.Comment("Antique Atlas sends packets to all players whenever anything is added to or removed from any atlas (markers/tiles). Set to true to only send packets to players with the modified atlas in inventory.")
    @Config.Name("Only send to all holding the atlas")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.sendtoallholding.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    public boolean sendToAllHolding = true;

    @Config.Comment("AA doesn't allocate the correct size for some packets which can lead to crashes. This fixes it.")
    @Config.Name("Fix Crash with Short/IntDimensionUpdatePacket")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.bytebufcrashfix.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    public boolean byteBufCrashFix = true;

    @Config.Comment("Whenever Antique Atlas checks for atlases in a players inventory it forgets to also check the offhand. Set to true to check offhand as well.")
    @Config.Name("Also check player offhand for atlases")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.offhand.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    public boolean checkOffhand = true;

    @Config.Comment("Antique Atlas uses a questionable regex to check if a marker label is a lang key (not allowing numbers for example), instead of using I18n.hasKey. It also only allows one parameter for parameterised lang keys. Both get fixed by this.")
    @Config.Name("Fix Atlas Marker Lang Keys")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.langkey.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    public boolean fixLangKeys = true;

    @Config.Comment("When combining atlases, the stack size of the output slot is not set correctly, resulting in a dupe. This fixes it.")
    @Config.Name("Fix Atlas Combining Recipe Dupe")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.recipedupe.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    public boolean fixCombiningRecipe = true;

    @Config.Comment("Markers data is sent in one packet per dimension, which can get really large and lag the server. Keep this enabled to send the markers in chunks of 100 markers per packet to reduce lag on player login.")
    @Config.Name("Marker data in smaller packets")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.antiqueatlas.overhaul.markerpacketchunking.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    public boolean markerPacketChunking = true;

    @Config.Comment("Will allow to hide specific marker types when clicking on \"Hide markers\" in Atlas GUI. \n" +
            "Shift-click a marker icon to only show that one or disable all markers except for it.")
    @Config.Name("Allow hiding specific markers")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.antiqueatlas.displaydisablemarkertypes.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    public boolean disableSpecificMarkers = true;

    @Config.Comment("When putting a new marker on your atlas, will render the marker types to select from in a 7x3 box that scrolls vetically instead of a horizontal scroll area.")
    @Config.Name("Scroll Marker Types Vertically")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.antiqueatlas.selectmarkersvertically.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    public boolean verticalScrolling = true;

    @Config.Comment("Enable setting keybindings for the Add, Delete, and Hide/Show Marker buttons. This also removes the mouse position reset behavior of the delete marker button.")
    @Config.Name("Keybindings for Buttons")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.antiqueatlas.atlasbasickeybinds.json", defaultValue = true)
    @MixinConfig.CompatHandling(modid = "antiqueatlas", desired = true)
    public boolean basicKeybindings = true;
}
