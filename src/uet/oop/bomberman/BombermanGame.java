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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
//import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.*;
import uet.oop.bomberman.entities.block.Bomb;
import uet.oop.bomberman.graphics.MenuButton;
import uet.oop.bomberman.level.Level1;
import javafx.scene.paint.Paint;
import java.awt.*;
import java.awt.Label;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uet.oop.bomberman.level.Level2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static uet.oop.bomberman.entities.Bomber.heart;
import static uet.oop.bomberman.graphics.textScene.*;


import static uet.oop.bomberman.entities.Portal.isPortal;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;
import static uet.oop.bomberman.graphics.Sprite.grass;

import static uet.oop.bomberman.graphics.MenuButton.*;
import static uet.oop.bomberman.graphics.viewManager.*;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;

    public static int check = 0;
    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;
    private GraphicsContext gc;

    private Canvas canvas;
    private Map map;
    public  List<Entity> entities = new ArrayList<>();
    public static final List<Entity> stillObjects = new ArrayList<>();
    public static final List<Entity> brick = new ArrayList<>();
    public static final List<Entity> items = new ArrayList<>();
    public static final List<Monster> monsters = new ArrayList<>();
    public static final List<Monster> monsterCount = new ArrayList<>();

    public static final List<Entity> block = new ArrayList<>();;
    private static int levelNum = 1;
    public static Timeline timeline;


    public static Entity bomberman;

    private boolean coPortal = true;
    public static Group root = new Group();
    private long last_time;
    public static boolean timerOn = false;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }


    @Override
    public void start(Stage stage) throws IOException {

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();



        InputStream box = Files.newInputStream(Paths.get("res/Buttons/TextBox.png"));
        Image imgBox = new Image(box);
        box.close();
        ImageView imgBoxi = new ImageView(imgBox);




        // Tao root container
//        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(imgBoxi);
        textScene.createText(root);
        textScene.createTextLives(root);
        viewManager viewManagering = new viewManager(root, pause);
        viewManager viewManager = new viewManager(root);




        // Tao scene
        Scene scene = new Scene(root);
        final int[] counter = {0};
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Bomman");

        last_time = System.currentTimeMillis();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            render();
            try {
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (start) {
                root.getChildren().remove(pane);
                start = false;
            }
            if (timerOn) {
                time();
                heart();
            }


        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        timeline.setRate(90);



            bomberman = new Bomber(1, 2, Sprite.player_right.getFxImage());
            bomberman.setLife(false);
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
                    if (keyEvent.getCode() == KeyCode.B) {
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


        public void update() throws IOException {

            stillObjects.forEach(Entity::update);
            block.forEach(Entity::update);
            monsterCount.forEach(Monster::update);
            items.forEach(Entity::update);
            bomberman.update();
            System.out.println(heart);
            boolean wait = false;
            if (monsterCount.size() == 0 && !isPortal && !wait) {
                Entity portal = new Portal(_width - 2, _height - 2, Sprite.portal.getFxImage(), true);
                items.add(portal);

                if (Collision.collisionPortal(bomberman.getSolidAreaRight(), portal) && coPortal == true) {
                    transition(root);
                    coPortal = false;
                    wait = true;
                    long waitingTime = System.currentTimeMillis();
                }
            }
        }

        public void render() {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            stillObjects.forEach(g -> g.render(gc));
            monsterCount.forEach((g -> g.render(gc)));
            items.forEach((g -> g.render(gc)));
            block.forEach((g -> g.render(gc)));
            bomberman.render(gc);
        }

        public void transition(Group root) throws IOException {
            GameSubscene subscene = new GameSubscene("res/Buttons/Black.jpg");
            GameSubscene subscene1 = new GameSubscene(levelNum);

            root.getChildren().add(subscene);
            root.getChildren().add(subscene1);
//            root.getChildren().add(textP);
            subscene.moveScene();
            subscene1.moveScene();

        }


    public void time() {
        long now = System.currentTimeMillis();
        if (now - last_time > 1000) {
            last_time = System.currentTimeMillis();
            time_number--;
            time.setText("Time: " + time_number);

        }

    }

    public void heart() {
        live.setText("Heart: " + heart);
    }
    }
