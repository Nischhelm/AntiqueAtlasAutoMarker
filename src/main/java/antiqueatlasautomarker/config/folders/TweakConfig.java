package antiqueatlasautomarker.config.folders;

import antiqueatlasautomarker.AntiqueAtlasAutoMarker;
import fermiumbooter.annotations.MixinConfig;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = AntiqueAtlasAutoMarker.MODID)
public class TweakConfig {
    @Config.Comment({
            "Displays other players on a shared atlas:",
            "  Directional arrows for players. Hover to see names",
            "  \"List Players\" key (TAB) will swap to rendering player heads"
    })
    @Config.Name("Show Position of Other Players")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.showotherplayers.json", defaultValue = true)
    public boolean showOtherPlayers = true;

    @Config.Comment({
            "Provides a variety of useful keybinds and buttons:",
            "  Add Marker - Keybind",
            "  Show Markers - Keybind",
            "  Follow Player - Keybind",
            "  Open Atlas - Keybind fixed to be usable with Atlas items.",
            "  Copy Marker - Button to select a marker to share in chat or copy to clipboard.",
            "  Compare Two Held Atlases - Button for viewing and copying over unique markers."
    })
    @Config.Name("Add Atlas Keybinds and Buttons")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin  = "mixins.aaam.antiqueatlas.overhaul.keybinds.json", defaultValue = true)
    public boolean addKeybinds = true;

    @Config.Comment("Will allow to hide specific marker types when clicking on \"Hide markers\" in Atlas GUI. \n" +
            "Shift-click a marker icon to only show that one or disable all markers except for it.\n" +
            "Also provides a searchbar to filter shown markers by their labels")
    @Config.Name("Allow hiding specified markers")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(
            earlyMixin = "mixins.aaam.vanilla.displaydisablemarkertypes.json",
            lateMixin = "mixins.aaam.antiqueatlas.displaydisablemarkertypes.json",
            defaultValue = true
    )
    public boolean hideMarkersByTypeAndLabel = true;

    @Config.Comment("When putting a new marker on your atlas, will render the marker types to select from in a 7x3 box that scrolls vertically instead of a horizontal scroll area.")
    @Config.Name("Scroll Marker Types Vertically")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.antiqueatlas.selectmarkersvertically.json", defaultValue = true)
    public boolean verticalScrolling = true;

    @Config.Comment("Shift clicking on a marker with the eraser tool will offer to delete all matching (type+label) markers. Confirm by clicking on link sent in chat.")
    @Config.Name("Shift Delete Matching Markers")
    @Config.RequiresMcRestart
    @MixinConfig.MixinToggle(lateMixin = "mixins.aaam.antiqueatlas.overhaul.shiftdelete.json", defaultValue = true)
    public boolean shiftDeleteMarkers = true;
}
