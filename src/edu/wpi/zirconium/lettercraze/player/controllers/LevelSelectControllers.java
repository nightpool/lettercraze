package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import edu.wpi.zirconium.lettercraze.player.views.LevelPackView;
import edu.wpi.zirconium.lettercraze.player.views.LevelSelectScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelSelectControllers implements Initializable {

    @FXML private LevelSelectScreen root;
    @FXML private Pane backButton;

    @FXML private LevelPackView puzzlePack;
    @FXML private LevelPackView lightningPack;
    @FXML private LevelPackView themePack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        backButton.setOnMouseClicked(this::onReturnToMenuClicked);

        this.puzzlePack.setPack(LevelPack.dummyPuzzle());
        this.lightningPack.setPack(LevelPack.dummyLightning());
        this.themePack.setPack(LevelPack.dummyTheme());
    }

    private void showLevelScreen(MouseEvent mouseEvent) {
        LetterCrazePlayer.showLevelScreen("level1");
    }

    private void onReturnToMenuClicked(MouseEvent mouseEvent) {
        LetterCrazePlayer.showMenuScreen();
    }
}
