package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.gamecollision.Collision;
import uet.oop.bomberman.gamecollision.CollisionChecker;
import uet.oop.bomberman.graphics.gamesprite.Sprite;

import static uet.oop.bomberman.entities.player.Bomber.*;
import static uet.oop.bomberman.gamerunner.BombermanGame.*;

public class Kodoria extends Monster {
    public  static long timeKodoria;
    public static long timeTmpKodoria;

    public static int tempKodoria;
    private CollisionChecker collisionCheckerBalloon;

    /**
     * dir = 1 -> go right
     * dir = -1 -> go left
     */
    private static int swapKillKodoria = 1;
    private static int resetX;
    private static int resetY;

    public Kodoria(int xUnit, int yUnit, Image img, boolean isColidable, boolean isAlive) {
        super(xUnit, yUnit, img, isColidable, isAlive);
        resetX = xUnit;
        resetY = yUnit;
    }

    private void killKondoria(Monster animal) {
            if (swapKillKodoria == 1) {
                animal.setImg(Sprite.kondoria_dead.getFxImage());
                swapKillKodoria = 2;
            } else if (swapKillKodoria == 2) {
                animal.setImg(Sprite.player_dead3.getFxImage());
                swapKillKodoria = 3;
            } else {
                animal.setAlive(false);
                monsters.remove(animal);
                swapKillKodoria = 1;
            }
    }
    public void setRec() {
        this.solidAreaUp.setLocation(this.x + 4, this.y - 4);
        this.solidAreaDown.setLocation(this.x + 3, this.y + 23);
        this.solidAreaLeft.setLocation(this.x +4, this.y + 11);
        this.solidAreaRight.setLocation(this.x + 16, this.y + 11);
        this.solidArea.setLocation(this.x, this.y);
    }
    public void movement() {
        Image[] left = {
                Sprite.kondoria_left1.getFxImage(),
                Sprite.kondoria_left2.getFxImage(),
                Sprite.kondoria_left3.getFxImage()
        };
        Image[] right = {
                Sprite.kondoria_right1.getFxImage(),
                Sprite.kondoria_right2.getFxImage(),
                Sprite.kondoria_right3.getFxImage()
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

        setRec();
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
            this.solidArea.setLocation(this.x, this.y);
        }

        x+= this.direction;
    }


    public void kodoChansingMove() {
        Image[] left = {
                Sprite.kondoria_left1.getFxImage(),
                Sprite.kondoria_left2.getFxImage(),
                Sprite.kondoria_left3.getFxImage()
        };
        Image[] right = {
                Sprite.kondoria_right1.getFxImage(),
                Sprite.kondoria_right2.getFxImage(),
                Sprite.kondoria_right3.getFxImage()
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
        setRec();
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

        if (Collision.collisionCheck(this.solidAreaRight)) {
            bomberman.setColidable(false);
            monsterCount.remove(this);
            System.out.println(heart);
            if (heart == 0) {
            }
        }

        if (Collision.collisionCheck(this.solidAreaLeft)) {
            bomberman.setColidable(false);
            monsterCount.remove(this);
            System.out.println(heart);
            if (heart == 0) {

            }
        }

        if (bomberman.getX() < this.x) {
                x --;
        }
        if (bomberman.getX() > this.x) {
                x++;
        }
        if (bomberman.getY() > this.y) {
                y++;
        }
         if (bomberman.getY() < this.y) {
                y--;
        }
    }
    public void update() {
/*
        for (Monster x : monsters) {
            if (x instanceof Kodoria) {
                if (Collision.collisionCheck(x.getSolidAreaLeft())) {
                    x.setImg(Sprite.transparent.getFxImage());
                    bomberman.setColidable(false);
                    heart--;
                    System.out.println(heart);
                    if (heart == 0) {

                    }
                    monsters.remove(x);
                    break;
                }
            }
        }
        for (Monster x : monsters) {
            if (x instanceof Kodoria) {
                if (Collision.collisionCheck(x.getSolidAreaRight())) {
                    x.setImg(Sprite.transparent.getFxImage());
                    //x.setImg(Sprite.kondoria_dead.getFxImage());
                    bomberman.setColidable(false);
                    heart--;
                    System.out.println(heart);
                    monsters.remove(x);
                    break;
                }
            }
        }
        for (Monster x : monsters) {
            if (x instanceof Kodoria) {
                if (Collision.collisionCheck(x.getSolidAreaDown())) {
                    x.setImg(Sprite.transparent.getFxImage());
                    //x.setImg(Sprite.kondoria_dead.getFxImage());
                    bomberman.setColidable(false);
                    heart--;
                    System.out.println(heart);
                    x.setAlive(false);
                    monsters.remove(x);
                    break;
                }
            }
        }
        for (Monster x : monsters) {
            if (x instanceof Kodoria) {
                if (Collision.collisionCheck(x.getSolidAreaUp())) {
                    x.setImg(Sprite.transparent.getFxImage());
                    //x.setImg(Sprite.kondoria_dead.getFxImage());
                    bomberman.setColidable(false);
                    heart--;
                    System.out.println(heart);
                    monsters.remove(x);

                }
            }
        }


 */

        double check = Math.sqrt((bomberman.getX()/32 - this.getX()/32) * (bomberman.getX()/32 - this.getX()/32) + (bomberman.getY()/32 - this.getY()/32) * (bomberman.getY()/32 - this.getY()/32));
        if(check>=4) {
            movement();
            this.setImg(Sprite.transparent.getFxImage());
        }else{
            kodoChansingMove();
        }
    }
}
