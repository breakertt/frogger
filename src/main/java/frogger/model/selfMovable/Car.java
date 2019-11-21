package frogger.model.selfMovable;

import frogger.constant.Direction;
import javafx.scene.image.Image;

public class Car extends SelfMovable {

	public Car(String imageLink, int xPos, int yPos, int speed, Direction direction) {
		this(imageLink, xPos, yPos, speed, 50, 50);
	}

	public Car(String imageLink, int xpos, int ypos, int s, int w, int h) {
		super(s);
		setImage(new Image(imageLink, w,h, true, true));
		setX(xpos);
		setY(ypos);
	}

}
