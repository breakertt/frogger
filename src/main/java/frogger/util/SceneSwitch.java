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

/**
 *
 *
 * <h1>SceneSwitch</h1>
 *
 * <p>A {@link SceneSwitch} is a object which helps switch scenes within {@link Main#getPrimaryStage()}
 * and pop up.
 *
 * <p>This class is implemented as {@link Enum} for singleton.</p>
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    SceneSwitch.INSTANCE.switchToHome();
 * </pre>
 *
 * </blockquote>
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see Main#getPrimaryStage()
 * @see GameManager
 */
public enum SceneSwitch {
  /** Instance for global view management. **/
  INSTANCE;

  /**
   * Hide primary stage.
   */
  private void hideStage() {
    Main.getPrimaryStage().hide();
  }

  /**
   * Show primary stage.
   */
  private void showStage() {
    Main.getPrimaryStage().show();
  }

  /**
   * Change scene for current primary stage
   * @param scene new scene for primary stage
   */
  private void setScene(Scene scene) {
    Main.getPrimaryStage().setScene(scene);
  }

  /**
   * Switch scene to home view.
   */
  public void switchToHome() {
    try {
      // play start game music
      ThemePlayer.INSTANCE.themeMusicFactory("START");

      // load home fxml view and create new scene for this view
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("frogger/view/home.fxml"));
      Pane root = loader.load();
      Scene homeScene = new Scene(root);

      // change new scene to primary stage
      hideStage();
      setScene(homeScene);
      showStage();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Switch scene to game view.
   *
   * @param playerName name of player
   */
  public void switchToGame(String playerName, int level) {
    try {
        // load game fxml view and create new scene for this view
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("frogger/view/game.fxml"));
        Pane root = loader.load();
        Scene gameScene = new Scene(root);

        // set up new map for this round of game and add elements to view
        Map map = new Map(level);
        Pane mapPane = (Pane) loader.getNamespace().get("map");
        ArrayList<Pane> laneListPane = (ArrayList<Pane>) loader.getNamespace().get("laneListPane");
        map.setPlayerName(playerName);
        map.draw(mapPane, laneListPane);

        // initialize game controller and game manager
        GameController gameController = loader.getController();
        GameManager.INSTANCE.init(map, gameController);

        // set keyboard event handle to bind on game manager
        gameScene.addEventHandler(
            KeyEvent.KEY_PRESSED, GameManager.INSTANCE::handleKeyPressed);
        gameScene.addEventHandler(
            KeyEvent.KEY_RELEASED, GameManager.INSTANCE::handleKeyReleased);

        // change new scene to primary stage
        hideStage();
        setScene(gameScene);
        showStage();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Show score board popup.
   */
  public void showScoreBoard() {
    try {
      // load game fxml view and create new scene for this view, set a popup stage.
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("frogger/view/scoreboard.fxml"));
      Pane root = loader.load();
      Scene scoreBoardScene = new Scene(root);

      // create a new pop up stage and set scene and properties of this stage
      Stage popup = new Stage();
      popup.setScene(scoreBoardScene);
      popup.initModality(Modality.WINDOW_MODAL);
      popup.initOwner(Main.getPrimaryStage().getScene().getWindow());
      popup.setResizable(false);
      popup.setTitle("Score Board");

      // set up controller
      ScoreBoardController scoreBoardController = loader.getController();
      scoreBoardController.init();

      // show this pop up
      popup.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void showHelp() {
    try {
      // load game fxml view and create new scene for this view, set a popup stage.
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("frogger/view/help.fxml"));
      Pane root = loader.load();
      Scene helpScene = new Scene(root);

      // create a new pop up stage and set scene and properties of this stage
      Stage popup = new Stage();
      popup.setScene(helpScene);
      popup.initModality(Modality.WINDOW_MODAL);
      popup.initOwner(Main.getPrimaryStage().getScene().getWindow());
      popup.setResizable(false);
      popup.setTitle("Help");

      // show this pop up
      popup.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Exit this game application.
   */
  public void exitGame() {
    Platform.exit();
    System.exit(0);
  }
}
