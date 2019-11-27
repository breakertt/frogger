package frogger.model.info;

import frogger.model.selfMovable.SelfMovable;
import frogger.util.GameManager;
import javafx.animation.AnimationTimer;

public class Time {

  private int secondsLeft = 60;

  private long base = -1;

  public Time() {
    Timer.start();
  }

  private AnimationTimer Timer = new AnimationTimer() {
    @Override
    public void handle(long now) {
      long secNow = now / 1000000000;
      if (base == -1) {
        base = secNow;
      }
      int secondsLeftNow = (int) (60 - (secNow - base));
      if (secondsLeftNow != secondsLeft) {
        secondsLeft = secondsLeftNow;
        GameManager.INSTANCE.handleTimeUpdate(secondsLeft);
      }
    }
  };

  public int getSecondsLeft() {
    return secondsLeft;
  }

  public void stop() {
    Timer.stop();
  }

  public void reset() {
    base = -1;
    secondsLeft = 60;
    GameManager.INSTANCE.handleTimeUpdate(secondsLeft);
  };

}
