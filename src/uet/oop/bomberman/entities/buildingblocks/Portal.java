package uet.oop.bomberman.entities.buildingblocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {
    public static boolean isPortal = false;

    public Portal(int x, int y, Image img, boolean collide) {
        super(x, y, img, collide);
    }

    @Override
    public void update() {

    }
}
