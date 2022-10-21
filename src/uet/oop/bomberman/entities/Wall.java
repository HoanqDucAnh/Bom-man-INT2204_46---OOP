package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int x, int y, Image img, boolean collidable) {
        super(x, y, img,collidable);
    }

    @Override
    public void update() {

    }
}
