package frogger.model.selfMovable;

import frogger.model.Frog;
import frogger.model.Movable;
import frogger.util.GameManager;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;

/**
 *
 *
 * <h1>SelfMovable</h1>
 *
 * <p>A {@link SelfMovable} is a {@link Movable} which can move itself.
 *
 * <p>This class extends {@link SelfMovable}.
 * <p>This class can set up a element in game with action, position and image.</p>
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.1
 */
public abstract class SelfMovable extends Movable {
  /** speed of current self movable **/
  private double speed;

  /** The {@link Frog} instance running in the same game of current self movable. **/
  private Frog frog;

  @Override
  public void transformAct(long now) {}

  @Override
  public void checkAct(long now) {}

  /**
   * Move action for this self movable.
   *
   * <p>When moves out of game view, reset position.</p>
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   */
  @Override
  public void moveAct(long now) {
    movePos(speed, 0);
    if (getX() > 700 && speed > 0) {
      setX(-getWidth());
    }
    if (getX() < -getWidth() && speed < 0) {
      setX(700);
    }
  }

  /**
   * Initialize current {@link SelfMovable}.
   *
   * <p>Set up image and lock preserve ratio and enable smooth.s</p>
   * <p>Resize height if unset(-1) to fit lane height.</p>
   * <p>Set height of this self movable.</p>
   * <p>Set position on X axis.</p>
   * <p>Set position on Y axis according to height of current self movable.</p>
   * <p>Set speed.</p>
   *
   * @param imageLink link for image of this self movable
   * @param height value for height of this self movable
   * @param xPos position on X axis of this self movable
   * @param speed value for speed of this self movable
   */
  public void initSelfMovable(String imageLink, int height, int xPos, double speed) {
    setImage(new Image(imageLink));
    this.setPreserveRatio(true);
    this.setSmooth(true);
    if (height == -1) {
      if (getHeight() > 50) {
        this.setFitHeight(45);
      } else if (getHeight() < 30) {
        this.setFitHeight(35);
      }
    } else {
      this.setFitHeight(height);
    }
    setX(xPos);
    setY(25 - getHeight() / 2);
    setSpeed(speed);
  }

  /**
   * Initialize current {@link SelfMovable} with no height specified.

   * @param imageLink link for image of this self movable
   * @param xPos position on X axis of this self movable
   * @param speed value for speed of this self movable
   */
  public void initSelfMovable(String imageLink, int xPos, double speed) {
    this.initSelfMovable(imageLink, -1, xPos, speed);
  }

  /**
   * Check whether this self movable is touching frog in same game.
   *
   * @return boolean for touching or not.
   */
  public boolean checkTouchFrog() {
    if (this.frog == null) this.frog = GameManager.INSTANCE.getMap().getFrog();
    Bounds frogBoundsInScene = this.frog.localToScene(this.frog.getBoundsInLocal());
    Bounds thisBoundsInScene = this.localToScene(this.getBoundsInLocal());
    return (frogBoundsInScene.intersects(thisBoundsInScene));
  }

  /**
   * Setter for {@link #speed}.
   *
   * @param speed speed of this self movable.
   */
  public void setSpeed(double speed) {
    this.speed = speed;
  }

  /**
   * Getter for {@link #speed}.
   *
   * @return value of {@link #speed}.
   */
  public double getSpeed() {
    return speed;
  }
}
