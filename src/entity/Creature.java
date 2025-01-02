package entity;

import auxiliary.Coordinates;
import world.WorldMap;

public abstract class Creature extends Entity {
    int healthPoints;
    int speed;

    public Creature(Coordinates coordinates, int healthPoints, int speed) {
        super(coordinates);
        this.healthPoints = healthPoints;
        this.speed = speed;
    }

    public abstract void makeMove(WorldMap worldMap);
}
