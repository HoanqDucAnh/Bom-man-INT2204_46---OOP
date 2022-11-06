package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.gamecollision.Collision;
import uet.oop.bomberman.gamecollision.CollisionChecker;
import uet.oop.bomberman.graphics.gamesprite.Sprite;

import static uet.oop.bomberman.entities.player.Bomber.*;
import static uet.oop.bomberman.gamerunner.BombermanGame.*;

public class Balloon extends Monster {
    private CollisionChecker collisionCheckerBalloon;
    private String type;

    /**
     * dir = 1 -> go right
     * dir = -1 -> go left
     */


    public Balloon(int xUnit, int yUnit, Image img, boolean isColidable, boolean isAlive,String type) {
        super(xUnit, yUnit, img, isColidable, isAlive);
        this.type = type;
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
        this.solidAreaDown.setLocation(this.x + 3, this.y + 24);
        this.solidAreaLeft.setLocation(this.x +4, this.y + 11);
        this.solidAreaRight.setLocation(this.x + 13, this.y + 11);
        this.solidArea.setLocation(this.x, this.y);
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
            //this.solidArea.setLocation(this.x, this.y);
        }

        if (Collision.collisionCheck(this.solidAreaRight)) {
            bomberman.setColidable(false);
            bomberman.setLife(false);
            heart--;
            System.out.println(heart);
            if (heart == 0) {
            }
        }

        if (Collision.collisionCheck(this.solidAreaLeft)) {
            bomberman.setColidable(false);
            bomberman.setLife(false);
            heart--;
            System.out.println(heart);
            if (heart == 0) {
//                Image img = new Image("res/Buttons/gameOver.png");
//                authorView.setImage(img);
            }
        }
        if(this.type == "Horizon") {
            x += this.direction;
        }else{
            y += this.direction;
        }
    }

    public void update() {
        movement();
    }
}
