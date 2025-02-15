## AntiqueAtlasAutoMarker

Antique Atlases are great, but don't you hate having to mark everything yourself?

This mod adds a lot of automatic marking features for the Antique Atlas.

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
- Can turn AARC Addon global Markers into AAAM structure markers, will use client AARC config for the added marker

Structure Markers
- are global markers (saved on server) which are sent to the players atlas when the player discovers the chunks the structure is in.
- can be deleted from an atlas, but will stay for any other atlas.
- enable any client to use their own AAAM settings for the marker customisation (whether it should get marked, the marker icon and marker label)
AAAM turns any global marker that other mods might create into AAAM structure markers, bc AA global markers have performance issues and can't be deleted.

### Planned features

- Automatic marking of roguelike dungeons
- Automatic marking of Ruins mod structures

### Community suggested features
Feel free to suggest more things to automatically mark in comments

- Remove dragon marker once the dragon is killed (both on player atlas and on structure marker list server side)