package frogger.model;

import frogger.constant.Death;
import frogger.model.info.End;
import frogger.util.GameManager;
import java.util.ArrayList;

import java.util.List;
import java.util.Timer;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class Frog extends Movable {

  private int imgSize = 40;
  private Image jumpImg[][];
  private Image waterDeathImg[];
  private Image carDeathImg[];

  private double jumpX = 40;
  private double jumpY = 50;
  private double yPosSmallest = 700;
  private boolean jumpLock = false;
  private boolean noMove = false;

  private Death death;
  private DeathFrame deathFrame;

  public Frog() {
    ImgFactory();
    reset();
  }

  private class DeathFrame {
    private int offset = 1;

    public int getFrameNum(long now, int loopFrameNum) {
      int sec = (int) ((now / 300000000) % loopFrameNum);
      if (offset == 1) {
        this.offset = sec;
      }
      return (sec + loopFrameNum - this.offset) % loopFrameNum;
    }

  }

  public Death getDeath() {
    return death;
  }

  public void setDeath(Death death) {
    this.death = death;
  }

  private void ImgFactory() {
    jumpImg = new Image[4][2];
    jumpImg[0][0] = new Image("/frogger/image/frogger/froggerUp.png", imgSize, imgSize, true, true);
    jumpImg[1][0] = new Image("/frogger/image/frogger/froggerLeft.png", imgSize, imgSize, true,
        true);
    jumpImg[2][0] = new Image("/frogger/image/frogger/froggerDown.png", imgSize, imgSize, true,
        true);
    jumpImg[3][0] = new Image("/frogger/image/frogger/froggerRight.png", imgSize, imgSize, true,
        true);
    jumpImg[0][1] = new Image("/frogger/image/frogger/froggerUpJump.png", imgSize, imgSize, true,
        true);
    jumpImg[1][1] = new Image("/frogger/image/frogger/froggerLeftJump.png", imgSize, imgSize, true,
        true);
    jumpImg[2][1] = new Image("/frogger/image/frogger/froggerDownJump.png", imgSize, imgSize, true,
        true);
    jumpImg[3][1] = new Image("/frogger/image/frogger/froggerRightJump.png", imgSize, imgSize, true,
        true);
    waterDeathImg = new Image[4];
    waterDeathImg[0] = new Image("/frogger/image/water/waterdeath1.png", imgSize, imgSize, true,
        true);
    waterDeathImg[1] = new Image("/frogger/image/water/waterdeath2.png", imgSize, imgSize, true,
        true);
    waterDeathImg[2] = new Image("/frogger/image/water/waterdeath3.png", imgSize, imgSize, true,
        true);
    waterDeathImg[3] = new Image("/frogger/image/water/waterdeath4.png", imgSize, imgSize, true,
        true);
    carDeathImg = new Image[3];
    carDeathImg[0] = new Image("/frogger/image/ground/cardeath1.png", imgSize, imgSize, true, true);
    carDeathImg[1] = new Image("/frogger/image/ground/cardeath2.png", imgSize, imgSize, true, true);
    carDeathImg[2] = new Image("/frogger/image/ground/cardeath3.png", imgSize, imgSize, true, true);
  }

  public void handleKeyPressed(KeyEvent event) {
    if (noMove || jumpLock) {
      return;
    }
    switch (event.getCode()) {
      case UP:
      case W:
        movePos(0, -jumpY);
        setImage(jumpImg[0][1]);
        break;
      case LEFT:
      case A:
        movePos(-jumpX, 0);
        setImage(jumpImg[1][1]);
        break;
      case DOWN:
      case S:
        movePos(0, jumpY);
        setImage(jumpImg[2][1]);
        break;
      case RIGHT:
      case D:
        movePos(jumpX, 0);
        setImage(jumpImg[3][1]);
        break;
    }
    jumpLock = true;
  }

  public void handleKeyReleased(KeyEvent event) {
    if (noMove) {
      return;
    }
    switch (event.getCode()) {
      case UP:
      case W:
        setImage(jumpImg[0][0]);
        GameManager.INSTANCE.handleFrogJumpUp();
        break;
      case DOWN:
      case S:
        setImage(jumpImg[2][0]);
        break;
      case LEFT:
      case A:
        setImage(jumpImg[1][0]);
        break;
      case RIGHT:
      case D:
        setImage(jumpImg[3][0]);
        break;
    }
    jumpLock = false;
  }

  private void deathTransform(long now, Death death) {
    Image[] deathImg;
    switch (death) {
      case DROP:
        deathImg = waterDeathImg;
        break;
      case CRASH:
        deathImg = carDeathImg;
        break;
      default:
        return;
    }
    noMove = true;
    int loopFrameNum = deathImg.length;
    int frameNumNow = deathFrame.getFrameNum(now, loopFrameNum + 1);
    if (frameNumNow != loopFrameNum) {
      if (getImage() != deathImg[frameNumNow]) {
        setImage(deathImg[frameNumNow]);
      };
    } else {
      this.reset();
      GameManager.INSTANCE.getTime().reset();
    }
  }

  private void resetY() {setY(600 + (50 - getHeight()) / 2);}

  private void resetX() {setX(300);}

  public void reset() {
    resetX();
    resetY();
    this.death = Death.NONE;
    noMove = false;
    jumpLock = false;
    deathFrame = new DeathFrame();
    setImage(jumpImg[0][0]);
  }

  private void check(long now) {
    if (getY() > 600) {
      resetY();
    }

    // out of screen, game over
    if (getX() < 0 || getX() > 700) {
      // todo
    }
  }

  @Override
  public void transformAct(long now) {
    // show death if possible
    if (this.death != Death.NONE) {
      deathTransform(now, this.death);
    }
  }

  @Override
  public void moveAct(long now) {
  }

  @Override
  public void checkAct(long now) {
    check(now);
    checkWater(now);
  }

  @Override
  public void stop() {
    reset();
    super.stop();
  }

  private void checkWater(long now) {
    if (getY() < 300) {
      GameManager.INSTANCE.handleFrogInWater();
    }
  }


  public double getyPosSmallest() {
    return yPosSmallest;
  }

  public void setyPosSmallest(double yPosSmallest) {
    this.yPosSmallest = yPosSmallest;
  }

  public void resetyPosSmallest() {
    this.yPosSmallest = 700;
  }

}
