package frogger.model;

import java.io.File;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyStage extends World{
	MediaPlayer mediaPlayer;
	@Override
	public void act(long now) {
		
	}
	
	public MyStage() {
	}
	
	public void playMusic() {
		String musicFile = "frogger/music/theme_main.mp3";
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(musicFile);
		String path = null;
		try {
			path = Objects.requireNonNull(resource).toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Media sound = new Media(new File(Objects.requireNonNull(path)).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.play();
	}
	
	public void stopMusic() {
		mediaPlayer.stop();
	}

}
