package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelSelectController implements Initializable {

	@FXML private StackPane digitsOfPiButton;
    @FXML private StackPane backToMenuButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        digitsOfPiButton.setOnMouseClicked(this::onDigitsOfPiClicked);
        backToMenuButton.setOnMouseClicked(this::onReturnToMenuClicked);
    }

    private void onDigitsOfPiClicked(MouseEvent mouseEvent) {
        try {
            LetterCrazePlayer.showPlayerLevelScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void onReturnToMenuClicked(MouseEvent mouseEvent) {
        try {
            LetterCrazePlayer.showMenuScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}