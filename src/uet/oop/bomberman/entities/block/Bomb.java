package uet.oop.bomberman.entities.block;

import javafx.scene.image.Image;
import uet.oop.bomberman.CollisionChecker;
import uet.oop.bomberman.entities.Entity;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;
import static uet.oop.bomberman.graphics.Sprite.powerup_bombs;
import uet.oop.bomberman.CollisionChecker;
public class Bomb extends Entity {
    private CollisionChecker collisionChecker;

    private static long timeBomb;
    private static long timeTmp;
    private static Entity bomb;
    private static int Status = 1;
    private static int swapExplosion = 1;

    // đánh dấu số bomb;
    public static int isBomb = 0;
    public static int powerBomb = 0;
    private static Rectangle Down;
    private static Rectangle Up;
    private static Rectangle Right;
    private static Rectangle Left;
    private static Entity edge_down = null;
    private static Entity edge_up = null;
    private static Entity edge_left = null;
    private static Entity edge_right = null;
    private static boolean isEdge = false;
    public static int bombNumber = 1000;
    public Bomb(int x, int y, Image img) {
        super(x, y, img,false);
    }

    public static void putBomb() {
        if (isBomb == 0 && bombNumber > 0) {
            bombNumber--;
            isBomb = 1;
            timeBomb = System.currentTimeMillis();
            timeTmp = timeBomb;
            int x = (bomberman.getX() + (SCALED_SIZE / 2)) / SCALED_SIZE;
            int y = (bomberman.getY() + (SCALED_SIZE / 2)) / SCALED_SIZE;
            //int x = bomberman.getX() * SCALED_SIZE;
            //int y = bomberman.getY() * SCALED_SIZE;
            // = Math.round(x);
            //y = Math.round(y);
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            block.add(bomb);
                edge_down = new Bomb(x, y + 1,
                        Sprite.transparent.getFxImage());
                Down = new Rectangle(x * SCALED_SIZE, y * SCALED_SIZE + 1 * SCALED_SIZE, 10, 10);
                edge_down.setSolidArea(Down);
                block.add(edge_down);
                edge_up = new Bomb(x, y - 1,
                        Sprite.transparent.getFxImage());
                Up = new Rectangle(x * SCALED_SIZE, (y - 1) * SCALED_SIZE, 10, 10);
                block.add(edge_up);
                edge_left = new Bomb(x - 1, y,
                        Sprite.transparent.getFxImage());
                Left = new Rectangle((x - 1) * SCALED_SIZE, y * SCALED_SIZE, 10, 10);
                edge_left.setSolidArea(Left);
                block.add(edge_left);
                edge_right = new Bomb(x + 1, y,
                        Sprite.transparent.getFxImage());
                Right = new Rectangle((x + 1) * SCALED_SIZE, y * SCALED_SIZE, 10, 10);
                edge_right.setSolidArea(Right);
                block.add(edge_right);

        }
    }

    public boolean collisionRight() {
        for (Entity stillObject : stillObjects) {
            if (stillObject.isColidable() == true) {
                collisionChecker = new CollisionChecker(edge_right.getSolidArea(), stillObject.getSolidArea());

                if (collisionChecker.isColided()) {
                    System.out.println("colldie" );
                    return true;
                }
            }
        }
        return false;
    }



