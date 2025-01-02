package entity;

import auxiliary.Actions;
import auxiliary.Coordinates;
import auxiliary.Path;
import world.WorldMap;

import java.util.ArrayList;
import java.util.Arrays;

public class Predator extends Creature {

    private final ArrayList<Coordinates> patchesRemember = new ArrayList<>();

    public Predator(Coordinates coordinates, int healthPoints, int speed) {
        super(coordinates, healthPoints, speed);
    }

    @Override
    public void makeMove(WorldMap worldMap){
        Coordinates closestHerbivore = Actions.getClosestHerbivore(worldMap,coordinates);
        Coordinates coordinatesToMove = coordinates;
        
        for(int step = 1; step != speed + 1; step++) {
            ArrayList<Path> sequelsPatchesToMove = getPaths(coordinatesToMove, closestHerbivore,worldMap);
            
            for(Path path : sequelsPatchesToMove) {
                if(new Path(coordinatesToMove,    closestHerbivore).getPathLength() == 1 && healthPoints > 0) {
                    worldMap.removeEntity(closestHerbivore);
                    worldMap.replaceEntity(coordinates, closestHerbivore);
                    healthPoints += 15;
                    patchesRemember.clear();
                    System.out.println("Predator " + coordinates + " eat herbivore in: " + closestHerbivore);
                    coordinates = closestHerbivore;
                    System.out.println("Predator health points is: " + healthPoints);
                    return;
                }
                if(worldMap.getEntity(path.getPathStart()) == null && !patchesRemember.contains(path.getPathStart()) && healthPoints > 0) {
                    coordinatesToMove = path.getPathStart();
                    patchesRemember.add(path.getPathStart());
                    healthPoints -= 5;
                    if(healthPoints > 0) {
                        break;
                    }
                    else {
                        worldMap.removeEntity(coordinates);
                        System.out.println("Predator " + coordinates + " is dead");
                        return;
                    }
                }
            }
        }
        worldMap.replaceEntity(coordinates, coordinatesToMove);
        System.out.println("Predator " + coordinates + " hunting herbivore in: " + closestHerbivore);
        coordinates = coordinatesToMove;
        System.out.println("Predator moves to: " + coordinatesToMove);
        System.out.println("Predator health points is: " + healthPoints);
    }

    private static ArrayList<Path> getPaths(Coordinates coordinateToMove, Coordinates closestHerbivore, WorldMap worldMap) {
        Path upCellPath = new Path(new Coordinates(coordinateToMove.getColumn(), coordinateToMove.getRow()+1), closestHerbivore);
        Path downCellPath = new Path(new Coordinates(coordinateToMove.getColumn(), coordinateToMove.getRow()-1), closestHerbivore);
        Path leftCellPath = new Path(new Coordinates(coordinateToMove.getColumn()-1, coordinateToMove.getRow()), closestHerbivore);
        Path rightCellPath = new Path(new Coordinates(coordinateToMove.getColumn()+1, coordinateToMove.getRow()), closestHerbivore);
        
        ArrayList<Path> sequelsPatchesToMove = new ArrayList<>(Arrays.asList(upCellPath, downCellPath, leftCellPath, rightCellPath));
        
        sequelsPatchesToMove.removeIf(path -> path.getPathStart().getColumn() < 0 || path.getPathStart().getRow() < 0 ||
                path.getPathEnd().getColumn() > worldMap.getMAP_COLUMNS()-1 || path.getPathEnd().getRow() > worldMap.getMAP_ROWS()-1);
        
        sequelsPatchesToMove.sort(Path::compareTo);
        
        return sequelsPatchesToMove;
    }

    @Override
    public String toString() {
        return "P";
    }
}
