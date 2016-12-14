package edu.wpi.zirconium.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

public class StyleClassProperty implements ChangeListener<Boolean> {
    private final Node node;
    private final String property;

    public StyleClassProperty(Node node, String property) {
        this.node = node;
        this.property = property;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
        if (newValue) {
            node.getStyleClass().add(property);
        } else {
            node.getStyleClass().removeIf(property::equals);
        }
    }
}
