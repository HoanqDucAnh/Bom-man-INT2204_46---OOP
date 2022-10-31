package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Collision;
import uet.oop.bomberman.CollisionChecker;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

public class Balloon extends Monster {
    private CollisionChecker collisionCheckerBalloon;

    /**
     * dir = 1 -> go right
     * dir = -1 -> go left
     */


    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);

        this.isColidable = true;
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
        if(Collision.collision(collisionCheckerBalloon,this.solidAreaLeft) || Collision.collision(collisionCheckerBalloon,this.solidAreaRight)){
            this.direction *= -1;
            this.solidArea.setLocation(this.x - 1, this.y);
        }
        x+= this.direction;

    }

    public void update() {
        movement();
    }
}
