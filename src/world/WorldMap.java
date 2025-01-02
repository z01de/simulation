package world;

import auxiliary.Coordinates;
import entity.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WorldMap {
    private final int MAP_COLUMNS;
    private final int MAP_ROWS;
    public HashMap<Coordinates, Entity> worldMap;

    public WorldMap(int MAP_COLUMNS, int MAP_ROWS) {
        this.MAP_COLUMNS = MAP_COLUMNS;
        this.MAP_ROWS = MAP_ROWS;
        worldMap = new HashMap<>();
    }

    public void addEntity(Entity entity, Coordinates coordinates) {
        if(!worldMap.containsKey(coordinates) && coordinates.getColumn() <= MAP_COLUMNS && coordinates.getRow() <= MAP_ROWS) {
            worldMap.put(coordinates, entity);
        }
    }

    public Entity getEntity(Coordinates coordinates) {
        return worldMap.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        worldMap.remove(coordinates);
    }

    public void replaceEntity(Coordinates oldCoordinates, Coordinates newCoordinates) {
        Entity tempEntity = worldMap.get(oldCoordinates);
        worldMap.remove(oldCoordinates);
        addEntity(tempEntity, newCoordinates);
    }

    public HashMap<Coordinates, Entity> getWorldMap() {
        return new HashMap<>(worldMap);
    }

    public Set<Coordinates> getCoordinates() {
        return worldMap.keySet();
    }

    public int getMAP_COLUMNS() {
        return MAP_COLUMNS;
    }
    public int getMAP_ROWS() {
        return MAP_ROWS;
    }

    public Collection<Map.Entry<Coordinates, Entity>> getEntrySet(){
        return worldMap.entrySet();
    }

}
