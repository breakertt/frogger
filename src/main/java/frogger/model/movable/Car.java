package frogger.model.movable;

import frogger.constant.Direction;
import javafx.scene.image.Image;

public class Car extends Movable {
	private int speed;
	@Override
	public void move(long now) {
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
	}

	public Car(String imageLink, int xPos, int yPos, int speed, Direction direction) {
		this(imageLink, xPos, yPos, speed, 50, 50);
	}

	public Car(String imageLink, int xpos, int ypos, int s, int w, int h) {
		setImage(new Image(imageLink, w,h, true, true));
		setX(xpos);
		setY(ypos);
		speed = s;
	}

}
