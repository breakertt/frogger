package frogger.model.selfMovable;

import frogger.constant.FileName;

public class Log extends SelfMovable {

  private boolean left;

  public Log(double speed, int xPos, int type) {
    initSelfMovable(FileName.IMAGE_LOGS.get(type), 30, xPos, speed);
    left = speed < 0;
  }

  public boolean getLeft() {
    return left;
  }
}
