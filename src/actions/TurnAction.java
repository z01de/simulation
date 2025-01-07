package actions;

import entities.Creature;
import geometry.Coordinates;
import map.SimulationMap;

import java.util.Random;

public class TurnAction extends Action {
    @Override
    public void perform(SimulationMap simulationMap) {
        Random random = new Random();
        while(true) {
            int randomColumn = random.nextInt(simulationMap.getColumnsCount());
            int randomRow = random.nextInt(simulationMap.getRowsCount());
            Coordinates randomCoordinates = new Coordinates(randomColumn, randomRow);
            if(simulationMap.getEntity(randomCoordinates) instanceof Creature creature) {
                creature.makeMove(simulationMap);
                break;
            }
        }

    }
}
