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
    public int spriteNum = 1;
    protected Rectangle solidAreaUp;
    protected Rectangle solidAreaDown;
    protected Rectangle solidAreaLeft;
    protected Rectangle solidAreaRight;
    protected boolean isAlive;

    public Monster(int xUnit,int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.solidAreaUp = new Rectangle(this.x + 4, this.y - 4, 10, 10);
        this.solidAreaDown = new Rectangle(this.x + 4, this.y + 23, 15, 10);
        this.solidAreaLeft = new Rectangle(this.x - 2, this.y + 11, 10, 10);
        this.solidAreaRight = new Rectangle(this.x + 16, this.y + 11, 10, 10);
        this.isAlive = true;
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
