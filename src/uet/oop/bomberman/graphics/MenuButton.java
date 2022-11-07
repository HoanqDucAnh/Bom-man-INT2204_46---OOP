package uet.oop.bomberman.graphics;


import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.event.EventHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuButton extends Button {

    private final String fontPath = "res/font/airstrike.ttf";
    private final String fontPath2 = "res/font/Marios.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: yellow; -fx-background-image: url('Buttons/red_button_pressed.png')";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: yellow; -fx-background-image: url('Buttons/red_button.png')";

    private final String BUTTON_PRESSED_STYLE_ING = "-fx-background-color: grey; -fx-background-image: url('Buttons/red_button_pressed.png')";
    private final String BUTTON_FREE_STYLE_ING = "-fx-background-color: grey; -fx-background-image: url('Buttons/red_button.png')";

    private final String BUTTON_PRESSED_STYLE_C = "-fx-background-color: transparent; -fx-background-image: url('Buttons/red_button_pressed.png')";
    private final String BUTTON_FREE_STYLE_C = "-fx-background-color: transparent; -fx-background-image: url('Buttons/red_button.png')";
    public boolean status = true;
    public static boolean quitButton = true;

    public MenuButton(String text) throws FileNotFoundException {
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    public MenuButton(String text, boolean check) throws FileNotFoundException {
        setText(text);
        setButtonFontIng();
        setPrefWidth(32);
        setPrefHeight(32);
        setStyle(BUTTON_PRESSED_STYLE_ING);
        initializeButtonListenersIng();
    }

    public MenuButton(String text, boolean check, boolean cheat) throws FileNotFoundException {
        setText(text);
        setButtonFontIng();
        setPrefWidth(32);
        setPrefHeight(32);
        setStyle(BUTTON_PRESSED_STYLE_C);
        initializeButtonListenersCheat();
    }

    private void setButtonFont() throws FileNotFoundException {
        try {
            setFont(Font.loadFont(new FileInputStream(fontPath),23));
        } catch(FileNotFoundException e) {
            setFont(Font.font("VERDANA",23));
        }
    }

    private void setButtonFontIng() throws FileNotFoundException {
        try {
            setFont(Font.loadFont(new FileInputStream(fontPath2),10));
        } catch(FileNotFoundException e) {
            setFont(Font.font("VERDANA",23));
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);

    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() - 4);

    }

    private void setButtonPressedStyleIng() {
        setStyle(BUTTON_PRESSED_STYLE_ING);
        setPrefHeight(32);
        setLayoutY(getLayoutY() + 4);

    }

    private void setButtonPressedStyleCheat() {
        setStyle(BUTTON_PRESSED_STYLE_C);
        setPrefHeight(32);
        setLayoutY(getLayoutY() + 4);

    }

    private void setButtonReleasedStyleIng() {
        setStyle(BUTTON_FREE_STYLE_ING);
        setPrefHeight(32);
        setLayoutY(getLayoutY() - 4);

    }

    private void setButtonReleasedStyleCheat() {
        setStyle(BUTTON_FREE_STYLE_C);
        setPrefHeight(32);
        setLayoutY(getLayoutY() - 4);

    }

    private void initializeButtonListeners() {

        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
//                    status = false;
//                    //isAlive = false;
//                    System.out.print(status);
//                    new Level1();
                }

            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();

                }

            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());

            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(null);

            }
        });


    }

    private void initializeButtonListenersIng() {

        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyleIng();

                }

            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyleIng();

                }

            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());

            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(null);

            }
        });


    }

    private void initializeButtonListenersCheat() {

        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyleCheat();

                }

            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyleCheat();

                }

            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());

            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(null);

            }
        });


    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

