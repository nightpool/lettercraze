package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML private StackPane exitButton;
    @FXML private StackPane playButton;
    @FXML private Pane hamburger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playButton.setOnMouseClicked(this::onPlayClicked);

        exitButton.setOnMouseClicked(me -> Platform.exit());
    }
    /**
     * Navigate to play level select screen.
     * @param mouseEvent
     */
    private void onPlayClicked(MouseEvent mouseEvent) {
        LetterCrazePlayer.showLevelSelectScreen();
    }
}
