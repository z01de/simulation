package callback;

import geometry.Coordinates;

public class HerbivoreMoveCallBack implements MoveCallBack {
    @Override
    public void onMove(Coordinates from, Coordinates to) {
        System.out.println("Herbivore moves from: " + from + " to " + to);
    }
}
