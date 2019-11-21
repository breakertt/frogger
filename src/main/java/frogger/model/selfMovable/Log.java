package frogger.model.selfMovable;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class Log extends SelfMovable {

	private boolean left;

	public Log(String imageLink, int size, int xpos, int ypos, double s) {
		super(s);
		setImage(new Image(imageLink, size,50, true, true));
		setX(xpos);
		setY(ypos);
		HBox.setMargin(this, new Insets(200,0,0,0));
		left = s < 0;
	}

	public boolean getLeft() {
		return left;
	}
}
