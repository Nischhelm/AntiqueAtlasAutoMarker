package antiqueatlasautomarker.mixininterface;

public interface IDeletedMarkerList {
    boolean aaam$markerIsDeleted(int markerID);
    void aaam$addDeletedMarker(int markerID);
}
