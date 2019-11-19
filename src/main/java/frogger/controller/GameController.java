package frogger.controller;

import frogger.constant.GameStatus;
import frogger.model.Map;

public enum GameController {
  INSTANCE;

  private Map map;

  private GameController gameController;

  private GameStatus gameStauts;

  private Life life;

  private Score oneUpScore;

  private Score highestScore;
}
