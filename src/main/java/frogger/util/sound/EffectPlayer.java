package frogger.util.sound;

import frogger.constant.FileName;

/**
 *
 *
 * <h1>EffectPlayer</h1>
 *
 * <p>A {@link EffectPlayer} is a object which can play a effect music.
 *
 * <p>This class is implemented as {@link Enum} for singleton.</p>
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    EffectPlayer.INSTANCE.effectMusicFactory(String);
 * </pre>
 *
 * </blockquote>
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see MusicPlayer
 * @see frogger.util.GameManager
 */
public enum EffectPlayer {
  /** Instance for global effect sound management. **/
  INSTANCE;
  /** Music player instance for this effect player singleton **/
  private MusicPlayer musicPlayer;

  /**
   * Create music player for effect music play.
   */
  EffectPlayer() {
    this.musicPlayer = new MusicPlayer();
  }

  /**
   * Play a new effect music file.
   * @param musicFile effect music file to be played
   */
  public void playNewEffectMusic(String musicFile) {
    this.musicPlayer.switchMediaPlayer(this.musicPlayer.createMedia(musicFile), false);
  }

  /**
   * Factory to decide which effect music file to be played.
   *
   * @param type effect music type
   * @see FileName
   */
  public void effectMusicFactory(String type) {
    switch (type) {
      case "EXTRA":
      case "PLUNK":
      case "SQUASH":
      case "TIME":
      case "HOP":
        playNewEffectMusic(FileName.EFFECT_AUDIO.get(type));
        break;
    }
  }
}
