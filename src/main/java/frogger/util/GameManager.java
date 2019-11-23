package frogger.util;

import frogger.constant.Death;
import frogger.constant.GameStatus;
import frogger.controller.GameController;
import frogger.model.Lane;
import frogger.model.Movable;
import frogger.model.selfMovable.Car;
import frogger.model.selfMovable.SelfMovable;
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

  private Score currentScore;

  private Score highestScore;

  public void init(Map map, GameController gameController, Scene gameScene) {
    this.map = map;
    this.gameController = gameController;
    this.gameScene = gameScene;
    this.gameStatus = GameStatus.START;
    this.frame = 0;
    this.life = new Life();
    this.currentScore = new Score();
    ScoreManager.INSTANCE.add(this.currentScore);
    this.highestScore = ScoreManager.INSTANCE.getHighestScore();
    this.run();
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

  public void handleCarTouched(Car car) {
    if (gameStatus == GameStatus.END) {
      return;
    }
    map.getFrog().setDeath(Death.CRASH);
    life.lose();
    currentScore.lose(10);
    updateScore();
    if (life.getCurrent() <= 0) {
      loseGame();
    }
    updateInfo();
  }

  private void loseGame() {
  }
}
