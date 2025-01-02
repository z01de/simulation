package entity;

import auxiliary.Coordinates;

public class Rock extends Entity {

    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String toString() {
        return "R";
    }
}
