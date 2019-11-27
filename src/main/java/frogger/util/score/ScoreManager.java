package frogger.util.score;

import frogger.model.info.Score;

import java.util.ArrayList;
import java.util.Collections;

public enum ScoreManager {
  INSTANCE;

  private ArrayList<Score> scoreList;

  private Score highestScore;

  private Score currentScore;

  public void init() {
    this.currentScore = null;
    this.highestScore = null;
    this.scoreList = new ArrayList<>();

    load();
  }

  private void load() {
    ScoreLoader scoreLoader = new ScoreLoader();
    this.scoreList = scoreLoader.loadList();
    this.highestScore = scoreLoader.loadHighest();
  }

  private void save() {
    ScoreSaver scoreSaver = new ScoreSaver();
    scoreSaver.saveList(scoreList);
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
    if (score != this.currentScore) {
      this.currentScore = score;
      scoreList.add(score);
    }
    if (this.highestScore == null) {
      this.highestScore = score;
    }
  }

  public void update() {
    sort();
    save();
  }

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

  public ArrayList<Score> getScoreList() {
    return scoreList;
  }
}
