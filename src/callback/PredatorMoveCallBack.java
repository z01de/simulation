package callback;

import geometry.Coordinates;

public class PredatorMoveCallBack implements MoveCallBack {

    @Override
    public void onMove(Coordinates from, Coordinates to) {
        System.out.println("Predator moves from: " + from + " to " + to);
    }
}
