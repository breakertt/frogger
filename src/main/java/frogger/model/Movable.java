package frogger.model;

import frogger.util.GameManager;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;


public abstract class Movable extends ImageView {

  public void movePos(double dx, double dy) {
    setX(getX() + dx);
    setY(getY() + dy);
  }

  public double getWidth() {
    return this.getBoundsInLocal().getWidth();
  }

  public double getHeight() {
    return this.getBoundsInLocal().getHeight();
  }

  private AnimationTimer Timer = new AnimationTimer() {
    @Override
    public void handle(long now) {
      checkAct(now);
      moveAct(now);
      transformAct(now);
    }
  };


  public void run() {
    Timer.start();
  }

  public void stop() {
    Timer.stop();
  }


  public abstract void transformAct(long now);

  public abstract void moveAct(long now);

  public abstract void checkAct(long now);

}
