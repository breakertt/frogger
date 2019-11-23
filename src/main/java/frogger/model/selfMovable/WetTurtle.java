package frogger.model.selfMovable;

import frogger.constant.FileName;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class WetTurtle extends SelfMovable {

  ArrayList<Image> wetTurtleImages;
  private boolean sunk = false;

  public WetTurtle(double speed, int xPos) {
    wetTurtleImages = new ArrayList<>() {{
      add(new Image(FileName.IMAGE_WET_TURTLES.get(0)));
      add(new Image(FileName.IMAGE_WET_TURTLES.get(1)));
      add(new Image(FileName.IMAGE_WET_TURTLES.get(2)));
      add(new Image(FileName.IMAGE_WET_TURTLES.get(3)));
    }};
    initSelfMovable(FileName.IMAGE_TURTLES.get(0), xPos, speed);
  }


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

  private AnimationTimer Timer() {
    return new AnimationTimer() {
      @Override
      public void handle(long now) {
        moveAct(now);
        transformAct(now);
      }
    };
  }

  @Override
  public void run() {
    Timer().start();
  }

  public boolean isSunk() {
    return sunk;
  }

  private void setSunk(boolean sunk) {
    this.sunk = sunk;
  }
}
