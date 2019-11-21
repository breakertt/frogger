package frogger.util;

import frogger.Main;
import frogger.controller.GameController;
import frogger.model.Map;
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

  public void switchToGame() {
    try {
      hideStage();

//      MusicPlayer.INSTANCE.playMusic();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/frogger/view/game.fxml"));
      Pane root = loader.load();
      Scene gameScene = new Scene(root);
      setScene(gameScene);

      Map map = new Map();
      Pane mapPane = (Pane) gameScene.lookup("#map");
      map.draw(mapPane);

      GameController gameController = loader.getController();
      ScoreManager.INSTANCE.init();
      GameManager.INSTANCE.init(map, gameController, gameScene);

      gameScene.addEventHandler(
          KeyEvent.KEY_PRESSED, event -> GameManager.INSTANCE.handleKeyPressed(event));
      gameScene.addEventHandler(
          KeyEvent.KEY_RELEASED, event -> GameManager.INSTANCE.handleKeyReleased(event));

      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
