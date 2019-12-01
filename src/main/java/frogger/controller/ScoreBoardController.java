package frogger.controller;

import frogger.model.info.Score;
import frogger.util.score.ScoreManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 *
 *
 * <h1>ScoreBoardController</h1>
 *
 * <p>A {@link ScoreBoardController} is a controller for score board.
 *
 * @author Tinayi GAO
 * @version 0.2
 * @since 0.2
 */
public class ScoreBoardController {
  /** List of scores in score board. */
  @FXML private ListView scoreBoardList;

  /** Colors used on scores **/
  String[] colors = {"#FF9D8C", "#B88AEB", "#9EEFFF", "#9FE884", "#FFE08F"};
  /** The index for color on next score to be used. **/
  private int colorIndex = 0;

  /**
   * Initialize scores in score board.
   *
   * <p>Get scores from {@link ScoreManager} and add them to {@link #scoreBoardList}</p>
   */
  public void init() {
    ObservableList<Score> options = FXCollections.observableArrayList();
    options.addAll(ScoreManager.INSTANCE.getScoreList());
    scoreBoardList.setItems(options);
    scoreBoardList.setCellFactory(c -> new ScoreBoardListCellFactory());
  }

  /**
   * Factory for creating new score board list cell with different color.
   */
  private class ScoreBoardListCellFactory extends ListCell<Score> {
    @Override
    protected void updateItem(Score score, boolean empty) {
      super.updateItem(score, empty);
      setGraphic(null);
      setText(null);
      if (score != null && !empty) {
        setText(score.getPlayerName() + ": " + score.getValue());

        // get color and set in style
        String color = colors[colorIndex];
        colorIndex = (colorIndex + 1) % colors.length;
        setStyle("-fx-text-fill: " + color);
      }
    }
  }
}
