package uet.oop.bomberman.entities.buildingblocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Wall extends Entity {

    public Wall(int x, int y, Image img, boolean collidable) {
        super(x, y, img,collidable);
    }

    @Override
    public void update() {

    }
}
