package uet.oop.bomberman.entities;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.brick;

public class Brick extends Entity {
    public static int swapKill = 1;
    public  static long timeBrick;

    public static int temp;
    public static long timeTempBrick1;
    public static long timeTempBrick2;

    public Brick(int x, int y, Image img,boolean collidable) {
        super(x, y, img,collidable);
    }

    @Override
    public void update() {

    }

}
