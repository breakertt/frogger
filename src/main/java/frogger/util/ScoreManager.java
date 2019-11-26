package frogger.util;

import frogger.model.info.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;

public enum ScoreManager {
  INSTANCE;

  private ArrayList<Score> scoreList;

  private Score highestScore;

  public void init() {
    this.scoreList = new ArrayList<>();
    highestScore = null;
    load();
  }

  private void load() {
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
    scoreList.add(score);
    if (this.highestScore == null) {
      this.highestScore = score;
    }
  }

  public void sort() {
    Collections.sort(scoreList, new Comparator<Score>() {
      @Override
      public int compare(Score s1, Score s2) {
        if (s1.getValue() > s2.getValue()) {
          return 1;
        }
        else {
          return -1;
        }
      }
    });
    for (Score score : scoreList) {
      System.out.println("Player: " + score.getPlayerName() + " Score: " + score.getValue());
    }
  }

}
