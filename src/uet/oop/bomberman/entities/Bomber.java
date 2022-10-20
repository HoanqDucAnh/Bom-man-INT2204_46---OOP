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
        this.solidAreaUp = new Rectangle(this.x+4,this.y-4,10,10);
        this.solidAreaDown = new Rectangle(this.x+4,this.y+23,10,10);
        this.solidAreaLeft = new Rectangle(this.x-2,this.y+11,10,10);
        this.solidAreaRight = new Rectangle(this.x+16,this.y+11,10,10);
    }


    @Override
    public void update() {
        movement();
    }


    public boolean collisionRight() {
        for (Entity stillObject : stillObjects) {
            if (stillObject.colidable) {
                collisionChecker = new CollisionChecker(this.solidAreaRight, stillObject.solidArea);

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
                if (stillObject.colidable) {
                    collisionChecker = new CollisionChecker(this.solidAreaUp, stillObject.solidArea);

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
                collisionChecker = new CollisionChecker(this.solidAreaDown, stillObject.solidArea);

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
            if (stillObject.colidable) {
                collisionChecker = new CollisionChecker(this.solidAreaLeft, stillObject.solidArea);

                if (collisionChecker.isColided()) {
                    System.out.println("colldie");
                    return true;
                }
            }
        }
        return false;
    }

public void setUP() {
        if(collisionUp()) {
            speedUp = 0;
        }
        if (!collisionUp()) {
            speedUp = 1;
        }
}

    public void setLeft() {
        if(collisionLeft()) {
            speedLeft = 0;
        }
        if (!collisionLeft()) {
            speedLeft= 1;
        }
    }

    public void setDown() {
        if(collisionDown()) {
            speedDown = 0;
        }
        if (!collisionDown()) {
            speedDown = 1;
        }
    }

    public void setRight() {
        if(collisionRight()) {
            speedRight = 0;
        }
        if (!collisionDown()) {
            speedRight = 1;
        }
    }

    public void movement() {
        Image[] up = {Sprite.player_up.getFxImage(), Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage()};
        Image[] down = {Sprite.player_down.getFxImage(), Sprite.player_down_1.getFxImage(), Sprite.player_down_2.getFxImage()};
        Image[] left= {Sprite.player_left.getFxImage(), Sprite.player_left_1.getFxImage(), Sprite.player_left_2.getFxImage()};
        Image[] right = {Sprite.player_right.getFxImage(), Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage()};
        spriteCounter++;
        this.solidAreaUp.setLocation(this.x+4,this.y-4);
        this.solidAreaDown.setLocation(this.x+4,this.y+23);
        this.solidAreaLeft.setLocation(this.x-2,this.y+11);
        this.solidAreaRight.setLocation(this.x+16,this.y+11);

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
            setUP();
            if (spriteNum == 1) {
                this.img = up[1];
            }

            if (spriteNum == 2) {
                this.img = up[0];
            }
            y-=speedUp;

        }
        if (downPressed) {
            setDown();

            if (spriteNum == 1) {
                this.img = down[1];
            }

            if (spriteNum == 2) {
                this.img = down[2];
            }

            y+=speedDown;

        }

        if (leftPressed) {
            setLeft();
            if (spriteNum == 1) {
                this.img = left[1];
            }

            if (spriteNum == 2) {
                this.img = left[2];
            }
            x-=speedLeft;

        }

        if (rightPressed) {
            setRight();
            if (spriteNum == 1) {
                this.img = right[1];
            }

            if (spriteNum == 2) {
                this.img = right[2];
            }
            x+=speedRight;

        }

    }

}



