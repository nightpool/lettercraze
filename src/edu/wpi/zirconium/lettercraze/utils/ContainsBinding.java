package edu.wpi.zirconium.lettercraze.utils;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContainsBinding<T> extends BooleanBinding {
    private final T value;
    private ObservableList<T> list;

    public ContainsBinding(T value, ObservableList<T> list) {
        this.value = value;
        this.list = list;
        bind(list);
    }

    @Override
    public void dispose() {
        unbind(list);
    }

    @Override
    public ObservableList<?> getDependencies() {
        return FXCollections.singletonObservableList(list);
    }

    @Override
    protected boolean computeValue() {
        return list.contains(value);
    }
}
