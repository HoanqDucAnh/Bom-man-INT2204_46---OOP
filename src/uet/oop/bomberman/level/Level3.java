package uet.oop.bomberman.level;

import uet.oop.bomberman.entities.Balloon;
import uet.oop.bomberman.entities.Monster;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;


import static uet.oop.bomberman.entities.Bomber.*;
import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.monsters;

public class Level3 {
    public Level3() {
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
        Monster enemy3 = new Balloon(10, 7,Sprite.oneal_right1.getFxImage());
        Monster enemy = new Balloon(10,1, Sprite.balloom_left1.getFxImage());
        Monster enemy1 = new Balloon(10,3, Sprite.balloom_left1.getFxImage());
        monsters.add(enemy);
        monsters.add(enemy1);
        monsters.add(enemy3);
        for (Monster monster : monsters) {
            monster.setAlive(true);
        }
    }

}
