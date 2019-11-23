package frogger.model.info;

import java.util.Date;

public class Score {
  private int value;
  private String playerName;
  private Date time;

  public Score(String playerName) {
    this.playerName = playerName;
    this.value = 0;
    this.time = new Date();
  }

  public Score() {
    this("Unknown");
  }

  public void gain(int value) {
    this.value += value;
  }

  public void lose(int value) {
    this.value += value;
  }


  public int getValue() {
    return value;
  }
}
