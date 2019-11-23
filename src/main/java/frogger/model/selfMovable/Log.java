package frogger.model.selfMovable;

import frogger.constant.FileName;
import frogger.util.GameManager;

public class Log extends SelfMovable {

  public Log(double speed, int xPos, int type) {
    initSelfMovable(FileName.IMAGE_LOGS.get(type), 30, xPos, speed);
  }

  @Override
  public void checkAct(long now) {
    if (checkTouchFrog()) GameManager.INSTANCE.handleLogTurtleTouched(this);
  }
}
