package frogger.util;

import frogger.Main;
import frogger.controller.GameController;
import frogger.model.Map;
import frogger.util.musicPlayer.ThemePlayer;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public enum SceneSwitch {
  INSTANCE;

  private void hideStage() {
    Main.getPrimaryStage().hide();
  }

  private void showStage() {
    Main.getPrimaryStage().show();
  }

  private void setScene(Scene scene) {
    Main.getPrimaryStage().setScene(scene);
  }

  public void switchToHome() {
    try {
      ThemePlayer.INSTANCE.themeMusicFactory("START");

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/frogger/view/home.fxml"));
      Pane root = loader.load();
      Scene homeScene = new Scene(root);

      hideStage();
      setScene(homeScene);
      showStage();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void switchToGame() {
    try {
      hideStage();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/frogger/view/game.fxml"));
      Pane root = loader.load();

      Scene gameScene = new Scene(root);
      setScene(gameScene);

      Map map = new Map();
      Pane mapPane = (Pane) loader.getNamespace().get("map");
      ArrayList<Pane> laneListPane = (ArrayList<Pane>) loader.getNamespace().get("laneListPane");
      map.draw(mapPane, laneListPane);

      GameController gameController = loader.getController();
      ScoreManager.INSTANCE.init();
      GameManager.INSTANCE.init(map, gameController);

      gameScene.addEventHandler(
          KeyEvent.KEY_PRESSED, event -> GameManager.INSTANCE.handleKeyPressed(event));
      gameScene.addEventHandler(
          KeyEvent.KEY_RELEASED, event -> GameManager.INSTANCE.handleKeyReleased(event));

      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void exitGame() {
    Platform.exit();
  }
}
