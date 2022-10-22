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

    protected int speedLeft = 1;
    protected int speedRight = 1;
    protected int speedUp = 1;
    protected int speedDown = 1;
    public Bomber(int x, int y, Image img) {
        super(x, y, img, false);
        this.solidAreaUp = new Rectangle(this.x + 4, this.y - 4, 10, 10);
        this.solidAreaDown = new Rectangle(this.x + 4, this.y + 23, 10, 10);
        this.solidAreaLeft = new Rectangle(this.x - 2, this.y + 11, 10, 10);
        this.solidAreaRight = new Rectangle(this.x + 16, this.y + 11, 10, 10);
        

    }


    @Override
    public void update() {
        movement();
    }

    public void setUP() {
        if (Collision.collisionUp(collisionCheckerBomber, this.solidAreaUp)) {
            speedUp = 0;
        }
        if (!Collision.collisionUp(collisionCheckerBomber, this.solidAreaUp)) {
            speedUp = 1;
        }
    }

    public void setLeft() {
        if (Collision.collisionLeft(collisionCheckerBomber, this.solidAreaLeft)) {
            speedLeft = 0;
        }
        if (!Collision.collisionLeft(collisionCheckerBomber, this.solidAreaLeft)) {
            speedLeft = 1;
        }
    }

    public void setDown() {
        if (Collision.collisionDown(collisionCheckerBomber, this.solidAreaDown)) {
            speedDown = 0;
        }
        if (!Collision.collisionDown(collisionCheckerBomber, this.solidAreaDown)) {
            speedDown = 1;
        }
    }

    public void setRight() {
        if (Collision.collisionRight(collisionCheckerBomber, this.solidAreaRight)) {
            speedRight = 0;
        }
        if (!Collision.collisionRight(collisionCheckerBomber, this.solidAreaRight)) {
            speedRight = 1;
        }
    }

    public void movement() {
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

    }

}



