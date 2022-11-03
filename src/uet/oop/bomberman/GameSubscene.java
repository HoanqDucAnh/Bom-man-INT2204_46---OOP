package uet.oop.bomberman;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameSubscene extends SubScene {


    public GameSubscene() throws IOException {
        super(new Group(), 800, 480);
        InputStream is = Files.newInputStream(Paths.get("res/Buttons/Black.jpg"));
//        InputStream box = Files.newInputStream(Paths.get("res/Buttons/TextBox.png"));
        Image img = new Image(is);
        ImageView imgView = new ImageView(img);
        is.close();

        Group root2 = (Group) this.getRoot();
        root2.getChildren().add(imgView);
    }

    public void moveScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setNode(this);

        transition.setToX(-800);
        transition.play();
    }
}
