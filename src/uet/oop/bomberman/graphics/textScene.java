package uet.oop.bomberman.graphics;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class textScene extends SubScene {
    private final String fontPath = "res/font/airstrike.ttf";
    private int x = 2;
    public textScene() throws FileNotFoundException {
        super(new Group(), 800, 480);
        Label label = new Label();
        label.setText("Test " + x);
        label.setFont(Font.loadFont(new FileInputStream(fontPath),23));
        label.setLayoutX(30);
        label.setLayoutY(5);
        Group root2 = (Group) this.getRoot();
        root2.getChildren().add(label);

    }

    public void text(String s) {

    }


}
