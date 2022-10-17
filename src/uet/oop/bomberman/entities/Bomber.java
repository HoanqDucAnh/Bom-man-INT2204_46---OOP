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

    public Bomber(int x, int y, Image img) {
        super(x,y,img,false);
        this.solidArea = new Rectangle(this.x,this.y,16,16);
    }


    @Override
    public void update() {
        movement();
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
    public void movement() {
        Image[] up = {Sprite.player_up.getFxImage(), Sprite.player_up_1.getFxImage(), Sprite.player_up_2.getFxImage()};
        Image[] down = {Sprite.player_down.getFxImage(), Sprite.player_down_1.getFxImage(), Sprite.player_down_2.getFxImage()};
        Image[] left= {Sprite.player_left.getFxImage(), Sprite.player_left_1.getFxImage(), Sprite.player_left_2.getFxImage()};
        Image[] right = {Sprite.player_right.getFxImage(), Sprite.player_right_1.getFxImage(), Sprite.player_right_2.getFxImage()};
        spriteCounter++;
        this.solidArea.setLocation(this.x,this.y);
        boolean isCollide = collision();
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

            if (spriteNum == 1) {
                this.img = up[1];
            }

            if (spriteNum == 2) {
                this.img = up[0];
            }

            if(!isCollide){
                y--;
            }

        }
        else if (downPressed) {
            if (spriteNum == 1) {
                this.img = down[1];
            }

            if (spriteNum == 2) {
                this.img = down[2];
            }


            if(!isCollide) y++;

        }

        else if (leftPressed) {
            if (spriteNum == 1) {
                this.img = left[1];
            }

            if (spriteNum == 2) {
                this.img = left[2];
            }

            if(!isCollide) x--;

        }

        else if (rightPressed) {
            if (spriteNum == 1) {
                this.img = right[1];
            }

            if (spriteNum == 2) {
                this.img = right[2];
            }

            if(!isCollide) x++;
        }

    }

}



