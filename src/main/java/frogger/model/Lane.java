package frogger.model;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.image.ImageView;

public class Lane {
  private Set<Movable> movables;

  public Lane() {
    movables = new HashSet<Movable>();
  }

  public void add(Movable movable) {
    movables.add(movable);
  }

  public Set<Movable> getMovables() {
    return movables;
  }
}
