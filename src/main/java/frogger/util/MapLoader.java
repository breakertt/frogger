package frogger.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import frogger.model.Lane;
import frogger.model.Map;
import frogger.model.selfMovable.End;
import frogger.model.selfMovable.Car;
import frogger.model.Frog;
import frogger.model.selfMovable.Log;
import frogger.model.selfMovable.SelfMovable;
import frogger.model.selfMovable.Turtle;
import frogger.model.selfMovable.WetTurtle;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Element;

/**
 *
 *
 * <h1>MapRender</h1>
 *
 * <p>A {@link MapLoader} is a object to load elements to map.
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see MapRender
 * @see frogger.model.Map
 */
public class MapLoader {
  /** File name of level config to add **/
  private String fileName;

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
   * @param fileName file name of level config to add
   * @param map map to load
   */
  public MapLoader(String fileName, Map map) {
    this.fileName = fileName;
    this.map = map;
    this.frog = null;
    this.laneListElement = new ArrayList<Lane>(12);
    for (int i = 0; i < 13; i++) {
      laneListElement.add(new Lane());
    }
    this.initEnd();
  }

  /** Initialize ends for this map. **/
  public void initEnd() {
    this.ends = new ArrayList<>(5);
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

  public class ElementLoader {
    private String className;
    private double speed;
    private int xPos;
    private int type = -1;
    private int lane = -1;

    public String getClassName() {
      return className;
    }

    public double getSpeed() {
      return speed;
    }

    public int getType() {
      return type;
    }

    public int getXPos() {
      return xPos;
    }

    public int getLane() {
      return lane;
    }

    public void setSpeed(double speed) {
      this.speed = speed;
    }

    public void setType(int type) {
      this.type = type;
    }

    public void setXPos(int xPos) {
      this.xPos = xPos;
    }

    public void setClassName(String className) {
      this.className = className;
    }

    public void setLane(int lane) {
      this.lane = lane;
    }

    public SelfMovable createNewElement() {
      if (className.equals("Car")) {
        return (new Car(speed, xPos, type));
      } else if (className.equals("Log")) {
        return (new Log(speed, xPos, type));
      } else if (className.equals("WetTurtle")) {
        return (new WetTurtle(speed, xPos));
      } else if (className.equals("Turtle")) {
        return (new Turtle(speed, xPos));
      }
      return null;
    }
  }

  /**
   * Load elements.
   */
  public void loadMap() {

    Gson gson = new Gson();

    System.out.println("["
        + "{\"className\":\"Log\",\"speed\":0.75,\"xPos\":0,\"type\":2,\"lane\":1},"
        + "{\"className\":\"Log\",\"speed\":0.75,\"xPos\":220,\"type\":2,\"lane\":1},"
        + "{\"className\":\"Log\",\"speed\":0.75,\"xPos\":400,\"type\":2,\"lane\":1}"
        + "]");

    Type listType = new TypeToken<List<ElementLoader>>() {}.getType();
    ArrayList<ElementLoader> elementLoaderArrayList = gson.fromJson("["
        + "{\"className\":\"Log\",\"speed\":0.75,\"xPos\":0,\"type\":2,\"lane\":1},"
        + "{\"className\":\"Log\",\"speed\":0.75,\"xPos\":220,\"type\":2,\"lane\":1},"
        + "{\"className\":\"Log\",\"speed\":0.75,\"xPos\":400,\"type\":2,\"lane\":1}"
        + "]", listType);

    elementLoaderArrayList.forEach((ElementLoader elementLoader) -> {
      SelfMovable selfMovable = elementLoader.createNewElement();
      if (selfMovable != null && (elementLoader.getLane() >= 0) && elementLoader.getLane() < laneListElement.size()) {
        laneAdd(elementLoader.getLane(), selfMovable);
      }
    });




    laneAdd(2, new WetTurtle(-1, 0));
    laneAdd(2, new WetTurtle(-1, 200));
    laneAdd(2, new WetTurtle(-1,400));
    laneAdd(3, new Log(-2, 0, 0));
    laneAdd(3, new Log(-2, 400, 0));
    laneAdd(4, new Log(0.75, 50, 1));
    laneAdd(4, new Log(0.75, 270, 1));
    laneAdd(4, new Log(0.75, 490, 1));
    laneAdd(5, new Turtle(-1, 500));
    laneAdd(5, new Turtle(-1, 300));
    laneAdd(5, new WetTurtle(-1,700));
    laneAdd(8, new Car(-5, 600, 0));
    laneAdd(9, new Car(1, 0, 2));
    laneAdd(9, new Car(1, 500, 2));
    laneAdd(10, new Car(-1, 100, 0));
    laneAdd(10, new Car(-1,250, 0));
    laneAdd(10, new Car(-1,400, 0));
    laneAdd(10, new Car(-1,550, 0));
    laneAdd(11, new Car(1, 0, 1));
    laneAdd(11, new Car(1, 300, 1));
    laneAdd(11, new Car(1, 600, 1));

    frog = new Frog();
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
