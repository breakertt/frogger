package frogger.model.movable;

import frogger.constant.Death;
import frogger.model.World;
import frogger.model.info.End;
import frogger.util.GameManager;
import java.util.ArrayList;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public class Frog extends Movable {

  Image imgW1;
  Image imgA1;
  Image imgS1;
  Image imgD1;
  Image imgW2;
  Image imgA2;
  Image imgS2;
  Image imgD2;
  int points = 0;
  int end = 0;
  private boolean second = false;
  boolean noMove = false;
  double movement = 13.3333333 * 2;
  double movementX = 10.666666 * 2;
  int imgSize = 40;
  boolean carDeath = false;
  boolean waterDeath = false;
  boolean stop = false;
  boolean scoreChanged = false;
  int carD = 0;
  double w = 800;
  ArrayList<End> inter = new ArrayList<End>();

  public Frog(String imageLink) {
    setImage(new Image(imageLink, imgSize, imgSize, true, true));
    setX(300);
    setY(679.8 + movement);
    imgW1 = new Image("/frogger/image/frogger/froggerUp.png", imgSize, imgSize, true, true);
    imgA1 = new Image("/frogger/image/frogger/froggerLeft.png", imgSize, imgSize, true, true);
    imgS1 = new Image("/frogger/image/frogger/froggerDown.png", imgSize, imgSize, true, true);
    imgD1 = new Image("/frogger/image/frogger/froggerRight.png", imgSize, imgSize, true, true);
    imgW2 = new Image("/frogger/image/frogger/froggerUpJump.png", imgSize, imgSize, true, true);
    imgA2 = new Image("/frogger/image/frogger/froggerLeftJump.png", imgSize, imgSize, true, true);
    imgS2 = new Image("/frogger/image/frogger/froggerDownJump.png", imgSize, imgSize, true, true);
    imgD2 = new Image("/frogger/image/frogger/froggerRightJump.png", imgSize, imgSize, true, true);
  }

  public void handleKeyPressed(KeyEvent event) {
    if (noMove) {

    } else {
      if (second) {
        if (event.getCode() == KeyCode.W) {
          move(0, -movement);
          scoreChanged = false;
          setImage(imgW1);
          second = false;
        } else if (event.getCode() == KeyCode.A) {
          move(-movementX, 0);
          setImage(imgA1);
          second = false;
        } else if (event.getCode() == KeyCode.S) {
          move(0, movement);
          setImage(imgS1);
          second = false;
        } else if (event.getCode() == KeyCode.D) {
          move(movementX, 0);
          setImage(imgD1);
          second = false;
        }
      } else if (event.getCode() == KeyCode.W) {
        move(0, -movement);
        setImage(imgW2);
        second = true;
      } else if (event.getCode() == KeyCode.A) {
        move(-movementX, 0);
        setImage(imgA2);
        second = true;
      } else if (event.getCode() == KeyCode.S) {
        move(0, movement);
        setImage(imgS2);
        second = true;
      } else if (event.getCode() == KeyCode.D) {
        move(movementX, 0);
        setImage(imgD2);
        second = true;
      }
    }
  }

  public void handleKeyReleased(KeyEvent event) {
    if (noMove) {
    } else {
      if (event.getCode() == KeyCode.W) {
        if (getY() < w) {
          scoreChanged = true;
          w = getY();
          points += 10;
        }
        move(0, -movement);
        setImage(imgW1);
        second = false;
      } else if (event.getCode() == KeyCode.A) {
        move(-movementX, 0);
        setImage(imgA1);
        second = false;
      } else if (event.getCode() == KeyCode.S) {
        move(0, movement);
        setImage(imgS1);
        second = false;
      } else if (event.getCode() == KeyCode.D) {
        move(movementX, 0);
        setImage(imgD1);
        second = false;
      }
    }
  }

  public <A extends Movable> List<A> getObjects(Class<A> cls) {
    Pane mapPane = (Pane) getScene().lookup("#map");
    ArrayList<A> someArray = new ArrayList<A>();
    for (Node n: mapPane.getChildren()) {
      if (cls.isInstance(n)) {
        someArray.add((A)n);
      }
    }
    return someArray;
  }

  public <A extends Movable> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
    ArrayList<A> someArray = new ArrayList<A>();
    for (A actor : getObjects(cls)) {
      if (actor != this && actor.intersects(this.getBoundsInLocal())) {
        someArray.add(actor);
      }
    }
    return someArray;
  }

  public void deathTransform(long now, Death death) {
    switch (death) {
      case DROP:
        noMove = true;
        if ((now) % 11 == 0) {
          carD++;
        }
        if (carD == 1) {
          setImage(new Image("/frogger/image/water/waterdeath1.png", imgSize, imgSize, true, true));
        }
        if (carD == 2) {
          setImage(new Image("/frogger/image/water/waterdeath2.png", imgSize, imgSize, true, true));
        }
        if (carD == 3) {
          setImage(new Image("/frogger/image/water/waterdeath3.png", imgSize, imgSize, true, true));
        }
        if (carD == 4) {
          setImage(new Image("/frogger/image/water/waterdeath4.png", imgSize, imgSize, true, true));
        }
        if (carD == 5) {
          setX(300);
          setY(679.8 + movement);
          waterDeath = false;
          carD = 0;
          setImage(new Image("/frogger/image/frogger/froggerUp.png", imgSize, imgSize, true, true));
          noMove = false;
          if (points > 50) {
            points -= 50;
            scoreChanged = true;
          }
        }
        break;
      case CRASH:
        noMove = true;
        if ((now) % 11 == 0) {
          carD++;
        }
        if (carD == 1) {
          setImage(new Image("/frogger/image/ground/cardeath1.png", imgSize, imgSize, true, true));
        }
        if (carD == 2) {
          setImage(new Image("/frogger/image/ground/cardeath2.png", imgSize, imgSize, true, true));
        }
        if (carD == 3) {
          setImage(new Image("/frogger/image/ground/cardeath3.png", imgSize, imgSize, true, true));
        }
        if (carD == 4) {
          setX(300);
          setY(679.8 + movement);
          carDeath = false;
          carD = 0;
          setImage(new Image("/frogger/image/frogger/froggerUp.png", imgSize, imgSize, true, true));
          noMove = false;
          if (points > 50) {
            points -= 50;
            scoreChanged = true;
          }
        }
        break;
      case EAT:
        break;
    }
  }

  public void Check(long now) {
    if (getY() < 0 || getY() > 734) {
      setX(300);
      setY(679.8 + movement);
    }
    if (getX() < 0) {
      move(movement * 2, 0);
    }

    // show death if possible
    if (carDeath) {
      deathTransform(now, Death.CRASH);
    }
    if (waterDeath) {
      deathTransform(now, Death.DROP);
    }

    // unknown
    if (getX() > 600) {
      move(-movement * 2, 0);
    }

    // check crash
    if (getIntersectingObjects(Car.class).size() >= 1) {
      carDeath = true;
    }
    if (getIntersectingObjects(Truck.class).size() >= 1) {
      carDeath = true;
    }

    // unknown
    if (getX() == 240 && getY() == 82) {
      stop = true;
    }

    // check on log
    if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
      if (getIntersectingObjects(Log.class).get(0).getLeft()) {
        move(-2, 0);
      } else {
        move(.75, 0);
      }
      // check on log
    } else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
      move(-1, 0);
      // check on wetturtle
    } else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
      if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
        waterDeath = true;
      } else {
        move(-1, 0);
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
      w = 800;
      getIntersectingObjects(End.class).get(0).setEnd();
      end++;
      setX(300);
      setY(679.8 + movement);
    } else if (getY() < 413) {
      waterDeath = true;
    }
  }

  @Override
  public void act(long now) {
    Check(now);

    if (this.isScoreChanged()) {
      GameManager.INSTANCE.setScoreValue(points);
    }


  }

  public boolean getStop() {
    return end == 5;
  }

  public int getPoints() {
    return points;
  }

  public boolean isScoreChanged() {
    if (scoreChanged) {
      scoreChanged = false;
      return true;
    }
    return false;
  }


}
