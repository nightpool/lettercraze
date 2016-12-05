package edu.wpi.zirconium.lettercraze.builder.controllers;

import edu.wpi.zirconium.lettercraze.builder.LetterCrazeBuilder;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML private StackPane newButton;
    @FXML private StackPane loadButton;
    @FXML private StackPane collectionsButton;
    @FXML private StackPane achievementsButton;

    @FXML private Pane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newButton.setOnMouseClicked(this::createNewLevel);

        loadButton.setOnMouseClicked(this::showNotImplemented);
        collectionsButton.setOnMouseClicked(this::showNotImplemented);
        achievementsButton.setOnMouseClicked(this::showNotImplemented);

    }

    @FXML private void showNotImplemented(Event mouseEvent) {
        final Stage dialog = new Stage(StageStyle.UNDECORATED);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(root.getScene().getWindow());
        VBox dialogVbox = new VBox(20);
        dialogVbox.setStyle(
            "-fx-background-color: white; -fx-border-color: #434343; -fx-border-width: 2px"
        );
        Text text = new Text("This feature is not yet implemented.");
        text.setFont(Font.font("Open Sans", 20));
        dialogVbox.getChildren().add(text);
        Scene dialogScene = new Scene(dialogVbox);
        dialog.setScene(dialogScene);
        dialog.sizeToScene();
        dialog.show();
    }

    private void createNewLevel(MouseEvent mouseEvent) {
        try {
            LetterCrazeBuilder.showBuilderScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
