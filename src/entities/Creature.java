package entities;

import geometry.Coordinates;
import geometry.Perpendicular;
import map.MapUtils;
import map.SimulationMap;
import path.GreedyPathFinder;
import path.PathFinder;
import callback.MoveCallBack;

import java.util.Deque;

public abstract class Creature extends Entity {
    protected Coordinates coordinates;
    private final int speed;

    public Creature(Coordinates coordinates, int speed) {
        this.coordinates = coordinates;
        this.speed = speed;
    }

    public void makeMove(SimulationMap simulationMap) {
        MoveCallBack callBack = getMoveCallBack();
        PathFinder pathFinder = new GreedyPathFinder();
        Coordinates closestTarget = MapUtils.getClosestTarget(simulationMap, coordinates);
        Coordinates startCoordinates = coordinates;
        Deque<Coordinates> path = pathFinder.findPath(coordinates, closestTarget, simulationMap);
        int step = 0;
        while (step < speed) {
            if (new Perpendicular(coordinates, closestTarget).getLength() == 1) {
                boolean isEaten = interactWithTarget(simulationMap, closestTarget);
                if (isEaten) {
                    callBack.onMove(startCoordinates, closestTarget);
                }
                else {
                    callBack.onMove(startCoordinates, coordinates);
                }
                return;
            }
            if (path.isEmpty()) {
                callBack.onMove(startCoordinates, coordinates);
                return;
            }
            Coordinates newCoordinates = path.pollFirst();
            simulationMap.replaceEntity(coordinates, newCoordinates);
            if(step == speed - 1) {
                callBack.onMove(startCoordinates, newCoordinates);
            }
            step++;
        }
    }

    abstract MoveCallBack getMoveCallBack();

    abstract boolean interactWithTarget(SimulationMap simulationMap, Coordinates closestTarget);

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
}