    public boolean collisionUp() {

        for (Entity stillObject : stillObjects) {
            if (stillObject.isColidable()) {
                collisionChecker = new CollisionChecker(Up, stillObject.getSolidArea());

                if (collisionChecker.isColided()) {
                    System.out.println("colldie");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collisionDown() {
        for (Entity stillObject : stillObjects) {
            if (stillObject.isColidable()) {
                collisionChecker = new CollisionChecker(Down, stillObject.getSolidArea());

                if (collisionChecker.isColided()) {
                    System.out.println("colldie");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean collisionLeft() {
        for (Entity stillObject : stillObjects) {
            if (stillObject.isColidable() == true) {
                collisionChecker = new CollisionChecker(edge_left.getSolidArea(), stillObject.getSolidArea());

                if (collisionChecker.isColided()) {
                    System.out.println("colldie");
                    return true;
                }
            }
        }
        return false;
    }

    public void checkUp() {
        if (collisionUp()) {
            edge_left = new Bomb(x - 1, y,
                    Sprite.transparent.getFxImage());
            edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
            edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
            edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
        }
        if (!collisionUp()) {
            edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
            edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
            edge_left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
            edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
        }
    }
    public static void StatusBomb() {
        if (Status == 1) {
            bomb.setImg(Sprite.bomb.getFxImage());
            Status = 2;
        } else if (Status == 2) {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            Status = 3;
        } else if (Status == 3) {
            bomb.setImg(Sprite.bomb_2.getFxImage());
            Status = 4;
        } else {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            Status = 1;
        }
    }
/*
    public void setUP() {
        if(collisionUp()) {
            edge_up.setImg(Sprite.transparent.getFxImage());
        }
        if (!collisionUp()) {
            edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
        }
    }

    public void setLeft() {
        if(collisionLeft()) {
            edge_left.setImg(Sprite.transparent.getFxImage());
        }
        if (!collisionLeft()) {
            edge_left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
        }
    }

    public void setDown() {
        if(collisionDown()) {
            edge_down.setImg(Sprite.transparent.getFxImage());
        }
        if (!collisionDown()) {
            edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
        }
    }

    public void setRight() {
        if(collisionRight()) {
            edge_right.setImg(Sprite.transparent.getFxImage());
        }
        if (!collisionRight()) {
            edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
        }
    }

 */
    public  void explosionCenter() {
        if (swapExplosion == 1) {
            bomb.setImg(Sprite.bomb_exploded.getFxImage());
            edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
            edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
            edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
            edge_left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
            if(collisionRight()) {
                edge_right.setImg(Sprite.transparent.getFxImage());
            }
            if (collisionLeft()) {
                edge_left.setImg(Sprite.transparent.getFxImage());
            }
            if (collisionDown()) {
                edge_down.setImg(Sprite.transparent.getFxImage());
            }
            if (collisionUp()) {
                edge_up.setImg(Sprite.transparent.getFxImage());
            }
            swapExplosion = 2;

        } else if (swapExplosion == 2) {
            /*
            if (collisionRight()) {
                edge_right.setImg(Sprite.transparent.getFxImage());
            }
            if (collisionLeft()) {
                edge_left.setImg(Sprite.transparent.getFxImage());
            }
            if (collisionUp()) {
                edge_up.setImg(Sprite.transparent.getFxImage());
            }
            if (collisionDown()) {
                edge_down.setImg(Sprite.transparent.getFxImage());
            }
            bomb.setImg(Sprite.bomb_exploded1.getFxImage());
            edge_down.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            edge_up.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            edge_left.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            edge_right.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            */

            swapExplosion = 3;

        } else if (swapExplosion == 3) {
            /*
            if (collisionRight()) {
                edge_right.setImg(Sprite.transparent.getFxImage());
            }
            if (collisionLeft()) {
                edge_left.setImg(Sprite.transparent.getFxImage());
            }
            if (collisionUp()) {
                edge_up.setImg(Sprite.transparent.getFxImage());
            }
            if (collisionDown()) {
                edge_down.setImg(Sprite.transparent.getFxImage());
            }
            bomb.setImg(Sprite.bomb_exploded2.getFxImage());
            edge_down.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            edge_up.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            edge_left.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            edge_right.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());

             */
            swapExplosion = 1;
        }
    }
/*
    public static void createEdge() {
        if (powerBomb == 0) {
            edge_down = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + 1,
                    Sprite.bomb_exploded.getFxImage());
            block.add(edge_down);
        }
    }

        if (powerBomb == 0) {
            edge_up = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - 1,
                    Sprite.bomb_exploded.getFxImage());
            block.add(edge_up);
        }
        if (powerBomb == 0) {
            edge_left = new Bomb(bomb.getX() / 32 - 1, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());

            block.add(edge_left);
        }
        if (powerBomb == 0) {
            edge_right = new Bomb(bomb.getX() / 32 + 1, bomb.getY() / 32,
                    Sprite.bomb_exploded.getFxImage());
            block.add(edge_right);
        }
    }


 */
    private static void Status() {
        if (isBomb == 1) {
            if (System.currentTimeMillis() - timeBomb < 2000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    StatusBomb();
                    timeTmp += 100;
                }
            } else {
                isBomb = 2;
                timeBomb = System.currentTimeMillis();
                timeTmp = timeBomb;
            }
        }
    }

    private void Explosion() {
        if (isBomb == 2)
            if (System.currentTimeMillis() - timeBomb < 1000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    //createEdge();
                    explosionCenter();
                    timeTmp += 100;
                }
            } else {
                isBomb = 0;
                bomb.setImg(Sprite.transparent.getFxImage());
                edge_up.setImg(Sprite.transparent.getFxImage());
                edge_down.setImg(Sprite.transparent.getFxImage());
                edge_right.setImg(Sprite.transparent.getFxImage());
                edge_left.setImg(Sprite.transparent.getFxImage());
            }
    }

    @Override
    public void update() {
        Status();
        Explosion();
    }
}