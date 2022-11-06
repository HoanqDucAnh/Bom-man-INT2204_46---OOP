package uet.oop.bomberman;

import javafx.animation.TranslateTransition;
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
        Text textP = new Text("Game Over ");

        textP.setFill(Color.RED);

        textP.setFont(Font.loadFont(new FileInputStream(fontPath),15));
        textP.setX(400);
        textP.setY(250);

        Group root2 = (Group) this.getRoot();
        root2.getChildren().add(textP);
    }

    public GameSubscene(boolean pause) throws FileNotFoundException {
        super(new Group(), 800, 480);
        MenuButton button1 = new MenuButton("| |");
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
}
