package path;

import geometry.Coordinates;
import map.SimulationMap;

import java.util.Deque;

public abstract class PathFinder {
    public abstract Deque<Coordinates> findPath(Coordinates start, Coordinates end, SimulationMap simulationMap);
}
