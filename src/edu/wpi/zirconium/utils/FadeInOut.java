package edu.wpi.zirconium.utils;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeInOut {
    public FadeInOut(Node node, int durIn, int delay, int durOut) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(durIn), node);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(Duration.millis(durOut), node);
        fadeOut.setDelay(Duration.millis(delay));
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        SequentialTransition both = new SequentialTransition();
        both.getChildren().addAll(fadeIn, fadeOut);
        both.play();
    }
}
