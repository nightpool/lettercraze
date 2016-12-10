package edu.wpi.zirconium.utils;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerExpression;
import javafx.beans.binding.StringBinding;

public class TimeFormatter {
    public static StringBinding forValue(IntegerExpression value) {
        return Bindings.createStringBinding(() -> {
            int seconds = value.get() % 60;
            int minutes = value.get() / 60;
            if (minutes > 0) {
                return String.format("%d:%02d", minutes, seconds);
            }
            return String.format(":%02d", seconds);
        }, value);
    }
}
