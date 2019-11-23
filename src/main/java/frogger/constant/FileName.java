package frogger.constant;

import java.util.HashMap;
import java.util.Map;

public class FileName {

  public static final Map<Integer, String> IMAGE_LOGS = new HashMap<Integer, String>() {{
    put(0, "/frogger/image/water/log0.png");
    put(1, "/frogger/image/water/log1.png");
    put(2, "/frogger/image/water/log2.png");
  }};

  public static final Map<Integer, String> IMAGE_CARS = new HashMap<Integer, String>() {{
    put(0, "/frogger/image/ground/car1Left.png");
    put(1, "/frogger/image/ground/truck1Right.png");
    put(2, "/frogger/image/ground/truck2Right.png");
  }};

  public static final Map<Integer, String> IMAGE_TURTLES = new HashMap<Integer, String>() {{
    put(0, "/frogger/image/water/TurtleAnimation0.png");
    put(1, "/frogger/image/water/TurtleAnimation1.png");
    put(2, "/frogger/image/water/TurtleAnimation2.png");
  }};

  public static final Map<Integer, String> IMAGE_WET_TURTLES = new HashMap<Integer, String>() {{
    put(0, "/frogger/image/water/WetTurtleAnimation0.png");
    put(1, "/frogger/image/water/WetTurtleAnimation1.png");
    put(2, "/frogger/image/water/WetTurtleAnimation2.png");
    put(3, "/frogger/image/water/WetTurtleAnimation3.png");
  }};
}
