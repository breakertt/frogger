package frogger.model.selfMovable;

import frogger.constant.FileName;
import frogger.util.GameManager;

public class Car extends SelfMovable {

	public Car(double speed, int xPos, int type) {
		initSelfMovable(FileName.IMAGE_CARS.get(type), xPos, speed);
	}

	@Override
	public void checkAct(long now) {
		if (checkTouchFrog()) GameManager.INSTANCE.handleCarTouched(this);
	}
}
