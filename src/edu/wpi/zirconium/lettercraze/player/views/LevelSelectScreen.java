package edu.wpi.zirconium.lettercraze.player.views;

import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.function.Function;

public class LevelSelectScreen extends AnchorPane {
    private final Function<String, LevelPack> levelPackFactory;

    public LevelSelectScreen(Function<String, LevelPack> levelPackFactory) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LevelSelect.fxml"));
        fxmlLoader.setRoot(this);
        this.levelPackFactory = levelPackFactory;

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't load FXML : " + getClass().getSimpleName());
        }
    }

    public Function<String, LevelPack> getLevelPackFactory() {
        return levelPackFactory;
    }
}
