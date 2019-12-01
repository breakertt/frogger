package frogger.util.score;

import frogger.constant.FileName;
import frogger.model.info.Score;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 * <h1>ScoreLoader</h1>
 *
 * <p>A {@link ScoreLoader} is a object to load historical score of current user.
 *
 * @author Tianyi GAO
 * @version 0.2
 * @since 0.2
 * @see ScoreManager
 * @see ScoreSaver
 */
public class ScoreLoader {
  /** list of scores played by previous players. **/
  private ArrayList<Score> scoreList;

  /**
   * Create a new score loader object.
   */
  public ScoreLoader() {
    scoreList = new ArrayList<>();
  }

  /**
   * Load one score every line in file.
   * @param line line which stores a score
   * @return score stored in this line
   */
  private Score loadLine(String line) {
    Pattern scorePattern = Pattern.compile("(.*?),(.*)");
    Matcher scoreMatcher = scorePattern.matcher(line);
    if (scoreMatcher.find()) {
      if (scoreMatcher.groupCount() == 2) {
        String playerNameTemp = stringBase64Decode(scoreMatcher.group(1));
        int valueTemp = Integer.parseInt(stringBase64Decode(scoreMatcher.group(2)));
        return (new Score(playerNameTemp, valueTemp));
      }
    }
    return null;
  }

  /**
   * Choose one historical score with highest value.
   *
   * @return score with highest value
   */
  public Score loadHighest() {
    if (scoreList.size() <= 0) {
      return null;
    } else {
      Score highestScore = scoreList.get(0);
      for (int i = 1; i < scoreList.size(); i++) {
        if (scoreList.get(i).getValue() >= highestScore.getValue()) {
          highestScore = scoreList.get(i);
        }
      }
      return highestScore;
    }
  }

  /**
   * Load scores from file in user directory.
   *
   * @return list of historical scores
   */
  public ArrayList<Score> loadList() {
    File f = new File(FileName.SCORE_PATH);
    if (f.exists()) {
      try (BufferedReader br = new BufferedReader(new FileReader(FileName.SCORE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
          try {
            Score score = loadLine(line);
            if (score != null) {
              scoreList.add(score);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return scoreList;
  }

  /**
   * Convert base64 encoded string to decoded string.
   * @param string string encoded
   * @return string decoded
   */
  private String stringBase64Decode(String string) {
    byte[] byteArray = Base64.getDecoder().decode(string.getBytes());
    return (new String(byteArray));
  }
}
