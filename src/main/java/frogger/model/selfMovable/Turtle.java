package frogger.model.selfMovable;

import frogger.constant.FileName;
import frogger.util.GameManager;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Turtle extends SelfMovable {

  ArrayList<Image> turtleImages;

  public Turtle(double speed, int xPos) {
    turtleImages = new ArrayList<>() {{
      add(new Image(FileName.IMAGE_TURTLES.get(0)));
      add(new Image(FileName.IMAGE_TURTLES.get(1)));
      add(new Image(FileName.IMAGE_TURTLES.get(2)));
    }};
    initSelfMovable(FileName.IMAGE_TURTLES.get(0), xPos, speed);
    System.out.println(getHeight());
  }


  @Override
  public void transformAct(long now) {
    int index = (int) ((now / 900000000) % 3);
    if (getImage() != turtleImages.get(index)) {
      setImage(turtleImages.get(index));
    }
  }

  @Override
  public void checkAct(long now) {
    if (checkTouchFrog()) GameManager.INSTANCE.handleLogTurtleTouched(this);
  }
}
