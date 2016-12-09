package edu.wpi.zirconium.lettercraze.entities.bindings;

import edu.wpi.zirconium.lettercraze.entities.Word;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WordStringBinding extends StringBinding {
    private final ObservableValue<? extends Word> word;

    public WordStringBinding(ObservableValue<? extends Word> word) {
        bind(word);
        this.word = word;
    }

    @Override
    public void dispose() {
        unbind(word);
    }

    @Override
    public ObservableList<?> getDependencies() {
        return FXCollections.singletonObservableList(word);
    }

    @Override
    protected String computeValue() {
        return word.getValue().asString();
    }
}
