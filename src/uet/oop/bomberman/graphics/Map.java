package uet.oop.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static uet.oop.bomberman.BombermanGame.*;

public class Map {
    public static int[][] map = new int[WIDTH][HEIGHT];
    public Map(String level) {
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File(level);
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();

            StringTokenizer tokens = new StringTokenizer(line);
            _level = Integer.parseInt(tokens.nextToken());
            _height = Integer.parseInt(tokens.nextToken());
            _width = Integer.parseInt(tokens.nextToken());

            while (sc.hasNextLine()) {
                for (int i = 0; i < _height; ++i) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);

                    for (int j = 0; j < _width; j++) {
                        int s = Integer.parseInt(tokenTile.nextToken());
                        map[j][i] = s;
                        Entity entity;
                        Monster monster = null;
                        switch (s) {
                            case 4:
                                entity = new Portal(j, i, Sprite.grass.getFxImage(), true);
                                s = 0;
                                break;
                            case 2:
                                entity = new Wall(j, i, Sprite.wall.getFxImage(),true);
                                break;
                            case 1:
                                entity = new Brick(j, i, Sprite.brick.getFxImage(),true);
                                break;
                            case 6:
                                entity = new Item(j, i, Sprite.powerup_speed.getFxImage(), true);
                                break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage(),false);
                        }
                        stillObjects.add(entity);

                    }


                }

            }

            for (int i = 0; i < stillObjects.size(); i ++) {
                if (stillObjects.get(i) instanceof Item) {
                    items.add(stillObjects.get(i));
                }
            }
            for (int i = 0; i < stillObjects.size(); i ++) {
                if (stillObjects.get(i) instanceof Brick) {
                    brick.add(stillObjects.get(i));
                }
            }
            for (int i = 0; i < stillObjects.size(); i ++) {
                if (stillObjects.get(i) instanceof Portal) {
                    items.add(stillObjects.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}