package frogger.model;

import frogger.model.selfMovable.Car;
import frogger.model.selfMovable.Log;
import frogger.model.selfMovable.Truck;
import frogger.model.selfMovable.Turtle;
import frogger.model.selfMovable.WetTurtle;
import frogger.util.MapLoader;
import frogger.util.MapRender;
import java.util.ArrayList;
import java.util.Set;
import javafx.scene.layout.Pane;

public class Map {

  private String fileName;

  private String playerName;

  private int level;

  private Frog frog;

  private Set<Log> logs;

  private Set<Turtle> turtles;

  private Set<WetTurtle> wetTurtles;

  private Set<Car> cars;

  private Set<Truck> trucks;

  private ArrayList<Lane> laneListElement;

  public ArrayList<Lane> getLaneListElement() {
    return laneListElement;
  }

  public void setLaneListElement(ArrayList<Lane> laneListElement) {
    this.laneListElement = laneListElement;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public Frog getFrog() {
    return frog;
  }

  public void setFrog(Frog frog) {
    this.frog = frog;
  }

  public Set<Log> getLogs() {
    return logs;
  }

  public void setLogs(Set<Log> logs) {
    this.logs = logs;
  }

  public Set<Turtle> getTurtles() {
    return turtles;
  }

  public void setTurtles(Set<Turtle> turtles) {
    this.turtles = turtles;
  }

  public Set<WetTurtle> getWetTurtles() {
    return wetTurtles;
  }

  public void setWetTurtles(Set<WetTurtle> wetTurtles) {
    this.wetTurtles = wetTurtles;
  }

  public Set<Car> getCars() {
    return cars;
  }

  public void setCars(Set<Car> cars) {
    this.cars = cars;
  }

  public Set<Truck> getTrucks() {
    return trucks;
  }

  public void setTrucks(Set<Truck> trucks) {
    this.trucks = trucks;
  }

  public Map() {
  }

  public void load() {
    MapLoader mapLoader = new MapLoader(fileName, this);
    mapLoader.loadMap();

    logs = mapLoader.getLogs();
    turtles = mapLoader.getTurtles();
    wetTurtles = mapLoader.getWetTurtles();
    cars = mapLoader.getCars();
    trucks = mapLoader.getTrucks();
    frog = mapLoader.getFrog();
    laneListElement = mapLoader.getLaneListElement();
  }

  public void draw(Pane root, ArrayList<Pane> laneListPane) {

    load();

    MapRender mapRender = new MapRender(root, laneListPane); // root to be deleted
    mapRender.drawLogs(logs);
    mapRender.drawTurtles(turtles);
    mapRender.drawWetTurtles(wetTurtles);
    mapRender.drawCars(cars);
    mapRender.drawTrucks(trucks);
    mapRender.drawFrog(frog);
    try {
      mapRender.drawLanes(laneListElement);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


}
