package frogger.util;

import frogger.model.Lane;
import frogger.model.Map;
import frogger.model.info.End;
import frogger.model.selfMovable.Car;
import frogger.model.Frog;
import frogger.model.selfMovable.Log;
import frogger.model.selfMovable.SelfMovable;
import frogger.model.selfMovable.Turtle;
import frogger.model.selfMovable.WetTurtle;
import java.util.ArrayList;

public class MapLoader {

  private String fileName;

  private Map map;

  private int level;

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  private ArrayList<Lane> laneListElement;

  private ArrayList<End> ends;

  public ArrayList<Lane> getLaneListElement() {
    return laneListElement;
  }

  private Frog frog;

  public Frog getFrog() {
    return frog;
  }

  public ArrayList<End> getEnds() {
    return ends;
  }

  public MapLoader(String fileName, Map map) {
    this.fileName = fileName;
    this.map = map;
    this.ends = new ArrayList<>(5);
    this.laneListElement = new ArrayList<Lane>(12);
    for (int i = 0; i < 13; i++) {
      laneListElement.add(new Lane());
    }
    this.frog = null;
    this.initEnd();
  }

  public void initEnd() {
    for (int i = 0; i < 5; i++) {
      End end = new End(22 + 150*i);
      this.ends.add(end);
      laneAdd(0, end);
    }
  }

   public void laneAdd(int index, SelfMovable selfMovable) {
    this.laneListElement.get(index).add(selfMovable);
  }

  public void loadMap() {
    this.level = 1;
    laneAdd(1, new Log(0.75, 0, 2));
    laneAdd(1, new Log(0.75, 220, 2));
    laneAdd(1, new Log(0.75, 440, 2));
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
}
