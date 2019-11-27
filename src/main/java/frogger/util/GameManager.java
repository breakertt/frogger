package frogger.util;

import frogger.constant.Death;
import frogger.constant.GameStatus;
import frogger.controller.GameController;
import frogger.model.Lane;
import frogger.model.Movable;
import frogger.model.info.End;
import frogger.model.info.Time;
import frogger.model.selfMovable.SelfMovable;
import frogger.util.score.ScoreManager;
import frogger.util.sound.ThemePlayer;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import frogger.model.info.Life;
import frogger.model.Map;
import frogger.model.info.Score;


public enum GameManager {
  INSTANCE;

  private Map map;

  private GameController gameController;

  private GameStatus gameStatus;

  private Life life;

  private Time time;

  private Score currentScore;

  private Score highestScore;

  public void init(Map map, GameController gameController) {
    this.map = map;
    this.gameController = gameController;
    this.gameStatus = GameStatus.START;
    this.updateAnimationStatus();
    this.initInfo();
    ThemePlayer.INSTANCE.themeMusicFactory("MAIN");
  }

  private void initInfo() {
    this.life = new Life();
    this.time = new Time();
    this.currentScore = new Score(getMap().getPlayerName());
    ScoreManager.INSTANCE.add(this.currentScore);
    this.highestScore = ScoreManager.INSTANCE.getHighestScore();
    gameController.updateLevel(map.getLevel());
    updateInfo();
  }

  public Map getMap() {
    return map;
  }

  public Time getTime() {
    return time;
  }

  private void updateScore() {
    if (this.highestScore.getValue() < this.currentScore.getValue()) {
      this.highestScore = this.currentScore;
    }
  }

  private void updateInfo() {
    this.updateScore();
    gameController.updateScore(this.currentScore, this.highestScore);
    gameController.updateLife(this.life);
  }

  private void updateAnimationStatus() {
    updateMovableAnimationStatus(map.getLaneListElement());
    updateMovableAnimationStatus(map.getFrog());
  }

  private void updateMovableAnimationStatus(ArrayList<Lane> laneArrayList) {
    for (Lane lane : laneArrayList) {
      for (Movable movable : lane.getSelfMovables()) {
        if (gameStatus == GameStatus.START) {
          movable.run();
        } else if (gameStatus == GameStatus.END) {
          movable.stop();
        }
      }
    }
  }

  private void updateMovableAnimationStatus(Movable movable) {
    if (gameStatus == GameStatus.START) {
      movable.run();
    } else if (gameStatus == GameStatus.END) {
      movable.stop();
    }
  }

  private boolean checkWin() {
    boolean isFrogAllExist = true;
    ArrayList<End> ends = map.getEnds();
    for (End end : ends) {
      if (!end.isFrogExist()) {
        isFrogAllExist = false;
        break;
      }
    }
    return isFrogAllExist;
  }

  private void winGame() {
    gameController.activateWinIndicator();
    endGame();
  }

  private void loseGame() {
    gameController.activateLoseIndicator();
    endGame();
  }

  private void endGame() {
    System.out.println("OVER");
    ThemePlayer.INSTANCE.themeMusicFactory("OVER");
    gameStatus = GameStatus.END;
    ScoreManager.INSTANCE.update();
    updateInfo();
    updateAnimationStatus();
  }

  public void handleKeyPressed(KeyEvent event) {
    if (gameStatus == GameStatus.END) {
      return;
    }
    map.getFrog().handleKeyPressed(event);
  }

  public void handleKeyReleased(KeyEvent event) {
    map.getFrog().handleKeyReleased(event);
  }

  public void handleLogTurtleTouched(SelfMovable selfMovable) {
    if (gameStatus == GameStatus.END) {
      return;
    }
    if (map.getFrog().getDeath() == Death.NONE) map.getFrog().movePos(selfMovable.getSpeed(), 0);
  }

  public void handleCarTouched() {
    if (gameStatus == GameStatus.END) {
      return;
    }
    HandleFrogDie(Death.CRASH);
  }

  public void HandleSunkWetTurtleTouched() {
    if (gameStatus == GameStatus.END) {
      return;
    }
    HandleFrogDie(Death.DROP);
  }

  public void handleEndTouched(End end) {
    if (gameStatus == GameStatus.END) {
      return;
    }
    end.setFrog();
    currentScore.gain(200);
    updateInfo();
    if (checkWin()) {
      System.out.println("Win!");
      winGame();
    } else {
      gameController.activateTimeIndicator(time.getSecondsLeft());
      ThemePlayer.INSTANCE.themeMusicFactory("HOMED");
    }
    time.reset();
    map.getFrog().resetyPosSmallest();
    map.getFrog().reset();
  }

  public void HandleFrogDie(Death death) {
    if (gameStatus == GameStatus.END || map.getFrog().getDeath() != Death.NONE) {
      return;
    }
    if (death == Death.DROP || death == Death.CRASH) {
      map.getFrog().setDeath(death);
    } else if (death == Death.TIMEOUT) {
      map.getFrog().resetyPosSmallest();
      map.getFrog().reset();
    }
    life.lose();
    currentScore.lose(50);
    updateScore();
    if (life.getCurrent() < 0) {
      loseGame();
      System.out.println("Lose!");
      return;
    }
    updateInfo();
    ThemePlayer.INSTANCE.themeMusicFactory("REBORN");
  }

  public void handleFrogJumpUp() {
    if (gameStatus == GameStatus.END) {
      return;
    }
    if (map.getFrog().getY() > 300 && map.getFrog().getY() < 350) {return;}
    if (map.getFrog().getY() < map.getFrog().getyPosSmallest()) {
      map.getFrog().setyPosSmallest(map.getFrog().getY());
      currentScore.gain(10);
      updateInfo();
    }
  }

  public void handleFrogInWater() {
    if (gameStatus == GameStatus.END) {
      return;
    }
    boolean frogWaterDie = true;
    for (int i = 0; i < 6; i++) {
      Lane lane = map.getLaneListElement().get(i);
      for (SelfMovable selfMovable : lane.getSelfMovables()) {
        if (selfMovable.checkTouchFrog()) {
          frogWaterDie = false;
          break;
        }
      }
    }
    if (frogWaterDie) HandleFrogDie(Death.DROP);
  }

  public void handleTimeUpdate(int secondsLeft) {
    if (gameStatus == GameStatus.START) {
      if (secondsLeft <= 0) {
        HandleFrogDie(Death.TIMEOUT);
      }
      if (secondsLeft == 57) {
        gameController.deactivateTimeIndicator();
      }
      gameController.updateTime(secondsLeft);
    } else if (gameStatus == GameStatus.END) {
      if (secondsLeft == 53) {
         SceneSwitch.INSTANCE.switchToHome();
      }
    }
  }
}
