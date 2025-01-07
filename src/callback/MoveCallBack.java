package callback;

import geometry.Coordinates;

public interface MoveCallBack {
    void onMove(Coordinates from, Coordinates to);
}
