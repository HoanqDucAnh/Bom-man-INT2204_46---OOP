package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.gamecollision.Collision;
import uet.oop.bomberman.gamecollision.CollisionChecker;
import uet.oop.bomberman.graphics.gamesprite.Sprite;

import java.util.Random;

import static uet.oop.bomberman.entities.player.Bomber.heart;
import static uet.oop.bomberman.gamerunner.BombermanGame.*;

public class Oneal extends Monster {
    private static int directionChangeTimer = 100;
    private static String currentDir = "UP";
    private CollisionChecker collisionCheckerOneal;
    public Oneal(int xUnit, int yUnit, Image img, boolean isColidable, boolean isAlive) {
        super(xUnit, yUnit, img, isColidable, isAlive);

    }

    public static int generateRandom()
    {
        Random random = new Random();
        int num =  random.nextInt(4);
        return num;
    }
    public String getDir(){
        if(this.getX()/16 == bomberman.getX()/16){
            if (this.getY()/16 > bomberman.getY()/16){
                currentDir = "UP";
                return currentDir;
            }else {
                currentDir = "DOWN";
                return currentDir;
            }
        }else if(this.getY()/16 == bomberman.getY()/16){
            if (this.getX()/16 > bomberman.getX()/16){
                currentDir = "LEFT";
                return currentDir;
            }else {
                currentDir = "RIGHT";
                return currentDir;
            }
        }else {
            int num = generateRandom();
            if(directionChangeTimer>0){
                directionChangeTimer--;
                return currentDir;
            }else {
                directionChangeTimer = 15;
                switch (num) {
                    case 0:
                        currentDir = "UP";
                        return currentDir;
                    case 1:
                        currentDir = "DOWN";
                        return currentDir;
                    case 2:
                        currentDir = "LEFT";
                        return currentDir;
                    case 3:
                        currentDir = "RIGHT";
                        return currentDir;
                }
            }
        }
        return currentDir;
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
        this.solidArea.setLocation(this.x+3, this.y+3);
//            if (bomberman.getX() < this.x) {
//                if(!Collision.onealCollisionBrick(collisionCheckerOneal,this.solidAreaLeft) &&
//                        (!Collision.collision(collisionCheckerOneal,this.solidAreaLeft))) {
//                    x--;
//                }
//            }
//            else if (bomberman.getX() > this.x) {
//                if(!Collision.onealCollisionBrick(collisionCheckerOneal,this.solidAreaRight) &&
//                        (!Collision.collision(collisionCheckerOneal,this.solidAreaRight))) {
//                    x++;
//                }
//            }
//            else if (bomberman.getY() > this.y) {
//                if(!Collision.onealCollisionBrick(collisionCheckerOneal,this.solidAreaUp) &&
//                        (!Collision.collision(collisionCheckerOneal,this.solidAreaUp))){
//                    y++;
//                }
//            }
//            else if (bomberman.getY() < this.y) {
//                if(!Collision.onealCollisionBrick(collisionCheckerOneal,this.solidAreaDown) &&
//                        (!Collision.collision(collisionCheckerOneal,this.solidArea))){
//                      y--;
//                }
//            }

        String dir = getDir();
        switch (dir) {
            case "UP":
                if(!Collision.collision(collisionCheckerOneal,this.solidAreaUp)) {
                    if (spriteNum == 1) {
                        this.img = left[1];
                    }

                    if (spriteNum == 2) {
                        this.img = left[2];
                    }
                    y--;
                }
                break;
            case "DOWN":
                if(!Collision.collision(collisionCheckerOneal,this.solidAreaDown)) {
                    if (spriteNum == 1) {
                        this.img = right[1];
                    }

                    if (spriteNum == 2) {
                        this.img = right[2];
                    }
                    y++;
                }
                break;
            case "LEFT" :
                if(!Collision.collision(collisionCheckerOneal,this.solidAreaLeft)) {
                    if (spriteNum == 1) {
                        this.img = left[1];
                    }

                    if (spriteNum == 2) {
                        this.img = left[2];
                    }
                    x--;
                }
                break;
            case "RIGHT":
                if(!Collision.collision(collisionCheckerOneal,this.solidAreaRight)) {
                    if (spriteNum == 1) {
                        this.img = right[1];
                    }

                    if (spriteNum == 2) {
                        this.img = right[2];
                    }
                    x++;
                }
                break;
        }


        if (Collision.collisionCheck(this.solidAreaLeft)) {
            bomberman.setColidable(false);
            bomberman.setLife(false);
            heart--;
            System.out.println(heart);
            if (heart == 0) {

            }
        }

        if (Collision.collisionCheck(this.solidAreaRight)) {
            bomberman.setColidable(false);
            bomberman.setLife(false);
            heart--;
            System.out.println(heart);
            if (heart == 0) {

            }
        }
        if (Collision.collisionCheck(this.solidAreaUp)) {
            bomberman.setColidable(false);
            bomberman.setLife(false);
            heart--;
            System.out.println(heart);
            if (heart == 0) {

            }
        }

        if (Collision.collisionCheck(this.solidAreaDown)) {
            bomberman.setColidable(false);
            bomberman.setLife(false);
            heart--;
            System.out.println(heart);
            if (heart == 0) {

            }
        }
    }

    @Override
    public void update() {
        movement();
    }

}
