package frogger.controller;

import frogger.model.info.Digit;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class GameController {

//  @FXML private Pane map;

  private Pane map;

  public GameController(Pane map) {
    this.map = map;
  }

  public void setScoreCount(int value) {
    int shift = 0;
    int n = value;
    while (n > 0) {
      int d = n / 10;
      int k = n - d * 10;
      n = d;
      map.getChildren().add(new Digit(k, 30, 360 - shift, 25));
      shift+=30;
    }
  }
}
