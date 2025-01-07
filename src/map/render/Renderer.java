package map.render;

import entities.Entity;
import geometry.Coordinates;
import map.SimulationMap;

public class Renderer implements Renderable {

    @Override
    public void render(SimulationMap simulationMap) {
        int columns = simulationMap.getColumnsCount();
        int rows = simulationMap.getRowsCount();
        System.out.println();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(simulationMap.isCellEmpty(new Coordinates(i, j))){
                    System.out.print("[" + EntitiesSprites.EMPTY_UNICODE_SPRITE.getSprite() + "]");
                }
                else{
                    System.out.print("[" + getSprite(simulationMap.getEntity(new Coordinates(i, j))) + "]");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private String getSprite(Entity entity)  {
        return switch (entity.getClass().getSimpleName()) {
            case "Predator" -> EntitiesSprites.PREDATOR_UNICODE_SPRITE.getSprite();
            case "Herbivore" -> EntitiesSprites.HERBIVORE_UNICODE_SPRITE.getSprite();
            case "Grass" -> EntitiesSprites.GRASS_UNICODE_SPRITE.getSprite();
            case "Rock" -> EntitiesSprites.ROCK_UNICODE_SPRITE.getSprite();
            case "Tree" -> EntitiesSprites.TREE_UNICODE_SPRITE.getSprite();
            default -> throw new IllegalArgumentException("Sprite not found");
        };
    }

}
