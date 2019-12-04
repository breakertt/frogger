package frogger.model.selfMovable;

import frogger.constant.FileName;
import frogger.model.Map;
import frogger.util.GameManager;

/**
 *
 *
 * <h1>Car</h1>
 *
 * <p>A {@link Car} is a {@link SelfMovable} in ground area of {@link Map}.
 *
 * <p>This class extends {@link SelfMovable}, when {@link frogger.model.Frog} touches a {@link Car}, where will be some checking action and set frog to death possibly.
 *
 * @author Tianyi GAO
 * @version 0.3
 * @since 0.1
 * @see SelfMovable
 * @see GameManager#handleCarTouched()
 */
public class Car extends SelfMovable {

	/**
	 * Creates a new car with speed, position on X axis and type.
	 *
	 * @param speed speed of this car.
	 * @param xPos position on x axis of this car.
	 * @param type type of this car, deciding with image of car to be used.
	 */
	public Car(double speed, int xPos, int type) {
		initSelfMovable(FileName.IMAGE_CARS.get(type), 40, xPos, speed);
	}

	/**
	 * Checks whether this car is touching a frog and call {@link GameManager#handleCarTouched()}.
	 *
	 * @param now The timestamp of the current frame given in nanoseconds.
	 *
	 * @see SelfMovable#checkTouchFrog()
	 * @see GameManager#handleCarTouched()
	 */
	@Override
	public void checkAct(long now) {
		if (checkTouchFrog()) GameManager.INSTANCE.handleCarTouched();
	}
}
