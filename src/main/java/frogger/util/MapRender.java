package frogger.util;

import frogger.model.Lane;
import frogger.model.Frog;
import frogger.model.selfMovable.SelfMovable;
import java.util.ArrayList;
import java.util.Set;
import javafx.scene.layout.Pane;

/**
 *
 *
 * <h1>MapRender</h1>
 *
 * <p>A {@link MapRender} is a object to render elements to game view.
 *
 * @author Tianyi GAO
 * @version 0.3
 * @since 0.2
 * @see MapLoader
 * @see frogger.model.Map
 */
public class MapRender {
  /** Root pane of game view to render **/
  private Pane root;
  /** List of panes for lanes in game view **/
  private ArrayList<Pane> laneListPane;

  /**
   * Create a new map render to render elements to game view
   *
   * @param root root pane of game view
   * @param laneListPane list of lane panes
   */
  public MapRender(Pane root, ArrayList<Pane> laneListPane) {
    this.root = root;
    this.laneListPane = laneListPane;
  }

  /**
   * Draw elements to lanes in game view from lane with elements.
   *
   * @param laneListElement lanes with elements initialized
   * @throws Exception Exception when size of lane list for elements and pane dont match
   */
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

  /**
   * Draw frog in game view.
   *
   * @param frog frog to add in game view
   */
  public void drawFrog(Frog frog) {
    Pane jumpBoard = (Pane) root.lookup("#jumpBoard");
    jumpBoard.getChildren().add(frog);
  }
}
