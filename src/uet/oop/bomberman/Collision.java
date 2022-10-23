package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.*;

public class Collision {
    public static boolean collision(CollisionChecker collisionCheckerer, Rectangle player) {
        for (Entity stillObject : stillObjects) {
            if (stillObject.isColidable()) {
                collisionCheckerer = new CollisionChecker(player, stillObject.getSolidArea());

                if (collisionCheckerer.isColided()) {

                    return true;
                }
            }
        }
        return false;
    }

    public static boolean collisionItemSpeed(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, items.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    items.get(i).setColidable(false);
                    items.get(i).setImg(Sprite.grass.getFxImage());
                    items.remove(i);
                    return true;

                }
            }
        }
        return false;
    }

    public static boolean collisionBrick(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < brick.size(); i++) {
            if (brick.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, brick.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    brick.get(i).setColidable(false);
                    brick.get(i).setImg(Sprite.grass.getFxImage());
                    brick.remove(i);
                    return true;

                }
            }
        }
        return false;
    }
}
