package frogger.util;

import frogger.model.Lane;
import frogger.model.Map;
import frogger.model.selfMovable.End;
import frogger.model.selfMovable.Car;
import frogger.model.Frog;
import frogger.model.selfMovable.Log;
import frogger.model.selfMovable.SelfMovable;
import frogger.model.selfMovable.Turtle;
import frogger.model.selfMovable.WetTurtle;
import java.util.ArrayList;

/**
 *
 *
 * <h1>MapRender</h1>
 *
 * <p>A {@link MapLoader} is a object to load elements to map.
 *
 * @author Tianyi GAO
 * @version 0.3
 * @since 0.2
 * @see MapRender
 * @see frogger.model.Map
 */
public class MapLoader {
  /** Map to load **/
  private Map map;

  /** Level of map to add **/
  private int level;

  /** List of lanes with elements **/
  private ArrayList<Lane> laneListElement;
  /** List of ends **/
  private ArrayList<End> ends;
  /** Frog to load **/
  private Frog frog;

  /**
   * Create a new map loader object.
   *
   * @param map map to load
   */
  public MapLoader(Map map) {
    this.level = map.getLevel();
    this.map = map;
    this.ends = new ArrayList<>(5);
    this.laneListElement = new ArrayList<Lane>(12);
    for (int i = 0; i < 13; i++) {
      laneListElement.add(new Lane());
    }
    this.frog = null;
    this.initEnd();
  }

  /** Initialize ends for this map. **/
  public void initEnd() {
    for (int i = 0; i < 5; i++) {
      End end = new End(22 + 150 * i);
      this.ends.add(end);
      laneAdd(0, end);
    }
  }

  /**
   * Add new element to lane with lane index and element.
   *
   * @param index land to add
   * @param selfMovable new element
   */
   public void laneAdd(int index, SelfMovable selfMovable) {
    this.laneListElement.get(index).add(selfMovable);
  }

  /**
   * Load elements.
   */
  public void loadMap() {
    frog = new Frog();

    if (level == 1) {
      laneAdd(1, new Log(1, 0, 2));
      laneAdd(1, new Log(1, 220, 2));
      laneAdd(1, new Log(1, 440, 2));
      laneAdd(2, new WetTurtle(-1, 0));
      laneAdd(2, new Turtle(-1, 200));
      laneAdd(2, new Turtle(-1,400));
      laneAdd(2, new Turtle(-1,600));
      laneAdd(3, new Log(-2, 0, 0));
      laneAdd(4, new Log(0.75, 50, 1));
      laneAdd(4, new Log(0.75, 270, 1));
      laneAdd(4, new Log(0.75, 490, 1));
      laneAdd(5, new Turtle(-1.25, 500));
      laneAdd(5, new Turtle(-1.25, 300));
      laneAdd(5, new WetTurtle(-1.25,700));
      laneAdd(7, new Car(1, 100, 1));
      laneAdd(7, new Car(1,600, 1));
      laneAdd(8, new Car(-1, 0, 0));
      laneAdd(9, new Car(1, 0, 4));
      laneAdd(9, new Car(1, 500, 4));
      laneAdd(10, new Car(-1, 200, 2));
      laneAdd(10, new Car(-1, 300, 2));
      laneAdd(10, new Car(-1, 400, 2));
      laneAdd(11, new Car(1, 0, 3));
      laneAdd(11, new Car(1, 200, 3));
      laneAdd(11, new Car(1, 400, 3));
    } else if (level == 2) {
      laneAdd(1, new Log(0.75, 0, 2));
      laneAdd(1, new Log(0.75, 220, 2));
      laneAdd(1, new Log(0.75, 440, 2));
      laneAdd(2, new WetTurtle(-1.5, 0));
      laneAdd(2, new WetTurtle(-1.5, 200));
      laneAdd(2, new WetTurtle(-1.5,400));
      laneAdd(3, new Log(-2.5, 0, 0));
      laneAdd(3, new Log(-2.5, 400, 0));
      laneAdd(4, new Log(0.75, 50, 1));
      laneAdd(4, new Log(0.75, 270, 1));
      laneAdd(4, new Log(0.75, 490, 1));
      laneAdd(5, new WetTurtle(-2, 200));
      laneAdd(5, new WetTurtle(-2, 350));
      laneAdd(5, new WetTurtle(-2,500));
      laneAdd(7, new Car(1, 100, 1));
      laneAdd(7, new Car(1, 350, 1));
      laneAdd(7, new Car(1,600, 1));
      laneAdd(8, new Car(-5, 0, 0));
      laneAdd(8, new Car(-5, 100, 0));
      laneAdd(9, new Car(1, 0, 4));
      laneAdd(9, new Car(1, 250, 4));
      laneAdd(9, new Car(1, 500, 4));
      laneAdd(10, new Car(-1.25, 200, 2));
      laneAdd(10, new Car(-1.25, 300, 2));
      laneAdd(10, new Car(-1.25, 400, 2));
      laneAdd(11, new Car(1.25, 0, 3));
      laneAdd(11, new Car(1.25, 200, 3));
      laneAdd(11, new Car(1.25, 400, 3));
    } else if (level == 3) {
      laneAdd(1, new Log(0.75, 0, 2));
      laneAdd(1, new Log(0.75, 300, 2));
      laneAdd(2, new Turtle(-1.5, 0));
      laneAdd(2, new Turtle(-1.5, 300));
      laneAdd(2, new WetTurtle(-1.5,600));
      laneAdd(3, new Log(-2.5, 0, 0));
      laneAdd(4, new Log(0.75, 50, 1));
      laneAdd(4, new Log(0.75, 250, 1));
      laneAdd(5, new WetTurtle(-2, 200));
      laneAdd(5, new Turtle(-2, 350));
      laneAdd(7, new Car(1, 100, 1));
      laneAdd(7, new Car(1, 350, 1));
      laneAdd(7, new Car(1,600, 1));
      laneAdd(8, new Car(-5, 0, 0));
      laneAdd(8, new Car(-5, 100, 0));
      laneAdd(8, new Car(-5, 200, 0));
      laneAdd(9, new Car(1, 0, 4));
      laneAdd(9, new Car(1, 225, 4));
      laneAdd(9, new Car(1, 450, 4));
      laneAdd(10, new Car(-1.25, 200, 2));
      laneAdd(10, new Car(-1.25, 300, 2));
      laneAdd(10, new Car(-1.25, 400, 2));
      laneAdd(11, new Car(1.25, 0, 3));
      laneAdd(11, new Car(1.25, 200, 3));
      laneAdd(11, new Car(1.25, 400, 3));
    }

  }

  /**
   * Getter for {@link #level}
   *
   * @return level of map to add
   */
  public int getLevel() {
    return level;
  }

  /**
   * Setter for {@link #level}
   *
   * @param level level of map to add
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Getter for {@link #laneListElement}
   * @return list of lanes with elements
   */
  public ArrayList<Lane> getLaneListElement() {
    return laneListElement;
  }

  /**
   * Getter for {@link #frog}
   * @return list of lanes with elements
   */
  public Frog getFrog() {
    return frog;
  }

  /**
   * Getter for {@link #ends}
   * @return list of ends
   */
  public ArrayList<End> getEnds() {
    return ends;
  }
}
