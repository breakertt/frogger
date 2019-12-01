package frogger.model.info;

/**
 *
 *
 * <h1>Score</h1>
 *
 * <p>A {@link Score} is a class for score recording.
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 */
public class Score {
  /** current value of this score **/
  private int value;

  /** name of player for this score **/
  private String playerName;

  /**
   * Create a new {@link Score} object with assigned {@link #playerName} and {@link #value}.
   *
   * @param playerName assigned name of player.
   * @param value assigned value of this score.
   */
  public Score(String playerName, int value) {
    this.playerName = playerName;
    this.value = value;
  }

  /**
   * Create a new {@link Score} object with assigned {@link #playerName} and value of 0.
   *
   * @param playerName assigned name of player.
   */
  public Score(String playerName) {
    this(playerName, 0);
  }


  /**
   * Create a new {@link Score} object with player name as "Unknown" and value of 0.
   *
   */
  public Score() {
    this("Unknown");
  }

  /**
   * Increase value of {@link #value} by param value.
   *
   * @param value value to be added to {@link #value}.
   * @see #value
   */
  public void gain(int value) {
    this.value += value;
  }

  /**
   * Decrease value of {@link #value} by param value, not less than 0.
   *
   * @param value value to be decreased to {@link #value}.
   * @see #value
   */
  public void lose(int value) {
    this.value = this.value - value;
    if (this.value < 0) {
      this.value = 0;
    }
  }

  /**
   * Getter for {@link #playerName}
   *
   * @return return name of player in this score object.
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Getter for {@link #value}
   *
   * @return return value for this score object.
   */
  public int getValue() {
    return value;
  }

}
