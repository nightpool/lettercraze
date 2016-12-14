package edu.wpi.zirconium.lettercraze.player.views;

import edu.wpi.zirconium.utils.StyleClassProperty;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

public class SubmitButton extends Button {
    public SubmitButton() {
        this.textProperty().bind(Bindings.createStringBinding(() ->
            valid.get() ? String.format("SUBMIT (+%d)", score.get()) :
                          "SUBMIT",
            valid, score));

        validProperty().addListener((new StyleClassProperty(this, "valid")));
    }

    private BooleanProperty valid = new SimpleBooleanProperty(this, "valid", false);
    private SimpleIntegerProperty score = new SimpleIntegerProperty(this, "score", 0);

    public boolean isValid() {
        return valid.get();
    }

    public BooleanProperty validProperty() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid.set(valid);
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }
}
