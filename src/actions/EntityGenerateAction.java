package actions;

import entities.Entity;
import geometry.Coordinates;
import map.MapUtils;
import map.SimulationMap;

public abstract class EntityGenerateAction<T extends Entity> extends Action{
    private final String entityFullClassName = createEntity().getClass().getName();
    protected double spawnRate;

    @Override
    public void perform(SimulationMap simulationMap) {
        while(getRate(simulationMap) < spawnRate){
            Coordinates coordinates = simulationMap.getRandomEmptyCell();
            simulationMap.addEntity(coordinates, createEntity());
        }
    }

    public double getRate(SimulationMap simulationMap){
        double rate = (double) MapUtils.entityCounter(simulationMap, entityFullClassName) / (simulationMap.getColumnsCount() * simulationMap.getRowsCount());
        if(rate < 0){
            throw new IllegalArgumentException("Such entities do not exist");
        }
        return rate;
    }

    abstract T createEntity();
}
