package frogger.util;

import frogger.model.*;

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