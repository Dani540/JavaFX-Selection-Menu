package org.battlegame.demo.gui.transitions;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TransitionRepo {
    public void fadeIn(Duration duration, Node node){
        FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void fadeOut(Duration duration, Node node){
        FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    public void open(Duration duration, Node node) {
        ScaleTransition transition = new ScaleTransition(duration, node);
        transition.setFromY(0);
        transition.setToY(1);
        transition.play();
    }

    public void none(Duration duration, Node node) {
        // Do nothing
    }
}
