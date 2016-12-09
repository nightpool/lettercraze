package edu.wpi.zirconium.lettercraze.builder.controllers;

import edu.wpi.zirconium.lettercraze.builder.LetterCrazeBuilder;
import edu.wpi.zirconium.lettercraze.shared.views.BoardView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class BuilderControllers implements Initializable {

    @FXML private Pane backButton;
    @FXML private Button saveButton;
    @FXML private Button newButton;
    @FXML private Button loadButton;
    @FXML private Button previewButton;

    @FXML private BoardView board;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnMouseClicked(_me -> LetterCrazeBuilder.showMenuScreen());

        board.getTiles().forEach(t -> t.setOnMouseClicked(c -> t.toggleBlocked()));
    }
}
