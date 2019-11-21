package frogger.model.selfMovable;

import frogger.model.Movable;

public class SelfMovable extends Movable {

  private double speed;

  public SelfMovable(double s)  {
    this.speed = s;
  }

  public SelfMovable(int s)  {
    this((double) s);
  }

  @Override
  public void move(long now) {
    move(speed , 0);
    if (getX() > 700 && speed>0)
      setX(-getWidth());
    if (getX() < -getWidth() && speed<0)
      setX(700);
  }

}
