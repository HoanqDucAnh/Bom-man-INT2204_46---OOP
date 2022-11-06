package uet.oop.bomberman.graphics.gamemap;

import uet.oop.bomberman.entities.buildingblocks.Brick;
import uet.oop.bomberman.entities.buildingblocks.Grass;
import uet.oop.bomberman.entities.buildingblocks.Portal;
import uet.oop.bomberman.entities.buildingblocks.Wall;
import uet.oop.bomberman.entities.enemy.Monster;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Item.HeartIterm;
import uet.oop.bomberman.entities.Item.SpeedItem;
import uet.oop.bomberman.graphics.gamesprite.Sprite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static uet.oop.bomberman.gamerunner.BombermanGame.*;

public class Map {
    public static int[][] mapObj = new int[HEIGHT][WIDTH];
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
                for (int i = 0; i < _height; i++) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenTile = new StringTokenizer(lineTile);

                    for (int j = 0; j < _width; j++) {
                        int s = Integer.parseInt(tokenTile.nextToken());
                        Entity entity;
                        Monster monster = null;
                        mapObj[i][j] = s;
                        switch (s) {
                            case 3:
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
                                entity = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage(), true);
                                break;
                            case 8:
                                entity = new HeartIterm(j, i, Sprite.powerup_detonator.getFxImage(), true);
                                break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage(),false);
                        }
                        stillObjects.add(entity);

                    }


                }

            }

            for (int i = 0; i < stillObjects.size(); i ++) {
                if (stillObjects.get(i) instanceof HeartIterm) {
                    items.add(stillObjects.get(i));
                }
            }
            for (int i = 0; i < stillObjects.size(); i ++) {
                if (stillObjects.get(i) instanceof SpeedItem) {
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
