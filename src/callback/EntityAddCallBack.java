package callback;

import geometry.Coordinates;

public class EntityAddCallBack implements AddCallBack {

    @Override
    public void callback(Coordinates coordinates) {
        System.out.println("Added new instance at: " + coordinates);
    }
}
