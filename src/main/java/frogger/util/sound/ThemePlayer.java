package frogger.util.sound;

import frogger.constant.FileName;

public enum ThemePlayer {
  INSTANCE;

  private MusicPlayer musicPlayer;

  private int homedIndex = 0;

  ThemePlayer() {
    this.musicPlayer = new MusicPlayer(FileName.THEME_AUDIO.get("MAIN"));
  }

  public void playNewThemeMusic(String musicFile, boolean endBackToMain) {
    this.musicPlayer.switchMediaPlayer(this.musicPlayer.createMedia(musicFile), endBackToMain);
  }

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
        homedIndex = (homedIndex + 1) % 6;
        break;
    }
  }
}
