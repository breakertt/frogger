package frogger.model;

import frogger.model.selfMovable.SelfMovable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * <h1>Lane</h1>
 *
 * <p>A {@link Lane} is a object contains all objects in one lane of game view.
 *
 * @author Tianyi GAO
 * @version 0.3
 * @since 0.2
 */
public class Lane {
  /** Set of elements in one lane **/
  private Set<SelfMovable> selfMovables;

  /**
   * Create a new lane object.
   **/
  public Lane() {
    selfMovables = new HashSet<SelfMovable>();
  }

  /**
   * Add one self movable to this lane.
   *
   * @param selfMovable self movable to be added to
   */
  public void add(SelfMovable selfMovable) {
    selfMovables.add(selfMovable);
  }

  /**
   * Getter for {@link #selfMovables}
   * @return all elements in this lane with a set
   */
  public Set<SelfMovable> getSelfMovables() {
    return selfMovables;
  }
}
