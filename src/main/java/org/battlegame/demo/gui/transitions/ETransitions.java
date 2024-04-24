package org.battlegame.demo.gui.transitions;

import javafx.scene.Node;
import javafx.util.Duration;

public enum ETransitions {
    FADE_IN,
    FADE_OUT,
    SLIDE_IN,
    SLIDE_OUT,
    OPEN,
    NONE;

    private IBehaviorTransition behaviorTransition;

    public IBehaviorTransition getBehaviorTransition() {
        return behaviorTransition;
    }

    public void setBehaviorTransition(IBehaviorTransition behaviorTransition) {
        this.behaviorTransition = behaviorTransition;
    }

    public void startTransition(Duration duration, Node node) {
        behaviorTransition.startTransition(duration,node);
    }

}
