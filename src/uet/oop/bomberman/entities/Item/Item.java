package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Item extends Entity {

    public Item(int xUnit, int yUnit, Image img, boolean colidable) {
        super(xUnit, yUnit, img, colidable);
    }

    @Override
    public void update() {

    }
}
