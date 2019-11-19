package frogger.util;

import frogger.Main;
import frogger.model.BackgroundImage;
import frogger.model.Map;
import frogger.model.MyStage;
import frogger.view.GameMain;
import javafx.scene.Scene;
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

      Pane root = new MyStage();
      Scene gameScene = new Scene(root);
      setScene(gameScene);

      root.getChildren().add(new BackgroundImage("/frogger/image/background/gamebg.png"));

      Map map = new Map();
      Pane mapPane = new Pane();

      root.getChildren().add(mapPane);
      map.draw(mapPane);
      
//      Scene scene = new GameMain();
//      setScene(scene);
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
