package frogger.constant;

public enum Direction {
  UP, DOWN, LEFT, RIGHT, UNKNOWN;

  private Direction getReverse(Direction direction) {
    Direction reverseDirection = UNKNOWN;
    if (direction == UP) {
      reverseDirection = DOWN;
    } else if (direction == DOWN) {
      reverseDirection =  UP;
    } else if (direction == LEFT) {
      reverseDirection =  RIGHT;
    } else if (direction == RIGHT) {
      reverseDirection =  LEFT;
    }
    return reverseDirection;
  }

}
