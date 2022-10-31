package uet.oop.bomberman.level;


import uet.oop.bomberman.entities.Balloon;
import uet.oop.bomberman.entities.Monster;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.MonsterMap;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.BombermanGame.*;

public class Level1 {
    public Level1() {
        block.clear();
        stillObjects.clear();
        new Map("res\\levels\\Level1.txt");
        //new MonsterMap("res\\levels\\Level1.txt");
        //bomberman.setLife(true); 
        Monster enemy = new Balloon(10,1, Sprite.balloom_left1.getFxImage());
        Monster enemy1 = new Balloon(10,3, Sprite.balloom_left1.getFxImage());
        monsters.add(enemy);
        monsters.add(enemy1);
        for (Monster monster : monsters) {
            monster.setAlive(true);
        }
    }


}
