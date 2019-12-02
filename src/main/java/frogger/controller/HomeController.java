package frogger.controller;

import frogger.util.SceneSwitch;
import frogger.util.score.ScoreManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 *
 * <h1>HomeController</h1>
 *
 * <p>A {@link HomeController} is a controller for home view.
 *
 * @author Tinayi GAO
 * @version 0.2
 * @since 0.2
 */
public class HomeController {
  /** Text for highestScore. **/
  @FXML public Text highestScoreValue;

  /** TextField for player to enter name. **/
  @FXML public TextField nameTextBox;

  /**
   * Initialize home view.
   *
   * <p>Set highest score value in {@link #highestScoreValue}.</p>
   * <p>Set prompt text for {@link #nameTextBox}</p>
   */
  @FXML
  public void initialize() {
    highestScoreValue.setText(String.format("%05d", (ScoreManager.INSTANCE.getHighestScore().getValue() % 99990)));
    nameTextBox.setPromptText("Enter Your Name Here");
    Platform.runLater(() -> {
      nameTextBox.getParent().requestFocus();
      // unset focus on text box for showing prompt text
    });
  }

  /**
   * Switch to game view when start button is clicked.
   */
  @FXML
  protected void handleClickStart() {
    if (nameTextBox.getText().length() <= 0) {
      SceneSwitch.INSTANCE.switchToGame("Unknown", 1);
    } else {
      SceneSwitch.INSTANCE.switchToGame(nameTextBox.getText(), 1);
    }
  }

  /**
   * Pop up help.
   */
  public void handleClickHelp() {
    SceneSwitch.INSTANCE.showHelp();
  }

  /**
   * Exit whole game when exit button is clicked.
   */
  @FXML
  public void handleClickExit() {
    SceneSwitch.INSTANCE.exitGame();
  }
}
