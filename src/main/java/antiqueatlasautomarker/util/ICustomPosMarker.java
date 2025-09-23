package antiqueatlasautomarker.util;

import antiqueatlasautomarker.structuremarkers.CustomPosition;

public interface ICustomPosMarker {
    void aaam$setDiscoverPosition(CustomPosition pos);
    CustomPosition aaam$getDiscoverPosition();
    default boolean isCustomPosMarker(){ return aaam$getDiscoverPosition() != null; }
}
