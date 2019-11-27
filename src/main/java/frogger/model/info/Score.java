package frogger.model.info;

public class Score {
  private int value;
  private String playerName;

  public Score(String playerName, int value) {
    this.playerName = playerName;
    this.value = value;
  }

  public Score(String playerName) {
    this(playerName, 0);
  }

  public Score() {
    this("Unknown");
  }

  public void gain(int value) {
    this.value += value;
  }

  public void lose(int value) {
    this.value = this.value - value;
    if (this.value < 0) {
      this.value = 0;
    }
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getValue() {
    return value;
  }

  public String toString() {
    return this.playerName + "," + this.value;
  }
}
