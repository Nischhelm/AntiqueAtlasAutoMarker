### sendToAll (Server->Client)
MarkersPacket
  - AddMarkerPacket.process (client creates)
  - MarkersAPIimpl.doPutMarker (server creates)
    - whenever any marker is created

DeleteMarkerPacket
  - MarkersAPIimpl.doDeleteMarker
    - whenever a marker is deleted

(Int/Short)TilesPacket
- TileApiImpl.putCustomGlobalTile

(Int/Short)DimensionUpdatePacket
- ItemAtlas.onUpdate

TileNameIdPacket
  - TileApiImpl.putCustomTile
  - TileApiImpl.putCustomGlobalTile
  - RegisterTileIdPacket.process
    - fine

DeleteCustomGlobalTilePacket
  - TileApiImpl.deleteCustomGlobalTile
    - when is that ever fired

### sendTo (Server->Client)
MarkersPacket
  - MarkersData.syncOnPlayer

OptionalMarkerPacket
  - NetherPortalWatcher.addPortalMarkerIfNone	-

MapDataPacket
  - AtlasData.syncOnPlayer 

TileGroupsPacket
  - DimensionData.syncOnPlayer
  - multiple packets of 100 tiles per packet 

TileNameIDPacket
  - ExtTileIdMap.syncOnPlayer

(Int/Short)TilesPacket
  - ExtBiomeData.syncOnPlayer

PutBiomeTilePacket
  - TileApiImpl.putBiomeTile

### sendToServer (Client -> Server)
AddMarkerPacket
  - MarkersAPIimpl.doPutMarker
    - fine, just sending new marker to server

DeleteMarkerPacket
  - MarkersAPIimpl.doDeleteMarker
    - fine, just sending deleted marker to server

RegisterTileIdPacket
  - TileApiImpl.putCustomTile

PutBiomeTilePacket
  - TileApiImpl.putBiomeTile
    - fine, we just create new biome tiles

BrowingPositionPacket
  - GUIAtlas.onGuiClosed
    - fine even though uneccessary. just saving browsing position on server on gui close