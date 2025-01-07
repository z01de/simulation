package map;

import entities.Creature;
import entities.Entity;
import geometry.Coordinates;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SimulationMap {
    private final Map<Coordinates, Entity> coordinatesWithEntities;
    private final int columnsCount;
    private final int rowsCount;

    public SimulationMap(int columnsCount, int rowsCount) {
        coordinatesWithEntities = new HashMap<>();
        this.columnsCount = columnsCount;
        this.rowsCount = rowsCount;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        if (isCellEmpty(coordinates) && coordinates.column() <= columnsCount && coordinates.row() <= rowsCount) {
            coordinatesWithEntities.put(coordinates, entity);
            if(entity instanceof Creature) {
                ((Creature) entity).setCoordinates(coordinates);
            }
        }
    }

    public void removeEntity(Coordinates coordinates) {
        coordinatesWithEntities.remove(coordinates);
    }

    public void replaceEntity(Coordinates oldCoordinates, Coordinates newCoordinates) {
        Entity tempEntity = coordinatesWithEntities.get(oldCoordinates);
        removeEntity(oldCoordinates);
        if(isCellEmpty(newCoordinates)) {
            addEntity(newCoordinates, tempEntity);
            return;
        }
        removeEntity(newCoordinates);
        addEntity(newCoordinates, tempEntity);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !coordinatesWithEntities.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return coordinatesWithEntities.get(coordinates);
    }

    public Coordinates getRandomEmptyCell() {
        if(getAllCoordinates().size() >= columnsCount * rowsCount) {
            throw new IllegalArgumentException("There are no empty fields on the map");
        }
        Random random = new Random();
        while(true){
            int randomRow = random.nextInt(rowsCount);
            int randomColumn = random.nextInt(columnsCount);
            Coordinates coordinates = new Coordinates(randomColumn, randomRow);
            if(isCellEmpty(coordinates)){
                return coordinates;
            }
        }
    }

    public Set<Coordinates> getAllCoordinates() {
        return coordinatesWithEntities.keySet();
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public int getRowsCount() {
        return rowsCount;
    }
}
