package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelController implements Initializable {

    @FXML private Button exitLevel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exitLevel.setOnMouseClicked(this::onExitClicked);
    }

    private void onExitClicked(MouseEvent mouseEvent) {
        try {
            LetterCrazePlayer.showLevelSelectScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}