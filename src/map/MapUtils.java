package map;

import entities.Herbivore;
import entities.Predator;
import geometry.Coordinates;
import geometry.Perpendicular;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MapUtils {
    public static int entityCounter(SimulationMap simulationMap, String fullClassName) {
        try {
            Class<?> entityClass = Class.forName(fullClassName);
            return (int) simulationMap.getAllCoordinates().stream()
                    .filter(coordinates -> entityClass.isInstance(simulationMap.getEntity(coordinates)))
                    .count();
        }catch (ClassNotFoundException e) {
            return -1;
        }
    }

    public static Coordinates getClosestTarget(SimulationMap simulationMap, Coordinates entityCoordinates){
        String targetClassName = "";
        Set<Coordinates> allEntitiesCoordinates = simulationMap.getAllCoordinates();

        if(simulationMap.getEntity(entityCoordinates) instanceof Herbivore){
            targetClassName = "entities.Grass";
        }
        else if(simulationMap.getEntity(entityCoordinates) instanceof Predator){
            targetClassName = "entities.Herbivore";
        }

        List<Perpendicular> perpendicularsToTarget = new ArrayList<>();

        try {
            Class<?> targetClass = Class.forName(targetClassName);
            allEntitiesCoordinates.stream()
                    .filter(coordinates -> targetClass.isInstance(simulationMap.getEntity(coordinates)))
                    .forEach(coordinates -> perpendicularsToTarget.add(new Perpendicular(coordinates, entityCoordinates)));

            perpendicularsToTarget.sort(Perpendicular::compareTo);

            if(perpendicularsToTarget.isEmpty()){
                throw new RuntimeException("Target not found");
            }
            return perpendicularsToTarget.getFirst().start();

        }catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find class " + targetClassName);
        }
    }

}
