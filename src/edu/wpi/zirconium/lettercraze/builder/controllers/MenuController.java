package edu.wpi.zirconium.lettercraze.builder.controllers;

import edu.wpi.zirconium.lettercraze.builder.LetterCrazeBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML private StackPane newButton;
    @FXML private StackPane loadButton;
    @FXML private StackPane collectionsButton;
    @FXML private StackPane achievementsButton;
    @FXML private Pane hamburger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newButton.setOnMouseClicked(this::onNewClicked);
    }

    private void onNewClicked(MouseEvent mouseEvent) {
        try {
            LetterCrazeBuilder.showBuilderScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
