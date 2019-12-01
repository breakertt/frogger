package frogger.model.info;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ScoreTest {

  @Test
  public void playerNameTest() {
    Score score1 = new Score();
    assertEquals(score1.getPlayerName(), "Unknown");

    Score score2 = new Score("Test");
    assertEquals(score2.getPlayerName(), "Test");

    Score score3 = new Score("Test", 100);
    assertEquals(score3.getPlayerName(), "Test");
  }

  @Test
  public void valueTest() {
    Score score1 = new Score();
    assertEquals(score1.getValue(), 0);

    Score score2 = new Score("Test");
    assertEquals(score2.getValue(), 0);

    Score score3 = new Score("Test", 100);
    assertEquals(score3.getValue(), 100);
  }

  @Test
  public void gainValueTest() {
    Score score1 = new Score();

    score1.gain(100);
    assertEquals(score1.getValue(), 100);

    score1.gain(999);
    assertEquals(score1.getValue(), 1099);
  }

  @Test
  public void loseValueTest() {
    Score score1 = new Score("Test", 1000);

    score1.lose(100);
    assertEquals(score1.getValue(), 900);

    score1.lose(900);
    assertEquals(score1.getValue(), 0);

    score1.lose(1);
    assertEquals(score1.getValue(), 0);
  }
}
