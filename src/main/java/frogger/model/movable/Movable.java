package frogger.model.movable;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;


public abstract class Movable extends ImageView {

  private double step;

  public void move(double dx, double dy) {
    setX(getX() + dx);
    setY(getY() + dy);
  }

  public double getWidth() {
    return this.getBoundsInLocal().getWidth();
  }

  public double getHeight() {
    return this.getBoundsInLocal().getHeight();
  }

  private AnimationTimer moveTimer() {
    return new AnimationTimer() {
      @Override
      public void handle(long now) {
        move(now);
      }
    };
  }

  public void run() {
    moveTimer().start();
  }

  private AnimationTimer transfromTimer() {
    return new AnimationTimer() {
      @Override
      public void handle(long now) {
        // todo
      }
    };
  }

  public abstract void move(long now);

}
