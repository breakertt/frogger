package frogger.util;

import frogger.model.Lane;
import frogger.model.Frog;
import frogger.model.selfMovable.SelfMovable;
import java.util.ArrayList;
import java.util.Set;

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
      Set<SelfMovable> laneElementSet = laneListElement.get(i).getSelfMovables();
      lanePane.getChildren().addAll(laneElementSet);
    }
  }

  public void drawFrog(Frog frog) {
    Pane jumpBoard = (Pane) root.lookup("#jumpBoard");
    jumpBoard.getChildren().add(frog);
  }


}
