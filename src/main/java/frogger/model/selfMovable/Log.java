package frogger.model.selfMovable;

import frogger.constant.FileName;
import frogger.model.Map;
import frogger.util.GameManager;

/**
 *
 *
 * <h1>Log</h1>
 *
 * <p>A {@link Log} is a {@link SelfMovable} in water area of {@link Map}.
 *
 * <p>This class extends {@link SelfMovable}, when {@link frogger.model.Frog} touches a {@link Log}, where will be some checking action and make the frog move with log.
 *
 * @author Tianyi GAO
 * @version 0.3
 * @since 0.1
 * @see SelfMovable
 * @see GameManager#handleLogTurtleTouched(SelfMovable) ()
 */
public class Log extends SelfMovable {

  /**
   * Creates a new log with speed, position on X axis and type.
   *
   * @param speed speed of this log.
   * @param xPos position on x axis of this log.
   * @param type type of this car, deciding with image of car to be used.
   */
  public Log(double speed, int xPos, int type) {
    initSelfMovable(FileName.IMAGE_LOGS.get(type), 30, xPos, speed);
  }

  /**
   * Checks whether this car is touching a frog and call {@link GameManager#handleLogTurtleTouched(SelfMovable)}.
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   *
   * @see SelfMovable#checkTouchFrog()
   * @see GameManager#handleLogTurtleTouched(SelfMovable)
   */
  @Override
  public void checkAct(long now) {
    if (checkTouchFrog()) GameManager.INSTANCE.handleLogTurtleTouched(this);
  }
}
