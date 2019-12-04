package frogger.model.info;

import frogger.controller.GameController;
import frogger.model.Map;
import frogger.util.GameManager;

/**
 *
 *
 * <h1>Life</h1>
 *
 * <p>A {@link Life} is a class recording life for one game.
 *
 * @author Tianyi GAO
 * @version 0.3
 * @since 0.2
 */
public class Life {
  /** Initial value of life. **/
  private int start = 3;

  /** maximum value of life for whole game. **/
  private int max = 5;

  /** current value of this life. **/
  private int current;

  /**
   * Creates a new default {@link Life} for new game.
   * 
   * @see GameManager#init(Map, GameController)
   */
  public Life() {
    this.current = this.start;
  }

  /**
   * Decrease value of current life by 1.
   */
  public void lose() {
    current--;
    if (current < 0) {
      current = -1;
    }
  }

  /**
   * Increase value of current life by 1, but not bigger than maximum.
   */
  public void gain() {
    current++;
    if (current > max) {
      current = max;
    }
  }

  /**
   * Getter for {@link #current}.
   *
   * @return return value of {@link Life#current} in current {@link Life} object.
   */
  public int getCurrent() {
    return current;
  }
}
