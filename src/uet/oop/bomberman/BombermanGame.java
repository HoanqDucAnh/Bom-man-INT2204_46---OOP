package uet.oop.bomberman;

import javafx.animation.Animation;
//import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import javafx.util.Duration;
//import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.MenuButton;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.block.Bomb;
import uet.oop.bomberman.level.Level1;
import javafx.scene.paint.Paint;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static uet.oop.bomberman.graphics.MenuButton.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;
import static uet.oop.bomberman.graphics.Sprite.grass;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;

    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;
    private GraphicsContext gc;
    public static Canvas canvas;
    private Map map;
    public  List<Entity> entities = new ArrayList<>();
    public static final List<Entity> stillObjects = new ArrayList<>();
    public static final List<Entity> brick = new ArrayList<>();
    public static final List<Entity> items = new ArrayList<>();
    public static final List<Monster> monsters = new ArrayList<>();

    public static final List<Entity> block = new ArrayList<>();;

    public static Entity bomberman;
    public boolean gameState = true;
    public static boolean isAlive = true;
    public Timeline timeline;
    public List<MenuButton> buttonList = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }


    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        InputStream is = Files.newInputStream(Paths.get("res/Buttons/White.png"));
        Image img = new Image(is);
        is.close();
        ImageView imgView = new ImageView(img);

        MenuButton start = new MenuButton("Start");
        MenuButton quit = new MenuButton("Quit");
        start.setLayoutX(290);
        start.setLayoutY(290);
        quit.setLayoutX(290);
        quit.setLayoutY(350);
        buttonList.add(start);
        buttonList.add(quit);


        // Tao root container
        Group root = new Group();

        root.getChildren().add(canvas);
        root.getChildren().add(imgView);
        for (int i = 0; i < buttonList.size(); i++) {
            root.getChildren().add(buttonList.get(i));
        }


        // Tao scene
        Scene scene = new Scene(root);
        final int[] counter = {0};
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Bomman");


        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            render();
            update();
            if (start.isStatus() == false) {
                for (int i = 0; i < buttonList.size(); i++) {
                    root.getChildren().remove(buttonList.get(i));
                }
                root.getChildren().remove(imgView);
            }

            if (quit.isStatus() == false) {
                System.exit(1);
            }


        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        timeline.setRate(90);
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
                        Bomb.putBomb();
                    }

                    if (keyEvent.getCode() == KeyCode.P) {
                        if (gameState) {
                            timeline.pause();
                            gameState = false;
                        } else {
                            timeline.play();
                            gameState = true;
                        }

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
        monsters.forEach(Monster::update);
        items.forEach(Entity::update);

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        monsters.forEach((g -> g.render(gc)));
        items.forEach((g -> g.render(gc)));
        block.forEach((g -> g.render(gc)));
        bomberman.render(gc);
    }

}