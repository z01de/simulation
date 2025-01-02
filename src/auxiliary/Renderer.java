package auxiliary;

import world.WorldMap;

public class Renderer {
    public void render(WorldMap worldMap) {
        int columns = worldMap.getMAP_COLUMNS();
        int rows = worldMap.getMAP_ROWS();
        System.out.println();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(worldMap.getEntity(new Coordinates(i,j)) == null) System.out.print("[   ]");
                else System.out.print("[ " + worldMap.getEntity(new Coordinates(i,j)) + " ]");
            }
            System.out.println();
        }
        System.out.println();
    }

}
