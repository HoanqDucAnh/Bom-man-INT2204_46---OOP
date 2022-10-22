package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Item extends Entity {

    public Item(int xUnit, int yUnit, Image img, boolean colidable) {
        super(xUnit, yUnit, img, colidable);
    }

    @Override
    public void update() {

    }
}
