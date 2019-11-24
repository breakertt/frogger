package frogger.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileName {

  public static final Map<Integer, String> IMAGE_LOGS = new HashMap<>() {{
    put(0, "/frogger/image/water/log0.png");
    put(1, "/frogger/image/water/log1.png");
    put(2, "/frogger/image/water/log2.png");
  }};

  public static final Map<Integer, String> IMAGE_CARS = new HashMap<>() {{
    put(0, "/frogger/image/ground/car1Left.png");
    put(1, "/frogger/image/ground/truck1Right.png");
    put(2, "/frogger/image/ground/truck2Right.png");
  }};

  public static final Map<Integer, String> IMAGE_TURTLES = new HashMap<>() {{
    put(0, "/frogger/image/water/TurtleAnimation0.png");
    put(1, "/frogger/image/water/TurtleAnimation1.png");
    put(2, "/frogger/image/water/TurtleAnimation2.png");
  }};

  public static final Map<Integer, String> IMAGE_WET_TURTLES = new HashMap<>() {{
    put(0, "/frogger/image/water/WetTurtleAnimation0.png");
    put(1, "/frogger/image/water/WetTurtleAnimation1.png");
    put(2, "/frogger/image/water/WetTurtleAnimation2.png");
    put(3, "/frogger/image/water/WetTurtleAnimation3.png");
  }};

  public static final Map<String, String> EFFECT_AUDIO = new HashMap<>() {{
    put("EXTRA", "frogger/music/effect_extra.wav");
    put("HOP", "frogger/music/effect_hop.wav");
    put("PLUNK", "frogger/music/effect_plunk.wav");
    put("SQUASH", "frogger/music/effect_squash.wav");
    put("TIME", "frogger/music/effect_time.wav");
  }};

  public static final Map<String, String> THEME_AUDIO = new HashMap<>() {{
    put("START", "frogger/music/theme_start.mp3");
    put("MAIN", "frogger/music/theme_main.mp3");
    put("OVER", "frogger/music/theme_over.mp3");
    put("REBORN", "frogger/music/theme_reborn.mp3");
  }};

  public static final ArrayList<String> HOMED_AUDIO = new ArrayList<>() {{
    add("frogger/music/theme_homed01.mp3");
    add("frogger/music/theme_homed02.mp3");
    add("frogger/music/theme_homed03.mp3");
    add("frogger/music/theme_homed04.mp3");
    add("frogger/music/theme_homed05.mp3");
  }};
}
