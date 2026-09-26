package antiqueatlasautomarker.mixininterface;

import antiqueatlasautomarker.features.structuremarkers.CustomPosition;

public interface ICustomPosMarker {
    void aaam$setDiscoverPosition(CustomPosition pos);
    CustomPosition aaam$getDiscoverPosition();
    default boolean isCustomPosMarker(){ return aaam$getDiscoverPosition() != null; }
}
