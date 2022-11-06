package uet.oop.bomberman.level;

import uet.oop.bomberman.graphics.gamemap.Map;


import static uet.oop.bomberman.entities.player.Bomber.*;
import static uet.oop.bomberman.gamerunner.BombermanGame.*;
import static uet.oop.bomberman.gamerunner.BombermanGame.monsters;

public class Level2 {
    public Level2() {
        stillObjects.clear();
        monsters.clear();
        items.clear();
        new Map("res\\levels\\Level2.txt");
        //new MonsterMap("res\\levels\\Level1.txt");
        //bomberman.setLife(true);
        currentSpeed = 1;
        speedLeft = 1;
        speedRight = 1;
        speedDown = 1;
        speedUp = 1;
//        Monster enemy = new Balloon(10,1, Sprite.balloom_left1.getFxImage());
//        Monster enemy1 = new Balloon(10,3, Sprite.balloom_left1.getFxImage());
//        monsters.add(enemy);
//        monsters.add(enemy1);
//        for (Monster monster : monsters) {
//            monster.setAlive(true);
//        }
    }

}
