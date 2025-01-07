package actions;

import geometry.Coordinates;
import entities.Herbivore;

public class HerbivoreGenerateAction extends EntityGenerateAction<Herbivore> {

    public HerbivoreGenerateAction() {
        spawnRate = 0.1;
    }

    @Override
    Herbivore createEntity() {
        return new Herbivore(new Coordinates(0,0),75,4);
    }
}