package frogger.view;

import frogger.model.Animal;
import frogger.model.BackgroundImage;
import frogger.model.Digit;
import frogger.model.End;
import frogger.model.Log;
import frogger.model.MyStage;
import frogger.model.Car;
import frogger.model.Turtle;
import frogger.model.WetTurtle;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GameMain extends Scene {

  private MyStage background;
  private AnimationTimer timer;
  private Animal animal;

  public GameMain() {
    super(new MyStage(), 600, 800);

    this.background = (MyStage) super.getRoot();

    BackgroundImage froggerback = new BackgroundImage("/frogger/image/background/gamebg.png");
    background.add(froggerback);


    background.add(new Log("/frogger/image/water/log3.png", 150, 0, 166, 0.75));
    background.add(new Log("/frogger/image/water/log3.png", 150, 220, 166, 0.75));
    background.add(new Log("/frogger/image/water/log3.png", 150, 440, 166, 0.75));
    background.add(new Log("/frogger/image/water/logs.png", 300, 0, 276, -2));
    background.add(new Log("/frogger/image/water/logs.png", 300, 400, 276, -2));
    background.add(new Log("/frogger/image/water/log3.png", 150, 50, 329, 0.75));
    background.add(new Log("/frogger/image/water/log3.png", 150, 270, 329, 0.75));
    background.add(new Log("/frogger/image/water/log3.png", 150, 490, 329, 0.75));

    background.add(new Turtle(500, 376, -1, 130, 130));
    background.add(new Turtle(300, 376, -1, 130, 130));
    background.add(new WetTurtle(700, 376, -1, 130, 130));
    background.add(new WetTurtle(600, 217, -1, 130, 130));
    background.add(new WetTurtle(400, 217, -1, 130, 130));
    background.add(new WetTurtle(200, 217, -1, 130, 130));

    background.add(new End(13,96));
    background.add(new End(141,96));
    background.add(new End(141 + 141-13,96));
    background.add(new End(141 + 141-13+141-13+1,96));
    background.add(new End(141 + 141-13+141-13+141-13+3,96));

    animal = new Animal("/frogger/image/frogger/froggerUp.png");
    background.add(animal);

    background.add(new Car("/frogger/image/ground/truck1Right.png", 0, 649, 1, 120, 120));
    background.add(new Car("/frogger/image/ground/truck1Right.png", 300, 649, 1, 120, 120));
    background.add(new Car("/frogger/image/ground/truck1Right.png", 600, 649, 1, 120, 120));
    background.add(new Car("/frogger/image/ground/car1Left.png", 100, 597, -1, 50, 50));
    background.add(new Car("/frogger/image/ground/car1Left.png", 250, 597, -1, 50, 50));
    background.add(new Car("/frogger/image/ground/car1Left.png", 400, 597, -1, 50, 50));
    background.add(new Car("/frogger/image/ground/car1Left.png", 550, 597, -1, 50, 50));
    background.add(new Car("/frogger/image/ground/truck2Right.png", 0, 540, 1, 200, 200));
    background.add(new Car("/frogger/image/ground/truck2Right.png", 500, 540, 1, 200, 200));
    background.add(new Car("/frogger/image/ground/car1Left.png", 500, 490, -5, 50, 50));
    background.add(new Digit(0, 30, 360, 25));

    background.start();
    start();
  }

  public void createTimer() {
    timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if (animal.changeScore()) {
          setNumber(animal.getPoints());
        }
        if (animal.getStop()) {
          System.out.print("STOPP:");
          background.stopMusic();
          stop();
          background.stop();
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("You Have Won The Game!");
          alert.setHeaderText("Your High Score: "+animal.getPoints()+"!");
          alert.setContentText("Highest Possible Score: 800");
          alert.show();
        }
      }
    };
  }

  public void start() {
    background.playMusic();
    createTimer();
    timer.start();
  }

  public void stop() {
    timer.stop();
  }

  public void setNumber(int n) {
    int shift = 0;
    while (n > 0) {
      int d = n / 10;
      int k = n - d * 10;
      n = d;
      background.add(new Digit(k, 30, 360 - shift, 25));
      shift+=30;
    }
  }
}
