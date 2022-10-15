package uet.oop.bomberman.entities.block;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;
public class Bomb extends Entity {

    private static long timeBomb;
    private static long timeTmp;
    private static Entity bomb;
    private static int Status = 1;
    private static int swapExplosion = 1;

    // đánh dấu số bomb;
    public static int isBomb = 0;

    public static int bombNumber = 10;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public static void putBomb() {
        if (isBomb == 0 && bombNumber > 0) {
            bombNumber--;
            isBomb = 1;
            timeBomb = System.currentTimeMillis();
            timeTmp = timeBomb;
            int x = (bomberman.getX() + (SCALED_SIZE / 2)) / SCALED_SIZE;
            int y = (bomberman.getY() + (SCALED_SIZE / 2)) / SCALED_SIZE;
            x = Math.round(x);
            y = Math.round(y);
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            block.add(bomb);
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

    public static void explosionCenter() {
        if (swapExplosion == 1) {
            bomb.setImg(Sprite.bomb_exploded.getFxImage());
            swapExplosion = 2;

        } else if (swapExplosion == 2) {
            bomb.setImg(Sprite.bomb_exploded1.getFxImage());
            swapExplosion = 3;

        } else if (swapExplosion == 3) {
            bomb.setImg(Sprite.bomb_exploded2.getFxImage());
            swapExplosion = 1;
        }
    }

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

    private static void Explosion() {
        if (isBomb == 2)
            if (System.currentTimeMillis() - timeBomb < 1000) {
                if (System.currentTimeMillis() - timeTmp > 100) {
                    explosionCenter();
                    timeTmp += 100;
                }
            } else {
                isBomb = 0;
                bomb.setImg(Sprite.transparent.getFxImage());
            }
    }

    @Override
    public void update() {
        Status();
        Explosion();
    }
}
