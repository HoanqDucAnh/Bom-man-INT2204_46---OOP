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

package uet.oop.bomberman.entities.player;

import javafx.scene.image.Image;

import uet.oop.bomberman.gamecollision.Collision;
import uet.oop.bomberman.gamecollision.CollisionChecker;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.gamesprite.Sprite;

import java.awt.*;


import static uet.oop.bomberman.gamerunner.BombermanGame.*;


public class Bomber extends Entity {

    public  static long timeBomber;
    public static long timeTmp1;
    public static int isBomber = 0;
    public static long timeTmp2;
    public static int swapKill = 1;
    private static int countKill = 0;
    private CollisionChecker collisionCheckerBomber;

    public CollisionChecker getCollisionCheckerBomber() {
        return collisionCheckerBomber;
    }

    public void setCollisionCheckerBomber(CollisionChecker collisionCheckerBomber) {
        this.collisionCheckerBomber = collisionCheckerBomber;
    }
    public static int heart = 3;
    public static int currentSpeed = 1;
    public static int speedLeft = 1;
    public static int speedRight = 1;
    public static int speedUp = 1;
    public static int speedDown = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img, true);
        this.solidAreaUp = new Rectangle(this.x + 4, this.y - 4, 15, 15);
        this.solidAreaDown = new Rectangle(this.x + 4, this.y + 23, 15, 15);
        this.solidAreaLeft = new Rectangle(this.x - 2, this.y + 11, 15, 15);
        this.solidAreaRight = new Rectangle(this.x + 16, this.y + 11, 15, 15);
        this.solidArea = new Rectangle(this.x, this.y, 20, 20);
        //this.solidArea = new Rectangle()
        life = true;
    }

    public static void killBomber() {
            if (swapKill == 1) {
                bomberman.setImg(Sprite.player_dead1.getFxImage());
                swapKill = 2;
            } else if (swapKill == 2) {
                bomberman.setImg(Sprite.player_dead2.getFxImage());
                swapKill = 3;
            } else if (swapKill == 3) {
                bomberman.setImg(Sprite.player_dead3.getFxImage());
                swapKill = 4;
            } else {
                bomberman.setImg(Sprite.player_right.getFxImage());
                bomberman.setX(1*32);
                bomberman.setY(2*32);
                swapKill = 1;
            }
            setIsAlive(true);
    }


    public void setUP() {
        if (Collision.collision(collisionCheckerBomber, this.solidAreaUp)) {
            speedUp = 0;
        }
        if (!Collision.collision(collisionCheckerBomber, this.solidAreaUp)) {
            speedUp = currentSpeed;
        }
        if (Collision.collisionItemSpeed(collisionCheckerBomber, this.solidAreaUp)) {
            currentSpeed++;
        }
        if (Collision.collisionItemHeart(collisionCheckerBomber, this.solidAreaUp)) {
            heart++;
        }
    }

    public void setLeft() {
        if (Collision.collision(collisionCheckerBomber, this.solidAreaLeft)) {
            speedLeft = 0;
        }
        if (!Collision.collision(collisionCheckerBomber, this.solidAreaLeft)) {
            speedLeft = currentSpeed;
        }
        if (Collision.collisionItemSpeed(collisionCheckerBomber, this.solidAreaLeft)) {
            currentSpeed++;
        }
        if (Collision.collisionItemHeart(collisionCheckerBomber, this.solidAreaLeft)) {
            heart++;
        }
    }

    public void setDown() {
        if (Collision.collision(collisionCheckerBomber, this.solidAreaDown)) {
            speedDown = 0;
        }
        if (!Collision.collision(collisionCheckerBomber, this.solidAreaDown)) {
            speedDown = currentSpeed;
        }
        if (Collision.collisionItemSpeed(collisionCheckerBomber, this.solidAreaDown)) {
            currentSpeed++;
        }
        if (Collision.collisionItemHeart(collisionCheckerBomber, this.solidAreaDown)) {
            heart++;
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
        if (Collision.collisionItemHeart(collisionCheckerBomber, this.solidAreaRight)) {
            heart++;
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
        this.solidAreaDown.setLocation(this.x + 3, this.y + 21);
        this.solidAreaLeft.setLocation(this.x - 2, this.y + 11);
        this.solidAreaRight.setLocation(this.x + 16, this.y + 11);
        this.solidArea.setLocation(this.x+3, this.y+3);
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
                    this.img = up[2];
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

/*
      if (Collision.collisionMonsterMain(collisionCheckerBomber, this.solidArea)) {
              heart--;
              System.out.println(heart);
              if (heart == 0) {
                  bomberman.setX(1 * 32);
                  bomberman.setY(1 * 32);
              }
      }

 */
    }

    public static void setIsAlive(boolean status) {
        life = status;
    }


    public boolean returnAlive() {
        return life;
    }

    public void update() {
        if (Collision.collisionMonsterKodoria(collisionCheckerBomber, this.solidArea)) {

        }
        if (bomberman.isLife() == true) {
            movement();
        }

    }
}



