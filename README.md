## Antique Atlas Auto Marker

Antique Atlases are great, but don't you hate having to mark everything yourself?

This mod adds a lot of automatic marking features for the Antique Atlas and fixes some bugs of the original mod.

### Interaction Markers (only needs the mod on clientside)
- Automatic marking of activated Waystones
  - with an option to auto rename the marker when the waystone is renamed.
  - and an option to always mark waystones players interact with, no matter if they activated them themselves (will make it impossible to not mark waystones you interact with)
- Automatic marking of Librarian book trades (can define in config which books you want it to mark)

### Structure Markers (needs mod on both client+server)
- Automatic marking of wild waystones
- Automatic marking of Dragon Roosts (Ice&Fire, any 1.12 version including RLCraft+RotN)
- Automatic marking of Cyclops Dens (Ice&Fire, same)
- Automatic marking of Hydra Lairs (I&F)
- Automatic marking of Lycanite Dungeons
- Automatic marking of Yung's Better Mineshafts
- Automatic marking of Doomlike Dungeons
- Automatic marking of Dungeons2 Dungeons
- Automatic marking of Battletowers
- Automatic marking of Ruins mod structures (custom config per structure)
- Can turn [AARC Addon](https://www.curseforge.com/minecraft/mc-mods/antiqueatlas-recurrentcomplex-compatability) global Markers into AAAM structure markers, will use client AARC config for the structure markers

Structure Markers:
- are global markers (saved on server) which are sent to the players atlas when the player discovers the chunks the structure is in.
- can be deleted from an atlas, but will stay for any other atlas.
- enable any client to use their own AAAM settings for the marker customisation (whether it should get marked, the marker icon and marker label)
AAAM turns any global marker that other mods might create into AAAM structure markers, bc AA global markers have performance issues and can't be deleted.

### Custom Position Structure Markers

Custom Position Markers can be defined in the serverside config which will have a specified location but will get discovered on a different location.
For example, a custom position marker could be discovered when a player gets to 0,0 which would make them receive a marker at a different position marking something they should check out.

### Bug fixes + Performance

- Antique Atlas sends Network Packets to all players whenever anything is modified on any Atlas (added/removed markers, added biome tiles etc). This is enormous overhead for no reason. Players can share atlases but they will receive that atlases data anyway, at least once they get it into their inventory for the first time since server last restarted. AAAM fixes that performance issue by only sending atlas data to players that actually have access to that specific atlas.
- Antique Atlas sends all Markers of one dimension in a single network packet when syncing on a player (logged in or pulled out a specific atlas for the first time since last server restart). These packets can get very large. AAAM splits those marker packets up into smaller bites of 100 markers each to hopefully reduce lag spikes.
- The Global Marker system of Antique Atlas is laggy + buggy. All global markers are sent to any player on login, which can create big lag spikes. Also they are impossible to remove for a client and do render twice in the minimap overlay. AAAM turns all global markers into AAAM structure markers which do not have these issues.
- Antique Atlas doesn't save Player Death Spots if the player had their atlas in their offhand. AAAM fixes that.

### Mod Support

Mods can use the ReceivedStructureMarkerEvent in order to do custom actions for a client that receives a structure marker.
They can also use the MarkStructureEvent to mark their own structures using this system (can register custom AutoMarkSettings to enable players to customise those markers on receive)

### Planned features

- Automatic marking of roguelike dungeons

### Community suggested features
Feel free to suggest more things to automatically mark in comments

- Remove dragon marker once the dragon is killed (also on player atlas)
- Mark destination points of vanilla maps you find (interaction marker)