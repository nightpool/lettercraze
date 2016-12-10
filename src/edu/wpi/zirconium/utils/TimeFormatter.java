package edu.wpi.zirconium.utils;

import javafx.beans.binding.IntegerExpression;
import javafx.beans.binding.StringBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TimeFormatter extends StringBinding {
    private final IntegerExpression value;

    public TimeFormatter(IntegerExpression value) {
        bind(value);
        this.value = value;
    }

    @Override
    public void dispose() {
        unbind(value);
    }

    @Override
    public ObservableList<?> getDependencies() {
        return FXCollections.singletonObservableList(value);
    }

    @Override
    protected String computeValue() {
        int seconds = this.value.get() % 60;
        int minutes = this.value.get() / 60;
        if (minutes > 0) {
            return String.format("%d:%02d", minutes, seconds);
        }
        return String.format(":%02d", seconds);
    }
}
