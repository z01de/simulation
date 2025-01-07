package path;

import geometry.Coordinates;

import java.util.ArrayDeque;
import java.util.Deque;

public class Path {
    private final Deque<Coordinates> queuedCoordinates;

    public Path() {
        this.queuedCoordinates = new ArrayDeque<>();
    }

    public void addCoordinateToPathEnd(Coordinates coordinates) {
        queuedCoordinates.addLast(coordinates);
    }


    public Coordinates goToFirstCoordinates() {
        return queuedCoordinates.pollFirst();
    }

    public Coordinates getLastCoordinates() {
        return queuedCoordinates.peekLast();
    }

    public int getSize(){
        return queuedCoordinates.size();
    }

    public void print() {
        System.out.println(queuedCoordinates);
    }
}
