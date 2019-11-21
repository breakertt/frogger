package frogger.util;

import frogger.model.*;

import frogger.model.selfMovable.Car;
import frogger.model.Frog;
import frogger.model.selfMovable.Log;
import frogger.model.selfMovable.Truck;
import frogger.model.selfMovable.Turtle;
import frogger.model.selfMovable.WetTurtle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MapLoader {

  private String fileName;

  private Map map;

  private ArrayList<Lane> laneListElement;

  public ArrayList<Lane> getLaneListElement() {
    return laneListElement;
  }

  private Set<Log> logs;

  private Set<Turtle> turtles;

  private Set<WetTurtle> wetTurtles;

  private Set<Car> cars;

  private Set<Truck> trucks;

  private Frog frog;

  public Set<Log> getLogs() {
    return logs;
  }

  public Set<Turtle> getTurtles() {
    return turtles;
  }

  public Set<WetTurtle> getWetTurtles() {
    return wetTurtles;
  }

  public Set<Car> getCars() {
    return cars;
  }

  public Set<Truck> getTrucks() {
    return trucks;
  }

  public Frog getFrog() {
    return frog;
  }

  public MapLoader(String fileName, Map map) {
    this.fileName = fileName;
    this.map = map;
    this.laneListElement = new ArrayList<Lane>(12);
    for (int i = 0; i < 13; i++) {
      laneListElement.add(new Lane());
    }
    this.logs = new HashSet<>();
    this.turtles = new HashSet<>();
    this.wetTurtles = new HashSet<>();
    this.cars = new HashSet<>();
    this.trucks = new HashSet<>();
    this.frog = null;
  }

  public void laneAdd(int index, Movable movable) {
    this.laneListElement.get(index).add(movable);
  }

  public void loadMap() {

    laneAdd(3, new Log(-2, 0, 1));
    laneAdd(3, new Log(-2, 400, 1));

    laneAdd(1, new Log(0.75, 0, 3));
    laneAdd(1, new Log(0.75, 220, 3));
    laneAdd(1, new Log(0.75, 440, 3));

    laneAdd(4, new Log(0.75, 50, 3));
    laneAdd(4, new Log(0.75, 270, 3));
    laneAdd(4, new Log(0.75, 490, 3));

    laneAdd(5, new Turtle(500, 0, -1, 130, 130));
    laneAdd(5, new Turtle(300, 0, -1, 130, 130));
    laneAdd(5, new WetTurtle(700, 0, -1, 130, 130));

    laneAdd(2, new WetTurtle(600, 0, -1, 130, 130));
    laneAdd(2, new WetTurtle(400, 0, -1, 130, 130));
    laneAdd(2, new WetTurtle(200, 0, -1, 130, 130));

    frog = new Frog("/frogger/image/frogger/froggerUp.png");

    laneAdd(11, new Truck("/frogger/image/ground/truck1Right.png", 0, 0, 1, 120, 120));
    laneAdd(11, new Truck("/frogger/image/ground/truck1Right.png", 300, 0, 1, 120, 120));
    laneAdd(11, new Truck("/frogger/image/ground/truck1Right.png", 600, 0, 1, 120, 120));

    laneAdd(10, new Car("/frogger/image/ground/car1Left.png", 100, 0, -1, 50, 50));
    laneAdd(10, new Car("/frogger/image/ground/car1Left.png", 250, 0, -1, 50, 50));
    laneAdd(10, new Car("/frogger/image/ground/car1Left.png", 400, 0, -1, 50, 50));
    laneAdd(10, new Car("/frogger/image/ground/car1Left.png", 550, 0, -1, 50, 50));

    laneAdd(9, new Truck("/frogger/image/ground/truck2Right.png", 0, 0, 1, 200, 200));
    laneAdd(9, new Truck("/frogger/image/ground/truck2Right.png", 500, 0, 1, 200, 200));
    laneAdd(8, new Car("/frogger/image/ground/car1Left.png", 500, 0, -5, 50, 50));
  }
}
