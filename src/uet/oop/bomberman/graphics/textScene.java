package uet.oop.bomberman.graphics;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static uet.oop.bomberman.entities.player.Bomber.heart;

public class textScene {

    public static Text time, live;
    private static final String fontPath = "res/font/Mario.ttf";
    public static int time_number = 120;   // the number of bomb is 20 and the time limit is 120 seconds

    public static void createText(Group root) throws FileNotFoundException {
        time = new Text("Timer: 120" );

//        time.setFill(Color.WHITE);
        time.setFont(Font.loadFont(new FileInputStream(fontPath),15));
        time.setX(30);
        time.setY(25);

        Pane pane = new Pane();
        pane.getChildren().add(time);
        root.getChildren().add(pane);
    }

    public static void createTextLives(Group root) throws FileNotFoundException {
        live = new Text("Lives: " + heart );

//        time.setFill(Color.WHITE);
        live.setFont(Font.loadFont(new FileInputStream(fontPath),15));
        live.setX(200);
        live.setY(25);

        Pane pane = new Pane();
        pane.getChildren().add(live);
        root.getChildren().add(pane);
    }

}
