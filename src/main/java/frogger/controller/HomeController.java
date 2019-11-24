package frogger.controller;

import frogger.util.SceneSwitch;
import frogger.util.ScoreManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
      highestScoreValue.requestFocus();
    });
  }

  @FXML
  protected void handleClickStart() {
    SceneSwitch.INSTANCE.switchToGame();
  }

  @FXML
  public void handleClickExit() {
    SceneSwitch.INSTANCE.exitGame();
  }

}
