package antiqueatlasautomarker.config.folders;

import net.minecraftforge.common.config.Config;

public class InternalConfig {
    @Config.Comment("AAAM can fire events when players receive a structure marker to enable mods to do custom actions for specific markers. Set to false for performance if no mods in the pack subscribe to the event")
    @Config.Name("Fire ReceivedStructureMarkerEvents on client")
    public boolean fireReceivedMarkerEvent = false;

    @Config.Name("Send Debug Messages")
    public boolean doDebugLogs = false;
}
