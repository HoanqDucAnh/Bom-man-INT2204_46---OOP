module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens uet.oop.bomberman to javafx.fxml;
    exports uet.oop.bomberman;
    exports uet.oop.bomberman.gamecollision;
    opens uet.oop.bomberman.gamecollision to javafx.fxml;
    exports uet.oop.bomberman.gamerunner;
    opens uet.oop.bomberman.gamerunner to javafx.fxml;
}