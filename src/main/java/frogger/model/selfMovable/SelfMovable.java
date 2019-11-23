package frogger.model.selfMovable;

import frogger.model.Movable;
import javafx.scene.image.Image;

public abstract class SelfMovable extends Movable {

  private double speed;

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  @Override
  public void transformAct(long now) {}

  @Override
  public void moveAct(long now) {
    movePos(speed, 0);
    if (getX() > 700 && speed > 0) {
      setX(-getWidth());
    }
    if (getX() < -getWidth() && speed < 0) {
      setX(700);
    }
  }

  public void initSelfMovable(String imageLink, int height, int xPos, double speed) {
    setImage(new Image(imageLink));
    this.setPreserveRatio(true);
    this.setSmooth(true);
    if (height == -1) {
      if (getHeight() > 50) {
        this.setFitHeight(45);
      } else if (getHeight() < 30) {
        this.setFitHeight(35);
      }
    } else {
      this.setFitHeight(height);
    }
    setX(xPos);
    setY(25 - getHeight() / 2);
    setSpeed(speed);
  }

  public void initSelfMovable(String imageLink, int xPos, double speed) {
    this.initSelfMovable(imageLink, -1, xPos, speed);
  }
}
