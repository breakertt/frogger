package frogger.controller;

import frogger.util.SceneSwitch;
import frogger.util.score.ScoreManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HomeController {

  @FXML public Text highestScoreValue;

  @FXML public TextField nameTextBox;

  @FXML
  public void initialize() {
    highestScoreValue.setText(String.format("%05d", (ScoreManager.INSTANCE.getHighestScore().getValue() % 99990)));
    nameTextBox.setPromptText("Enter Your Name Here");
    Platform.runLater(() -> {
      nameTextBox.getParent().requestFocus();
    });
  }

  @FXML
  protected void handleClickStart() {
    if (nameTextBox.getText().length() <= 0) {
      SceneSwitch.INSTANCE.switchToGame("Unknown");
    } else {
      SceneSwitch.INSTANCE.switchToGame(nameTextBox.getText());
    }
  }

  @FXML
  public void handleClickExit() {
    SceneSwitch.INSTANCE.exitGame();
  }

}
