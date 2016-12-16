package edu.wpi.zirconium.utils;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableNumberValue;

public class TimeFormatter {
    public static StringBinding forValue(ObservableNumberValue value) {
        return Bindings.createStringBinding(() -> {
            int i = value.intValue();
            if (i < 0) {
                return ":00";
            }
            int seconds = i % 60;
            int minutes = i / 60;
            if (minutes > 0) {
                return String.format("%d:%02d", minutes, seconds);
            }
            return String.format(":%02d", seconds);
        }, value);
    }
}
