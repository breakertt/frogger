package frogger.model;

import frogger.util.MapLoader;
import frogger.util.MapRender;
import java.util.Set;
import javafx.scene.layout.Pane;

public class Map {

  private String fileName;

  private String playerName;

  private int level;

  private Set<Log> logs;

  private Set<Turtle> turtles;

  private Set<Car> cars;

  private Set<Truck> trucks;

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
    logs = mapLoader.getLogs();
    turtles = mapLoader.getTurtles();
    cars = mapLoader.getCars();
    trucks = mapLoader.getTrucks();
    //mapConfigLoader.
  }

  public void draw(Pane root) {

    load();

    MapRender mapRender = new MapRender(root);
    mapRender.drawLogs(logs);
    mapRender.drawTurtles(turtles);
    mapRender.drawCars(cars);
    mapRender.drawTrucks(trucks);

  }


}
