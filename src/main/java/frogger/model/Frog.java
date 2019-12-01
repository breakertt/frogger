package frogger.model;

import frogger.constant.Death;
import frogger.util.GameManager;
import frogger.util.sound.EffectPlayer;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

/**
 *
 *
 * <h1>Frog</h1>
 *
 * <p>A {@link Frog} is an critical object of {@link Movable} which is manipulated by player.</p>
 *
 * <p>This class is extends {@link Movable} for basic properties amd timer for animation.</p>
 * <p>This class is originally "Animal" class in legacy code.</p>
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.1
 * @see Map
 * @see Movable
 * @see GameManager
 */
public class Frog extends Movable {

  /** Size of frog images **/
  private int imgSize = 40;
  /** Array for jump images **/
  private Image jumpImg[][];
  /** Array for water death images **/
  private Image waterDeathImg[];
  /** Array for car death images **/
  private Image carDeathImg[];

  /** Distance for jump on Up or Down direction which is on X axis **/
  private double jumpX = 40;
  /** Distance for jump on Left or Right direction which is on Y axis **/
  private double jumpY = 50;
  /** Smallest position on Y axis ever in one life of frog **/
  private double yPosSmallest = 700;
  /** Jump lock for key press events **/
  private boolean jumpLock = false;
  /** Move lock for reset period of frog **/
  private boolean noMove = false;

  /** Death status for current frog **/
  private Death death;
  /** DeathFrame class for current frog **/
  private DeathFrame deathFrame;

  /**
   * Create new frog.
   *
   * <p>Set up images for jump and death.</p>
   * <p>Reset frog status to initial status.</p>
   */
  public Frog() {
    ImgFactory();
    reset();
  }

  /**
   * Inner class for death image transformation.
   *
   * <p>Get number for death images and nanoseconds now and get the frame number of current death.</p>
   */
  private class DeathFrame {
    /** offset to calculate frame number now. **/
    private int offset = 1;

    /**
     * Get the frame number for death.
     *
     * @param now The timestamp of the current frame given in nanoseconds.
     * @param loopFrameNum Number of all death images in this death animation.
     * @return frame number for death.
     */
    public int getFrameNum(long now, int loopFrameNum) {
      int frame = (int) ((now / 300000000) % loopFrameNum); // calculate current frame without offset
      if (offset == 1) {
        this.offset = frame;
      }
      return (frame + loopFrameNum - this.offset) % loopFrameNum;
    }
  }

  /**
   * Getter for {@link Death}
   *
   * @return death status of current frog.
   */
  public Death getDeath() {
    return death;
  }

  /**
   * Setter for {@link Death}
   *
   * @param death death status to be set on this frog.
   */
  public void setDeath(Death death) {
    this.death = death;
  }

  /**
   * Factory for preparing images used in animation.
   *
   * @see #jumpImg
   * @see #waterDeathImg
   * @see #carDeathImg
   */
  private void ImgFactory() {
    jumpImg = new Image[4][2];
    jumpImg[0][0] = new Image("/frogger/image/frogger/froggerUp.png", imgSize, imgSize, true, true);
    jumpImg[1][0] = new Image("/frogger/image/frogger/froggerLeft.png", imgSize, imgSize, true,
        true);
    jumpImg[2][0] = new Image("/frogger/image/frogger/froggerDown.png", imgSize, imgSize, true,
        true);
    jumpImg[3][0] = new Image("/frogger/image/frogger/froggerRight.png", imgSize, imgSize, true,
        true);
    jumpImg[0][1] = new Image("/frogger/image/frogger/froggerUpJump.png", imgSize, imgSize, true,
        true);
    jumpImg[1][1] = new Image("/frogger/image/frogger/froggerLeftJump.png", imgSize, imgSize, true,
        true);
    jumpImg[2][1] = new Image("/frogger/image/frogger/froggerDownJump.png", imgSize, imgSize, true,
        true);
    jumpImg[3][1] = new Image("/frogger/image/frogger/froggerRightJump.png", imgSize, imgSize, true,
        true);

    waterDeathImg = new Image[4];
    waterDeathImg[0] = new Image("/frogger/image/water/waterdeath1.png", imgSize, imgSize, true,
        true);
    waterDeathImg[1] = new Image("/frogger/image/water/waterdeath2.png", imgSize, imgSize, true,
        true);
    waterDeathImg[2] = new Image("/frogger/image/water/waterdeath3.png", imgSize, imgSize, true,
        true);
    waterDeathImg[3] = new Image("/frogger/image/water/waterdeath4.png", imgSize, imgSize, true,
        true);

    carDeathImg = new Image[3];
    carDeathImg[0] = new Image("/frogger/image/ground/cardeath1.png", imgSize, imgSize, true, true);
    carDeathImg[1] = new Image("/frogger/image/ground/cardeath2.png", imgSize, imgSize, true, true);
    carDeathImg[2] = new Image("/frogger/image/ground/cardeath3.png", imgSize, imgSize, true, true);
  }

