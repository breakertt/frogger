package frogger.util.score;

import frogger.constant.FileName;
import frogger.model.info.Score;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 *
 * <h1>ScoreSaver</h1>
 *
 * <p>A {@link ScoreLoader} is a object to save scores.
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see ScoreManager
 * @see ScoreLoader
 */
public class ScoreSaver {

  /**
   * Save a list of scores to file in user directory.
   *
   * @param scoreList list of scores to be saved
   */
  public void saveList(ArrayList<Score> scoreList) {
    if (scoreList.size() <= 0) {
      return;
    }
    createFile();
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(FileName.SCORE_PATH));
      for (Score score : scoreList) {
        saveScore(bw, score);
      }
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Save a single score to single line.
   *
   * @param bw buffer writer to write line for score
   * @param score score to written into file
   * @throws IOException IO error for not able to write file.
   */
  private void saveScore(BufferedWriter bw, Score score) throws IOException {
    String line = stringBase64Encode(score.getPlayerName()) + "," + stringBase64Encode(Integer.toString(score.getValue()));
    bw.write(line);
    bw.newLine();
  }

  /**
   * Rewrite file in user directory.
   */
  private void createFile() {
    File f = new File(FileName.SCORE_PATH);
    try {
      f.getParentFile().mkdirs();
      f.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Encode a normal string to base64
   * @param string normal string
   * @return base64 decoded string
   */
  private String stringBase64Encode(String string) {
    byte[] byteArray = Base64.getEncoder().encode((string.getBytes()));
    return (new String(byteArray));
  }

}
