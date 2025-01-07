package actions;

import callback.AddCallBack;
import callback.EntityAddCallBack;
import entities.Entity;
import geometry.Coordinates;
import map.MapUtils;
import map.SimulationMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RefillerAction extends Action {

    private static final List<String> capableOfDyingOutClassNames = new ArrayList<>(Arrays.asList(
            "entities.Predator",
            "entities.Herbivore",
            "entities.Grass"
    ));

    @Override
    public void perform(SimulationMap simulationMap) {
        AddCallBack addCallBack = new EntityAddCallBack();
        for (String fullClassName : capableOfDyingOutClassNames) {
            try {
                Class<?> capableClass = Class.forName(fullClassName);
                if (MapUtils.entityCounter(simulationMap, fullClassName) <= 1) {
                    Entity entity = (Entity) capableClass.getDeclaredConstructor().newInstance();
                    Coordinates randomEmptyCell = simulationMap.getRandomEmptyCell();
                    simulationMap.addEntity(randomEmptyCell, entity);
                    addCallBack.callback(randomEmptyCell);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Class not found: " + fullClassName, e);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Could not create instance of: " + fullClassName, e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("No suitable constructor found for: " + fullClassName, e);
            } catch (Exception e) {
                throw new RuntimeException("An error occurred while trying to perform refilling action: " + e.getMessage(), e);
            }
        }
    }
}
