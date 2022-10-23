package uet.oop.bomberman.level;


import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.MonsterMap;

public class Level1 {
    public Level1() {
        new Map("res\\levels\\Level1.txt");
        new MonsterMap("res\\levels\\Level1.txt");
    }


}
