package frogger.model.selfMovable;

import javafx.scene.image.Image;

public class Log extends SelfMovable {

	private boolean left;

	public Log(String imageLink, int size, int xpos, int ypos, double s) {
		super(s);
		setImage(new Image(imageLink, size,size, true, true));
		setX(xpos);
		setY(ypos);
		left = s < 0;
	}

	public boolean getLeft() {
		return left;
	}
}