  /**
   * Called when key is pressed and related to {@link Frog}.
   *
   * <p>Move current frog according to this key event by calling {@link #movePos(double, double)} and set jump image.</p>
   * <p>Play hop effect sound by calling {@link EffectPlayer#effectMusicFactory(String)}.</p>
   * <p>Set {@link #jumpLock} to true to wait for key release event.</p>
   *
   * @param event KeyEvent to be handled.
   */
  public void handleKeyPressed(KeyEvent event) {
    if (noMove || jumpLock) {
      return;
    }
    switch (event.getCode()) {
      case UP:
      case W:
        movePos(0, -jumpY / 2);
        setImage(jumpImg[0][1]);
        EffectPlayer.INSTANCE.effectMusicFactory("HOP");
        break;
      case LEFT:
      case A:
        movePos(-jumpX / 2, 0);
        setImage(jumpImg[1][1]);
        EffectPlayer.INSTANCE.effectMusicFactory("HOP");
        break;
      case DOWN:
      case S:
        movePos(0, jumpY / 2);
        setImage(jumpImg[2][1]);
        EffectPlayer.INSTANCE.effectMusicFactory("HOP");
        break;
      case RIGHT:
      case D:
        movePos(jumpX / 2, 0);
        setImage(jumpImg[3][1]);
        EffectPlayer.INSTANCE.effectMusicFactory("HOP");
        break;
    }
    jumpLock = true;
  }

  /**
   * Called when key is released and related to {@link Frog}.
   *
   * <p>Move current frog according to this key event by calling {@link #movePos(double, double)} and set default image.</p>
   * <p>Set {@link #jumpLock} to false.</p>
   *
   * @param event KeyEvent to be handled.
   */
  public void handleKeyReleased(KeyEvent event) {
    if (noMove || !jumpLock) {
      return;
    }
    switch (event.getCode()) {
      case UP:
      case W:
        movePos(0, -jumpY / 2);
        setImage(jumpImg[0][0]);
        GameManager.INSTANCE.handleFrogJumpUp();
        break;
      case LEFT:
      case A:
        movePos(-jumpX / 2, 0);
        setImage(jumpImg[1][0]);
        break;
      case DOWN:
      case S:
        movePos(0, jumpY / 2);
        setImage(jumpImg[2][0]);
        break;
      case RIGHT:
      case D:
        movePos(jumpX / 2, 0);
        setImage(jumpImg[3][0]);
        break;
    }
    jumpLock = false;
  }

  /**
   * Transform image of frog when in death.
   *
   * <p>Change image during a frog's death and get frame number from calling {@link DeathFrame#getFrameNum(long, int)}</p>
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   * @param death Death type for current frog.
   *
   * @see DeathFrame
   */
  private void deathTransform(long now, Death death) {
    Image[] deathImg;
    switch (death) {
      case DROP:
        deathImg = waterDeathImg;
        break;
      case CRASH:
        deathImg = carDeathImg;
        break;
      default:
        return;
    }
    noMove = true;
    int loopFrameNum = deathImg.length;
    int frameNumNow = deathFrame.getFrameNum(now, loopFrameNum + 1);
    if (frameNumNow != loopFrameNum) {
      if (getImage() != deathImg[frameNumNow]) {
        setImage(deathImg[frameNumNow]);
      };
    } else {
      this.reset();
      GameManager.INSTANCE.getTime().reset();
    }
  }

  /**
   * Reset frog position on Y axis.
   */
  private void resetY() {setY(600 + (50 - getHeight()) / 2);}

  /**
   * Reset frog position on X axis.
   */
  private void resetX() {setX(300);}

  /**
   * Reset frog for a new life.
   *
   * <p>Reset position by calling {@link #resetX()} and {@link #resetY()}.</p>
   * <p>Reset {@link #death} and {@link #deathFrame}.</p>
   * <p>Reset jump/move lockers.</p>
   * <p>Reset Image.</p>
   */
  public void reset() {
    resetX();
    resetY();
    this.death = Death.NONE;
    deathFrame = new DeathFrame();
    noMove = false;
    jumpLock = false;
    setImage(jumpImg[0][0]);
  }

  /**
   * Transform image of frog when necessary.
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   *
   * @see #deathTransform(long, Death)
   */
  @Override
  public void transformAct(long now) {
    // show death if possible
    if (this.death != Death.NONE) {
      deathTransform(now, this.death);
    }
  }

  @Override
  public void moveAct(long now) {
  }

  /**
   * Check actions for current frog.
   *
   * <p>Check whether current frog is out of game screen by calling {@link #checkOverScreen(long)}.</p>
   * <p>Check whether current frog is in water area by calling {@link #checkWater(long)}.</p>
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   */
  @Override
  public void checkAct(long now) {
    checkOverScreen(now);
    checkWater(now);
  }

  private void checkWater(long now) {
    if (getY() < 300) {
      GameManager.INSTANCE.handleFrogInWater();
    }
  }

  /**
   * Check if frog if out of screen.
   *
   * <p>Forbid frog to move lower than start lane.</p>
   * <p>Set frog to death if out of left and right screen.</p>
   *
   * @param now The timestamp of the current frame given in nanoseconds.
   */
  private void checkOverScreen(long now) {
    if (getY() > 600) {
      resetY();
    }

    // out of screen, game over
    if (getX() < 0 || getX() > 700) {
      GameManager.INSTANCE.handleFrogDie(Death.OVERSCREEN);
    }
  }

  /**
   * Getter of {@link #yPosSmallest}
   *
   * @return value of smallest position on Y axis current life of frog.
   */
  public double getYPosSmallest() {
    return yPosSmallest;
  }

  /**
   * Setter of {@link #yPosSmallest}
   *
   * @param yPosSmallest value of smallest position on Y axis current life of frog.
   */
  public void setYPosSmallest(double yPosSmallest) {
    this.yPosSmallest = yPosSmallest;
  }

  /**
   * Reset smallest position on Y axis to biggest value.
   */
  public void resetYPosSmallest() {
    this.yPosSmallest = 700;
  }

  /**
   * Stop the frogger for game ending.
   *
   * @see GameManager
   */
  @Override
  public void stop() {
    reset();
    super.stop();
  }
}
