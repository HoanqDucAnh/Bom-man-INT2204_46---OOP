package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.SmartMon.Oneal;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static uet.oop.bomberman.BombermanGame.*;

public class MonsterMap {
    public MonsterMap(String level) {
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
                        Monster monster;
                        switch (s) {
                            case 4:
                                monster = new Balloon(j, i, Sprite.balloom_left1.getFxImage(), true, true);
                                break;
                            case 5:
                                monster = new Oneal(j, i, Sprite.oneal_right1.getFxImage(),true,true);
                                break;
                            default:
                                monster = new Unharmed(j, i, Sprite.transparent.getFxImage(), false, false);

                        }
                        monsters.add(monster);

                    }


                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i) instanceof Balloon) {
                monsterCount.add(monsters.get(i));
            }
            if(monsters.get(i) instanceof Oneal){
                monsterCount.add(monsters.get(i));
            }
        }
    }

}


