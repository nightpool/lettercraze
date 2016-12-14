package edu.wpi.zirconium.lettercraze.player.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LevelSelectScreen extends AnchorPane {
    public LevelSelectScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LevelSelect.fxml"));
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : " + getClass().getSimpleName());
        }
    }
}
