package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Portal extends Entity {
    public static boolean isPortal = false;

    public Portal(int x, int y, Image img, boolean collide) {
        super(x, y, img, collide);
    }

    @Override
    public void update() {

    }
}
