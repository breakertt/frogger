package frogger.util;

import frogger.model.*;

import frogger.model.movable.Car;
import frogger.model.movable.Frog;
import frogger.model.movable.Log;
import frogger.model.movable.Truck;
import frogger.model.movable.Turtle;
import frogger.model.movable.WetTurtle;
import java.util.HashSet;
import java.util.Set;

public class MapLoader {

  private String fileName;

  private Map map;

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
    this.logs = new HashSet<>();
    this.turtles = new HashSet<>();
    this.wetTurtles = new HashSet<>();
    this.cars = new HashSet<>();
    this.trucks = new HashSet<>();
    this.frog = null;
  }

  public void loadMap() {
    logs.add(new Log("/frogger/image/water/log3.png", 150, 0, 116, 0.75));
    logs.add(new Log("/frogger/image/water/log3.png", 150, 220, 116, 0.75));
    logs.add(new Log("/frogger/image/water/log3.png", 150, 440, 116, 0.75));
    logs.add(new Log("/frogger/image/water/logs.png", 300, 0, 226, -2));
    logs.add(new Log("/frogger/image/water/logs.png", 300, 400, 226, -2));
    logs.add(new Log("/frogger/image/water/log3.png", 150, 50, 279, 0.75));
    logs.add(new Log("/frogger/image/water/log3.png", 150, 270, 279, 0.75));
    logs.add(new Log("/frogger/image/water/log3.png", 150, 490, 279, 0.75));

    turtles.add(new Turtle(500, 326, -1, 130, 130));
    turtles.add(new Turtle(300, 326, -1, 130, 130));

    wetTurtles.add(new WetTurtle(700, 326, -1, 130, 130));
    wetTurtles.add(new WetTurtle(600, 167, -1, 130, 130));
    wetTurtles.add(new WetTurtle(400, 167, -1, 130, 130));
    wetTurtles.add(new WetTurtle(200, 167, -1, 130, 130));

//    background.add(new End(13,96));
//    background.add(new End(141,96));
//    background.add(new End(141 + 141-13,96));
//    background.add(new End(141 + 141-13+141-13+1,96));
//    background.add(new End(141 + 141-13+141-13+141-13+3,96));

    frog = new Frog("/frogger/image/frogger/froggerUp.png");

    trucks.add(new Truck("/frogger/image/ground/truck1Right.png", 0, 599, 1, 120, 120));
    trucks.add(new Truck("/frogger/image/ground/truck1Right.png", 300, 599, 1, 120, 120));
    trucks.add(new Truck("/frogger/image/ground/truck1Right.png", 600, 599, 1, 120, 120));
    cars.add(new Car("/frogger/image/ground/car1Left.png", 100, 547, -1, 50, 50));
    cars.add(new Car("/frogger/image/ground/car1Left.png", 250, 547, -1, 50, 50));
    cars.add(new Car("/frogger/image/ground/car1Left.png", 400, 547, -1, 50, 50));
    cars.add(new Car("/frogger/image/ground/car1Left.png", 550, 547, -1, 50, 50));
    trucks.add(new Truck("/frogger/image/ground/truck2Right.png", 0, 490, 1, 200, 200));
    trucks.add(new Truck("/frogger/image/ground/truck2Right.png", 500, 490, 1, 200, 200));
    trucks.add(new Truck("/frogger/image/ground/car1Left.png", 500, 440, -5, 50, 50));
  }
}
