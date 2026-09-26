package antiqueatlasautomarker.mixinwrapper;

public interface IDeletedMarkerList {
    boolean aaam$markerIsDeleted(int markerID);
    void aaam$addDeletedMarker(int markerID);
}
