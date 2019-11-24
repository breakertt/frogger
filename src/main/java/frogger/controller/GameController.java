package frogger.controller;

import frogger.model.info.Life;
import frogger.model.info.Score;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameController {

  @FXML private Text currentScoreValue;

  @FXML private Text highestScoreValue;

  @FXML private ArrayList<ImageView> lifeList;

  @FXML private ArrayList<ImageView> levelList;

  @FXML private ArrayList<Pane> laneListPane;

  @FXML private Rectangle timeBar;

  public void initialize() {
  }

  @FXML public void updateScore(Score currentScore, Score highestScore) {
    String currentScoreValueString = String.format("%05d", (currentScore.getValue() % 99990));
    this.currentScoreValue.setText(currentScoreValueString);

    String highestScoreValueString = String.format("%05d", (highestScore.getValue() % 99990));
    this.highestScoreValue.setText(highestScoreValueString);
  }

  @FXML public void updateLife(Life life) {
    System.out.println("LifeValue: " + life.getCurrent());
    for (int i = 0; i < life.getCurrent(); i++) {
      lifeList.get(i).setVisible(true);
    }
    for (int i = life.getCurrent(); i < lifeList.size(); i++) {
      lifeList.get(i).setVisible(false);
    }
  }

  @FXML public void updateTime(int secondsLeft) {
    this.timeBar.setWidth((secondsLeft * 325 / 60));
  }

  @FXML public void updateLevel(int levelValue) {
    levelList.get(levelValue - 1).setVisible(true);
  }
  public ArrayList<Pane> getLaneListPane() {
    return laneListPane;
  }

}