package frogger.util;

import frogger.model.Car;
import frogger.model.Log;
import frogger.model.Map;
import frogger.model.Truck;
import frogger.model.Turtle;
import java.util.Set;

public class MapLoader {

  private Set<Log> logs;

  private Set<Turtle> turtles;

  private Set<Car> cars;

  private Set<Truck> trucks;

  public MapLoader(String fileName, Map map) {
  }

  public Set<Log> getLogs() {
    return logs;
  }

  public Set<Turtle> getTurtles() {
    return turtles;
  }

  public Set<Car> getCars() {
    return cars;
  }

  public Set<Truck> getTrucks() {
    return trucks;
  }

}
