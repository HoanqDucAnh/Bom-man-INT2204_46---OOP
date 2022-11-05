package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Collision;
import uet.oop.bomberman.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

public class Unharmed extends Monster {


    public Unharmed(int xUnit, int yUnit, Image img, boolean isColidable, boolean isAlive) {
        super(xUnit, yUnit, img, isColidable, isAlive);
    }

    @Override
    public void update() {

    }

}
