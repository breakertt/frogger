package frogger.util;

import frogger.constant.GameStatus;
import frogger.controller.GameController;
import frogger.model.movable.Movable;
import java.util.Set;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import frogger.model.info.Life;
import frogger.model.Map;
import frogger.model.info.Score;


public enum GameManager {
  INSTANCE;

  private Map map;

  private GameController gameController;

  private Scene gameScene;

  private GameStatus gameStauts;

  private Life life;

  private Score currentScore;

  private Score highestScore;

  public void init(Map map, GameController gameController, Scene gameScene) {
    this.map = map;
    this.gameController = gameController;
    this.gameScene = gameScene;
    this.gameStauts = GameStatus.START;
    this.life = new Life();
    this.currentScore = new Score();
//    ScoreManager.INSTANCE.add(this.currentScore);
//    this.highestScore = ScoreManager.INSTANCE.getHighestScore();
    this.highestScore = this.currentScore;
    this.run();
  }

  private void updateScore() {
    if (this.highestScore.getValue() < this.currentScore.getValue()) {
      this.highestScore = this.currentScore;
    }
  }

  private void run() {
    runSelfMovable((Set<Movable>) (Set<?>) map.getCars());
    runSelfMovable((Set<Movable>) (Set<?>) map.getTrucks());
    runSelfMovable((Set<Movable>) (Set<?>) map.getLogs());
    runSelfMovable((Set<Movable>) (Set<?>) map.getTurtles());
    runSelfMovable((Set<Movable>) (Set<?>) map.getWetTurtles());
    runSelfMovable(map.getFrog());
  }

  private void runSelfMovable(Movable movable) {
    movable.run();
  }

  private void runSelfMovable(Set<Movable> movables) {
    for (Movable movable : movables) {
      movable.run();
    }
  }

  private void updateUi() {

  }

  public void handleKeyPressed(KeyEvent event) {
    map.getFrog().handleKeyPressed(event);
  }

  public void handleKeyReleased(KeyEvent event) {
    map.getFrog().handleKeyReleased(event);
  }

  public void setScoreValue(int value) {
    currentScore.Increase(value - currentScore.getValue());
    updateScore();
  }
}
