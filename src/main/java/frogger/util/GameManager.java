package frogger.util;

import frogger.constant.GameStatus;
import frogger.controller.GameController;
import frogger.model.MyStage;
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
    this.gameStauts = GameStatus.PAUSE;
    this.life = new Life();
    this.currentScore = new Score();
    ScoreManager.INSTANCE.add(this.currentScore);
    this.highestScore = ScoreManager.INSTANCE.getHighestScore();

    MyStage bg = (MyStage) gameScene.getRoot();
    bg.start();
  }

  private void updateScore() {
    if (this.highestScore.getValue() < this.currentScore.getValue()) {
      this.highestScore = this.currentScore;
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
}
