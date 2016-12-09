package edu.wpi.zirconium.lettercraze.player.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LevelScreen extends BorderPane {
    public LevelScreen(String levelKey) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Level.fxml"));
        this.setLevelKey(levelKey);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : " + getClass().getSimpleName());
        }
    }

    public LevelScreen() {
        this("level1");
    }

    public String getLevelKey() {
        return levelKey.get();
    }

    public StringProperty levelKeyProperty() {
        return levelKey;
    }

    public void setLevelKey(String levelKey) {
        this.levelKey.set(levelKey);
    }

    StringProperty levelKey = new SimpleStringProperty(this, "levelKey");
}
