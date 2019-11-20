package frogger.model;


import frogger.model.movable.Movable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class World extends BorderPane {

    public World() {}

    public <A extends Movable> List<A> getObjects(Class<A> cls) {
        Pane mapPane = (Pane) getScene().lookup("#map");
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: mapPane.getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A)n);
            }
        }
        return someArray;
    }

}
