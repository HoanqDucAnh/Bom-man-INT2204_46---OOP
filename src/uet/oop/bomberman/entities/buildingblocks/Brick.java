package uet.oop.bomberman.entities.buildingblocks;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemy.Monster;

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
