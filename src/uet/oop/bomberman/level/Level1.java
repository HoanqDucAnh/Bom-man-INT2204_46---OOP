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
        new MonsterMap("res\\levels\\Level1.txt");
        //new MonsterMap("res\\levels\\Level1.txt");
        //bomberman.setLife(true);
        for (Monster monster : monsters) {
            monster.setAlive(true);
        }
    }


}
