package uet.oop.bomberman.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.GameSubscene;
import uet.oop.bomberman.level.Level1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static uet.oop.bomberman.gamerunner.BombermanGame.*;
import static uet.oop.bomberman.graphics.textScene.time_number;
import static uet.oop.bomberman.sound.soundManager.mediaPlayer;


public class viewManager {

    public static Pane pane = new Pane();
    public static Pane ingPane = new Pane();
    public static boolean start = false;
    public static boolean pause = false;
    public static boolean mute = false;
    private static final String fontPath = "res/font/Mario.ttf";

    public viewManager(Group root) throws IOException {
        createBackground();
        createStart();
        createQuit();
        createGuide();
        root.getChildren().add(pane);

    }

    public viewManager(Group root, boolean bruh) throws FileNotFoundException {
        createPause();
        createMute();
        root.getChildren().add(ingPane);

    }

    public void createStart() throws FileNotFoundException {

        MenuButton button = new MenuButton("Start");
        button.setLayoutX(290);
        button.setLayoutY(290);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                time_number = 120;
                start = true;
                timerOn = true;
                new Level1();
                System.out.println("bruh");

            }
        });
        pane.getChildren().add(button);
    }

    public void createQuit() throws FileNotFoundException {

        MenuButton button = new MenuButton("Quit");
        button.setLayoutX(290);
        button.setLayoutY(410);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        pane.getChildren().add(button);
    }

    public void createBackground() throws IOException {
        InputStream is = Files.newInputStream(Paths.get("res/Buttons/White.png"));
        Image img = new Image(is);
        ImageView imgView = new ImageView(img);
        pane.getChildren().add(imgView);
    }

    public void createGuide() throws IOException {
        Pane pane1 = new Pane();
        pane1.setPrefWidth(200);
        pane1.setPrefHeight(300);
//        InputStream is = Files.newInputStream(Paths.get());
//        Image img = new Image(is);
//        ImageView imgView = new ImageView(img);
        GameSubscene subscene = new GameSubscene("res/Buttons/Control1.png");
        MenuButton button = new MenuButton("How to play");
        button.setLayoutX(290);
        button.setLayoutY(350);
        subscene.widthProperty().bind(pane1.widthProperty());
        subscene.heightProperty().bind(pane1.heightProperty());
        subscene.setLayoutX(290);
        subscene.setLayoutY(-300);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                subscene.moveGuide();

            }
        });
        pane.getChildren().add(button);
        pane1.getChildren().add(subscene);
        pane.getChildren().add(pane1);
    }

    public void createPause() throws FileNotFoundException {
        MenuButton button = new MenuButton("| |", pause);
        Text textP = new Text("Pause ->");

//        time.setFill(Color.WHITE);
        textP.setFont(Font.loadFont(new FileInputStream(fontPath),15));
        textP.setX(650);
        textP.setY(25);
        button.setLayoutX(768);
        button.setLayoutY(0);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (pause == false) {
                    timeline.pause();
                    button.setText("~");
                    textP.setText("Resume ->");
                    pause = true;
                } else {
                    timeline.play();
                    button.setText("| |");
                    textP.setText("Pause ->");
                    pause = false;

                }
            }
        });
        ingPane.getChildren().add(textP);
        ingPane.getChildren().add(button);
    }

    public void createMute() throws FileNotFoundException {
        MenuButton button = new MenuButton("| |", pause);
        Text textP = new Text("Mute ->");

//        time.setFill(Color.WHITE);
        textP.setFont(Font.loadFont(new FileInputStream(fontPath),15));
        textP.setX(500);
        textP.setY(25);
        button.setLayoutX(650);
        button.setLayoutY(0);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (mute == false) {
                    button.setText("~");
                    textP.setText("Unmute ->");
                    mediaPlayer.pause();
                    mute = true;
                } else {

                    button.setText("| |");
                    textP.setText("Mute ->");
                    mute = false;
                    mediaPlayer.play();
                }
            }
        });
        ingPane.getChildren().add(textP);
        ingPane.getChildren().add(button);
    }

    public void createCheat() throws IOException {
        Pane pane1 = new Pane();
        pane1.setPrefWidth(200);
        pane1.setPrefHeight(300);
//        InputStream is = Files.newInputStream(Paths.get());
//        Image img = new Image(is);
//        ImageView imgView = new ImageView(img);
        GameSubscene subscene = new GameSubscene("res/Buttons/Box.png");
        MenuButton button = new MenuButton("How to play");
        button.setLayoutX(290);
        button.setLayoutY(350);
        subscene.widthProperty().bind(pane1.widthProperty());
        subscene.heightProperty().bind(pane1.heightProperty());
        subscene.setLayoutX(290);
        subscene.setLayoutY(-300);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                subscene.moveGuide();

            }
        });
        pane.getChildren().add(button);
        pane1.getChildren().add(subscene);
        pane.getChildren().add(pane1);
    }

}
