package frogger.controller;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *
 *
 * <h1>GameController</h1>
 *
 * <p>A {@link GameController} is a controller for game view.
 *
 * @author Tinayi GAO
 * @version 0.2
 * @since 0.2
 */
public class GameController {
  /** Value for current score. **/
  @FXML private Text currentScoreValue;
  /** Value for highest score. **/
  @FXML private Text highestScoreValue;

  /** ArrayList for life icons. **/
  @FXML private ArrayList<ImageView> lifeList;
  /** ArrayList for level icons. **/
  @FXML private ArrayList<ImageView> levelList;
  /** ArrayList for lanes. **/
  @FXML private ArrayList<Pane> laneListPane;

  /** Bar style indicator for left time. **/
  @FXML private Rectangle timeBar;
  /** Label style indicator for game win. **/
  @FXML private Label winIndicator;
  /** Label style indicator for game lose. **/
  @FXML private Label loseIndicator;
  /** Label style indicator for time used to touch end. **/
  @FXML private Label timeIndicator;

  /**
   * Update score text in game view
   *
   * @param currentScoreValue value of current score
   * @param highestScoreValue value of highest score
   */
  @FXML public void updateScore(int currentScoreValue, int highestScoreValue) {
    String currentScoreValueString = String.format("%05d", (currentScoreValue % 99990));
    this.currentScoreValue.setText(currentScoreValueString);

    String highestScoreValueString = String.format("%05d", (highestScoreValue % 99990));
    this.highestScoreValue.setText(highestScoreValueString);
  }

  /**
   * Update number of life icons in game view
   *
   * @param lifeValue value of frog life in this game
   */
  @FXML public void updateLife(int lifeValue) {
    System.out.println("LifeValue: " + lifeValue);
    if (lifeValue >= 0) {
      for (int i = 0; i < lifeValue; i++) {
        lifeList.get(i).setVisible(true);
      }
      for (int i = lifeValue; i < lifeList.size(); i++) {
        lifeList.get(i).setVisible(false);
      }
    }
  }

  /**
   * Update width of timeBar according to seconds left in game.
   *
   * @param secondsLeft seconds left in game
   */
  @FXML public void updateTime(int secondsLeft) {
    this.timeBar.setWidth((secondsLeft * 325 / 60));
  }

  /**
   * Update number of level icons in game view
   *
   * @param levelValue value of level fot current game
   */
  @FXML public void updateLevel(int levelValue) {
    levelList.get(levelValue - 1).setVisible(true);
  }

  /**
   * Show time used to touch end last time.
   * @param secondsUsed seconds used to touch end
   */
  @FXML public void activateTimeIndicator(int secondsUsed) {
    timeIndicator.setText("TIME " + secondsUsed);
    timeIndicator.setVisible(true);
  }

  /**
   * Set time indicator invisible.
   */
  @FXML public void deactivateTimeIndicator() {
    timeIndicator.setVisible(false);
  }

  /**
   * Set game win indicator visible.
   */
  @FXML public void activateWinIndicator() {
    winIndicator.setVisible(true);
  }

  /**
   * Set game lose indicator visible.
   */
  @FXML public void activateLoseIndicator() {
    loseIndicator.setVisible(true);
  }
}