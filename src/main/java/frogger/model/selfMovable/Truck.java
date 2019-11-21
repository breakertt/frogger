package frogger.model.selfMovable;

import javafx.scene.image.Image;

public class Truck extends SelfMovable {

	public Truck(String imageLink, int xpos, int ypos, int s, int w, int h) {
		super(s);
		setImage(new Image(imageLink, w, h, true, true));
		setX(xpos);
		setY(ypos);
	}

}
