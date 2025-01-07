package actions;

import entities.Tree;

public class TreeGenerateAction extends EntityGenerateAction<Tree> {

    public TreeGenerateAction() {
        spawnRate = 0.1;
    }

    @Override
    Tree createEntity() {
        return new Tree();
    }
}