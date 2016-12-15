package edu.wpi.zirconium.lettercraze.builder.views;

import edu.wpi.zirconium.lettercraze.entities.Level;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BuilderScreen extends BorderPane {
    public BuilderScreen(Level level) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Builder.fxml"));
        this.setLevel(level);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : " + getClass().getSimpleName());
        }
    }

    public BuilderScreen() {
        this(Level.dummy(6));
    }

    private ObjectProperty<Level> level = new SimpleObjectProperty<>(this, "level");

    public Level getLevel() {
        return level.get();
    }

    public ObjectProperty<Level> levelProperty() {
        return level;
    }

    public void setLevel(Level level) {
        this.level.set(level);
    }
}
