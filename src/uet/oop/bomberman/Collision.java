package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.items;
import static uet.oop.bomberman.BombermanGame.stillObjects;
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

}
