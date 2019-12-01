package frogger.util.score;

import frogger.model.info.Score;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 *
 * <h1>ScoreManager</h1>
 *
 * <p>A {@link ScoreManager} is a object of utility to manage game scores.
 *
 * <p>This class is implemented as {@link Enum} for singleton.</p>
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    ThemePlayer.INSTANCE.{METHOD};
 * </pre>
 *
 * </blockquote>
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see Score
 * @see ScoreSaver
 * @see ScoreLoader
 * @see frogger.util.GameManager
 */
public enum ScoreManager {
  /** Instance for global score management. **/
  INSTANCE;

  /** List of scores for current game application. **/
  private ArrayList<Score> scoreList;
   /** highestScore in current game application. **/
  private Score highestScore;
  /** currentScore in current game application for current gamer. **/
  private Score currentScore;

  /**
   * Initialize score manger.
   *
   * <p>Load score and score list by calling {@link #load()}</p>
   */
  public void init() {
    this.currentScore = null;
    this.highestScore = null;
    this.scoreList = new ArrayList<>();

    load();
  }

  /**
   * Load score list, currentScore and highestScore.
   *
   * @see ScoreLoader
   */
  private void load() {
    ScoreLoader scoreLoader = new ScoreLoader();
    this.scoreList = scoreLoader.loadList();
    this.highestScore = scoreLoader.loadHighest();
  }

  /**
   * Save current score list.
   *
   * @see ScoreSaver
   */
  private void save() {
    ScoreSaver scoreSaver = new ScoreSaver();
    scoreSaver.saveList(scoreList);
  }

  /**
   * Add new score to score list maintained by this score manager.
   *
   * @param score score to add
   */
  public void add(Score score) {
    if (score != this.currentScore) {
      this.currentScore = score;
      scoreList.add(score);
    }
    if (this.highestScore == null) {
      this.highestScore = score;
    }
  }

  /**
   * Sort current score list and save it to file.
   *
   * @see #sort()
   * @see #save()
   */
  public void update() {
    sort();
    save();
  }

  /**
   * Sort current score list by its value.
   */
  private void sort() {
    Collections.sort(scoreList, (s1, s2) -> {
      if (s1.getValue() > s2.getValue()) {
        return -1;
      } else {
        return 1;
      }
    });
    for (Score score : scoreList) {
      System.out.println("Player: " + score.getPlayerName() + " Score: " + score.getValue());
    }
    if (scoreList.size() >= 0) {
      this.highestScore = scoreList.get(0);
    }
  }


  /**
   * Getter for {@link #highestScore}.
   *
   * @return highestScore in current game application
   */
  public Score getHighestScore() {
    if (highestScore != null) {
      return highestScore;
    }
    else {
      // return a score with value 0 if score requested is null
      return new Score();
    }
  }

  /**
   * Getter for {@link #scoreList}.
   *
   * @return score list maintained by this score manager
   */
  public ArrayList<Score> getScoreList() {
    return scoreList;
  }
}
