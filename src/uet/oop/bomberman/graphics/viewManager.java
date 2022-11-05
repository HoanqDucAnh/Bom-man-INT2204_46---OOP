package uet.oop.bomberman.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import uet.oop.bomberman.GameSubscene;
import uet.oop.bomberman.level.Level1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static uet.oop.bomberman.BombermanGame.buttonList;


public class viewManager {

    public static Pane pane = new Pane();
    public static boolean start = false;

    public viewManager(Group root) throws IOException {
        createBackground();
        createStart();
        createQuit();
        createGuide();
        root.getChildren().add(pane);

    }

    public void createStart() throws FileNotFoundException {

        MenuButton button = new MenuButton("Start");
        button.setLayoutX(290);
        button.setLayoutY(290);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                start = true;
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
        pane1.setPrefWidth(220);
        pane1.setPrefHeight(230);
//        InputStream is = Files.newInputStream(Paths.get());
//        Image img = new Image(is);
//        ImageView imgView = new ImageView(img);
        GameSubscene subscene = new GameSubscene("res/Buttons/Control.png");
        MenuButton button = new MenuButton("How to play");
        button.setLayoutX(290);
        button.setLayoutY(350);
        subscene.widthProperty().bind(pane1.widthProperty());
        subscene.heightProperty().bind(pane1.heightProperty());
        subscene.setLayoutX(290);
        subscene.setLayoutY(-230);
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
