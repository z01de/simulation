package actions;

import geometry.Coordinates;
import entities.Predator;

public class PredatorGenerateAction extends EntityGenerateAction<Predator> {

    public PredatorGenerateAction() {
        spawnRate = 0.1;
    }

    @Override
    Predator createEntity() {
        return new Predator(new Coordinates(0,0),100,4);
    }
}
