package frogger.model.selfMovable;

import frogger.model.Movable;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class LogTest extends SelfMovable {

  private boolean left;

  public LogTest(String imageLink, int size, int xpos, int ypos, double s) {
    super(s);
    setImage(new Image(imageLink, size,50, true, true));
    setX(xpos);
    setY(ypos);
    left = s < 0;
  }

  public boolean getLeft() {
    return left;
  }
}
