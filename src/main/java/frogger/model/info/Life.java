package frogger.model.info;

public class Life {

  private int start = 3;
  private int current;

  public Life() {
    this.current = this.start;
  }

  public void lose() {
    current--;
    if (current < 0) {
      current = -1;
    }
  }

  public void gain() {
    current++;
//    if (current > start) {
//      current = start;
//    }
  }

  public int getStart() {
    return start;
  }

  public int getCurrent() {
    return current;
  }
}
