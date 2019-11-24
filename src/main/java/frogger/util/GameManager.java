package frogger.util;

import frogger.constant.Death;
import frogger.constant.GameStatus;
import frogger.controller.GameController;
import frogger.model.Frog;
import frogger.model.Lane;
import frogger.model.Movable;
import frogger.model.info.End;
import frogger.model.info.Time;
import frogger.model.selfMovable.Car;
import frogger.model.selfMovable.SelfMovable;
import frogger.model.selfMovable.Turtle;
import frogger.model.selfMovable.WetTurtle;
import java.util.ArrayList;
import java.util.Set;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import frogger.model.info.Life;
import frogger.model.Map;
import frogger.model.info.Score;


public enum GameManager {
  INSTANCE;

  private int frame;

  private Map map;

  private GameController gameController;

  private Scene gameScene;

  private GameStatus gameStatus;

  private Life life;

  private Time time;

  private Score currentScore;

  private Score highestScore;

  public void init(Map map, GameController gameController, Scene gameScene) {
    this.map = map;
    this.gameController = gameController;
    this.gameScene = gameScene;
    this.gameStatus = GameStatus.START;
    this.frame = 0;
    this.life = new Life();
    this.time = new Time();
    this.currentScore = new Score();
    ScoreManager.INSTANCE.add(this.currentScore);
    this.highestScore = ScoreManager.INSTANCE.getHighestScore();
    this.run();
    gameController.updateLevel(map.getLevel());
    MusicPlayer.INSTANCE.playMusic();
  }

  private void updateScore() {
    if (this.highestScore.getValue() < this.currentScore.getValue()) {
      this.highestScore = this.currentScore;
    }
  }

  private void initInfo() {

  }

  private void updateInfo() {
    gameController.updateScore(this.currentScore, this.highestScore);
    gameController.updateLife(this.life);
  }

  private void run() {
    runSelfMovable((ArrayList<Lane>) map.getLaneListElement());
    runSelfMovable(map.getFrog());
  }

  private void runSelfMovable(ArrayList<Lane> laneArrayList) {
    for (Lane lane : laneArrayList) {
      for (SelfMovable selfMovable : lane.getSelfMovables()) {
        selfMovable.run();
      }
    }
  }

  private void runSelfMovable(Movable movable) {
    movable.run();
  }

  public void handleKeyPressed(KeyEvent event) {
    map.getFrog().handleKeyPressed(event);
  }

  public void handleKeyReleased(KeyEvent event) {
    map.getFrog().handleKeyReleased(event);
  }

  public void setScoreValue(int value) {
    currentScore.gain(value - currentScore.getValue());
    updateScore();
  }

  public Map getMap() {
    return map;
  }

  public void handleFrogInWater() {
    boolean frogWaterDie = true;
    for (int i = 1; i < 6; i++) {
      Lane lane = map.getLaneListElement().get(i);
      for (SelfMovable selfMovable : lane.getSelfMovables()) {
        if (selfMovable.checkTouchFrog()) {
          frogWaterDie = false;
          break;
        }
      }
    }
    System.out.println(frogWaterDie);
    if (frogWaterDie) HandleFrogDie(Death.DROP);
  }

  private void loseGame() {
  }

  public void handleEndTouched(End end) {
    end.setFrog();
    currentScore.gain(200);
    updateScore();
    updateInfo();
    time.reset();
    map.getFrog().resetyPosSmallest();
    map.getFrog().reset();
  }

  public void handleLogTurtleTouched(SelfMovable selfMovable) {
    if (map.getFrog().getDeath() == Death.NONE) map.getFrog().movePos(selfMovable.getSpeed(), 0);
  }

  public void handleCarTouched() {
    HandleFrogDie(Death.CRASH);
  }

  public void HandleSunkWetTurtleTouched() {
    HandleFrogDie(Death.DROP);
  }

  public void HandleFrogDie(Death death) {
    if (gameStatus == GameStatus.END || map.getFrog().getDeath() != Death.NONE) {
      return;
    }
    map.getFrog().setDeath(death);
    life.lose();

    currentScore.lose(50);
    updateScore();
    if (life.getCurrent() <= 0) {
      loseGame();
    }
    updateInfo();
  }

  public void handleFrogJumpUp() {
    if (map.getFrog().getY() > 300 && map.getFrog().getY() < 350) {return;}
    if (map.getFrog().getY() < map.getFrog().getyPosSmallest()) {
      map.getFrog().setyPosSmallest(map.getFrog().getY());
      currentScore.gain(10);
      updateScore();
      updateInfo();
    }
  }

  public void handleTimeUpdate(int secondsLeft) {
    gameController.updateTime(secondsLeft);
  }
}
