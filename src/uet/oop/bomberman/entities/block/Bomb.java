package uet.oop.bomberman.entities.block;

import javafx.scene.image.Image;
import uet.oop.bomberman.Collision;
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
    private CollisionChecker collisionCheckerBomb;
    public  static long timeBomb;
    public static long timeTmp;
    public static long timeTmp4;
    public static long timeTmp5;
    public static long timeTmp6;
    public  static Entity bomb;
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
    public static Rectangle mid = null;

    private static boolean isEdge = false;
    public static int bombNumber = 10000;
    public Bomb(int x, int y, Image img, boolean colidable) {
        super(x, y, img, true);
    }

    public static void putBomb() {
        if (isBomb == 0 && bombNumber > 0) {
            bombNumber--;
            isBomb = 1;
            timeBomb = System.currentTimeMillis();
            timeTmp = timeBomb;
            int x = (bomberman.getX() + (SCALED_SIZE / 2)) / SCALED_SIZE;
            int y = (bomberman.getY() + (SCALED_SIZE / 2)) / SCALED_SIZE;
            //x = Math.round(x);
            //y = Math.round(y);
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage(), false);
            stillObjects.add(bomb);;
            System.out.println("addBomb");
            mid = new Rectangle(x * SCALED_SIZE, y * SCALED_SIZE, 15, 15);
            bomb.setSolidArea(mid);
            edge_down = new Bomb(x, y + 1,
                    Sprite.transparent.getFxImage(),false);
            Down = new Rectangle(x * SCALED_SIZE, y * SCALED_SIZE + 1 * SCALED_SIZE, 15, 5);
            edge_down.setSolidArea(Down);
            block.add(edge_down);
            edge_up = new Bomb(x, y - 1,
                    Sprite.transparent.getFxImage(),false);
            Up = new Rectangle(x * SCALED_SIZE, (y - 1) * SCALED_SIZE, 15, 5);
            block.add(edge_up);
            edge_left = new Bomb(x - 1, y,
                    Sprite.transparent.getFxImage(), false);
            Left = new Rectangle((x - 1) * SCALED_SIZE, y * SCALED_SIZE, 5, 15);
            edge_left.setSolidArea(Left);
            block.add(edge_left);
            edge_right = new Bomb(x + 1, y,
                    Sprite.transparent.getFxImage(), false);
            Right = new Rectangle((x + 1) * SCALED_SIZE, y * SCALED_SIZE, 5, 15);
            edge_right.setSolidArea(Right);
            block.add(edge_right);

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

    public void explosionCenter() {
        if (swapExplosion == 1) {
            bomb.setImg(Sprite.bomb_exploded.getFxImage());
            edge_up.setImg(Sprite.explosion_vertical_top_last.getFxImage());
            edge_right.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
            edge_down.setImg(Sprite.explosion_vertical_down_last.getFxImage());
            edge_left.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
            if (Collision.collision(collisionCheckerBomb, Right)) {
                edge_right.setImg(Sprite.transparent.getFxImage());
            }
            if (Collision.collision(collisionCheckerBomb, Left)) {
                edge_left.setImg(Sprite.transparent.getFxImage());
            }
            if (Collision.collision(collisionCheckerBomb, Down)) {
                edge_down.setImg(Sprite.transparent.getFxImage());
            }

            if (Collision.collision(collisionCheckerBomb, Up)) {
                edge_up.setImg(Sprite.transparent.getFxImage());
            }

            if (Collision.collisionBrick(collisionCheckerBomb, Right)) {
                edge_right.setImg(Sprite.brick_exploded.getFxImage());
            }
            if (Collision.collisionBrick(collisionCheckerBomb, Left)) {
                edge_left.setImg(Sprite.brick_exploded.getFxImage());
            }
            if (Collision.collisionBrick(collisionCheckerBomb, Up)) {
                edge_up.setImg(Sprite.brick_exploded.getFxImage());
            }
            if (Collision.collisionBrick(collisionCheckerBomb, Down)) {
                edge_down.setImg(Sprite.brick_exploded.getFxImage());
            }

            if (Collision.collisionMonster(collisionCheckerBomb, Right)) {

                edge_right.setImg(Sprite.balloom_dead.getFxImage());
            }
            if (Collision.collisionMonster(collisionCheckerBomb, Left)) {
                edge_left.setImg(Sprite.balloom_dead.getFxImage());
            }
            if (Collision.collisionMonster(collisionCheckerBomb, Down)) {
                edge_down.setImg(Sprite.balloom_dead.getFxImage());
            }
            if (Collision.collisionMonster(collisionCheckerBomb, Up)) {
                edge_up.setImg(Sprite.balloom_dead.getFxImage());
            }
            if (Collision.collisionMonster(collisionCheckerBomb, mid)) {
                bomb.setImg(Sprite.balloom_dead.getFxImage());
            }

            if (Collision.collisionCheck(Left)) {
            }
            if (Collision.collisionCheck(Right)) {
            }
            if (Collision.collisionCheck(Up)) {
            }
            if (Collision.collisionCheck(Down)) {
            }
            swapExplosion = 2;

        } else if (swapExplosion == 2) {
            bomb.setImg(Sprite.bomb_exploded1.getFxImage());
            edge_up.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            edge_right.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            edge_down.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            edge_left.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            if (Collision.collision(collisionCheckerBomb, Right)) {
                edge_right.setImg(Sprite.transparent.getFxImage());
            }
            if (Collision.collision(collisionCheckerBomb, Left)) {
                edge_left.setImg(Sprite.transparent.getFxImage());
            }
            if (Collision.collision(collisionCheckerBomb, Down)) {
                edge_down.setImg(Sprite.transparent.getFxImage());
            }
            if (Collision.collision(collisionCheckerBomb, Up)) {
                edge_up.setImg(Sprite.transparent.getFxImage());
            }

            swapExplosion = 3;

        } else if (swapExplosion == 3) {
            bomb.setImg(Sprite.bomb_exploded2.getFxImage());
            edge_up.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            edge_right.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
            edge_down.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            edge_left.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            if (Collision.collision(collisionCheckerBomb, Right)) {
                edge_right.setImg(Sprite.transparent.getFxImage());
            }
            if (Collision.collision(collisionCheckerBomb, Left)) {
                edge_left.setImg(Sprite.transparent.getFxImage());
            }
            if (Collision.collision(collisionCheckerBomb, Down)) {
                edge_down.setImg(Sprite.transparent.getFxImage());
            }
            if (Collision.collision(collisionCheckerBomb, Up)) {
                edge_up.setImg(Sprite.transparent.getFxImage());
            }
            swapExplosion = 1;
        }
    }
    private static void Status() {
        if (isBomb == 1) {
            if (System.currentTimeMillis() - timeBomb < 800) {
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
            if (System.currentTimeMillis() - timeBomb < 800) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    //createEdge();
                    System.out.println(System.currentTimeMillis());
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
                mid.setLocation(0,0);
//                System.out.println("Clear");
            }
        if (Collision.collisionBetBombAndBomber(bomberman.getSolidArea())) {
            System.out.println("true");
            //mid.setLocation(bomb.getX(), bomb.getY());
        }
    }

    @Override
    public void update() {


        Status();
        Explosion();
    }
}