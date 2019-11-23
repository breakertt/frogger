package frogger.model.info;

import frogger.model.Movable;
import frogger.model.selfMovable.SelfMovable;
import frogger.util.GameManager;
import javafx.scene.image.Image;

public class End extends SelfMovable {

	boolean frogExist = false;

	@Override
	public void moveAct(long now) {
	}

	@Override
	public void checkAct(long now) {
		if (checkTouchFrog()) GameManager.INSTANCE.handleEndTouched(this);
	}

	public End(double xPos) {
		setImage(new Image("/frogger/image/water/emptyEnd.png"));
		setX(xPos);
	}

	public void setFrog() {
		setImage(new Image("/frogger/image/water/froggerEnd.png"));
		frogExist = true;
	}

	public boolean isFrogExist() {
		return frogExist;
	}
	

}
