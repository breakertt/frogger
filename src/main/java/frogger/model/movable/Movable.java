package frogger.model.movable;

import frogger.model.World;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;

import java.util.ArrayList;


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
        act(now);
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

  public void manageInput(InputEvent e) {

  }

  public abstract void act(long now);

}
