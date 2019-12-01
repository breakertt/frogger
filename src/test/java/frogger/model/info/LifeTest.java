package frogger.model.info;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LifeTest {

  @Test
  public void defaultLifeValueTest() {
    Life life = new Life();
    assertEquals(3, life.getCurrent());
  }

  @Test
  public void loseLifeTest() {
    Life life = new Life();
    life.lose();
    assertEquals(2, life.getCurrent());
    life.lose();
    assertEquals(1, life.getCurrent());
    life.lose();
    assertEquals(0, life.getCurrent());
    life.lose();
    assertEquals(-1, life.getCurrent());
    life.lose();
    assertEquals(-1, life.getCurrent());
  }

  @Test
  public void gainLifeTest() {
    Life life = new Life();
    life.gain();
    assertEquals(4, life.getCurrent());
    life.gain();
    assertEquals(5, life.getCurrent());
    life.gain();
    assertEquals(5, life.getCurrent());
  }
}
