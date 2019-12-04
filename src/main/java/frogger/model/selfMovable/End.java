package frogger.model.selfMovable;

import frogger.util.GameManager;
import javafx.scene.image.Image;

/**
 *
 *
 * <h1>End</h1>
 *
 * <p>A {@link End} is a {@link SelfMovable} in every {@link frogger.model.Map}, it can not move actually.
 *
 * <p>This class extends {@link SelfMovable}, when {@link frogger.model.Frog} touches a {@link End}, where will be some checking action.
 *
 * @author Tianyi GAO
 * @version 0.3
 * @since 0.1
 * @see SelfMovable
 * @see GameManager#handleEndTouched(End)
 */
public class End extends SelfMovable {

	/**
	 * Whether current end have frogger touched ever.
	 */
	boolean frogExist = false;

	/**
	 * This method checks whether a {@link frogger.model.Frog} is at this end.
	 *
 	 * @param now - The timestamp of the current frame given in nanoseconds.
	 */
	@Override
	public void checkAct(long now) {
		if (checkTouchFrog()) GameManager.INSTANCE.handleEndTouched(this);
	}

	/**
	 * Creates a new end with position on X axis.
	 *
	 * @param xPos - position on X axis of this end.
	 */
	public End(double xPos) {
		setImage(new Image("/frogger/image/water/emptyEnd.png"));
		setX(xPos);
	}

	/**
	 * Set current end to frog exist status.
	 *
	 * @see #frogExist
	 */
	public void setFrog() {
		setImage(new Image("/frogger/image/water/froggerEnd.png"));
		frogExist = true;
	}

	/**
	 * Get whether current end exists a frog.
	 *
	 * @return boolean for is a frog exists.
	 * @see #frogExist
	 */
	public boolean isFrogExist() {
		return frogExist;
	}
	

}
