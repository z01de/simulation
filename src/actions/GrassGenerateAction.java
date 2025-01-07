package actions;

import entities.Grass;

public class GrassGenerateAction extends EntityGenerateAction<Grass> {

    public GrassGenerateAction() {
        spawnRate = 0.1;
    }

    @Override
    Grass createEntity() {
        return new Grass();
    }
}