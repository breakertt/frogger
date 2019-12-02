package frogger.model.selfMovable;

import frogger.constant.FileName;
import frogger.model.Map;
import frogger.util.GameManager;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 *
 * <h1>Turtle</h1>
 *
 * <p>A {@link Turtle} is a {@link SelfMovable} in water area of {@link Map}.
 *
 * <p>This class extends {@link SelfMovable}, when {@link frogger.model.Frog} touches a {@link Turtle}, where will be some checking action and make the frog move with turtle.
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.1
 * @see SelfMovable
 * @see GameManager#handleLogTurtleTouched(SelfMovable) ()
 */
public class Turtle extends SelfMovable {
  /** ArrayList for images of turtle **/
  ArrayList<Image> turtleImages;

  /**
   * Creates a new turtle with speed, position on X axis.
   *
   * @param speed speed of this turtle.
   * @param xPos position on x axis of this turtle.
   */
  public Turtle(double speed, int xPos) {
    turtleImages = new ArrayList<>() {{
      add(new Image(FileName.IMAGE_TURTLES.get(0)));
      add(new Image(FileName.IMAGE_TURTLES.get(1)));
      add(new Image(FileName.IMAGE_TURTLES.get(2)));
    }};
    initSelfMovable(FileName.IMAGE_TURTLES.get(0), xPos, speed);
  }

  /**
   * Transform image of turtle to next one.
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   *
   * @see #turtleImages
   */
  @Override
  public void transformAct(long now) {
    int index = (int) ((now / 900000000) % 3);
    if (this.getImage() != turtleImages.get(index)) {
      this.setImage(turtleImages.get(index));
    }
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
