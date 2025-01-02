package auxiliary;

import entity.*;
import world.WorldMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Actions {

    public static void initActions(WorldMap worldMap, int predatorCount, int herbivoreCount, int grassCount, int rockCount){
        mapFill(worldMap, predatorCount, herbivoreCount, grassCount, rockCount);
        Renderer renderer = new Renderer();
        renderer.render(worldMap);
    }

    public static void turnActions(WorldMap worldMap){
        if(entityCounter(worldMap,1) == 1){
            mapFill(worldMap,2,entityCounter(worldMap,2),entityCounter(worldMap,3),entityCounter(worldMap,4));
            System.out.println("*New predator added* \n");
        }
        if(entityCounter(worldMap,2) == 1){
            mapFill(worldMap,entityCounter(worldMap,1),2,entityCounter(worldMap,3),entityCounter(worldMap,4));
            System.out.println("*New herbivore added* \n");
        }
        if(entityCounter(worldMap,3) == 1){
            mapFill(worldMap,entityCounter(worldMap,1),entityCounter(worldMap,2),2,entityCounter(worldMap,4));
            System.out.println("*New grass added* \n");
        }
        while(true){
            Random random = new Random();
            int column = random.nextInt(worldMap.getMAP_COLUMNS());
            int row = random.nextInt(worldMap.getMAP_ROWS());
            if(worldMap.getEntity(new Coordinates(column, row)) instanceof Creature creature){
                creature.makeMove(worldMap);
                break;
            }
        }
        Renderer renderer = new Renderer();
        renderer.render(worldMap);
    }

    private static void mapFill(WorldMap worldMap, int predatorCount, int herbivoreCount, int grassCount, int rockCount){
        Random random = new Random();
        while(entityCounter(worldMap,1) != predatorCount){
            int randomColumn = random.nextInt(worldMap.getMAP_COLUMNS());
            int randomRow = random.nextInt(worldMap.getMAP_ROWS());
            Coordinates predatorCoordinates = new Coordinates(randomColumn, randomRow);
            worldMap.addEntity(new Predator(predatorCoordinates,100,4), predatorCoordinates);
        }
        while(entityCounter(worldMap,2) != herbivoreCount){
            int randomColumn = random.nextInt(worldMap.getMAP_COLUMNS());
            int randomRow = random.nextInt(worldMap.getMAP_ROWS());
            Coordinates herbivoreCoordinates = new Coordinates(randomColumn, randomRow);
            worldMap.addEntity(new Herbivore(herbivoreCoordinates,50,6), herbivoreCoordinates);
        }
        while(entityCounter(worldMap,3) != grassCount){
            int randomColumn = random.nextInt(worldMap.getMAP_COLUMNS());
            int randomRow = random.nextInt(worldMap.getMAP_ROWS());
            Coordinates grassCoordinates = new Coordinates(randomColumn, randomRow);
            worldMap.addEntity(new Grass(grassCoordinates), grassCoordinates);
        }
        while(entityCounter(worldMap,4) != rockCount){
            int randomColumn = random.nextInt(worldMap.getMAP_COLUMNS());
            int randomRow = random.nextInt(worldMap.getMAP_ROWS());
            Coordinates rockCoordinates = new Coordinates(randomColumn, randomRow);
            worldMap.addEntity(new Rock(rockCoordinates), rockCoordinates);
        }
    }

    private static int entityCounter(WorldMap worldMap, int selector){
        int predatorCount = 0;
        int herbivoreCount = 0;
        int grassCount = 0;
        int rockCount = 0;
        Set<Coordinates> selected = new HashSet<>(worldMap.getCoordinates());
        for(Coordinates coordinates : selected) {
            if(worldMap.getEntity(coordinates) instanceof Predator) {
                predatorCount++;
            }
            if(worldMap.getEntity(coordinates) instanceof Herbivore) {
                herbivoreCount++;
            }
            if(worldMap.getEntity(coordinates) instanceof Grass) {
                grassCount++;
            }
            if(worldMap.getEntity(coordinates) instanceof Rock) {
                rockCount++;
            }
        }
        return switch (selector) {
            case 1 -> predatorCount;
            case 2 -> herbivoreCount;
            case 3 -> grassCount;
            case 4 -> rockCount;
            default -> 0;
        };
    }
    public static Coordinates getClosestGrass(WorldMap worldMap, Coordinates entityCoordinates){
        Set<Coordinates> entitiesCoordinates = worldMap.getCoordinates();
        ArrayList<Path> grassesCoordinates = new ArrayList<>();
        for(Coordinates coordinates : entitiesCoordinates) {
            if (worldMap.getEntity(coordinates) instanceof Grass) {
                grassesCoordinates.add(new Path(entityCoordinates, coordinates));
            }
        }
        grassesCoordinates.sort(Path::compareTo);
        return grassesCoordinates.getFirst().getPathEnd();
    }

    public static Coordinates getClosestHerbivore(WorldMap worldMap, Coordinates entityCoordinates){
        Set<Coordinates> entitiesCoordinates = worldMap.getCoordinates();
        ArrayList<Path> herbivoreCoordinates = new ArrayList<>();
        for(Coordinates coordinates : entitiesCoordinates) {
            if (worldMap.getEntity(coordinates) instanceof Herbivore) {
                herbivoreCoordinates.add(new Path(entityCoordinates, coordinates));
            }
        }
        herbivoreCoordinates.sort(Path::compareTo);
        return herbivoreCoordinates.getFirst().getPathEnd();
    }
}
