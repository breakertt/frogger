package frogger.model;

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

  private AnimationTimer Timer() {
    return new AnimationTimer() {
      @Override
      public void handle(long now) {
        moveAct(now);
        transformAct(now);
      }
    };
  }

  public void run() {
    Timer().start();
  }

  public void transformAct(long now) {

  }

  public abstract void moveAct(long now);
}
