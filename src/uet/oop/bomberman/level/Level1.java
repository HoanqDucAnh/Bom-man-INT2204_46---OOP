package uet.oop.bomberman.level;


import uet.oop.bomberman.entities.Balloon;
import uet.oop.bomberman.entities.Monster;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.MonsterMap;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.BombermanGame.*;

public class Level1 {
    public Level1() {
        stillObjects.clear();
        monsters.clear();
        items.clear();
        block.clear();
        stillObjects.clear();
        new Map("res\\levels\\Level1.txt");
        //new MonsterMap("res\\levels\\Level1.txt");
        //bomberman.setLife(true); 
        Monster enemy = new Balloon(12,2, Sprite.balloom_left1.getFxImage());
        Monster enemy1 = new Balloon(10,4, Sprite.balloom_left1.getFxImage());
        Monster enemy2 = new Balloon(10,6, Sprite.balloom_left1.getFxImage());
        Monster enemy3 = new Balloon(10,8, Sprite.balloom_left1.getFxImage());
        Monster enemy4 = new Balloon(10,10, Sprite.balloom_left1.getFxImage());
        Monster enemy5 = new Balloon(16,12, Sprite.balloom_left1.getFxImage());
        Monster enemy6 = new Balloon(13,13, Sprite.balloom_left1.getFxImage());
        monsters.add(enemy);
        monsters.add(enemy1);
        monsters.add(enemy2);
        monsters.add(enemy3);
        monsters.add(enemy4);
        monsters.add(enemy5);
        monsters.add(enemy6);
        for (Monster monster : monsters) {
            monster.setAlive(true);
        }
    }


}
