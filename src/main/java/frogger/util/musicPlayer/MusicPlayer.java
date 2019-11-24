package frogger.util.musicPlayer;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {

  private MediaPlayer defaultMediaPlayer;

  private MediaPlayer currentMediaPlayer;

  public MediaPlayer createMedia(String musicFile) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(musicFile);
    String path = null;
    try {
      path = Objects.requireNonNull(resource).toURI().getPath();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    Media media = new Media(new File(Objects.requireNonNull(path)).toURI().toString());
    return new MediaPlayer(media);
  }

  public void switchMediaPlayer(MediaPlayer mediaPlayer) {
    switchMediaPlayer(mediaPlayer, true);
  }

  public void switchMediaPlayer(MediaPlayer mediaPlayer, boolean endBackToDefault) {
    if (currentMediaPlayer != null) {
      currentMediaPlayer.stop();
    }
    currentMediaPlayer = mediaPlayer;
    currentMediaPlayer.setCycleCount(1);
    currentMediaPlayer.play();
    if (endBackToDefault) {
      currentMediaPlayer.setOnEndOfMedia(new Runnable() {
        @Override public void run() {
          currentMediaPlayer = null;
          if (defaultMediaPlayer != null) {
            switchMediaPlayer(defaultMediaPlayer);
          }
        }
      });
    }
  }

  public MusicPlayer(String defaultMediaPlayerFile) {
    MediaPlayer defaultMediaPlayer = createMedia(defaultMediaPlayerFile);
    this.defaultMediaPlayer = defaultMediaPlayer;
    this.currentMediaPlayer = null;
  }

  public MusicPlayer() {
    this.defaultMediaPlayer = null;
    this.currentMediaPlayer = null;
  }

}
