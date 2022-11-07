package uet.oop.bomberman;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.MenuButton;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static uet.oop.bomberman.entities.player.Bomber.heart;
import static uet.oop.bomberman.gamerunner.BombermanGame.monsterCount;
import static uet.oop.bomberman.graphics.textScene.time_number;
import static uet.oop.bomberman.sound.soundManager.creditMusic;

public class GameSubscene extends SubScene {

    public boolean guide = true;
    private static final String fontPath = "res/font/Mario.ttf";

    public GameSubscene(String path) throws IOException {
        super(new Group(), 800, 480);
        InputStream is = Files.newInputStream(Paths.get(path));
//        InputStream box = Files.newInputStream(Paths.get("res/Buttons/TextBox.png"));
        Image img = new Image(is);
        ImageView imgView = new ImageView(img);
        is.close();

        Group root2 = (Group) this.getRoot();
        root2.getChildren().add(imgView);
    }

    public GameSubscene(int level) throws IOException {
        super(new Group(), 1000, 480);
        Text textP = new Text("Level: " + level);

        textP.setFill(Color.RED);

        textP.setFont(Font.loadFont(new FileInputStream(fontPath),15));
        textP.setX(400);
        textP.setY(250);

        Group root2 = (Group) this.getRoot();
        root2.getChildren().add(textP);
    }

    public GameSubscene(String text, boolean over) throws IOException {
        super(new Group(), 1000, 480);
        Text textP = new Text(text);

        textP.setFill(Color.WHITE);

        textP.setFont(Font.loadFont(new FileInputStream(fontPath),25));
        textP.setX(330);
        textP.setY(250);

        Group root2 = (Group) this.getRoot();
        root2.getChildren().add(textP);
    }

    public GameSubscene() throws IOException {
        super(new Group(), 1000, 1920);
        Text group = new Text("Group 21");
        Text name1 = new Text("Hoang Viet Anh");
        Text name2 = new Text("Hoang Duc Anh");
        Text name3 = new Text("Nguyen Trung Hieu");
        Text thanks = new Text("Thanks for playing!");

        group.setFill(Color.WHITE);
        group.setFont(Font.loadFont(new FileInputStream(fontPath),25));
        group.setX(300);
        group.setY(100);

        name1.setFill(Color.WHITE);
        name1.setFont(Font.loadFont(new FileInputStream(fontPath),25));
        name1.setX(290);
        name1.setY(250);

        name2.setFill(Color.WHITE);
        name2.setFont(Font.loadFont(new FileInputStream(fontPath),25));
        name2.setX(290);
        name2.setY(320);

        name3.setFill(Color.WHITE);
        name3.setFont(Font.loadFont(new FileInputStream(fontPath),25));
        name3.setX(290);
        name3.setY(390);

        thanks.setFill(Color.WHITE);
        thanks.setFont(Font.loadFont(new FileInputStream(fontPath),25));
        thanks.setX(290);
        thanks.setY(900);

        Group root2 = (Group) this.getRoot();
        root2.getChildren().addAll(group, name1, name2, name3, thanks);

    }

    public GameSubscene(boolean pause) throws FileNotFoundException {
        super(new Group(), 800, 480);
        boolean br = false;
        MenuButton button = new MenuButton("", br);
        MenuButton button1 = new MenuButton("", br);
        MenuButton button3 = new MenuButton("", br);
        Text cheat1 = new Text("Kill all");
        Text cheat2 = new Text("More hearts ");
        Text cheat3 = new Text("More time");
        cheat1.setFont(Font.loadFont(new FileInputStream(fontPath),15));
        cheat2.setFont(Font.loadFont(new FileInputStream(fontPath),15));
        cheat3.setFont(Font.loadFont(new FileInputStream(fontPath),15));
        cheat1.setFill(Color.RED);
        cheat2.setFill(Color.RED);
        cheat3.setFill(Color.RED);
        cheat1.setY(25);
        cheat1.setX(40);
        cheat2.setY(80);
        cheat2.setX(40);
        cheat3.setY(145);
        cheat3.setX(40);
        button1.setLayoutX(0);
        button1.setLayoutY(60);
        button3.setLayoutY(116);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                monsterCount.clear();

            }
        });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                heart++;
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                time_number += 15;

            }
        });
        Group root2 = (Group) this.getRoot();
        root2.getChildren().addAll(cheat1, cheat2, cheat3);
        root2.getChildren().addAll(button, button1, button3);


    }

    public void moveScene() throws FileNotFoundException {
        TranslateTransition transition = new TranslateTransition();

        transition.setDuration(Duration.seconds(2));
        transition.setNode(this);
        transition.setToX(-800);

        transition.play();

    }


    public void moveGuide() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setNode(this);

        if (guide) {
            transition.setToY(300);
            guide = false;
        } else {
            transition.setToY(-300);
            guide = true;
        }

        transition.play();
    }

    public void movePause() {
        TranslateTransition transition = new TranslateTransition();

        transition.setDuration(Duration.seconds(3));
        transition.setNode(this);
        transition.setFromY(-480);
        transition.setToY(0);

        transition.play();
    }

    public void moveCredit() {
        TranslateTransition transition = new TranslateTransition();

        transition.setDuration(Duration.seconds(12));
        transition.setNode(this);
        transition.setFromY(960);
        transition.setToY(-480);

        transition.play();
        creditMusic.play();
    }

    public void moveCreditBackground() {
        TranslateTransition transition = new TranslateTransition();

        transition.setDuration(Duration.seconds(7));
        transition.setNode(this);
        transition.setFromY(960);
        transition.setToY(120);

        transition.play();

    }
}
