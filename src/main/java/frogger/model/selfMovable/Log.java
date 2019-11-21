package frogger.model.selfMovable;

import frogger.constant.FileName;
import javafx.scene.image.Image;

public class Log extends SelfMovable {

  private boolean left;

  public Log(double s, int xpos, int type) {
    super(s);
    switch (type) {
      case 1:
        initImage(FileName.IMAGE_LOGS.get(0), 150, xpos, 10, s);
      case 2:
        initImage(FileName.IMAGE_LOGS.get(1), 300, xpos, 10, s);
      case 3:
        initImage(FileName.IMAGE_LOGS.get(2), 300, xpos, 10, s);
    }

  }

  public void initImage(String imageLink, int size, int xpos, int ypos, double s) {
    setImage(new Image(imageLink, size, 30, true, true));
    setX(xpos);
    setY(ypos);
    left = s < 0;
  }

  public boolean getLeft() {
    return left;
  }
}
