package edu.wpi.zirconium.lettercraze.player.controllers;

import edu.wpi.zirconium.lettercraze.entities.LevelPack;
import edu.wpi.zirconium.lettercraze.player.LetterCrazePlayer;
import edu.wpi.zirconium.lettercraze.shared.views.LevelPackView;
import edu.wpi.zirconium.lettercraze.player.views.LevelSelectScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class LevelSelectControllers implements Initializable {

    @FXML private LevelSelectScreen root;
    @FXML private Pane backButton;

    @FXML private LevelPackView puzzlePack;
    @FXML private LevelPackView lightningPack;
    @FXML private LevelPackView themePack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        backButton.setOnMouseClicked(this::onReturnToMenuClicked);

        this.puzzlePack.setPack(LevelPack.get("puzzle_levels"));
        this.lightningPack.setPack(LevelPack.get("lightning_levels"));
        this.themePack.setPack(LevelPack.get("theme_levels"));

        Stream.of(puzzlePack, lightningPack, themePack)
            .flatMap(LevelPackView::getTiles)
            .forEach(lt -> {
                lt.setOnMouseClicked(me -> LetterCrazePlayer.showLevelScreen(lt.getLevel()));
            }
        );
    }
    /**
     * Navigate to the player main menu screen.
     * @param mouseEvent
     */
    private void onReturnToMenuClicked(MouseEvent mouseEvent) {
        LetterCrazePlayer.showMenuScreen();
    }
}
