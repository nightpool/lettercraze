package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML private StackPane playButton;
    @FXML private StackPane achievementsButton;
    @FXML private Pane hamburger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playButton.setOnMouseClicked(this::onPlayClicked);
    }

    private void onPlayClicked(MouseEvent mouseEvent) {
        LetterCrazePlayer.showLevelSelectScreen();
    }
}
