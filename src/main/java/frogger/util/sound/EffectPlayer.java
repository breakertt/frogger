package frogger.util.sound;

import frogger.constant.FileName;

public enum EffectPlayer {
  INSTANCE;

  private MusicPlayer musicPlayer;


  EffectPlayer() {
    this.musicPlayer = new MusicPlayer();
  }

  public void playNewEffectMusic(String musicFile) {
    this.musicPlayer.switchMediaPlayer(this.musicPlayer.createMedia(musicFile), false);
  }

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
