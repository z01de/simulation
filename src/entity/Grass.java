package entity;

import auxiliary.Coordinates;

public class Grass extends Entity{

    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String toString() {
        return "G";
    }
}
