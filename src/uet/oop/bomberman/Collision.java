package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Portal;
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

    public static boolean collisionPortal( Rectangle player, Entity x) {
        CollisionChecker collisionCheckerer;
        if (x instanceof Portal) {
                collisionCheckerer = new CollisionChecker(player, x.getSolidArea());
                if (collisionCheckerer.isColided()) {
                    System.out.println("Collide");
                    return true;
                }
            }
        return false;
    }
    public static boolean collisionMonster(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, monsters.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    monsters.get(i).setDirection(0);
                    monsters.get(i).setAlive(false);
                    monsters.get(i).setImg(Sprite.transparent.getFxImage());
                    monsters.remove(i);
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean collisionMonsterMain(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, monsters.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    //monsters.get(i).setAlive(false);
                    //monsters.get(i).setDirection(0);
                    //monsters.get(i).setImg(Sprite.transparent.getFxImage());
                    //monsters.remove(i);
                    System.out.println("CollideMonster");
                    return true;
                }
            }
        }
        return false;
    }
}
