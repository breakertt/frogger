package frogger.util;

import frogger.model.Lane;
import frogger.model.Movable;
import frogger.model.selfMovable.Car;
import frogger.model.Frog;
import frogger.model.selfMovable.Log;
import frogger.model.selfMovable.Truck;
import frogger.model.selfMovable.Turtle;
import frogger.model.selfMovable.WetTurtle;
import java.util.ArrayList;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public class MapRender {

  private Pane root;
  private ArrayList<Pane> laneListPane;

  public MapRender(Pane root, ArrayList<Pane> laneListPane) {
    this.root = root;
    this.laneListPane = laneListPane;
  }
  
  public void drawLanes(ArrayList<Lane> laneListElement) throws Exception {
    if (laneListElement.size() != laneListPane.size()) {
      throw new Exception("Number of lane in view and config inconsistent");
    }
    for (int i = 0; i < laneListElement.size(); i++) {
      Pane lanePane = laneListPane.get(i);
      Set<Movable> laneElementSet = laneListElement.get(i).getMovables();
      lanePane.getChildren().addAll(laneElementSet);
    }
  }

  public void drawLogs(Set<Log> logs) {
    root.getChildren().addAll(logs);
  }

  public void drawTurtles(Set<Turtle> turtles) {
    root.getChildren().addAll(turtles);
  }

  public void drawWetTurtles(Set<WetTurtle> wetTurtles) {
    root.getChildren().addAll(wetTurtles);
  }

  public void drawCars(Set<Car> cars) {
    root.getChildren().addAll(cars);
  }

  public void drawTrucks(Set<Truck> trucks) {
    root.getChildren().addAll(trucks);
  }

  public void drawFrog(Frog frog) {
    root.getChildren().add(frog);
  }


}
