package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.block.Bomb;
import uet.oop.bomberman.entities.block.Bomb;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 20;

    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;
    private GraphicsContext gc;
    private Canvas canvas;
    private Map map;
    public  List<Entity> entities = new ArrayList<>();
    public static final List<Entity> stillObjects = new ArrayList<>();

    public static final List<Entity> block = new ArrayList<>();;

    public static Entity bomberman;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }


    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        final int[] counter = {0};


        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();


        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A) {
                    bomberman.setLeftPressed(true);
                }
                if (keyEvent.getCode() == KeyCode.W) {
                    bomberman.setUpPressed(true);
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    bomberman.setDownPressed(true);
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    bomberman.setRightPressed(true);
                }
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    ;Bomb.putBomb();
                }
//
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A) {
                    bomberman.setLeftPressed(false);
                    bomberman.setImg(Sprite.player_left.getFxImage());
                }
                if (keyEvent.getCode() == KeyCode.W) {
                    bomberman.setUpPressed(false);
                    bomberman.setImg(Sprite.player_up.getFxImage());
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    bomberman.setDownPressed(false);
                    bomberman.setImg(Sprite.player_down.getFxImage());
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    bomberman.setRightPressed(false);
                    bomberman.setImg(Sprite.player_right.getFxImage());
                }
//
            }
        });
//
    }




    public void update() {
        block.forEach(Entity::update);
        entities.forEach(Entity::update);
        bomberman.update();
        block.clear();

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        block.forEach(g->g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomberman.render(gc);
    }
}
