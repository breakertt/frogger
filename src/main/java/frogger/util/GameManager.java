package frogger.util;

import frogger.constant.Death;
import frogger.constant.GameStatus;
import frogger.controller.GameController;
import frogger.model.Frog;
import frogger.model.Map;
import frogger.model.Lane;
import frogger.model.Movable;
import frogger.model.info.End;
import frogger.model.info.Time;
import frogger.model.info.Life;
import frogger.model.info.Score;
import frogger.model.selfMovable.SelfMovable;
import frogger.util.score.ScoreManager;
import frogger.util.sound.EffectPlayer;
import frogger.util.sound.ThemePlayer;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;

/**
 *
 *
 * <h1>GameManager</h1>
 *
 * <p>A {@link GameManager} is an object of utility to manage the game status and events globally,
 * and update view of game.
 *
 * <p> This class is implemented as {@link Enum} for singleton.</p>
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    GameManager.INSTANCE.{METHOD}()
 * </pre>
 *
 * </blockquote>
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see Map
 * @see GameController
 * @see GameStatus
 */
public enum GameManager {
  /** Instance for global game management. **/
  INSTANCE;

  /** Current map(game board) {@link Map}. **/
  private Map map;

  /** Current controller for current map {@link GameController}. **/
  private GameController gameController;

  /** Current game status {@link GameStatus}. **/
  private GameStatus gameStatus;

  /** Life for the frog in this game {@link Life}. **/
  private Life life;

  /** Time remaining for this round of game {@link Time}. **/
  private Time time;

  /** Current score {@link Score}. **/
  private Score currentScore;

  /** Highest score ever on this machine {@link Score}. **/
  private Score highestScore;

  /**
   * Initialize(reset) GameManager for every new map in order to reset Info, Music and Time.
   *
   * @param map current {@link Map}
   * @param gameController controller for current view of map {@link GameController}
   */
  public void init(Map map, GameController gameController) {
    this.map = map;
    this.gameController = gameController;
    this.gameStatus = GameStatus.START;
    this.updateAnimationStatus();
    this.initInfo();
    ThemePlayer.INSTANCE.themeMusicFactory("MAIN");
  }

  /**
   * Initialize(reset) classes related to information, including {@link Life}, {@link Time} and {@link Score}.
   *
   * <p>For {@link Life}, reset value of life to default.</p>
   * <p>For {@link Time}, reset remaining seconds in time to 60.</p>
   * <p>For {@link Score}, reset currentScore to 0 and get highestScore from {@link ScoreManager}</p>
   *
   * @see #life
   * @see #time
   * @see #currentScore
   * @see #highestScore
   */
  private void initInfo() {
    this.life = new Life();
    this.time = new Time();
    this.currentScore = new Score(getMap().getPlayerName());
    ScoreManager.INSTANCE.add(this.currentScore);
    this.highestScore = ScoreManager.INSTANCE.getHighestScore();
    gameController.updateLevel(map.getLevel());
    updateInfo();
  }

  /**
   * Returns the current {@link Map}.
   *
   * @return the current {@link Map}
   * @see #map
   */
  public Map getMap() {
    return map;
  }

  /**
   * Returns the current {@link Time}.
   *
   * @return the current {@link Time}
   * @see #time
   */
  public Time getTime() {
    return time;
  }

  /**
   * Compare currentScore with highestScore, set highestScore to currentScore if true.
   *
   * @see #currentScore
   * @see #highestScore
   */
  private void updateScore() {
    if (this.highestScore.getValue() < this.currentScore.getValue()) {
      this.highestScore = this.currentScore;
    }
  }

  /**
   * Check highestScore and update score and life in view.
   *
   * @see #updateScore()
   * @see GameController
   */
  private void updateInfo() {
    this.updateScore();
    gameController.updateScore(this.currentScore, this.highestScore);
    gameController.updateLife(this.life);
  }

  /**
   * Update animation timer status of elements in current map.
   *
   * @see #updateAnimationStatus()
   * @see Map#getLaneListElement()
   * @see Map#getFrog()
   */
  private void updateAnimationStatus() {
    updateMovableAnimationStatus(map.getLaneListElement());
    updateMovableAnimationStatus(map.getFrog());
  }

