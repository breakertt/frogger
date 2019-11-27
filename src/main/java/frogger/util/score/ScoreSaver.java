package frogger.util.score;

import frogger.constant.FileName;
import frogger.model.info.Score;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class ScoreSaver {

  public ScoreSaver(ArrayList<Score> scoreList) {
    if (scoreList.size() > 0) {
      saveList(scoreList);
    }
  }

  private void saveList(ArrayList<Score> scoreList) {
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

  private void saveScore(BufferedWriter bw, Score score) throws IOException {
    String line = stringBase64Encode(score.getPlayerName()) + "," + stringBase64Encode(Integer.toString(score.getValue()));
    bw.write(line);
    bw.newLine();
  }

  private void createFile() {
    File f = new File(FileName.SCORE_PATH);
    try {
      f.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private String stringBase64Encode(String string) {
    byte[] byteArray = Base64.getEncoder().encode((string.getBytes()));
    return (new String(byteArray));
  }

}
