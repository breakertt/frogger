package frogger.util;

import frogger.Main;
import frogger.controller.GameController;
import frogger.controller.ScoreBoardController;
import frogger.model.Map;
import frogger.util.sound.ThemePlayer;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

  public void switchToGame(String playerName) {
    try {
      hideStage();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/frogger/view/game.fxml"));
      Pane root = loader.load();

      Scene gameScene = new Scene(root);
      setScene(gameScene);

      Map map = new Map();
      Pane mapPane = (Pane) loader.getNamespace().get("map");
      ArrayList<Pane> laneListPane = (ArrayList<Pane>) loader.getNamespace().get("laneListPane");
      map.setPlayerName(playerName);
      map.draw(mapPane, laneListPane);

      GameController gameController = loader.getController();
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

  public void showScoreBoard() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/frogger/view/scoreboard.fxml"));
      Pane root = loader.load();
      Scene scoreBoardScene = new Scene(root);
      Stage popup = new Stage();
      popup.setScene(scoreBoardScene);
      popup.initModality(Modality.WINDOW_MODAL);
      popup.initOwner(Main.getPrimaryStage().getScene().getWindow());
      popup.setResizable(false);
      popup.setTitle("Score Board");

      ScoreBoardController scoreBoardController = loader.getController();
      scoreBoardController.initUi();
      popup.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void exitGame() {
    Platform.exit();
  }

}
