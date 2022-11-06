package uet.oop.bomberman.gamecollision;

import java.awt.*;

public class CollisionChecker {
    private Rectangle player;
    private Rectangle obstacle;

    public CollisionChecker(Rectangle player, Rectangle obstacle) {
        this.player = player;
        this.obstacle = obstacle;
    }

    public Rectangle getObstacle() {
        return obstacle;
    }

    public Rectangle getPlayer() {
        return player;
    }

    public void setObstacle(Rectangle obstacle) {
        this.obstacle = obstacle;
    }

    public void setPlayer(Rectangle player) {
        this.player = player;
    }

    public boolean isColided() {
        return player.intersects(obstacle);
    }
}
