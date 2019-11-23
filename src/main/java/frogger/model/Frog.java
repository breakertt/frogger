package frogger.model;

import frogger.constant.Death;
import frogger.model.info.End;
import frogger.util.GameManager;
import java.util.ArrayList;

import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public class Frog extends Movable {

  Image jumpImg[][];
  Image waterDeathImg[];
  Image carDeathImg[];

  int points = 0;
  int end = 0;
  boolean noMove = false;

  private double jumpX = 40;
  private double jumpY = 50;
  private boolean jumpLock = false;

  int imgSize = 40;
  private Death death;
  boolean scoreChanged = false;
  int carD = 0;
  double yPosSmallest = 700;
  DeathFrame deathFrame;
  ArrayList<End> inter = new ArrayList<End>();


  public Frog() {
    ImgFactory();
    deathFrame = new DeathFrame();
    reset();
  }

  public class DeathFrame {
    private int offset = 1;

    public int getFrameNum(long now, int loopFrameNum) {
      int sec = (int) ((now / 900000000) % loopFrameNum);
      if (offset == 1) {
        this.offset = sec;
      }
      return (sec + loopFrameNum - this.offset) % loopFrameNum;
    }

    public void reset() {
      offset = 1;
    }
  }

  public Death getDeath() {
    return death;
  }

  public void setDeath(Death death) {
    this.death = death;
  }

  public void ImgFactory() {
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
        if (getY() < yPosSmallest) {
          scoreChanged = true;
          yPosSmallest = getY();
          points += 10;
        }
        setImage(jumpImg[0][0]);
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

  public <A extends Movable> List<A> getObjects(Class<A> cls) {
    Pane mapPane = (Pane) getScene().lookup("#map");
    ArrayList<A> someArray = new ArrayList<A>();
    for (Node nNode : mapPane.getChildren()) {
      Pane pNode = (Pane) nNode;
      for (Node m : pNode.getChildren()) {
        if (cls.isInstance(m)) {
          someArray.add((A) m);
        }
      }
    }
    return someArray;
  }

  public <A extends Movable> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
    ArrayList<A> someArray = new ArrayList<A>();
    for (A actor : getObjects(cls)) {
      Bounds actorBoundsInScene = actor.localToScene(actor.getBoundsInLocal());
      Bounds thisBoundsInScene = this.localToScene(this.getBoundsInLocal());
      if (actor != this && actorBoundsInScene.intersects(thisBoundsInScene)) {
        someArray.add(actor);
      }
    }
    return someArray;
  }

  public void deathTransform(long now, Death death) {
    int loopFrameNum  = 0;
    int frameNumNow = 0;
    switch (death) {
      case DROP:
        noMove = true;
        loopFrameNum = waterDeathImg.length;
        frameNumNow = deathFrame.getFrameNum(now, loopFrameNum + 1);
        if (frameNumNow != loopFrameNum) {
          setImage(waterDeathImg[frameNumNow]);
        } else {
          deathFrame.reset();
          this.reset();
          scoreChanged = true;
          noMove = false;
        }
        break;
      case CRASH:
        noMove = true;
        loopFrameNum = carDeathImg.length;
        frameNumNow = deathFrame.getFrameNum(now, loopFrameNum + 1);
        if (frameNumNow != loopFrameNum) {
          if (getImage() != carDeathImg[frameNumNow]) {
            setImage(carDeathImg[frameNumNow]);
            System.out.println(frameNumNow + "  " + loopFrameNum);
          };
        } else {
          System.out.println("End!");
          deathFrame.reset();
          this.reset();
          scoreChanged = true;
          noMove = false;
        }
        break;
    }
  }

  private void resetY() {setY(600);}

  private void resetX() {setX(300);}

  private void reset() {
    resetX();
    resetY();
    this.death = Death.NONE;
    setImage(jumpImg[0][0]);
  }

  public void check(long now) {
//    System.out.println(getY());

    // jump lower, jump back
    if (getY() > 600) {
      resetY();
    }

    // out of screen, game over
    if (getX() < 0 || getX() > 700) {
    }

    if (getY() < 300) {

      death = Death.DROP;
    }

    return;
    // check crash
/**
    // check on log
    if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
      if (getIntersectingObjects(Log.class).get(0).getLeft()) {
        movePos(-2, 0);
      } else {
        movePos(.75, 0);
      }
      // check on log
    } else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
      movePos(-1, 0);
      // check on wetTurtle
    } else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
      if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
        death = Death.DROP;
      } else {
        movePos(-1, 0);
      }
      // check on end
    } else if (getIntersectingObjects(End.class).size() >= 1) {
      inter = (ArrayList<End>) getIntersectingObjects(End.class);
      if (getIntersectingObjects(End.class).get(0).isActivated()) {
        end--;
        points -= 50;
      }
      points += 50;
      scoreChanged = true;
      yPosSmallest = 700;
      getIntersectingObjects(End.class).get(0).setEnd();
      end++;
      setX(300);
      setY(629.8 + movement);
    } else if (getY() < 363) {
      death = Death.DROP;
    }
 **/
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
    check(now);

    if (this.isScoreChanged()) {
      GameManager.INSTANCE.setScoreValue(points);
    }


  }

  @Override
  public void checkAct(long now) {

  }

  public boolean getStop() {
    return end == 5;
  }

  public boolean isScoreChanged() {
    if (scoreChanged) {
      scoreChanged = false;
      return true;
    }
    return false;
  }


}
