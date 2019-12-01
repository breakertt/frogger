package frogger.util.sound;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 *
 * <h1>MusicPlayer</h1>
 *
 * <p>A {@link MusicPlayer} is a object which plays a music and can end back to play default one.</p>
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see EffectPlayer
 * @see ThemePlayer
 */
public class MusicPlayer {
  /** Media player for default music **/
  private MediaPlayer defaultMediaPlayer;
  /** Media player for current music **/
  private MediaPlayer currentMediaPlayer;

  /**
   * Create a new music media player with a musicFile path.
   *
   * @param musicFile file path for new music
   * @return MediaPlayer for given music file
   */
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

  /**
   * Switch media player to given one and not end back to default.
   * @param mediaPlayer new media player to switch
   */
  public void switchMediaPlayer(MediaPlayer mediaPlayer) {
    switchMediaPlayer(mediaPlayer, false);
  }

  /**
   * Switch a media player.
   *
   * <p>Stop current media player.</p>
   * <p>Play new media player.</p>
   * <p>Set up end event if back to play default music is required.</p>
   *
   * @param mediaPlayer new music player to be played
   * @param endBackToDefault whether to play default music after this ends
   */
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

  /**
   * Create a new music player with default media player.
   *
   * @param defaultMediaPlayerFile default media player which can be end back to sometimes
   */
  public MusicPlayer(String defaultMediaPlayerFile) {
    MediaPlayer defaultMediaPlayer = createMedia(defaultMediaPlayerFile);
    this.defaultMediaPlayer = defaultMediaPlayer;
    this.currentMediaPlayer = null;
  }

  /**
   * Create a new music player with nothing assigned.
   */
  public MusicPlayer() {
    this.defaultMediaPlayer = null;
    this.currentMediaPlayer = null;
  }
}
