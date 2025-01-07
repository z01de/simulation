package entities;

import callback.HerbivoreMoveCallBack;
import callback.MoveCallBack;
import geometry.Coordinates;
import map.SimulationMap;

public class Herbivore extends Creature{
    private int healthPoints;

    public Herbivore(){
        super(new Coordinates(0,0),4);
        healthPoints = 50;
    }

    public Herbivore(Coordinates coordinates, int speed, int healthPoints) {
        super(coordinates, speed);
        this.healthPoints = healthPoints;
    }

    @Override
    MoveCallBack getMoveCallBack() {
        return new HerbivoreMoveCallBack();
    }

    @Override
    boolean interactWithTarget(SimulationMap simulationMap, Coordinates closestTarget) {
        simulationMap.replaceEntity(coordinates, closestTarget);
        return true;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
