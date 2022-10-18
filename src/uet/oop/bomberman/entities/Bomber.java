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

import uet.oop.bomberman.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.*;


public class Bomber extends Entity {
    private CollisionChecker collisionChecker;

    protected int speedLeft = 1;
    private String direction;
    boolean collideLeft = false;
    private boolean checkLeft = false;
    private boolean checkRight = false;
    private boolean checkUp = false;
    private boolean checkDown = false;
    protected int speedRight = 1;
    protected int speedUp = 1;
    protected int speedDown = 1;
    public Bomber(int x, int y, Image img) {
        super(x,y,img,false);
        this.solidArea = new Rectangle(this.x+8,this.y+8,10,10);
    }

    public void setSpeedTest() {
        if (collision() == true) {
            switch (direction) {
                case "LEFT":
                    speedLeft = 0;
                    break;

            }
        }
    }
    @Override
    public void update() {
        movement();
    }

    public boolean checkLeft() {
        if (direction == "LEFT" && collision() == true) {
            return true;
        }
        return false;
    }

    public boolean checkRight() {
        if (direction == "RIGHT" && collision() == true) {
            return true;
        }
        return false;
    }
    public boolean collision() {
            for (Entity stillObject : stillObjects) {
                if (stillObject.colidable) {
                    collisionChecker = new CollisionChecker(this.solidArea, stillObject.solidArea);
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
                if (stillObject.colidable) {
                    collisionChecker = new CollisionChecker(this.solidArea, stillObject.solidArea);
                    if (collisionChecker.isColided() && upPressed) {
                        System.out.println("colldie");
                        return true;
                    }
                }
            }
            return false;
    }
    public void movement() {
        Image[] up = {Sprite.player_up.getFxImage(), Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage()};
        Image[] down = {Sprite.player_down.getFxImage(), Sprite.player_down_1.getFxImage(), Sprite.player_down_2.getFxImage()};
        Image[] left= {Sprite.player_left.getFxImage(), Sprite.player_left_1.getFxImage(), Sprite.player_left_2.getFxImage()};
        Image[] right = {Sprite.player_right.getFxImage(), Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage()};
        spriteCounter++;
        this.solidArea.setLocation(this.x+8,this.y+8);
        boolean Down = collisionDown();
        if (spriteCounter > 35) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }

            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }


        if (upPressed) {
            direction = "UP";
            checkUp = true;
            checkRight = false;
            checkLeft = false;
            checkDown = false;
            if (spriteNum == 1) {
                this.img = up[1];
            }

            if (spriteNum == 2) {
                this.img = up[0];
            }
            y-= speedUp;
        }
        if (downPressed) {
            direction = "DOWN";
            checkUp = false;
            checkRight = false;
            checkLeft = false;
            checkDown = true;
            if (spriteNum == 1) {
                this.img = down[1];
            }

            if (spriteNum == 2) {
                this.img = down[2];
            }

            y+=speedDown;
            if(Down && checkDown == true){
                speedDown = 0;
            } else {
                speedDown = 1;
            }
        }

        if (leftPressed) {
            direction = "LEFT";
            if (spriteNum == 1) {
                this.img = left[1];
            }

            if (spriteNum == 2) {
                this.img = left[2];
            }
            x-=speedLeft;
            if (checkLeft() == true) {
                speedLeft = 0;
            } else {
                speedLeft = 1;
            }
        }

        if (rightPressed) {
            direction = "RIGHT";
            if (spriteNum == 1) {
                this.img = right[1];
            }

            if (spriteNum == 2) {
                this.img = right[2];
            }
            x+=speedRight;
            if (checkRight() == true) {
                speedRight = 0;
            } else {
                speedRight = 1;
            }
        }

    }

}



