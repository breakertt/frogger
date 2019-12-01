package frogger.util.sound;

import frogger.constant.FileName;

/**
 *
 *
 * <h1>EffectPlayer</h1>
 *
 * <p>A {@link ThemePlayer} is a object which can play a theme music.
 *
 * <p>This class is implemented as {@link Enum} for singleton.</p>
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    ThemePlayer.INSTANCE.themeMusicFactory(String);
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
public enum ThemePlayer {
  /** Instance for global theme music management. **/
  INSTANCE;

  /** Music player instance for this theme player singleton **/
  private MusicPlayer musicPlayer;

  /** Index for next homed music **/
  private int homedIndex = 0;

  /**
   * Create music player for theme music play.
   */
  ThemePlayer() {
    this.musicPlayer = new MusicPlayer(FileName.THEME_AUDIO.get("MAIN"));
  }

  /**
   * Play a new theme music file.
   *
   * @param musicFile new theme music file to be played
   * @param endBackToMain whether play main theme music after this theme music
   */
  public void playNewThemeMusic(String musicFile, boolean endBackToMain) {
    this.musicPlayer.switchMediaPlayer(this.musicPlayer.createMedia(musicFile), endBackToMain);
  }

  /**
   * Factory to decide which theme music file to be played.
   *
   * @param type theme music type
   * @see FileName
   */
  public void themeMusicFactory(String type) {
    switch (type) {
      case "START":
      case "OVER":
        playNewThemeMusic(FileName.THEME_AUDIO.get(type), false);
        break;
      case "MAIN":
      case "REBORN":
        playNewThemeMusic(FileName.THEME_AUDIO.get(type), true);
        break;
      case "HOMED":
        playNewThemeMusic(FileName.HOMED_AUDIO.get(homedIndex), true);
        homedIndex = (homedIndex + 1) % 5; // increment homeIndex to next one
        break;
    }
  }
}