  /**
   * Update animation timer status of elements in laneListElement current map.
   *
   * @param laneListElement the ArrayList for lanes {@link Lane}
   */
  private void updateMovableAnimationStatus(ArrayList<Lane> laneListElement) {
    for (Lane lane : laneListElement) {
      for (Movable movable : lane.getSelfMovables()) {
        updateMovableAnimationStatus(movable);
      }
    }
  }

  /**
   * Update animation timer status of single {@link Movable} in laneListElement current map according to {@link GameStatus}.
   *
   * @param movable The movable for animation status switch.
   * @see Movable
   * @see GameStatus
   */
  private void updateMovableAnimationStatus(Movable movable) {
    if (gameStatus == GameStatus.START) {
      movable.run();
    } else if (gameStatus == GameStatus.END) {
      movable.stop();
    }
  }

  /**
   * Tests if player win this level.
   *
   * @return boolean for whether all {@link End} have frog.
   */
  private boolean checkWin() {
    boolean isFrogAllExist = true;
    ArrayList<End> ends = map.getEnds();
    for (End end : ends) {
      if (!end.isFrogExist()) {
        isFrogAllExist = false;
        break;
      }
    }
    return isFrogAllExist;
  }

  /**
   * Player wins the game.
   *
   * <p>Adds point of 1000 to currentScore and update to game view.</p>
   * <p>Calls {@link GameController#activateWinIndicator()} to activate indicator.</p>
   * <p>Calls {@link #endGame()} to end game.</p>
   */
  private void winGame() {
    currentScore.gain(1000);
    updateInfo();
    gameController.activateWinIndicator();
    endGame();
  }

  /**
   * Player loses the game.
   *
   * <p>Calls {@link GameController#activateWinIndicator()} to activate indicator.</p>
   * <p>Calls {@link #endGame()} to end game.</p>
   */
  private void loseGame() {
    gameController.activateLoseIndicator();
    endGame();
  }

  /**
   * Ends current game.
   *
   * <p>Play end music by calling {@link ThemePlayer#themeMusicFactory(String)}.</p>
   * <p>Set {@link #gameStatus} to END and call {@link #updateAnimationStatus()} pause Animations.</p>
   * <p>Reset {@link #time}.</p>
   * <p>Call {@link ScoreManager#update()} to save score.</p>
   */
  private void endGame() {
    ThemePlayer.INSTANCE.themeMusicFactory("OVER");

    gameStatus = GameStatus.END;
    updateAnimationStatus();

    time.reset();
    ScoreManager.INSTANCE.update();
  }

  /**
   * Called when key is pressed.
   *
   * @param event the {@link KeyEvent} to be handled.
   */
  public void handleKeyPressed(KeyEvent event) {
    if (gameStatus == GameStatus.END) {
      return;
    }
    map.getFrog().handleKeyPressed(event);
  }

  /**
   * Called when key is unpressed.
   *
   * @param event the {@link KeyEvent} to be handled.
   */
  public void handleKeyReleased(KeyEvent event) {
    map.getFrog().handleKeyReleased(event);
  }

  /**
   * Called when frog is touching a log or turtle.
   *
   * <p>Moves the frog with this log or turtle.</p>
   *
   * @param selfMovable the {@link SelfMovable} which is touching frog.
   */
  public void handleLogTurtleTouched(SelfMovable selfMovable) {
    if (gameStatus == GameStatus.END) {
      return;
    }
    if (map.getFrog().getDeath() == Death.NONE) {
      map.getFrog().movePos(selfMovable.getSpeed(), 0);
      // move with Log
    }
  }

  /**
   * Called when frog is touching a car.
   *
   * <p>set drop death to frog by calling {@link #handleFrogDie(Death)}</p>
   */
  public void handleCarTouched() {
    if (gameStatus == GameStatus.END) {
      return;
    }
    handleFrogDie(Death.CRASH);
  }

  /**
   * Called when frog is touching a sunk wet turtle.
   *
   * <p>set drop death to frog by calling {@link #handleFrogDie(Death)}</p>
   */
  public void handleSunkWetTurtleTouched() {
    if (gameStatus == GameStatus.END) {
      return;
    }
    handleFrogDie(Death.DROP);
  }

