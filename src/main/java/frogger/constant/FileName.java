package frogger.constant;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * <h1>FileName</h1>
 *
 * <p>A {@link FileName} is a object with path to static files.
 *
 * @author Tianyi GAO
 * @version 0.3
 * @since 0.2
 */
public class FileName {

  public static final Map<Integer, String> IMAGE_LOGS = new HashMap<>() {{
    put(0, "/frogger/image/water/log0.png");
    put(1, "/frogger/image/water/log1.png");
    put(2, "/frogger/image/water/log2.png");
  }};

  public static final Map<Integer, String> IMAGE_CARS = new HashMap<>() {{
    put(0, "/frogger/image/ground/car1.png");
    put(1, "/frogger/image/ground/car2.png");
    put(2, "/frogger/image/ground/car3.png");
    put(3, "/frogger/image/ground/truck1.png");
    put(4, "/frogger/image/ground/truck2.png");
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

  private static final String USER_PATH =
      System.getProperty("user.home") + File.separator + ".frogger" + File.separator;

  public static final String SCORE_PATH = USER_PATH + "score.csv";

  public static final String VIEW_HOME = "frogger/view/home.fxml";
  public static final String VIEW_GAME = "frogger/view/game.fxml";
  public static final String VIEW_HELP = "frogger/view/help.fxml";
  public static final String VIEW_SCOREBOARD = "frogger/view/scoreboard.fxml";
}
