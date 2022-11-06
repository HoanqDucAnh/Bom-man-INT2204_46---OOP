package uet.oop.bomberman.gamecollision;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.HeartIterm;
import uet.oop.bomberman.entities.Item.SpeedItem;
import uet.oop.bomberman.entities.buildingblocks.Portal;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.enemy.Kodoria;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.entities.player.Bomb;
import uet.oop.bomberman.graphics.gamesprite.Sprite;
import uet.oop.bomberman.level.Level2;
import uet.oop.bomberman.level.Level3;

import static uet.oop.bomberman.gamerunner.BombermanGame.*;
import java.awt.*;

import static uet.oop.bomberman.entities.buildingblocks.Brick.*;
import static uet.oop.bomberman.entities.player.Bomber.*;
import static uet.oop.bomberman.entities.buildingblocks.Brick.swapKill;
import static uet.oop.bomberman.entities.player.Bomb.*;

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
            if (items.get(i) instanceof SpeedItem) {
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
        }
        return false;
    }

    public static boolean collisionItemHeart(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof HeartIterm) {
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

    public static boolean onealCollisionBrick(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < brick.size(); i++) {
            if (brick.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, brick.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    temp = i;
                    timeTempBrick1 = System.currentTimeMillis();
                    timeTempBrick2 = timeTempBrick1;
                    brick.get(i).setColidable(false);
                    return true;

                }
            }
        }
        timeBrick = System.currentTimeMillis();
        if (System.currentTimeMillis() - timeTempBrick1 < 500) {
            if (System.currentTimeMillis() - timeTempBrick2 > 100) {
                if (swapKill == 1) {
                    brick.get(temp).setImg(Sprite.brick_exploded.getFxImage());
                    swapKill = 2;
                } else if (swapKill == 2) {
                    brick.get(temp).setImg(Sprite.brick_exploded1.getFxImage());
                    swapKill = 3;
                } else if (swapKill == 3) {
                    brick.get(temp).setImg(Sprite.brick_exploded2.getFxImage());
                    swapKill = 4;
                } else {
                    brick.get(temp).setImg(Sprite.grass.getFxImage());
                    brick.remove(temp);
                    swapKill = 1;
                }
                timeTempBrick2 += 100;
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
                        bomberman.setX(1*32);
                        bomberman.setY(1*32);
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

    public static boolean collisionMonsterBalloon(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < monsterCount.size(); i++) {
            if (monsterCount.get(i) instanceof Balloon && monsterCount.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, monsterCount.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    monsterCount.get(i).setDirection(0);
                    monsterCount.get(i).setAlive(false);
                    monsterCount.get(i).setImg(Sprite.transparent.getFxImage());
                    monsterCount.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean collisionMonsterKodoria(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < monsterCount.size(); i++) {
            if (monsterCount.get(i) instanceof Kodoria && monsterCount.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, monsterCount.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    monsterCount.get(i).setDirection(0);
                    monsterCount.get(i).setAlive(false);
                    monsterCount.get(i).setImg(Sprite.transparent.getFxImage());
                    monsterCount.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean collisionMonsterOneal(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < monsterCount.size(); i++) {
            if (monsterCount.get(i) instanceof Oneal && monsterCount.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, monsterCount.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    monsterCount.get(i).setDirection(0);
                    monsterCount.get(i).setAlive(false);
                    monsterCount.get(i).setImg(Sprite.transparent.getFxImage());
                    monsterCount.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean collisionMonsterMid(CollisionChecker collisionCheckerer, Rectangle player) {
        for (int i = 0; i < monsterCount.size(); i++) {
            if (monsterCount.get(i).isColidable()) {
                collisionCheckerer = new CollisionChecker(player, monsterCount.get(i).getSolidArea());
                if (collisionCheckerer.isColided()) {
                    monsterCount.get(i).setDirection(0);
                    monsterCount.get(i).setAlive(false);
                    monsterCount.get(i).setImg(Sprite.transparent.getFxImage());
                    monsterCount.remove(i);
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
            currentSpeed = 1;
        }
        return false;
    }

 

    public static boolean collisionBetBombAndBomber(Rectangle player) {
        CollisionChecker collisionChecker;
        collisionChecker = new CollisionChecker(player, bomb.getSolidArea());
            if (bomb.isColidable()) {
                if (collisionChecker.isColided()) {
                    timeTmp4 = System.currentTimeMillis();
                    bomb.setColidable(false);
                    return true;
                }
            }
            timeTmp6 = System.currentTimeMillis();
        if (timeTmp6 - timeTmp4 > 50) {
            bomb.setColidable(true);
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
                    //monsters.get(i)setAlive(false);
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
