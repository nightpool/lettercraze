package edu.wpi.zirconium.lettercraze.builder.controllers;

import edu.wpi.zirconium.lettercraze.builder.LetterCrazeBuilder;
import javafx.application.Platform;
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
    @FXML private Pane hamburgerMenu;

    @FXML private Pane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newButton.setOnMouseClicked(this::createNewLevel);

        loadButton.setOnMouseClicked(this::showNotImplemented);
        collectionsButton.setOnMouseClicked(this::showNotImplemented);
        achievementsButton.setOnMouseClicked(this::showNotImplemented);

        hamburger.setOnMouseClicked(this::openHamburger);
    }

    private void showNotImplemented(MouseEvent mouseEvent) {

    }

    private void openHamburger(MouseEvent mouseEvent) {
        if (!hamburgerMenu.isVisible()) {
            hamburgerMenu.setVisible(true);
            hamburger.getStyleClass().add("active");

            Platform.runLater(() -> {
                root.setOnMouseClicked(me -> {
                    hamburger.getStyleClass().remove("active");
                    hamburgerMenu.setVisible(false);
                    root.setOnMouseClicked(null);
                });
            });
        }
    }

    private void createNewLevel(MouseEvent mouseEvent) {
        try {
            LetterCrazeBuilder.showBuilderScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
