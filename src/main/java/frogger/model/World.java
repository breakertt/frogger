package frogger.model;


import frogger.model.movable.Movable;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


public abstract class World extends Pane {
    private AnimationTimer timer;
    
    public World() {}

//    public void createTimer() {
//        timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                act(now);
//                List<Movable> movables = getObjects(Movable.class);
//
//                for (Movable anMovable : movables) {
//                	anMovable.act(now);
//                }
//
//            }
//        };
//    }
//
//    public void start() {
//    	createTimer();
//        timer.start();
//    }

//    public void stop() {
//        timer.stop();
//    }

    public void add(Movable movable) {
        getChildren().add(movable);
    }

    public void remove(Movable movable) {
        getChildren().remove(movable);
    }

    public <A extends Movable> List<A> getObjects(Class<A> cls) {
        Pane mapPane = (Pane) getScene().lookup("#map");
//        System.out.println(mapPane.getChildren());
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: mapPane.getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A)n);
            }
        }
        return someArray;
    }

    public abstract void act(long now);
}
