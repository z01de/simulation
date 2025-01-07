package path;

import geometry.Coordinates;
import geometry.Perpendicular;
import map.SimulationMap;

import java.util.*;

public class GreedyPathFinder extends PathFinder {

    @Override
    public Deque<Coordinates> findPath(Coordinates start, Coordinates end, SimulationMap simulationMap) {
        Deque<Coordinates> path = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>(); // Используем Set для отслеживания посещенных координат
        Coordinates current = start;
        visited.add(current); // Добавляем начальную координату в посещенные

        while(new Perpendicular(current,end).getLength() != 1){
            List<Coordinates> neighbours = getNeighbours(current);
            Coordinates next = getPrioriryCoordinates(neighbours, start, end, simulationMap, visited);
            if (next.equals(start)) {
                return path; // Если не можем двигаться дальше, возвращаем текущий путь
            }
            path.addLast(next);
            visited.add(next); // Добавляем следующую координату в посещенные
            current = next; // Обновляем текущую координату
        }
        path.addLast(end);
        return path;
    }

    private List<Coordinates> getNeighbours(Coordinates coordinates) {
        return new ArrayList<Coordinates>(List.of(
                new Coordinates(coordinates.column() + 1,coordinates.row()),
                new Coordinates(coordinates.column() - 1,coordinates.row()),
                new Coordinates(coordinates.column(),coordinates.row() + 1),
                new Coordinates(coordinates.column(),coordinates.row() - 1)
        ));
    }

    private Coordinates getPrioriryCoordinates(List<Coordinates> coordinates, Coordinates start, Coordinates end, SimulationMap simulationMap, Set<Coordinates> visited) {
        List<Perpendicular> neighboursPerpendicularsToTarget = new ArrayList<>();
        for (Coordinates coordinate : coordinates) {
            if (!visited.contains(coordinate)) { // Проверяем, была ли координата посещена
                neighboursPerpendicularsToTarget.add(new Perpendicular(coordinate, end));
            }
        }

        neighboursPerpendicularsToTarget.removeIf(perpendicular -> perpendicular.start().column() < 0 || perpendicular.start().row() < 0);
        neighboursPerpendicularsToTarget.removeIf(perpendicular -> perpendicular.start().column() >= simulationMap.getColumnsCount() || perpendicular.start().row() >= simulationMap.getRowsCount());
        neighboursPerpendicularsToTarget.removeIf(perpendicular -> !simulationMap.isCellEmpty(perpendicular.start()));

        neighboursPerpendicularsToTarget.sort(Perpendicular::compareTo);

        if (neighboursPerpendicularsToTarget.isEmpty()) {
            return start;
        }
        return neighboursPerpendicularsToTarget.getFirst().start();
    }
}
