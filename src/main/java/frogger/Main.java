package frogger;

import frogger.util.SceneSwitch;
import frogger.util.score.ScoreManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * {@link Main} class for frogger game application.
 */
public class Main extends Application {

  /**
   * Primary stage for this application.
   **/
  private static Stage primaryStage;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Getter for {@link #primaryStage}
   *
   * @return primary stage of this application
   */
  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  /**
   * Start the game application
   *
   * <p>Assign the primary stage and set stage not sizable and its title.</p>
   * <p>Initialize {@link ScoreManager} and switch scene to home by calling {@link SceneSwitch#switchToHome()}</p>
   *
   * @param primaryStage primary stage of this application
   */
  @Override
  public void start(Stage primaryStage) {
    Main.primaryStage = primaryStage;

    primaryStage.setResizable(false);
    primaryStage.setTitle("frogger");


    ScoreManager.INSTANCE.init();
    SceneSwitch.INSTANCE.switchToHome();
  }
}
