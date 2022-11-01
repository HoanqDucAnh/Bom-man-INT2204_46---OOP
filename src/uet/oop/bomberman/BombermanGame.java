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
import javafx.stage.Stage;
import javafx.util.Duration;
//import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.block.Bomb;
import uet.oop.bomberman.level.Level1;
import javafx.scene.paint.Paint;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uet.oop.bomberman.level.Level2;
import uet.oop.bomberman.level.LevelUp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.*;
import javafx.stage.Stage;



import static uet.oop.bomberman.entities.Portal.isPortal;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;
import static uet.oop.bomberman.graphics.Sprite.grass;
import static uet.oop.bomberman.level.LevelUp.*;
import uet.oop.bomberman.graphics.MenuButton;
import static uet.oop.bomberman.graphics.MenuButton.*;

public class BombermanGame extends Application {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;

    public static int check = 0;
    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;
    private GraphicsContext gc;
    public static boolean running;
    private Canvas canvas;
    private Map map;
    public  List<Entity> entities = new ArrayList<>();
    public static final List<Entity> stillObjects = new ArrayList<>();
    public static final List<Entity> brick = new ArrayList<>();
    public static final List<Entity> items = new ArrayList<>();
    public static final List<Monster> monsters = new ArrayList<>();

    public static final List<Entity> block = new ArrayList<>();;
    public boolean gameState = true;
    public static boolean isAlive = true;
    public Timeline timeline;
    public List<MenuButton> buttonList = new ArrayList<>();

    public static Entity bomberman;

    public static ImageView authorView;

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


//            if (monsters.size() == 0 ) {
//                stillObjects.clear();
//                block.clear();
//                new Level2();
//            }
//            if (wait) {
//                long now = System.currentTimeMillis();
//                if (now - waitingTime > 3000) {
//                    switch (_level) {
//                        case 1:
//                            isPortal = false;
//                            new Level2();
//                            break;
////                        case 3:
////                            new Level1();
//                    }
//                    wait = false;
//                }
//            }
        System.out.print(stillObjects.size());
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
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

        Rectangle rec1 = new Rectangle(bomberman.getX() ,bomberman.getY() ,30,30); //up
        Rectangle rec2 = new Rectangle(1 * Sprite.SCALED_SIZE + 4,1 * Sprite.SCALED_SIZE + 23 ,10,10); //down
        Rectangle rec3 = new Rectangle(1 * Sprite.SCALED_SIZE + 16,1 * Sprite.SCALED_SIZE + 16,10,10); //right
        Rectangle rec4 = new Rectangle(1 * Sprite.SCALED_SIZE - 2,1 * Sprite.SCALED_SIZE + 11 ,10,10); //left
        rec1.setFill(Color.RED);
        rec2.setFill(Color.BLUE);
        rec3.setFill(Color.YELLOW);
        rec4.setFill(Color.GREEN);

        root.getChildren().add(rec1);
        root.getChildren().add(rec2);
        root.getChildren().add(rec3);
        root.getChildren().add(rec4);
    }


    public void update() {
        //bomberman.update();
        stillObjects.forEach(Entity::update);
        block.forEach(Entity::update);
        monsters.forEach(Monster::update);
        items.forEach(Entity::update);
        bomberman.update();

        if (monsters.size() == 0 && !isPortal && !wait) {
            Entity portal = new Portal(_width - 2, _height - 2, Sprite.portal.getFxImage(), true);
            items.add(portal);

            if (Collision.collisionPortal(bomberman.getSolidAreaRight(), portal)) {
                wait = true;
                waitingTime = System.currentTimeMillis();
            }
        }
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