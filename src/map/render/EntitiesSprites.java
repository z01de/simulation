package map.render;

public enum EntitiesSprites {
    PREDATOR_UNICODE_SPRITE("\uD83E\uDD81"),
    HERBIVORE_UNICODE_SPRITE("\uD83D\uDC11"),
    GRASS_UNICODE_SPRITE("\uD83C\uDF31"),
    ROCK_UNICODE_SPRITE("⛰️"),
    TREE_UNICODE_SPRITE("\uD83C\uDFDD️"),
    EMPTY_UNICODE_SPRITE("\uD83D\uDFEB");

    private final String sprite;

    EntitiesSprites(String sprite) {
        this.sprite = sprite;
    }

    public String getSprite() {
        return sprite;
    }
}
