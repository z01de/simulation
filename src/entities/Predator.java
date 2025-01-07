package entities;

import callback.MoveCallBack;
import callback.PredatorMoveCallBack;
import geometry.Coordinates;
import map.SimulationMap;

public class Predator extends Creature {
    private final int attackPower;

    public Predator(){
        super(new Coordinates(0,0),5);
        this.attackPower = 15;
    }

    public Predator(Coordinates coordinates, int speed, int attackPower) {
        super(coordinates, speed);
        this.attackPower = attackPower;
    }

    @Override
    MoveCallBack getMoveCallBack() {
        return new PredatorMoveCallBack();
    }

    @Override
    boolean interactWithTarget(SimulationMap simulationMap, Coordinates closestTarget) {
        Herbivore attackedTarget = ((Herbivore) simulationMap.getEntity(closestTarget));
        if(attackedTarget.getHealthPoints() > attackPower) {
            attackedTarget.setHealthPoints(attackedTarget.getHealthPoints() - attackPower);
            return false;
        }
        simulationMap.removeEntity(closestTarget);
        simulationMap.replaceEntity(coordinates, closestTarget);
        return true;
    }
}
