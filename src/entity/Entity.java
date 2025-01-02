package entity;

import auxiliary.Coordinates;

public abstract class Entity {
    Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
