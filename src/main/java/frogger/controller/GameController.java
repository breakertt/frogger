package frogger.controller;

import frogger.model.info.Score;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GameController {

  @FXML private Text currentScoreValue;

  @FXML private Text highestScoreValue;

  @FXML private HBox lifeBox;

  @FXML private HBox levelBox;

  @FXML public void updateScore(Score currentScore, Score highestScore) {
    String currentScoreValueString = String.format("%05d", (currentScore.getValue() % 99990));
    this.currentScoreValue.setText(currentScoreValueString);

    String highestScoreValueString = String.format("%05d", (highestScore.getValue() % 99990));
    this.highestScoreValue.setText(highestScoreValueString);
  }
}