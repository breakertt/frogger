package frogger.model;

import frogger.model.selfMovable.SelfMovable;
import java.util.HashSet;
import java.util.Set;

public class Lane {
  private Set<SelfMovable> selfMovables;

  public Lane() {
    selfMovables = new HashSet<SelfMovable>();
  }

  public void add(SelfMovable selfMovable) {
    selfMovables.add(selfMovable);
  }

  public Set<SelfMovable> getSelfMovables() {
    return selfMovables;
  }
}
