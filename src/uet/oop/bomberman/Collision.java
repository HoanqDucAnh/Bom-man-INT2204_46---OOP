package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.stillObjects;
public class Collision {
    public static boolean collisionRight(CollisionChecker collisionCheckerer, Rectangle player) {
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



    public static boolean collisionUp(CollisionChecker collisionChecker, Rectangle player) {

        for (Entity stillObject : stillObjects) {
            if (stillObject.isColidable()) {
                collisionChecker = new CollisionChecker(player, stillObject.getSolidArea());

                if (collisionChecker.isColided()) {

                    return true;
                }
            }
        }
        return false;
    }

    public static boolean collisionDown(CollisionChecker collisionChecker, Rectangle player) {
        for (Entity stillObject : stillObjects) {
            if (stillObject.isColidable()) {
                collisionChecker = new CollisionChecker(player, stillObject.getSolidArea());

                if (collisionChecker.isColided()) {

                    return true;
                }
            }
        }
        return false;
    }

    public static boolean collisionLeft(CollisionChecker collisionChecker, Rectangle player) {
        for (Entity stillObject : stillObjects) {
            if (stillObject.isColidable()) {
                collisionChecker = new CollisionChecker(player, stillObject.getSolidArea());
                if (collisionChecker.isColided()) {
                    return true;
                }
            }
        }
        return false;
    }
}
