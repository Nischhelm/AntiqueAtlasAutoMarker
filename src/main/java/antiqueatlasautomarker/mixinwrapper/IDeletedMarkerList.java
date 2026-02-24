package antiqueatlasautomarker.mixinwrapper;

public interface IDeletedMarkerList {
    boolean markerIsDeleted(int markerID);
    void addDeletedMarker(int markerID);
}
