package uet.oop.bomberman;

import javafx.animation.Animation;
//import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
//import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Balloon;
import uet.oop.bomberman.entities.Monster;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.block.Bomb;
import uet.oop.bomberman.level.Level1;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import javafx.scene.paint.Paint;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;

    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;
    private GraphicsContext gc;
    private Canvas canvas;
    private Map map;
    public  List<Entity> entities = new ArrayList<>();
    public static final List<Entity> stillObjects = new ArrayList<>();
    public static final List<Entity> brick = new ArrayList<>();

    public static final List<Entity> block = new ArrayList<>();;

    public static Entity bomberman;
    public static List<Monster> monstersEntity = new ArrayList<>();
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0 ;
    private boolean arrayFilled = false ;
    public Rectangle bomber;
    public Monster balloon1;
    public Monster balloon2;
    public Monster balloon3;
    public static void main(String[] args) {

        System.setProperty("prism.verbose", "true");
        System.setProperty("prism.dirtyopts", "false");
        //System.setProperty("javafx.animation.fullspeed", "true");
        System.setProperty("javafx.animation.pulse", "10");
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
        stage.setTitle("Bomman");


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            render();
            update();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        timeline.setRate(120);

        new Level1();

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        balloon1 = new Balloon(2,13,Sprite.balloom_left1.getFxImage());
        balloon2 = new Balloon(23,13,Sprite.balloom_left1.getFxImage());
        balloon3 = new Balloon(14,1,Sprite.balloom_left1.getFxImage());


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
                    Bomb.putBomb();
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

            }
        });
    }


    public void update() {
        bomberman.update();
        block.forEach(Entity::update);
        balloon1.update();
        balloon2.update();
        balloon3.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        block.forEach((g -> g.render(gc)));
        bomberman.render(gc);
        balloon3.render(gc);
        balloon2.render(gc);
        balloon1.render(gc);
    }
}