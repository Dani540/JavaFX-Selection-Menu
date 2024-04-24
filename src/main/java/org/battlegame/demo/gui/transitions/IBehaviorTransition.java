package org.battlegame.demo.gui.transitions;

import javafx.scene.Node;
import javafx.util.Duration;

@FunctionalInterface
public interface IBehaviorTransition {
    void startTransition(Duration duration, Node node);
}