  /**
   * Called when a frog is touching end.
   *
   * <p>Set {@link End} to a frog exist status.</p>
   * <p>Add 200 points by calling {@link Score#gain(int)} and update to game view.</p>
   * <p>{@link #checkWin()} and send frog to home if not.</p>
   * <p>Reset {@link #time}, {@link Frog#resetyPosSmallest()} and frog itself.</p>
   *
   * @param end the end touched
   *
   * @see End
   */
  public void handleEndTouched(End end) {
    if (gameStatus == GameStatus.END) {
      return;
    }
    end.setFrog();
    currentScore.gain(200);
    updateInfo();
    if (checkWin()) {
      System.out.println("Win!");
      winGame();
    } else {
      gameController.activateTimeIndicator(time.getSecondsLeft());
      ThemePlayer.INSTANCE.themeMusicFactory("HOMED");
    }
    time.reset();
    map.getFrog().resetyPosSmallest();
    map.getFrog().reset();
  }

  /**
   * Called when there are events causing death of frog.
   *
   * <p>Set frog to dead by {@link Frog#setDeath(Death)}, play corresponding effect music and decrease life and score. After death, play REBORN effect sound.</p>
   *
   * @param death the death type to be set
   */
  public void handleFrogDie(Death death) {
    if (gameStatus == GameStatus.END || map.getFrog().getDeath() != Death.NONE) {
      // No going to do any further when game is end or frog is already dead.
      return;
    }
    System.out.println(death);
    if (death == Death.DROP) {
      map.getFrog().setDeath(death);
      EffectPlayer.INSTANCE.effectMusicFactory("PLUNK");
    } else if (death == Death.CRASH) {
      map.getFrog().setDeath(death);
      EffectPlayer.INSTANCE.effectMusicFactory("SQUASH");
    } else if (death == Death.TIMEOUT) {
      map.getFrog().resetyPosSmallest();
      map.getFrog().reset();
    }
    life.lose();
    currentScore.lose(50);
    if (life.getCurrent() < 0) {
      loseGame();
      System.out.println("Lose!");
      return;
    }
    updateInfo();
    ThemePlayer.INSTANCE.themeMusicFactory("REBORN");
  }

  /**
   * Called when a frog jump to up direction.
   *
   * <p>If frog is jump to right area, update high bound and increase score.</p>
   */
  public void handleFrogJumpUp() {
    if (gameStatus == GameStatus.END) {
      return;
    }
    if (map.getFrog().getY() > 300 && map.getFrog().getY() < 350) {
      return;
    }
    if (map.getFrog().getY() < map.getFrog().getyPosSmallest()) {
      map.getFrog().setyPosSmallest(map.getFrog().getY());
      currentScore.gain(10);
      updateInfo();
    }
  }

  /**
   * Called when current frog is in water area.
   *
   * <p>Iterate all elements in water area, if no element is touching, set frog to death.</p>
   */
  public void handleFrogInWater() {
    if (gameStatus == GameStatus.END) {
      return;
    }
    boolean frogWaterDie = true;
    for (int i = 0; i < 6; i++) {
      Lane lane = map.getLaneListElement().get(i);
      for (SelfMovable selfMovable : lane.getSelfMovables()) {
        if (selfMovable.checkTouchFrog()) {
          frogWaterDie = false;
          break;
        }
      }
    }
    if (frogWaterDie) {
      handleFrogDie(Death.DROP);
    }
  }

  /**
   * Called once every second during game period by {link @Time}.
   *
   * <p>When game is not end, call {@link GameController#deactivateTimeIndicator()} or {@link EffectPlayer#effectMusicFactory(String)} at specific time.</p>
   * <p>When game is end, call {@link #switchBack()} when 7 seconds past.</p>
   *
   * @param secondsLeft seconds left for this round of game
   */
  public void handleTimeUpdate(int secondsLeft) {
    if (gameStatus == GameStatus.START) {
      if (secondsLeft <= 0) {
        handleFrogDie(Death.TIMEOUT);
      }
      if (secondsLeft == 57) {
        gameController.deactivateTimeIndicator();
      }
      if (secondsLeft == 10) {
        EffectPlayer.INSTANCE.effectMusicFactory("TIME");
      }
      gameController.updateTime(secondsLeft);
    } else if (gameStatus == GameStatus.END) {
      if (secondsLeft == 53) {
        time.stop();
        switchBack();
      }
    }
  }

  /**
   * Switch back to home screen and pop up score board.
   * 
   * @see SceneSwitch#switchToGame(String)
   * @see SceneSwitch#showScoreBoard()
   */
  private void switchBack() {
    SceneSwitch.INSTANCE.switchToHome();
    SceneSwitch.INSTANCE.showScoreBoard();
  }
}
