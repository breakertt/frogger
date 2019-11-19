package frogger.util;

import frogger.Main;
import frogger.controller.GameController;
import frogger.model.Map;
import frogger.model.MyStage;
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
      FXMLLoader fxmlLoader = new FXMLLoader();
      Pane root = new MyStage();

      fxmlLoader.setRoot(root);
      fxmlLoader.setController(new GameController());

      Scene gameScene = new Scene(root);
      setScene(gameScene);

      ImageView bg = new ImageView();
      bg.setImage(new Image("/frogger/image/background/gamebg.png", 600, 800, true, true));
      root.getChildren().add(bg);

      Map map = new Map();
      Pane mapPane = new Pane();
      root.getChildren().add(mapPane);
      map.draw(mapPane);

      GameController gameController = fxmlLoader.getController();
      GameManager.INSTANCE.init(map, gameController);

      gameScene.addEventHandler(
          KeyEvent.KEY_PRESSED, event -> GameManager.INSTANCE.handleKeyPressed(event));
      gameScene.addEventHandler(
          KeyEvent.KEY_RELEASED, event -> GameManager.INSTANCE.handleKeyReleased(event));
//      Scene scene = new GameMain();
//      setScene(scene);
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
