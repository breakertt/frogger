package frogger.model.info;

import frogger.util.GameManager;
import javafx.animation.AnimationTimer;

/**
 *
 *
 * <h1>Time</h1>
 *
 * <p>A {@link Time} is a class for track seconds past in every life in game.
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 */
public class Time {
  /** secondsLeft for current life in game. **/
  private int secondsLeft = 60;

  /** base time for calculating {@link #secondsLeft} **/
  private long base = -1;

  /**
   * Create a time and start {@link #Timer} in JavaFx.
   */
  public Time() {
    Timer.start();
  }

  /**
   * Timer instance of {@link AnimationTimer}.
   *
   * <p>Set up {@link #base} at first</p>
   * <p>Update {@link #secondsLeft} every second past then call {@link GameManager#handleTimeUpdate(int)}</p>
   *
   * @see GameManager#handleTimeUpdate(int)
   */
  private AnimationTimer Timer = new AnimationTimer() {
    @Override
    public void handle(long now) {
      long secNow = now / 1000000000;
      // convert nanoseconds to seconds.
      if (base == -1) {
        base = secNow;
      }
      int secondsLeftNow = (int) (60 - (secNow - base));
      // compare to base and get seconds left for game
      if (secondsLeftNow != secondsLeft) {
        secondsLeft = secondsLeftNow;
        GameManager.INSTANCE.handleTimeUpdate(secondsLeft);
      }
    }
  };

  /**
   * Getter for {@link #secondsLeft}
   *
   * @return seconds left for game.
   */
  public int getSecondsLeft() {
    return secondsLeft;
  }

  /**
   * Called to stop the timer when game ends.
   */
  public void stop() {
    Timer.stop();
  }

  /**
   * Reset time for next life of frog but same game
   *
   * <p>Reset {@link #base} and {@link #secondsLeft} and call {@link GameManager#handleTimeUpdate(int)} to update for whole game.</p>
   * 
   * @see GameManager#handleTimeUpdate(int) 
   */
  public void reset() {
    base = -1;
    secondsLeft = 60;
    GameManager.INSTANCE.handleTimeUpdate(secondsLeft);
  };
}
