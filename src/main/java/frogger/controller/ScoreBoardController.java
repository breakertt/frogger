package frogger.controller;

import frogger.model.info.Score;
import frogger.util.score.ScoreManager;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;


public class ScoreBoardController {

  @FXML private ListView scoreBoardList;

  private String colors[] = {"#FF9D8C",  "#B88AEB", "#9EEFFF", "#9FE884", "#FFE08F"};
  private int colorIndex = 0;

  public void initUi() {
    ObservableList<Score> options = FXCollections.observableArrayList();
    options.addAll(ScoreManager.INSTANCE.getScoreList());
    scoreBoardList.setItems(options);
    scoreBoardList.setCellFactory(c -> new ScoreBoardListCellFactory());
  }

  private class ScoreBoardListCellFactory extends ListCell<Score> {
    @Override
    protected void updateItem(Score score, boolean empty) {
      super.updateItem(score, empty);
      setGraphic(null);
      setText(null);
      if (score != null && !empty) {
        setText(score.getPlayerName() + ": " + score.getValue());
        setStyle("-fx-text-fill: " + colors[(colorIndex++ % colors.length)]);
      }
    }
  }
}
