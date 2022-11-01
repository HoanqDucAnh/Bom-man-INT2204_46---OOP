//package uet.oop.bomberman.entities;
//import javafx.scene.*;
//import javafx.scene.SnapshotParameters;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.paint.Color;
//import javafx.fxml.FXMLLoader;
//
//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.beans.EventHandler;
//
//public class Bomber extends Entity {
//
//    public Bomber(int x, int y, Image img) {
//        super( x, y, img);
//    }
//
//
//    @Override
//    public void update() {
//        Scene scene = new Scene(root);
//    }
//}

package uet.oop.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import uet.oop.bomberman.Collision;
import uet.oop.bomberman.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.*;


public class Bomber extends Entity {
    private CollisionChecker collisionCheckerBomber;

    public CollisionChecker getCollisionCheckerBomber() {
        return collisionCheckerBomber;
    }

    public void setCollisionCheckerBomber(CollisionChecker collisionCheckerBomber) {
        this.collisionCheckerBomber = collisionCheckerBomber;
    }

    public static int currentSpeed = 1;
    public static int speedLeft = 1;
    public static int speedRight = 1;
    public static int speedUp = 1;
    public static int speedDown = 1;


    public Bomber(int x, int y, Image img) {
        super(x, y, img, false);
        this.solidAreaUp = new Rectangle(this.x + 4, this.y - 4, 10, 10);
        this.solidAreaDown = new Rectangle(this.x + 4, this.y + 23, 10, 10);
        this.solidAreaLeft = new Rectangle(this.x - 2, this.y + 11, 10, 10);
        this.solidAreaRight = new Rectangle(this.x + 16, this.y + 11, 10, 10);
        this.solidArea = new Rectangle(this.x, this.y, 32, 32);
    }


    @Override
    public void update() {
        movement();
    }

    public void setUP() {
        if (Collision.collision(collisionCheckerBomber, this.solidAreaUp)) {
            speedUp = 0;
        }
        if (!Collision.collision(collisionCheckerBomber, this.solidAreaUp)) {
            speedUp = currentSpeed;
        }
        if (Collision.collisionItemSpeed(collisionCheckerBomber, this.solidAreaRight)) {
            currentSpeed++;
        }
    }

    public void setLeft() {
        if (Collision.collision(collisionCheckerBomber, this.solidAreaLeft)) {
            speedLeft = 0;
        }
        if (!Collision.collision(collisionCheckerBomber, this.solidAreaLeft)) {
            speedLeft = currentSpeed;
        }
        if (Collision.collisionItemSpeed(collisionCheckerBomber, this.solidAreaRight)) {
            currentSpeed++;
        }
    }

    public void setDown() {
        if (Collision.collision(collisionCheckerBomber, this.solidAreaDown)) {
            speedDown = 0;
        }
        if (!Collision.collision(collisionCheckerBomber, this.solidAreaDown)) {
            speedDown = currentSpeed;
        }
        if (Collision.collisionItemSpeed(collisionCheckerBomber, this.solidAreaRight)) {
            currentSpeed++;
        }
    }

    public void setRight() {
        if (Collision.collision(collisionCheckerBomber, this.solidAreaRight)) {
            speedRight = 0;
        }
        if (!Collision.collision(collisionCheckerBomber, this.solidAreaRight)) {
            speedRight = currentSpeed;
        }
        if (Collision.collisionItemSpeed(collisionCheckerBomber, this.solidAreaRight)) {
            currentSpeed++;
        }
    }

    public void movement() {
        if (bomberman.life == false) {

        }
        Image[] up = {
                Sprite.player_up.getFxImage(),
                Sprite.player_up_1.getFxImage(),
                Sprite.player_up_2.getFxImage()
        };
        Image[] down = {
                Sprite.player_down.getFxImage(),
                Sprite.player_down_1.getFxImage(),
                Sprite.player_down_2.getFxImage()
        };
        Image[] left = {
                Sprite.player_left.getFxImage(),
                Sprite.player_left_1.getFxImage(),
                Sprite.player_left_2.getFxImage()
        };
        Image[] right = {
                Sprite.player_right.getFxImage(),
                Sprite.player_right_1.getFxImage(),
                Sprite.player_right_2.getFxImage()
        };
        spriteCounter++;
        this.solidAreaUp.setLocation(this.x + 4, this.y - 4);
        this.solidAreaDown.setLocation(this.x + 3, this.y + 23);
        this.solidAreaLeft.setLocation(this.x - 2, this.y + 11);
        this.solidAreaRight.setLocation(this.x + 16, this.y + 11);
        this.solidArea.setLocation(this.x, this.y);
        if (spriteCounter > 35) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }


        if (upPressed) {
            setUP();
            if (spriteNum == 1) {
                this.img = up[1];
            }

            if (spriteNum == 2) {
                this.img = up[0];
            }
            y -= speedUp;

        }
        if (downPressed) {
            setDown();

            if (spriteNum == 1) {
                this.img = down[1];
            }

            if (spriteNum == 2) {
                this.img = down[2];
            }

            y += speedDown;

        }

        if (leftPressed) {
            setLeft();
            if (spriteNum == 1) {
                this.img = left[1];
            }

            if (spriteNum == 2) {
                this.img = left[2];
            }
            x -= speedLeft;

        }

        if (rightPressed) {
            setRight();
            if (spriteNum == 1) {
                this.img = right[1];
            }

            if (spriteNum == 2) {
                this.img = right[2];
            }
            x += speedRight;

        }
        if (Collision.collisionMonsterMain(collisionCheckerBomber, this.solidArea)) {
            System.out.println("Colid");
        }
        if (Collision.collisionMonsterMain(collisionCheckerBomber, this.solidArea)) {
            bomberman.setLife(false);
            System.out.println("Colid");
        }
        if (Collision.collisionMonsterMain(collisionCheckerBomber, this.solidArea)) {
            bomberman.setLife(false);
            System.out.println("Colid");
        }
        if (Collision.collisionMonsterMain(collisionCheckerBomber, this.solidArea)) {
            bomberman.setLife(false);
            System.out.println("Colid");
        }
    }
}



