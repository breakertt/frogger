package frogger.model.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TimeTest {

  @Test
  public void startTest() {
    Time time = new Time();
    assertEquals(time.getSecondsLeft(), 60);
  }

  @Test
  public void stopTest() {
    Time time = new Time();
    int secondsLeftBeforeTest = time.getSecondsLeft();
    time.stop();
    assertEquals(time.getSecondsLeft(), secondsLeftBeforeTest);
  }

  @Test
  public void runAndResetTest() {
    Time time = new Time();
    time.reset();
    assertEquals(time.getSecondsLeft(), 60);
  }
}
