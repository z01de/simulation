package actions;

import entities.Rock;

public class RockGenerateAction extends EntityGenerateAction<Rock> {

    public RockGenerateAction() {
        spawnRate = 0.1;
    }

    @Override
    Rock createEntity() {
        return new Rock();
    }
}