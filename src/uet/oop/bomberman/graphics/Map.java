package uet.oop.bomberman.graphics;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static uet.oop.bomberman.BombermanGame.*;

public class Map {
    private GraphicsContext gc;

//    public Map(String level) throws IOException {
//        File map = new File(level);
//
//        Scanner scan = new Scanner(map);
//        ArrayList<String> loadMap = new ArrayList<String>();
//        while(scan.hasNextLine()){
//            loadMap.add(scan.nextLine());
//        }
//        String[] simpleArray = loadMap.toArray(new String[]{});
//
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (simpleArray[j].charAt(i) == '#') {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                } else if (simpleArray[j].charAt(i) == '*') {
//                    object = new Brick(i, j, Sprite.brick.getFxImage());
//                } else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//
//            }
//        }
//    }

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
                        Entity entity;
                        switch (s) {
                            case 2:
                                entity = new Wall(j, i, Sprite.wall.getFxImage(),true);
                                break;
                            case 1:
                                entity = new Brick(j, i, Sprite.brick.getFxImage(),true);
                                break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage(),false);
                        }
                        stillObjects.add(entity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
