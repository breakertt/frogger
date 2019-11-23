package frogger.model.selfMovable;

import frogger.model.Frog;
import frogger.model.Movable;
import frogger.util.GameManager;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;

public abstract class SelfMovable extends Movable {

  private double speed;

  private Frog frog;

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public double getSpeed() {
    return speed;
  }

  @Override
  public void transformAct(long now) {}

  @Override
  public void checkAct(long now) {}

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

  public boolean checkTouchFrog() {
    if (this.frog == null) this.frog = GameManager.INSTANCE.getMap().getFrog();
    Bounds frogBoundsInScene = this.frog.localToScene(this.frog.getBoundsInLocal());
    Bounds thisBoundsInScene = this.localToScene(this.getBoundsInLocal());
    return (frogBoundsInScene.intersects(thisBoundsInScene));
  }

}
