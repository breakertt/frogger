package frogger.util;

import frogger.constant.GameStatus;
import frogger.controller.GameController;
import javafx.scene.input.KeyEvent;
import frogger.model.Life;
import frogger.model.Map;
import frogger.model.Score;


public enum GameManager {
  INSTANCE;

  private Map map;

  private GameController gameController;

  private GameStatus gameStauts;

  private Life life;

  private Score oneUpScore;

  private Score highestScore;

  public void init(Map map, GameController gameController) {
    this.map = map;

    this.gameController = gameController;


  }

  public void handleKeyPressed(KeyEvent event) {

  }

  public void handleKeyReleased(KeyEvent event) {

  }
}
