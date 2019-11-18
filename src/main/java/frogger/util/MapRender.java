package frogger.util;

import frogger.model.Car;
import frogger.model.Log;
import frogger.model.Truck;
import frogger.model.Turtle;
import java.util.Set;
import javafx.scene.layout.Pane;

public class MapRender {

  Pane root;

  public MapRender(Pane root) {
    this.root = root;
  }

  public void drawLogs(Set<Log> logs) {
    root.getChildren().addAll(logs);
  }

  public void drawTurtles(Set<Turtle> turtles) {
    root.getChildren().addAll(turtles);
  }

  public void drawCars(Set<Car> cars) {
    root.getChildren().addAll(cars);
  }

  public void drawTrucks(Set<Truck> trucks) {
    root.getChildren().addAll(trucks);
  }
}
