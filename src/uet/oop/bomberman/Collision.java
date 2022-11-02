package uet.oop.bomberman;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.block.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Level2;
import uet.oop.bomberman.level.Level3;

import static uet.oop.bomberman.BombermanGame.*;
import java.awt.*;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Bomber.*;
import static uet.oop.bomberman.entities.block.Bomb.timeBomb;
import static uet.oop.bomberman.entities.block.Bomb.timeTmp;

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
                    check++;
                    stillObjects.clear();
                    items.clear();
                    block.clear();
                    if (check == 1) {
                        System.out.println(check);
                        stillObjects.clear();
                        items.clear();
                        block.clear();
                        new Level2();
                    }
                    if (check == 2) {
                        System.out.println(check);
                        stillObjects.clear();
                        items.clear();
                        block.clear();
                        new Level3();
                    }
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

    public static boolean collisionBomb(Rectangle player, Entity x) {
        CollisionChecker collisionCheckerer;
        if (x instanceof Bomb) {
            collisionCheckerer = new CollisionChecker(player, x.getSolidArea());
            if (collisionCheckerer.isColided()) {
                return true;
            }
        }
        return false;
    }

    public static boolean collisionCheck(Rectangle player) {
        CollisionChecker collisionCheckerer;
            collisionCheckerer = new CollisionChecker(player, bomberman.getSolidArea());
            if (bomberman.isColidable()) {
                if (collisionCheckerer.isColided()) {
                    timeTmp1 = System.currentTimeMillis();
                    timeTmp2 = timeTmp1;
                    isBomber = 1;
                   bomberman.setColidable(false);
                   return true;
                }
            }
            timeBomber = System.currentTimeMillis();

            if (isBomber == 1) {
                if (System.currentTimeMillis() - timeTmp1 < 1000) {
                    if (System.currentTimeMillis() - timeTmp2 > 200) {
                        killBomber();
                        timeTmp2 += 200;
                    }
                }
            }
        if (timeBomber - timeTmp1 > 2000) {
            bomberman.setColidable(true);
        }
        return false;
    }


    public static boolean collisionMonsterMain(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, monsters.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    //if (System.currentTimeMillis() - timeTmp > )
                    monsters.get(i).setColidable(false);
                    //monsters.get(i).setAlive(false);
                    //monsters.get(i).setDirection(0);
                    //monsters.get(i).setImg(Sprite.transparent.getFxImage());
                    //monsters.remove(i);
                    System.out.println("CollideMonster");
                    return true;
                }
            }
            //monsters.get(i).setColidable(true);
        }

        return false;
    }
}
