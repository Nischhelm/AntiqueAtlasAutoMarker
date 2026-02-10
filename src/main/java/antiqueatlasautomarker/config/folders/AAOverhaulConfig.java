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
    public boolean sendToAllHolding = true;

    @Config.Comment("AA doesn't allocate the correct size for some packets which can lead to crashes. This fixes it.")
    @Config.Name("Fix Crash with Short/IntDimensionUpdatePacket")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.bytebufcrashfix.json", defaultValue = true)
    public boolean byteBufCrashFix = true;

    @Config.Comment("Whenever Antique Atlas checks for atlases in a players inventory it forgets to also check the offhand. Set to true to check offhand as well.")
    @Config.Name("Also check player offhand for atlases")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.offhand.json", defaultValue = true)
    public boolean checkOffhand = true;

    @Config.Comment({
            "Allows players with the same atlases to see each other:",
            "\tDirectional arrows for players and hover to see names",
            "\tShow online player list key will swap to rendering player heads",
            "\tServer manages and provides client player positions"
    })
    @Config.Name("Show Position of Other Players")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.showotherplayers.json", defaultValue = true)
    public boolean showOtherPlayers = true;

    @Config.Comment({
            "Provides a variety of useful keybinds and buttons:",
                "\tAdd Marker - Keybind",
                "\tShow Markers - Keybind",
                "\tFollow Player - Keybind",
                "\tOpen Atlas - Keybind fixed to be usable with Atlas items.",
                "\tCopy Marker - Button to select a marker to share in chat or copy to clipboard.",
                "\tCompare Two Held Atlases - Button for viewing and copying over unique markers."
    })
    @Config.Name("Add Atlas Keybinds and Buttons")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.keybinds.json", defaultValue = true)
    public boolean addKeybinds = true;

    @Config.Comment("Antique Atlas uses a questionable regex to check if a marker label is a lang key (not allowing numbers for example), instead of using I18n.hasKey. It also only allows one parameter for parameterised lang keys. Both get fixed by this.")
    @Config.Name("Fix Atlas Marker Lang Keys")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.langkey.json", defaultValue = true)
    public boolean fixLangKeys = true;

    @Config.Comment("When combining atlases, the stack size of the output slot is not set correctly, resulting in a dupe. This fixes it.")
    @Config.Name("Fix Atlas Combining Recipe Dupe")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.recipedupe.json", defaultValue = true)
    public boolean fixCombiningRecipe = true;

    @Config.Comment("Markers data is sent in one packet per dimension, which can get really large and lag the server. Keep this enabled to send the markers in chunks of 100 markers per packet to reduce lag on player login.")
    @Config.Name("Marker data in smaller packets")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.antiqueatlas.overhaul.markerpacketchunking.json", defaultValue = true)
    public boolean markerPacketChunking = true;

    @Config.Comment("Will allow to hide specific marker types when clicking on \"Hide markers\" in Atlas GUI. \n" +
            "Shift-click a marker icon to only show that one or disable all markers except for it.\n" +
            "Also provides a searchbar to filter shown markers by their labels")
    @Config.Name("Allow hiding specific markers")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(
            earlyMixin = "mixins.aaam.vanilla.displaydisablemarkertypes.json",
            lateMixin = "mixins.aaam.antiqueatlas.displaydisablemarkertypes.json",
            defaultValue = true
    )
    public boolean disableSpecificMarkers = true;

    @Config.Comment("When putting a new marker on your atlas, will render the marker types to select from in a 7x3 box that scrolls vetically instead of a horizontal scroll area.")
    @Config.Name("Scroll Marker Types Vertically")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.antiqueatlas.selectmarkersvertically.json", defaultValue = true)
    public boolean verticalScrolling = true;

    @Config.Comment({
            "The area around every player is always scanned to check if anything has changed on the atlas or if theres newly explored chunks.",
            "By default this happens both on server and on client and is somewhat computation- and network-heavy.",
            "However, there isn't really a reason to do it on both sides.",
            "Due to a bug in AA, only the Atlas #0 was actually updated for both sides, all other atlases only updated on the clientside",
            "That's why shared atlases that arent #0 would not update the other players explored area except for client relogs",
            "To make shared atlases update the other players tiles, this config is set to SERVER by default.",
            "Setting to CLIENT will only remove networking overhead, as the server will still scan around the player, just not send to client.",
            "Setting to BOTH restores the behavior of old atlas #0 for all atlases, but i can't really see any upsides of it during gameplay (might be minimally smoother)",
            "This fix should also remove rare occurrences of the entries of one atlas bleeding into another of a totally different player",
            "Set to DISABLE_MIXIN to disable this fix."
    })
    @Config.Name("Atlas Scanning Update Side")
    @Config.RequiresMcRestart
    public UpdateSide updateSide = UpdateSide.SERVER;
    public enum UpdateSide { SERVER, CLIENT, BOTH, DISABLE_MIXIN }

    @Config.Name("Biome to Tile Config")
    public BiomeTileConfig tileConfig = new BiomeTileConfig();
}
