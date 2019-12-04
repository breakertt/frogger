package frogger.model.selfMovable;

import frogger.constant.FileName;
import frogger.model.Map;
import frogger.util.GameManager;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 *
 * <h1>WetTurtle</h1>
 *
 * <p>A {@link WetTurtle} is a {@link SelfMovable} in water area of {@link Map}.
 *
 * <p>This class extends {@link SelfMovable}.
 * <p>When {@link frogger.model.Frog} touches a {@link WetTurtle}, if this wet turtle is not sunk, act like normal turtle; if not, set frog to death.</p>
 *
 * @author Tianyi GAO
 * @version 0.3
 * @since 0.1
 * @see SelfMovable
 * @see GameManager#handleLogTurtleTouched(SelfMovable)
 * @see GameManager#handleSunkWetTurtleTouched()
 */
public class WetTurtle extends SelfMovable {
  /** ArrayList for images of wet turtle **/
  ArrayList<Image> wetTurtleImages;

  /** If this wet turtle is sunk now **/
  private boolean sunk = false;

  /**
   * Creates a new turtle with speed, position on X axis.
   *
   * @param speed speed of this turtle.
   * @param xPos position on x axis of this turtle.
   */
  public WetTurtle(double speed, int xPos) {
    wetTurtleImages = new ArrayList<>() {{
      add(new Image(FileName.IMAGE_WET_TURTLES.get(0)));
      add(new Image(FileName.IMAGE_WET_TURTLES.get(1)));
      add(new Image(FileName.IMAGE_WET_TURTLES.get(2)));
      add(new Image(FileName.IMAGE_WET_TURTLES.get(3)));
    }};
    initSelfMovable(FileName.IMAGE_TURTLES.get(0), xPos, speed);
  }

  /**
   * Transform image of turtle to next one and set this wet turtle to sunk at some frame.
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   *
   * @see #wetTurtleImages
   * @see #setSunk(boolean)
   */
  @Override
  public void transformAct(long now) {
    int index = (int) ((now / 900000000) % 4);
    setImage(wetTurtleImages.get(index));
    if (index == 3) {
      setSunk(true);
    }
    else {
      setSunk(false);
    }
  }

  /**
   * Checks whether this car is touching a frog and call corresponding method in {@link GameManager}.
   *
   * <p>Call {@link GameManager#handleLogTurtleTouched(SelfMovable)} when not sunk.</p>
   * <p>Call {@link GameManager#handleSunkWetTurtleTouched()} when sunk.</p>
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   *
   * @see SelfMovable#checkTouchFrog()
   * @see GameManager
   */
  @Override
  public void checkAct(long now) {
    if (checkTouchFrog()) {
      if (isSunk()) {
        GameManager.INSTANCE.handleSunkWetTurtleTouched();
      } else {
        GameManager.INSTANCE.handleLogTurtleTouched(this);
      }
    }

  }

  /**
   * Getter for {@link #sunk}.
   * @return whether current wet turtle is sunk or not.
   */
  public boolean isSunk() {
    return sunk;
  }

  /**
   * Setter for {@link #sunk}.
   *
   * @param sunk set {@link #sunk} to true or false
   */
  private void setSunk(boolean sunk) {
    this.sunk = sunk;
  }
}
