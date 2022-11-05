package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uet.oop.bomberman.Collision;
import uet.oop.bomberman.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.Bomber.*;
import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Bomber.timeTmp1;
import static uet.oop.bomberman.entities.block.Bomb.*;
import java.awt.*;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Balloon extends Monster {
    private CollisionChecker collisionCheckerBalloon;

    /**
     * dir = 1 -> go right
     * dir = -1 -> go left
     */


    public Balloon(int xUnit, int yUnit, Image img, boolean isColidable, boolean isAlive) {
        super(xUnit, yUnit, img, isColidable, isAlive);

    }


    public void movement() {
        Image[] left = {
                Sprite.balloom_left1.getFxImage(),
                Sprite.balloom_left2.getFxImage(),
                Sprite.balloom_left3.getFxImage()
        };
        Image[] right = {
                Sprite.balloom_right1.getFxImage(),
                Sprite.balloom_right2.getFxImage(),
                Sprite.balloom_right3.getFxImage()
        };
        spriteCounter++;
        if (spriteCounter > 16) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        this.solidAreaUp.setLocation(this.x + 4, this.y - 4);
        this.solidAreaDown.setLocation(this.x + 3, this.y + 23);
        this.solidAreaLeft.setLocation(this.x - 2, this.y + 11);
        this.solidAreaRight.setLocation(this.x + 16, this.y + 11);
        this.solidArea.setLocation(this.x + 1, this.y);
        if(this.direction == -1) {
            if (spriteNum == 1) {
                this.img = left[1];
            }

            if (spriteNum == 2) {
                this.img = left[2];
            }
        }else{
            if (spriteNum == 1) {
                this.img = right[1];
            }

            if (spriteNum == 2) {
                this.img = right[2];
            }
        }
        if((Collision.collision(collisionCheckerBalloon,this.solidAreaLeft) || Collision.collision(collisionCheckerBalloon,this.solidAreaRight))){
            this.direction *= -1;
            this.solidArea.setLocation(this.x - 1, this.y);
        }

        if (Collision.collisionCheck(this.solidAreaRight)) {
            bomberman.setColidable(false);
            heart--;
            System.out.println(heart);
            if (heart == 0) {
            }
        }

        if (Collision.collisionCheck(this.solidAreaLeft)) {
            bomberman.setColidable(false);
            heart--;
            System.out.println(heart);
            if (heart == 0) {
//                Image img = new Image("res/Buttons/gameOver.png");
//                authorView.setImage(img);
            }
        }
        x+= this.direction;
    }

    public void update() {
        movement();
    }
}
