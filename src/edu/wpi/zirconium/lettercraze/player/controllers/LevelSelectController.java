package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelSelectController implements Initializable {

	@FXML private Pane backButton;
    @FXML private Group level1;
    @FXML private Group level2;
    @FXML private Group level3;
    @FXML private Group level4;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level1.setOnMouseClicked(this::showLevelScreen);
        level2.setOnMouseClicked(this::showLevelScreen);
        level3.setOnMouseClicked(this::showLevelScreen);
        level4.setOnMouseClicked(this::showLevelScreen);

        backButton.setOnMouseClicked(this::onReturnToMenuClicked);
    }

    private void showLevelScreen(MouseEvent mouseEvent) {
        LetterCrazePlayer.showPlayerLevelScreen();
    }
    
    private void onReturnToMenuClicked(MouseEvent mouseEvent) {
        LetterCrazePlayer.showMenuScreen();
    }
}