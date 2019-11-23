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
  }


  @Override
  public void transformAct(long now) {
    int index = (int) ((now / 900000000) % 3);
    setImage(turtleImages.get(index));
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

}
