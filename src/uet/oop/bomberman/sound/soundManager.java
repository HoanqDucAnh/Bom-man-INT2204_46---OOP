package uet.oop.bomberman.sound;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Path;
import java.nio.file.Paths;

public class soundManager {
    public static MediaPlayer mediaPlayer;
    public static MediaPlayer bombSound;
    public static MediaPlayer endSound;
    public static boolean isEx = false;
    public soundManager() {
        backgroundMusic();

    }

    public soundManager(boolean check) {
        explodeMusic();
        gameOvermusic();
    }
    public void backgroundMusic() {
        String s = "res/music/background.mp3";
        Media media = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
    }

    public void explodeMusic() {
        String s = "res/music/bomb.mp3";
        Media media = new Media(Paths.get(s).toUri().toString());
        bombSound = new MediaPlayer(media);

    }

    public void gameOvermusic() {
        String s = "res/music/end.mp3";
        Media media = new Media(Paths.get(s).toUri().toString());
        endSound = new MediaPlayer(media);

    }
}
