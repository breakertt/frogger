package frogger.util;

import frogger.Main;
import frogger.controller.GameController;
import frogger.model.Map;
import frogger.model.World;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

      MusicPlayer.INSTANCE.playMusic();

      FXMLLoader fxmlLoader = new FXMLLoader();
      Pane root = new World();

      Scene gameScene = new Scene(root, 600, 800);
      setScene(gameScene);

      ImageView bg = new ImageView();
      bg.setImage(new Image("/frogger/image/background/gamebg.png", 600, 800, true, true));
      root.getChildren().add(bg);

      Map map = new Map();
      Pane mapPane = new Pane();
      mapPane.setId("map");
      System.out.println(mapPane.getId());
      root.getChildren().add(mapPane);
      map.draw(mapPane);

      fxmlLoader.setRoot(root);
      fxmlLoader.setController(new GameController(mapPane));
      GameController gameController = fxmlLoader.getController();
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
