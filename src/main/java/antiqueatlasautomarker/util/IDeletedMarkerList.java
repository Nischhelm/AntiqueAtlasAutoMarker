package antiqueatlasautomarker.util;

public interface IDeletedMarkerList {
    boolean markerIsDeleted(int markerID);
    void addDeletedMarker(int markerID);
}
