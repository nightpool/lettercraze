package edu.wpi.zirconium.lettercraze.builder.controllers;

import edu.wpi.zirconium.lettercraze.builder.LetterCrazeBuilder;
import edu.wpi.zirconium.lettercraze.builder.LetterCrazeBuilder.TabNumber;
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
    @FXML private Group newPuzzle;
    @FXML private Group newLightning;
    @FXML private Group newTheme;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level1.setOnMouseClicked(this::showPuzzleLevelScreen);
        level2.setOnMouseClicked(this::showPuzzleLevelScreen);
        level3.setOnMouseClicked(this::showLightningLevelScreen);
        
        // TODO eventually make this toggle new levels
        newPuzzle.setOnMouseClicked(this::showPuzzleLevelScreen);
        newLightning.setOnMouseClicked(this::showLightningLevelScreen);
        newTheme.setOnMouseClicked(this::showThemeLevelScreen);

        backButton.setOnMouseClicked(this::onReturnToMenuClicked);
    }

    private void showPuzzleLevelScreen(MouseEvent mouseEvent) {
        try {
            LetterCrazeBuilder.showBuilderScreen(TabNumber.Puzzle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showLightningLevelScreen(MouseEvent mouseEvent) {
        try {
            LetterCrazeBuilder.showBuilderScreen(TabNumber.Lightning);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showThemeLevelScreen(MouseEvent mouseEvent) {
        try {
            LetterCrazeBuilder.showBuilderScreen(TabNumber.Theme);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void onReturnToMenuClicked(MouseEvent mouseEvent) {
        try {
        	LetterCrazeBuilder.showMenuScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}