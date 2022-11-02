package uet.oop.bomberman.entities.SmartMon;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Monster;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.SmartMon.*;
import uet.oop.bomberman.graphics.Map.*;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Oneal extends Monster {
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

        if (this.x % 32 == 0 && this.y %32 == 0) {
            Node initial_node = new Node(this.y / 32, this.x / 32);
            Node final_node = new Node(bomberman.getY()/32, bomberman.getX() / 32);
            int width = WIDTH;
            int height = HEIGHT;
            int rows = height;
            int cols = width;

            Astar a_star = new Astar(rows, cols, initial_node, final_node);

            int[][] blocks_in_array = new int[width * height][2];
            int count_block = 0;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (Map.map[j][i] != 0) {
                        blocks_in_array[count_block][0] = i;
                        blocks_in_array[count_block][1] = j;
                        count_block++;
                    }
                }
            }
            a_star.setBlocks(blocks_in_array, count_block);
            List<Node> path = a_star.findPath();
            if (path.size() != 0) {
                int nextX = path.get(1).getCol();
                int nextY = path.get(1).getRow();

                if (this.y / 32 > nextY)
                    y++;
                if (this.y / 32 < nextY)
                    y--;
                if (this.x / 32 > nextX)
                    x++;
                if (this.x / 32 < nextX)
                    x--;
            }
        }
    }

    @Override
    public void update() {
        movement();
    }

}
