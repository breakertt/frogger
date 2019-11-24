package frogger.util;

import frogger.model.info.Score;

import java.util.HashSet;
import java.util.Set;

public enum ScoreManager {
  INSTANCE;

  private Set<Score> scoreSet;

  private Score highestScore;

  public void init() {
    this.scoreSet = new HashSet<>();
    highestScore = null;
  }

  public Score getHighestScore() {
    if (highestScore != null) {
      return highestScore;
    }
    else {
      return new Score();
    }
  }

  public void add(Score score) {
    scoreSet.add(score);
    if (this.highestScore == null) {
      this.highestScore = score;
    }
  }

  public void update(Score score) {
    if (highestScore.getValue() < score.getValue()) {
      this.highestScore = score;
    }
  }
}
