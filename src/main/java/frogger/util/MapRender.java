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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MapRender {

  private Pane root;
  private ObservableList<Pane> lanePaneList;

  public MapRender(Pane root) {
    this.root = root;
    this.lanePaneList = (ObservableList<Pane>) (ObservableList<?>) root.getChildren();
  }

  public void drawLanes(ArrayList<Lane> laneArrayList) {
    for (int i = 0; i < laneArrayList.size(); i++) {
      Pane lanePane = lanePaneList.get(i);
      Set<Movable> laneElementList = laneArrayList.get(i).getMovables();
      lanePane.getChildren().addAll(laneElementList);
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
