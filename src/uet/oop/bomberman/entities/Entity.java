package uet.oop.bomberman.entities;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected Image img;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    protected Rectangle solidArea;
    protected Rectangle solidAreaUp;
    protected Rectangle solidAreaDown;
    protected Rectangle solidAreaLeft;
    protected Rectangle solidAreaRight;

    private boolean colidable;

    protected boolean life;

    public boolean isLife() {
        return life;
    }

    public void setLife(boolean life) {
        this.life = life;
    }

    public Entity( int xUnit, int yUnit, Image img,boolean colidable) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.colidable = colidable;
        this.solidArea = new Rectangle(x,y,32, 32);

    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
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

    public boolean isColidable() {
        return colidable;
    }

    public void setColidable(boolean colidable) {
        this.colidable = colidable;
    }

    public Rectangle getSolidAreaRight() {
        return solidAreaRight;
    }

    public void setSolidAreaRight(Rectangle solidAreaRight) {
        this.solidAreaRight = solidAreaRight;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
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

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();

}