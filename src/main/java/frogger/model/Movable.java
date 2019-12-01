package frogger.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

/**
 *
 *
 * <h1>Movable</h1>
 *
 * <p>A {@link Movable} is an abstract class with animation timer and movePos feature.
 *
 * <p>This abstract class extends from {@link ImageView}, has its own animation timer with three
 * action related methods to be implemented.</p>
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see AnimationTimer
 * @see ImageView
 * @see frogger.model.selfMovable.SelfMovable
 */
public abstract class Movable extends ImageView {

  /**
   * Move position with given dx and dy for this movable.
   *
   * @param dx delta to be moved on X axis
   * @param dy delta to be moved on Y axis
   */
  public void movePos(double dx, double dy) {
    setX(getX() + dx);
    setY(getY() + dy);
  }

  /**
   * Get width of this movable.
   * @return width of this movable
   */
  public double getWidth() {
    return this.getBoundsInLocal().getWidth();
  }

  /**
   * Get height of this movable.
   * @return height of this movable
   */
  public double getHeight() {
    return this.getBoundsInLocal().getHeight();
  }

  /**
   * AnimationTimer for this movable with three pre-set action methods.
   * 
   * <p>First {@link #checkAct(long)}, then {@link #moveAct(long)}, at last {@link #transformAct(long)}.</p>
   *
   * @see AnimationTimer
   */
  private AnimationTimer Timer = new AnimationTimer() {
    @Override
    public void handle(long now) {
      checkAct(now);
      moveAct(now);
      transformAct(now);
    }
  };

  /**
   * Run timer(animation)
   *
   * Call {@link AnimationTimer#start()}.
   */
  public void run() {
    Timer.start();
  }

  /**
   * Stop timer(animation)
   *
   * Call {@link AnimationTimer#stop()} ()}.
   */
  public void stop() {
    Timer.stop();
  }

  /**
   * Check action to be implemented.
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   */
  public abstract void checkAct(long now);

  /**
   * Movement action to be implemented.
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   */
  public abstract void moveAct(long now);

  /**
   * Transformation action to be implemented.
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   */
  public abstract void transformAct(long now);
}
