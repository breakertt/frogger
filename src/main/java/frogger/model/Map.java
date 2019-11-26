package frogger.model;

import frogger.model.info.End;
import frogger.util.MapLoader;
import frogger.util.MapRender;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class Map {

  private String fileName;

  private String playerName;

  private int level;

  private Frog frog;

  private ArrayList<End> ends;

  private ArrayList<Lane> laneListElement;

  public ArrayList<Lane> getLaneListElement() {
    return laneListElement;
  }

  public ArrayList<End> getEnds() {
    return ends;
  }

  public void setLaneListElement(ArrayList<Lane> laneListElement) {
    this.laneListElement = laneListElement;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public Frog getFrog() {
    return frog;
  }

  public void setFrog(Frog frog) {
    this.frog = frog;
  }

  public Map() {
  }

  public void load() {
    MapLoader mapLoader = new MapLoader(fileName, this);
    mapLoader.loadMap();
    level = mapLoader.getLevel();
    frog = mapLoader.getFrog();
    ends = mapLoader.getEnds();
    laneListElement = mapLoader.getLaneListElement();
  }

  public void draw(Pane root, ArrayList<Pane> laneListPane) {
    load();
    MapRender mapRender = new MapRender(root, laneListPane); // root to be deleted

    mapRender.drawFrog(frog);
    try {
      mapRender.drawLanes(laneListElement);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
