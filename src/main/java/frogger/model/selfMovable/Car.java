package frogger.model.selfMovable;

import frogger.constant.FileName;

public class Car extends SelfMovable {

	public Car(double speed, int xPos, int type) {
		initSelfMovable(FileName.IMAGE_CARS.get(type), xPos, speed);
	}

}
