package uet.oop.bomberman.entities.SmartMon;

import javafx.scene.image.Image;
import uet.oop.bomberman.Collision;
import uet.oop.bomberman.CollisionChecker;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Monster;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.SmartMon.*;
import uet.oop.bomberman.graphics.Map.*;

import java.awt.*;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Oneal extends Monster {
    private CollisionChecker collisionCheckerOneal;
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.isColidable = true;
    }

    public void movement() {
        Image[] left = {
                Sprite.oneal_left1.getFxImage(),
                Sprite.oneal_left2.getFxImage(),
                Sprite.oneal_left3.getFxImage()
        };
        Image[] right = {
                Sprite.oneal_right1.getFxImage(),
                Sprite.oneal_right2.getFxImage(),
                Sprite.oneal_right3.getFxImage()
        };
        spriteCounter++;
        if (spriteCounter > 32) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        this.solidAreaUp = new Rectangle(this.x + 4, this.y - 4, 20, 16);
        this.solidAreaDown = new Rectangle(this.x + 4, this.y + 23, 20, 16);
        this.solidAreaLeft = new Rectangle(this.x - 2, this.y + 11, 20, 16);
        this.solidAreaRight = new Rectangle(this.x + 16, this.y + 11, 20, 16);
        System.out.println(this.y);
            if (bomberman.getX() < this.x) {
                if(!Collision.collision(collisionCheckerOneal,this.solidAreaLeft)) {
                    x --;
                }else{
                    x+=0;
                }
            }
            else if (bomberman.getX() > this.x) {
                if(!Collision.collision(collisionCheckerOneal,this.solidAreaRight)) {
                    x++;
                }else{
                    x-=0;
                }
            }
            else if (bomberman.getY() > this.y) {
                if(!Collision.collision(collisionCheckerOneal,this.solidAreaUp)){
                    y++;
                }
            }
            else if (bomberman.getY() < this.y) {
                if(!Collision.collision(collisionCheckerOneal,this.solidAreaDown)){
                      y--;
                }
            }

    }

    @Override
    public void update() {
        movement();
    }

}
