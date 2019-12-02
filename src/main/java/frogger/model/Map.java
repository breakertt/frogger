package frogger.model;

import frogger.model.selfMovable.End;
import frogger.util.MapLoader;
import frogger.util.MapRender;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

/**
 *
 *
 * <h1>Map</h1>
 *
 * <p>A {@link Map} is a object with key information in a round of game.
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see Lane
 * @see Frog
 * @see End
 * @see MapLoader
 * @see MapRender
 */
public class Map {
  /** Level of current map **/
  private int level;
  /** Filename of map for current level **/
  private String fileName;
  /** Name of player **/
  private String playerName;

  /** Frog instance in current map **/
  private Frog frog;
  /** List of end in current map **/
  private ArrayList<End> ends;
  /** List of lanes in current map **/
  private ArrayList<Lane> laneListElement;

  /**
   * Getter for {@link #getLaneListElement()}
   * @return list of lanes for movables
   */
  public ArrayList<Lane> getLaneListElement() {
    return laneListElement;
  }

  /**
   * Getter for {@link #ends}.
   * @return list of ends in current game
   */
  public ArrayList<End> getEnds() {
    return ends;
  }

  /**
   * Setter for {@link #fileName}.
   * @param fileName filename for config of game in this level.
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Getter for {@link #playerName}.
   * @return name of player for current map
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Setter for {@link #playerName}.
   * @param playerName name of player for current map
   */
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Getter for {@link #level}.
   * @return level of current map
   */
  public int getLevel() {
    return level;
  }

  /**
   * Setter for {@link #level}.
   * @param level level of current map
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Getter for {@link #frog}.
   * @return frog of current map
   */
  public Frog getFrog() {
    return frog;
  }

  /**
   * Setter for {@link #frog}.
   * @param frog level of current map
   */
  public void setFrog(Frog frog) {
    this.frog = frog;
  }

  /**
   * Load lanes, level, frog and ends of this map file in current level.
   *
   * <p>Call {@link MapLoader#loadMap()} to set up elements for this map.</p>
   *
   * @see MapLoader
   */
  public void load() {
    MapLoader mapLoader = new MapLoader(fileName, this);
    mapLoader.loadMap();
    level = mapLoader.getLevel();
    frog = mapLoader.getFrog();
    ends = mapLoader.getEnds();
    laneListElement = mapLoader.getLaneListElement();
  }

  /**
   * Draw elements to scene.
   *
   * <p>use {@link MapRender} to add elements loaded in scene.</p>
   *
   * @param root root of map pane in game view
   * @param laneListPane list of lane panes in game view
   */
  public void draw(Pane root, ArrayList<Pane> laneListPane) {
    load();
    MapRender mapRender = new MapRender(root, laneListPane); // root to be deleted

    try {
      mapRender.drawFrog(frog);
      mapRender.drawLanes(laneListElement);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
