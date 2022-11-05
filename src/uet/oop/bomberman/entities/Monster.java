package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Monster {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected Image img;
    public int spriteCounter = 0;
    protected boolean isColidable;
    public int spriteNum = 1;
    protected Rectangle solidArea;
    protected Rectangle solidAreaUp;
    protected Rectangle solidAreaDown;
    protected Rectangle solidAreaLeft;
    protected Rectangle solidAreaRight;
    protected boolean isAlive;

    protected int direction = 1;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public boolean isColidable() {
        return isColidable;
    }

    public void setColidable(boolean colidable) {
        isColidable = colidable;
    }

    public Rectangle getSolidAreaUp() {
        return solidAreaUp;
    }

    public void setSolidAreaUp(Rectangle solidAreaUp) {
        this.solidAreaUp = solidAreaUp;
    }

    public Rectangle getSolidAreaDown() {
        return solidAreaDown;
    }

    public void setSolidAreaDown(Rectangle solidAreaDown) {
        this.solidAreaDown = solidAreaDown;
    }

    public Rectangle getSolidAreaLeft() {
        return solidAreaLeft;
    }

    public void setSolidAreaLeft(Rectangle solidAreaLeft) {
        this.solidAreaLeft = solidAreaLeft;
    }

    public Rectangle getSolidAreaRight() {
        return solidAreaRight;
    }

    public void setSolidAreaRight(Rectangle solidAreaRight) {
        this.solidAreaRight = solidAreaRight;
    }

    public Monster(int xUnit, int yUnit, Image img, boolean isColidable, boolean isAlive) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.solidAreaUp = new Rectangle(this.x + 4, this.y +1, 15, 10);
        this.solidAreaDown = new Rectangle(this.x + 4, this.y + 24, 15, 10);
        this.solidAreaLeft = new Rectangle(this.x - 2, this.y + 11, 10, 15);
        this.solidAreaRight = new Rectangle(this.x + 16, this.y + 11, 10, 15);
        this.solidArea = new Rectangle(this.x+3, this.y-5, 10, 10);
        this.isColidable = isColidable;
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();

}
